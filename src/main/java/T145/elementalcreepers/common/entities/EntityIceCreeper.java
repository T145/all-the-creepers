package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityIceCreeper extends EntityBaseCreeper {

	public EntityIceCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			for (int i = 0; i < 4; i++) {
				if (Math.round(posX + 0.5D) != Math.round(prevPosX + 0.5D) || Math.round(posY) != Math.round(prevPosY) || Math.round(posZ + 0.5D) != Math.round(prevPosZ + 0.5D)) {
					BlockPos pos = new BlockPos(Math.round(prevPosX), Math.round(prevPosY), Math.round(prevPosZ));

					if (world.isAirBlock(pos) && Blocks.SNOW_LAYER.canPlaceBlockAt(world, pos)) {
						world.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState());
					}
				}
			}
		}
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.iceCreeperRadius * explosionPower : ConfigGeneral.iceCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
					IBlockState state = world.getBlockState(pos);

					if (state != null && state.getBlock() != null) {
						Block block = state.getBlock();

						if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
							world.setBlockState(pos, Blocks.ICE.getDefaultState());
						} else if (block == Blocks.LAVA || block == Blocks.FLOWING_LAVA) {
							world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
						}
					}
				}
			}
		}

		if (ConfigGeneral.domeExplosion) {
			domeExplosion(radius, Blocks.SNOW);
		} else {
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);

						if (Blocks.DIRT.canPlaceBlockAt(world, pos) && !Blocks.DIRT.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z))) {
							if (rand.nextBoolean()) {
								world.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState());
							} else {
								world.setBlockState(pos, Blocks.SNOW.getDefaultState());
							}
						}
					}
				}
			}
		}
	}
}