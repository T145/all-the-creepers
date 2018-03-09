package T145.elementalcreepers.explosion;

import T145.elementalcreepers.explosion.base.ExplosionSpecial;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ExplosionWind extends ExplosionSpecial {

	private final double explosionPower;

	public ExplosionWind(World world, Entity exploder, double x, double y, double z, double explosionPower, float size, boolean flaming, boolean damagesTerrain) {
		super(world, exploder, x, y, z, size, flaming, damagesTerrain);
		this.explosionPower = explosionPower;
	}

	@Override
	public float getExplosionDamage(double d10, double d7, double d9, double d14, double d102, double d11, float explosionSize) {
		return 1F;
	}

	@Override
	public boolean editEntityMotion(Entity entity, double d5, double d7, double d9, double d10, double d11, double d12, double d13, double d14) {
		entity.motionX += d5 * d11 * explosionPower;
		entity.motionY += d7 * d11 * 1.01D;
		entity.motionZ += d9 * d11 * explosionPower;
		return true;
	}
}