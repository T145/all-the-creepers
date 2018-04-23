package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDarkCreeper extends EntityBaseCreeper {

    public EntityDarkCreeper(World world) {
        super(world);
    }

    @Override
    public void onLivingUpdate() {
        if (world.isDaytime() && !world.isRemote && !isChild()) {
            float brightness = getBrightness();

            if (brightness > 0.5F && rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F && world.canSeeSky(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))) && !getPowered()) {
                setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public void explode(boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.darkCharged : ModConfig.EXPLOSION_RADII.dark;

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);
                    IBlockState state = world.getBlockState(MUTABLE_POS);
                    Block block = state.getBlock();

                    if (block.getLightValue(state, world, MUTABLE_POS) > 0.5F) {
                        block.dropBlockAsItem(world, MUTABLE_POS, state, 0);
                        world.setBlockToAir(MUTABLE_POS);
                        block.onBlockDestroyedByExplosion(world, MUTABLE_POS, SIMPLE_EXPLOSION);
                    }
                }
            }
        }
    }
}