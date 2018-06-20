package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityGhostCreeper extends EntityBaseCreeper {

	public EntityGhostCreeper(World world) {
		super(world);
	}

	@Override
	public void detonate() {
		world.createExplosion(this, posX, posY, posZ, ModConfig.EXPLOSION_RADII.ghost, ForgeEventFactory.getMobGriefingEvent(world, this));
	}
}