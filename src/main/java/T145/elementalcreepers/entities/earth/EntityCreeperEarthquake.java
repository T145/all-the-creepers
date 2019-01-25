package T145.elementalcreepers.entities.earth;

import T145.elementalcreepers.entities.EntityCreeperElemental;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class EntityCreeperEarthquake extends EntityCreeperElemental {

	public EntityCreeperEarthquake(World world) {
		super(world);
		// earth + entropy
	}

	@Override
	public void detonate() {
		float radius = getExplosionRadius();

		for (float y = 0; y >= -radius; --y) {
			for (float x = 0; x >= -radius; --x) {
				for (float z = 0; z >= -radius; --z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					IBlockState state = world.getBlockState(pos);
					Block block = state.getBlock();

					if (canExplosionDestroyBlock(expl, world, pos, state, 0) && rand.nextBoolean()) {
						block.dropBlockAsItem(world, pos, state, 0);
						world.setBlockToAir(pos);
						block.onExplosionDestroy(world, pos, expl);
					}
				}
			}
		}
	}
}
