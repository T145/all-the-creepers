package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityWaterCreeper extends EntityBaseCreeper {

	public EntityWaterCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.waterCreeperRadius * explosionPower : ConfigGeneral.waterCreeperRadius;

		if (ConfigGeneral.domeExplosion) {
			domeExplosion(radius, Blocks.WATER);
		} else {
			wildExplosion(radius, Blocks.WATER);
		}
	}
}