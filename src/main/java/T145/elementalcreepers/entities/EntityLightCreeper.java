package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityLightCreeper extends EntityBaseCreeper {

	public EntityLightCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ModConfig.lightCreeperRadius * explosionPower : ModConfig.lightCreeperRadius;

		if (ModConfig.domeExplosion) {
			domeExplosion(radius, Blocks.GLOWSTONE);
		} else {
			wildExplosion(radius, Blocks.GLOWSTONE);
		}
	}
}