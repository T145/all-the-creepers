package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityLightCreeper extends EntityBaseCreeper {

	public EntityLightCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.lightCreeperRadius * explosionPower : ConfigGeneral.lightCreeperRadius;

		if (ConfigGeneral.domeExplosion) {
			domeExplosion(radius, Blocks.GLOWSTONE);
		} else {
			wildExplosion(radius, Blocks.GLOWSTONE);
		}
	}
}