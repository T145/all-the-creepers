package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityCreeperEntropy extends EntityCreeperElemental {

	public EntityCreeperEntropy(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.demolition;
	}

	@Override
	public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
		return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.OBSIDIAN.getDefaultState());
	}

	@Override
	public void detonate() {
		float radius = this.getExplosionRadius();

		for (float x = -radius; x <= radius; ++x) {
			for (float y = -radius; y <= radius; ++y) {
				for (float z = -radius; z <= radius; ++z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					IBlockState state = world.getBlockState(pos);
					Block block = state.getBlock();

					if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && canExplosionDestroyBlock(expl, world, pos, state, 0)) {
						block.dropBlockAsItem(world, pos, state, 0);
						world.setBlockToAir(pos);
						block.onExplosionDestroy(world, pos, expl);
					}
				}
			}
		}
	}
}
