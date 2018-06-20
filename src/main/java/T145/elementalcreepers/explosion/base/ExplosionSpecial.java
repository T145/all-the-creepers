package T145.elementalcreepers.explosion.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.lib.MutableVec3d;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExplosionSpecial extends Explosion {

	public final MutableBlockPos mutablePos = new MutableBlockPos();
	public final MutableVec3d vec = new MutableVec3d();
	private final Set<BlockPos> set = new HashSet<>();

	@SideOnly(Side.CLIENT)
	public ExplosionSpecial(World world, Entity entity, double x, double y, double z, float size, List<BlockPos> affectedPositions) {
		super(world, entity, x, y, z, size, affectedPositions);
	}

	public ExplosionSpecial(World world, Entity entity, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain) {
		super(world, entity, x, y, z, size, causesFire, damagesTerrain);
	}

	@SideOnly(Side.CLIENT)
	public ExplosionSpecial(World world, Entity entity, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain, List<BlockPos> affectedPositions) {
		super(world, entity, x, y, z, size, causesFire, damagesTerrain, affectedPositions);
	}

	@Override
	public void doExplosionA() {
		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				for (int l = 0; l < 16; ++l) {
					if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
						double d0 = (j / 15.0F * 2.0F - 1.0F);
						double d1 = (k / 15.0F * 2.0F - 1.0F);
						double d2 = (l / 15.0F * 2.0F - 1.0F);
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 = d0 / d3;
						d1 = d1 / d3;
						d2 = d2 / d3;
						float f = size * (0.7F + world.rand.nextFloat() * 0.6F);
						double d4 = x;
						double d6 = y;
						double d8 = z;

						for (float f1 = 0.3F; f > 0.0F; f -= 0.225F) {
							BlockPos pos = mutablePos.setPos(d4, d6, d8);
							IBlockState state = world.getBlockState(pos);

							if (state.getMaterial() != Material.AIR) {
								float f2 = exploder != null ? exploder.getExplosionResistance(this, world, pos, state) : state.getBlock().getExplosionResistance(world, pos, null, this);
								f -= (f2 + 0.3F) * 0.3F;
							}

							if (f > 0.0F && (exploder == null || exploder.canExplosionDestroyBlock(this, world, pos, state, f))) {
								set.add(pos);
							}

							d4 += d0 * 0.3D;
							d6 += d1 * 0.3D;
							d8 += d2 * 0.3D;
						}
					}
				}
			}
		}

		getAffectedBlockPositions().addAll(set);
		set.clear();

		float f3 = size * 2.0F;
		int k1 = MathHelper.floor(x - f3 - 1.0D);
		int l1 = MathHelper.floor(x + f3 + 1.0D);
		int i2 = MathHelper.floor(y - f3 - 1.0D);
		int i1 = MathHelper.floor(y + f3 + 1.0D);
		int j2 = MathHelper.floor(z - f3 - 1.0D);
		int j1 = MathHelper.floor(z + f3 + 1.0D);
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));

		ForgeEventFactory.onExplosionDetonate(world, this, list, f3);

		vec.setPos(x, y, z);

		for (Entity entity : list) {
			if (!entity.isImmuneToExplosions()) {
				double d12 = entity.getDistance(x, y, z) / f3;

				if (d12 <= 1.0D) {
					double d5 = entity.posX - x;
					double d7 = entity.posY + entity.getEyeHeight() - y;
					double d9 = entity.posZ - z;
					double d13 = MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

					if (d13 != 0.0D) {
						d5 = d5 / d13;
						d7 = d7 / d13;
						d9 = d9 / d13;
						double d14 = world.getBlockDensity(vec, entity.getEntityBoundingBox());
						double d10 = (1.0D - d12) * d14;
						// old spot for dealing entity damange
						double d11 = d10;

						if (entity instanceof EntityLivingBase) {
							d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10);
						}

						if (dealDamage(entity)) {
							entity.attackEntityFrom(getExplosionDamageSource(), getExplosionDamage(d5, d7, d9, d14, d10, d11, f3));
						}

						if (editEntityMotion(entity, d5, d7, d9, d10, d11, d12, d13, d14) && entity instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer) entity;

							if (!player.isSpectator() && canMovePlayer(player)) {
								getPlayerKnockbackMap().put(player, vec.setPos(d5 * d10, d7 * d10, d9 * d10));
							}
						}
					}
				}
			}
		}
	}

	private boolean canMovePlayer(EntityPlayer player) {
		if (ModConfig.GENERAL.explosionsMoveFlyingPlayers) {
			return true;
		}
		return !player.isCreative() || !player.capabilities.isFlying;
	}

	public boolean editEntityMotion(Entity entity, double d5, double d7, double d9, double d10, double d11, double d12, double d13, double d14) {
		entity.motionX += d5 * d11;
		entity.motionY += d7 * d11;
		entity.motionZ += d9 * d11;
		return true;
	}

	public float getExplosionDamage(double d10, double d7, double d9, double d14, double d102, double d11, float explosionSize) {
		return ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * explosionSize + 1.0D));
	}

	public DamageSource getExplosionDamageSource() {
		return DamageSource.causeExplosionDamage(this);
	}

	public boolean dealDamage(Entity entity) {
		return true;
	}

	@Override
	public void doExplosionB(boolean spawnParticles) {
		playBoom();
		spawnParticlesHere();

		if (damagesTerrain) {
			for (BlockPos pos : getAffectedBlockPositions()) {
				IBlockState state = world.getBlockState(pos);

				if (spawnParticles) {
					double d0 = pos.getX() + world.rand.nextFloat();
					double d1 = pos.getY() + world.rand.nextFloat();
					double d2 = pos.getZ() + world.rand.nextFloat();
					double d3 = d0 - x;
					double d4 = d1 - y;
					double d5 = d2 - z;
					double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
					d3 = d3 / d6;
					d4 = d4 / d6;
					d5 = d5 / d6;
					double d7 = 0.5D / (d6 / size + 0.1D);
					d7 = d7 * (world.rand.nextFloat() * world.rand.nextFloat() + 0.3F);
					d3 = d3 * d7;
					d4 = d4 * d7;
					d5 = d5 * d7;

					spawnParticlesAt(pos, d0, d1, d2, d3, d4, d5, d6, d7);
				}

				if (state.getMaterial() != Material.AIR) {
					Block block = state.getBlock();

					if (block.canDropFromExplosion(this)) {
						block.dropBlockAsItemWithChance(world, pos, world.getBlockState(pos), 1.0F / size, 0);
					}

					block.onBlockExploded(world, pos, this);
				}
			}
		}

		if (causesFire) {
			for (BlockPos pos : getAffectedBlockPositions()) {
				if (world.getBlockState(pos).getMaterial() == Material.AIR && world.getBlockState(pos.down()).isFullBlock() && random.nextInt(3) == 0) {
					world.setBlockState(pos, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	public void playBoom() {
		world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
	}

	public void spawnParticlesHere() {
		if (size >= 2.0F && damagesTerrain) {
			world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
		} else {
			world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, x, y, z, 1.0D, 0.0D, 0.0D);
		}
	}

	public void spawnParticlesAt(BlockPos pos, double d0, double d1, double d2, double d3, double d4, double d5, double d6, double d7) {
		world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + x) / 2.0D, (d1 + y) / 2.0D, (d2 + z) / 2.0D, d3, d4, d5);
		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
	}
}