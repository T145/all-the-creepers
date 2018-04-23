package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDemolitionCreeper extends EntityBaseCreeper {

    public EntityDemolitionCreeper(World world) {
        super(world);
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
        return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.OBSIDIAN.getDefaultState());
    }

    @Override
    public void explode(boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.demolitionCharged : ModConfig.EXPLOSION_RADII.demolition;

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);
                    IBlockState state = world.getBlockState(MUTABLE_POS);
                    Block block = state.getBlock();

                    if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && canExplosionDestroyBlock(SIMPLE_EXPLOSION, world, MUTABLE_POS, state, 0)) {
                        block.dropBlockAsItem(world, MUTABLE_POS, state, 0);
                        world.setBlockToAir(MUTABLE_POS);
                        block.onBlockDestroyedByExplosion(world, MUTABLE_POS, SIMPLE_EXPLOSION);
                    }
                }
            }
        }
    }
}