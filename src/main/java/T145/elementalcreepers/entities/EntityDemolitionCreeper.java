package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Collections;

public class EntityDemolitionCreeper extends EntityBaseCreeper {

    public EntityDemolitionCreeper(World world) {
        super(world);
    }

    @Override
    public float getExplosionResistance(@Nonnull Explosion explosion, @Nonnull World world, @Nonnull BlockPos pos, IBlockState state) {
        return state.getBlock().getExplosionResistance(world, pos, this, explosion);
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
        return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.OBSIDIAN.getDefaultState());
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.demolition * explosionPower : ModConfig.EXPLOSION_RADII.demolition;

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    pos.setPos(posX + x, posY + y, posZ + z);
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();
                    Explosion explosion = new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, Collections.emptyList());

                    if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && canExplosionDestroyBlock(explosion, world, pos, state, 0)) {
                        block.dropBlockAsItem(world, pos, state, 0);
                        world.setBlockToAir(pos);
                        block.onBlockDestroyedByExplosion(world, pos, explosion);
                    }
                }
            }
        }
    }
}