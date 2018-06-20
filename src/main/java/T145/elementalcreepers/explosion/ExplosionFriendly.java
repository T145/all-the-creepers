package T145.elementalcreepers.explosion;

import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import T145.elementalcreepers.explosion.base.ExplosionSpecial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

public class ExplosionFriendly extends ExplosionSpecial {

	public ExplosionFriendly(World world, Entity exploder, double x, double y, double z, float size, boolean damagesTerrain) {
		super(world, exploder, x, y, z, size, false, damagesTerrain);
	}

	@Override
	public boolean dealDamage(Entity entity) {
		if (!(exploder instanceof EntityFriendlyCreeper)) {
			return true;
		}

		EntityFriendlyCreeper exploder = (EntityFriendlyCreeper) this.exploder;

		if (exploder.getOwner().equals(entity)) {
			return false;
		}

		if (entity instanceof EntityTameable) {
			EntityTameable pet = (EntityTameable) entity;

			if (pet.isTamed()) {
				if (pet.getOwner().equals(exploder.getOwner())) {
					return false;
				} else {
					return pet.getAttackTarget().equals(exploder.getOwner()) || pet.getAttackTarget().equals(exploder);
				}
			}
		}

		return true;
	}

	@Override
	public boolean editEntityMotion(Entity entity, double d5, double d7, double d9, double d10, double d11, double d12, double d13, double d14) {
		return dealDamage(entity) && super.editEntityMotion(entity, d5, d7, d9, d10, d11, d12, d13, d14);
	}
}