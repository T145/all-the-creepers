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
	public void detonate() {
		specialExplosion(getPowered() ? ModConfig.EXPLOSION_RADII.lightCharged : ModConfig.EXPLOSION_RADII.light, Blocks.GLOWSTONE.getDefaultState());
	}
}