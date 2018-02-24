package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityReverseCreeper extends EntityBaseCreeper {

	public EntityReverseCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		if (griefingEnabled) {
			int radius = (int) (getPowered() ? ModConfig.explosionRadii.reverseCreeperRadius * 1.5F : ModConfig.explosionRadii.reverseCreeperRadius);
			IBlockState[][][] states = new IBlockState[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];
			TileEntity[][][] tiles = new TileEntity[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];

			for (int x = -radius - 1; x <= radius; x++) {
				for (int y = -radius - 1; y <= radius; y++) {
					for (int z = -radius - 1; z <= radius; z++) {
						int ax = x + radius + 1;
						int ay = y + radius + 1;
						int az = z + radius + 1;
						double ex = posX + x;
						double ey = posY + y;
						double ez = posZ + z;
						pos.setPos(ex, ey, ez);
						IBlockState state = world.getBlockState(pos);

						if (state != null && state.getBlock() != null) {
							Block id = state.getBlock();

							if (id != Blocks.BEDROCK) {
								states[ax][ay][az] = null;

								if (id != null && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && ey > -1) {
									states[ax][ay][az] = state;
									tiles[ax][ay][az] = world.getTileEntity(pos);
								}
							}
						}
					}
				}
			}

			for (int x = -radius - 1; x <= radius; x++) {
				for (int y = -radius - 1; y <= radius; y++) {
					for (int z = -radius - 1; z <= radius; z++) {
						pos.setPos(posX + x, posY + y, posZ + z);
						IBlockState state = states[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];
						TileEntity te = tiles[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];

						if (state != null && state.getBlock() != null && posY + y > 0) {
							world.setBlockState(pos, state, 3);

							if (te != null) {
								world.setTileEntity(pos, te);
							}
						}
					}
				}
			}
		}
	}
}