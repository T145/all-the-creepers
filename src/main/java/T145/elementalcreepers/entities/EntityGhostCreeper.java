package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.world.World;

public class EntityGhostCreeper extends EntityBaseCreeper {

	public EntityGhostCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		world.createExplosion(this, posX, posY, posZ, ModConfig.explosionRadii.ghostCreeperRadius, canGrief);
	}
}