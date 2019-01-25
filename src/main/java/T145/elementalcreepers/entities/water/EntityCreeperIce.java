package T145.elementalcreepers.entities.water;

import T145.elementalcreepers.entities.EntityCreeperWater;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityCreeperIce extends EntityCreeperWater {

	public EntityCreeperIce(World world) {
		super(world);
		// water + air
	}

	@Override
	public void preExplode(float radius, IBlockState state, boolean suffocateEntities) {
		Block block = world.getBlockState(pos).getBlock();

		if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
			world.setBlockState(pos, Blocks.ICE.getDefaultState());
		} else if (block == Blocks.FLOWING_LAVA) {
			world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
		} else if (block == Blocks.LAVA) {
			world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		}
	}

	@Override
	public void detonate() {
		createExplosion(Blocks.SNOW.getDefaultState(), false);

		if (rand.nextBoolean()) {
			// spawn snowman
		}
	}
}
