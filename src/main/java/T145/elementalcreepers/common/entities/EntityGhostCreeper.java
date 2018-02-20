package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.world.World;

public class EntityGhostCreeper extends EntityBaseCreeper {

	public EntityGhostCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int exPower = ConfigGeneral.ghostCreeperRadius * explosionPower;
		world.createExplosion(this, posX, posY, posZ, exPower, false);
	}
}