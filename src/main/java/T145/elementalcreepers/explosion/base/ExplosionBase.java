package T145.elementalcreepers.explosion.base;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class ExplosionBase extends Explosion {

	public static final int RANGE = 16;

	protected final World world;
	protected final Entity exploder;
	protected final double x;
	protected final double y;
	protected final double z;
	protected final double explosionPower;
	protected final float size;

	public ExplosionBase(World world, Entity exploder, double x, double y, double z, double explosionPower, float size, boolean flaming, boolean damagesTerrain) {
		super(world, exploder, x, y, z, size, flaming, damagesTerrain);
		this.world = world;
		this.exploder = exploder;
		this.x = x;
		this.y = y;
		this.z = z;
		this.explosionPower = explosionPower;
		this.size = size;
	}

	public boolean doDamage() {
		return true;
	}

	public void doExplosionA() {
		Set<BlockPos> set = Sets.<BlockPos>newHashSet();

		for (int j = 0; j < RANGE; ++j) {
			for (int k = 0; k < RANGE; ++k) {
				for (int l = 0; l < RANGE; ++l) {
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

						for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
							BlockPos blockpos = new BlockPos(d4, d6, d8);
							IBlockState iblockstate = world.getBlockState(blockpos);

							if (iblockstate.getMaterial() != Material.AIR) {
								float f2 = exploder != null ? exploder.getExplosionResistance(this, world, blockpos, iblockstate) : iblockstate.getBlock().getExplosionResistance(world, blockpos, null, this);
								f -= (f2 + 0.3F) * 0.3F;
							}

							if (f > 0.0F && (exploder == null || exploder.canExplosionDestroyBlock(this, world, blockpos, iblockstate, f))) {
								set.add(blockpos);
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
		float f3 = size * 2.0F;
		int k1 = MathHelper.floor(x - f3 - 1.0D);
		int l1 = MathHelper.floor(x + f3 + 1.0D);
		int i2 = MathHelper.floor(y - f3 - 1.0D);
		int i1 = MathHelper.floor(y + f3 + 1.0D);
		int j2 = MathHelper.floor(z - f3 - 1.0D);
		int j1 = MathHelper.floor(z + f3 + 1.0D);
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(world, this, list, f3);
		Vec3d vec3d = new Vec3d(x, y, z);

		for (int k2 = 0; k2 < list.size(); ++k2) {
			Entity entity = list.get(k2);

			if (!entity.isImmuneToExplosions()) {
				double d12 = entity.getDistance(x, y, z) / f3;

				if (d12 <= 1.0D) {
					double d5 = entity.posX - x;
					double d7 = entity.posY + entity.getEyeHeight() - y;
					double d9 = entity.posZ - z;
					double d13 = MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

					if (d13 != 0.0D) {
						d5 /= d13;
						d7 /= d13;
						d9 /= d13;
						double d14 = world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
						double d10 = (1.0D - d12) * d14;
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), doDamage() ? ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * f3 + 1.0D)) : 1);
						double d11 = d10;

						if (entity instanceof EntityLivingBase) {
							d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10);
						}

						editEntityMotion(entity, d5, d7, d9, d10, d11, d12, d13, d14);

						if (entity instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer) entity;

							if (!player.isSpectator() && (!player.isCreative() || !player.capabilities.isFlying)) {
								getPlayerKnockbackMap().put(player, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
							}
						}
					}
				}
			}
		}
	}

	public abstract void editEntityMotion(Entity entity, double d5, double d7, double d9, double d10, double d11, double d12, double d13, double d14);
}