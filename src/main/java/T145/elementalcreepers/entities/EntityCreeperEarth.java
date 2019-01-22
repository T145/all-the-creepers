package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityCreeperEarth extends EntityCreeperElemental {

	public EntityCreeperEarth(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.earth;
	}

	@Override
	public void detonate() {
		this.createExplosion(Blocks.DIRT.getDefaultState());
	}
}
