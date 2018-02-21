package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.explosion.ExplosionPsychic;
import T145.elementalcreepers.explosion.base.ExplosionBase;
import net.minecraft.world.World;

public class EntityPsychicCreeper extends EntityBaseCreeper {

	public EntityPsychicCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		if (!world.isRemote) {
			int radius = ModConfig.psychicCreeperRadius * explosionPower;
			ExplosionBase explosion = new ExplosionPsychic(world, this, posX, posY, posZ, ModConfig.psychicCreeperPower, radius, false, griefingEnabled);
			explosion.doExplosionA();
		}
	}
}