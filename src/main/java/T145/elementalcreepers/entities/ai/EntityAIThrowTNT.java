package T145.elementalcreepers.entities.ai;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIThrowTNT extends EntityAIBase {

	protected final EntityBaseCreeper creeper;
	protected final double range;
	protected EntityLivingBase target;
	protected int attackCooldown;

	public EntityAIThrowTNT(EntityBaseCreeper creeper, double range) {
		this.creeper = creeper;
		this.range = range;
	}

	@Override
	public boolean shouldExecute() {
		if (ModConfig.general.ballisticCreeperAI) {
			AxisAlignedBB bb = new AxisAlignedBB(creeper.posX - range, creeper.posY - range, creeper.posZ - range, creeper.posX + range, creeper.posY + range, creeper.posZ + range);
			List<Entity> entities = creeper.world.getEntitiesWithinAABBExcludingEntity(creeper, bb);

			if (!entities.isEmpty()) {
				float dist = Float.POSITIVE_INFINITY;

				for (Entity entity : entities) {
					if (entity instanceof EntityLivingBase) {
						float newDist = creeper.getDistance(entity);
						EntityLivingBase creature = (EntityLivingBase) entity;

						if (creature instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer) creature;

							if (player.capabilities.isCreativeMode) {
								break;
							}
						}

						if (newDist < dist) {
							dist = newDist;
							target = creature;
						}
					}
				}
			}
		} else {
			target = creeper.getAttackTarget();
		}

		return target != null && target.isEntityAlive() && creeper.canEntityBeSeen(target) && creeper.getDistance(target) > 2.0D;
	}

	@Override
	public void resetTask() {
		target = null;
		attackCooldown = 0;
	}

	public void throwTNT(EntityLivingBase target, boolean longThrow) {
		EntityTNTPrimed tnt = new EntityTNTPrimed(creeper.world, creeper.posX, creeper.posY, creeper.posZ, creeper);
		tnt.setLocationAndAngles(creeper.posX, creeper.posY, creeper.posZ, creeper.rotationYaw, 0.0F);
		tnt.motionX = (target.posX - tnt.posX) / 18D;
		tnt.motionY = (target.posY - tnt.posY) / 18D + 0.5D;
		tnt.motionZ = (target.posZ - creeper.posZ) / 18D;

		if (longThrow) {
			tnt.motionY *= 1.5D;
		} else {
			tnt.setFuse(30);
		}

		creeper.world.spawnEntity(tnt);
	}

	@Override
	public void updateTask() {
		if (target != null && --attackCooldown <= 0) {
			if (!creeper.world.isRemote && creeper.getCreeperState() <= 0) {
				throwTNT(target, false);
			}
			attackCooldown = 60;
		}
	}
}