package T145.elementalcreepers.entities.ai;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;

public class EntityAIThrowTNT extends EntityAIBase {

	protected EntityBaseCreeper creeper;
	protected EntityLivingBase target;
	protected int attackCooldown;

	public EntityAIThrowTNT(EntityBaseCreeper creeper) {
		this.creeper = creeper;
	}

	@Override
	public boolean shouldExecute() {
		if (ModConfig.general.ballisticCreeperAI) {
			double range = 4.5D;
			List<Entity> targets = creeper.getEntityWorld().getEntitiesWithinAABBExcludingEntity(creeper, creeper.getEntityBoundingBox().expand(range, range, range));

			if (!targets.isEmpty()) {
				HashMap<Float, EntityLivingBase> distances = new HashMap<>();

				for (Entity entity : targets) {
					if (entity instanceof EntityLivingBase) {
						EntityLivingBase creature = (EntityLivingBase) entity;
						distances.put(creeper.getDistance(entity), creature);
					}
				}

				if (!distances.isEmpty()) {
					target = distances.get(Collections.min(distances.keySet()));
				}
			}
		} else {
			target = creeper.getAttackTarget();
		}

		if (target == null || !target.isEntityAlive()) {
			return false;
		} else {
			if (creeper.getDistance(target) > 2.0D && creeper.canEntityBeSeen(target)) {
				return true;
			}

			return false;
		}
	}

	@Override
	public void resetTask() {
		target = null;
		attackCooldown = 0;
	}

	public void throwTNT(EntityLivingBase target, boolean longThrow) {
		EntityTNTPrimed tnt = new EntityTNTPrimed(creeper.getEntityWorld(), creeper.posX, creeper.posY, creeper.posZ, creeper);
		tnt.setLocationAndAngles(creeper.posX, creeper.posY, creeper.posZ, creeper.rotationYaw, 0.0F);
		tnt.motionX = (target.posX - tnt.posX) / 18D;
		tnt.motionY = (target.posY - tnt.posY) / 18D + 0.5D;
		tnt.motionZ = (target.posZ - creeper.posZ) / 18D;

		if (longThrow) {
			tnt.motionY *= 1.5D;
		} else {
			tnt.setFuse(30);
		}

		creeper.getEntityWorld().spawnEntity(tnt);
	}

	@Override
	public void updateTask() {
		if (target != null && --attackCooldown <= 0) {
			if (!creeper.getEntityWorld().isRemote && !(creeper.getCreeperState() > 0)) {
				throwTNT(target, false);
			}
			attackCooldown = 60;
		}
	}
}