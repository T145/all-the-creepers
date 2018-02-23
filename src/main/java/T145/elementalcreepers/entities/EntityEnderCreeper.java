package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.entities.ai.EntityAIFindPlayer;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEnderCreeper extends EntityBaseCreeper {

	protected byte teleportDelay;

	public EntityEnderCreeper(World world) {
		super(world);
		stepHeight = 1.0F;
		setPathPriority(PathNodeType.WATER, -1.0F);

		// Reset basic creeper AI to apply EntityAIFindPlayer
		targetTasks.taskEntries.clear();
		targetTasks.addTask(1, new EntityAIFindPlayer(this));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	public boolean shouldAttackPlayer(EntityPlayer player) {
		ItemStack stack = player.inventory.armorInventory.get(3);

		if (stack.getItem() == Item.getItemFromBlock(Blocks.PUMPKIN)) {
			return false;
		} else {
			Vec3d vec3d = player.getLook(1.0F).normalize();
			Vec3d vec3d1 = new Vec3d(posX - player.posX, getEntityBoundingBox().minY + getEyeHeight() - (player.posY + player.getEyeHeight()), posZ - player.posZ);
			double d0 = vec3d1.lengthVector();
			vec3d1 = vec3d1.normalize();
			double d1 = vec3d.dotProduct(vec3d1);
			return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
		}
	}

	@Override
	public void onLivingUpdate() {
		if (world.isRemote && getCreeperState() <= 0) {
			if (teleportDelay > 0) {
				--teleportDelay;
			}

			for (int i = 0; i < 2; ++i) {
				world.spawnParticle(EnumParticleTypes.PORTAL, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
			}

			if (getAttackTarget() != null) {
				if (getAttackTarget() instanceof EntityPlayer) {
					if (getAttackTarget().getDistance(this) > 6.0D && rand.nextInt(100) < 5) {
						teleportToEntity(getAttackTarget());
						teleportDelay = 70;
					}
				} else if (getAttackTarget().getDistance(this) > 256.0D && teleportDelay++ >= 30 && teleportToEntity(getAttackTarget())) {
					teleportDelay = 0;
				}
			} else {
				teleportDelay = 0;
			}
		}

		// isJumping = false;
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() { // Should this code be in onLivingUpdate()?
		if (isWet()) {
			attackEntityFrom(DamageSource.DROWN, 1.0F);
		}

		if (world.isDaytime()) {
			float f = getBrightness();

			if (f > 0.5F && world.canSeeSky(new BlockPos(this)) && rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
				setAttackTarget(null);
				teleportRandomly();
				teleportDelay = 50;
			}
		}

		super.updateAITasks();
	}

	public boolean teleportRandomly(Entity target) {
		double d0 = target.posX + (rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = target.posY + (rand.nextInt(64) - 32);
		double d2 = target.posZ + (rand.nextDouble() - 0.5D) * 64.0D;
		return teleportTo(d0, d1, d2);
	}

	public boolean teleportRandomly() {
		return teleportRandomly(this);
	}

	public boolean teleportToEntity(Entity target) {
		Vec3d vec3d = new Vec3d(posX - target.posX, getEntityBoundingBox().minY + (height / 2.0F) - target.posY + target.getEyeHeight(), posZ - target.posZ);
		vec3d = vec3d.normalize();
		double d0 = 16.0D;
		double d1 = posX + (rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
		double d2 = posY + (rand.nextInt(16) - 8) - vec3d.y * 16.0D;
		double d3 = posZ + (rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
		return teleportTo(d1, d2, d3);
	}

	private boolean teleportTo(double x, double y, double z) {
		EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);

		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		boolean flag = attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

		if (flag) {
			world.playSound(null, prevPosX, prevPosY, prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, getSoundCategory(), 1.0F, 1.0F);
			playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
		}

		return flag;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source)) {
			return false;
		} else if (source instanceof EntityDamageSourceIndirect) {
			for (int i = 0; i < 64; ++i) {
				if (teleportRandomly()) {
					return true;
				}
			}

			return false;
		} else {
			boolean flag = super.attackEntityFrom(source, amount);

			if (source.isUnblockable() && rand.nextInt(10) != 0) {
				teleportRandomly();
			}

			return flag;
		}
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		float radius = getPowered() ? 4.5F : 3F;
		AxisAlignedBB bounds = new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, bounds);

		world.createExplosion(this, posX, posY, posZ, radius, false);

		if (!entities.isEmpty()) {
			for (Entity entity : entities) {
				if (entity instanceof EntityLivingBase && rand.nextInt(100) <= 25) {
					teleportRandomly(entity);
				}
			}
		}
	}
}