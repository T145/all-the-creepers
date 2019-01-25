package T145.elementalcreepers.entities.water;

import T145.elementalcreepers.entities.EntityCreeperWater;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityCreeperPermafrost extends EntityCreeperIce {

	public EntityCreeperPermafrost(World world) {
		super(world);
		// water + order
	}

	@Override
	public void detonate() {
		createExplosion(Blocks.ICE.getDefaultState(), true);
	}
}
