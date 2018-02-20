package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityEarthCreeper extends EntityBaseCreeper {

	public EntityEarthCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.earthCreeperRadius * explosionPower : ConfigGeneral.earthCreeperRadius;

		if (ConfigGeneral.domeExplosion) {
			domeExplosion(radius, Blocks.DIRT);
		} else {
			wildExplosion(radius, Blocks.DIRT);
		}
	}
}