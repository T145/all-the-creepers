package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.ArrayList;

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
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.explosionRadii.darkCreeperRadius * explosionPower : ModConfig.explosionRadii.darkCreeperRadius;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);
                    IBlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block.getLightValue(state, world, pos) > 0.5F) {
                        block.dropBlockAsItem(world, pos, state, 0);
                        world.setBlockToAir(pos);
                        block.onBlockDestroyedByExplosion(world, pos, new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, new ArrayList<>()));
                    }
                }
            }
        }
    }
}