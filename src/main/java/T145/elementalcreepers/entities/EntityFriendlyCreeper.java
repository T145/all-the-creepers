package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.world.World;

public class EntityFriendlyCreeper extends EntityBaseCreeper {

	public EntityFriendlyCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		
	}
}