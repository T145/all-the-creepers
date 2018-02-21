package T145.elementalcreepers.explosion;

import T145.elementalcreepers.explosion.base.ExplosionBase;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ExplosionPsychic extends ExplosionBase {

	public ExplosionPsychic(World world, Entity exploder, double x, double y, double z, double explosionPower, float size, boolean flaming, boolean damagesTerrain) {
		super(world, exploder, x, y, z, explosionPower, size, flaming, damagesTerrain);
	}

	@Override
	public void editEntityMotion(Entity entity, double d5, double d7, double d9, double d10, double d11, double d12, double d13, double d14) {
		entity.motionX += d5 * d11;
		entity.motionY += d7 * d11 * explosionPower;
		entity.motionZ += d9 * d11;
	}
}