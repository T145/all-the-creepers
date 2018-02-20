package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import T145.elementalcreepers.common.entities.explosion.ExplosionPsychic;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityPsychicCreeper extends EntityBaseCreeper {

	public EntityPsychicCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int exPower = ConfigGeneral.psychicCreeperRadius * explosionPower;

		if (!world.isRemote) {
			createPsyhicBurst(this, posX, posY, posZ, exPower, true);
		}
	}

	private ExplosionPsychic createPsyhicBurst(Entity entity, double x, double y, double z, float strength, boolean flag) {
		ExplosionPsychic explosion = new ExplosionPsychic(world, entity, x, y, z, strength, ConfigGeneral.psychicCreeperPower);
		explosion.isFlaming = false;
		explosion.isSmoking = flag;
		explosion.doExplosionA();
		return explosion;
	}
}