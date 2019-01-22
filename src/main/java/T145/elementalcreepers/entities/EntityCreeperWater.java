package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityCreeperWater extends EntityCreeperElemental {

	public EntityCreeperWater(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.water;
	}

	@Override
	public void detonate() {
		this.createExplosion(Blocks.WATER.getDefaultState());
	}
}
