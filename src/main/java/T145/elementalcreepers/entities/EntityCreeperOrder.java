package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityCreeperOrder extends EntityCreeperElemental {

	public EntityCreeperOrder(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.reverse;
	}

	@Override
	public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
		return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.BEDROCK.getDefaultState());
	}

	@Override
	public void detonate() {
		int radius = (int) getExplosionRadius();
		IBlockState[][][] states = new IBlockState[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];
		TileEntity[][][] tiles = new TileEntity[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];

		for (int x = -radius - 1; x <= radius; ++x) {
			for (int y = -radius - 1; y <= radius; ++y) {
				for (int z = -radius - 1; z <= radius; ++z) {
					int ax = x + radius + 1;
					int ay = y + radius + 1;
					int az = z + radius + 1;
					double ex = posX + x;
					double ey = posY + y;
					double ez = posZ + z;

					pos.setPos(ex, ey, ez);

					IBlockState state = world.getBlockState(pos);

					if (canExplosionDestroyBlock(expl, world, pos, world.getBlockState(pos), 0)) {
						states[ax][ay][az] = null;

						if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && ey > -1) {
							states[ax][ay][az] = state;
							tiles[ax][ay][az] = world.getTileEntity(pos);
						}
					}
				}
			}
		}

		for (int x = -radius - 1; x <= radius; ++x) {
			for (int y = -radius - 1; y <= radius; ++y) {
				for (int z = -radius - 1; z <= radius; ++z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					IBlockState state = states[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];
					TileEntity te = tiles[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];

					if (state != null && posY + y > 0) {
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
