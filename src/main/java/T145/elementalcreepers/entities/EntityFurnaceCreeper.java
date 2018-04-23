package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class EntityFurnaceCreeper extends EntityBaseCreeper {

    public EntityFurnaceCreeper(World world) {
        super(world);
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
        return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.OBSIDIAN.getDefaultState());
    }

    private void generateTrap(EntityPlayer player) {
        IBlockState wall = Blocks.STONEBRICK.getDefaultState();
        IBlockState gate = Blocks.IRON_BARS.getDefaultState();
        IBlockState lava = Blocks.LAVA.getDefaultState();

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 3; y++) {
                for (int z = -1; z < 2; z++) {
                    MUTABLE_POS.setPos(player.posX + x, player.getEntityBoundingBox().minY + y, player.posZ + z);

                    if (canExplosionDestroyBlock(SIMPLE_EXPLOSION, world, MUTABLE_POS, world.getBlockState(MUTABLE_POS), 0)) {
                        if (x == -1 && z == 0 && y == 1) {
                            world.setBlockState(MUTABLE_POS, gate);
                        } else if (x == 0 && z == 0 && y == 0) {
                            world.setBlockState(MUTABLE_POS, lava);
                        } else if (x != 0 || z != 0 || y != 1) {
                            world.setBlockState(MUTABLE_POS, wall);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void explode(boolean canGrief) {
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getAreaOfEffect(getPowered() ? ModConfig.EXPLOSION_RADII.furnaceCharged : ModConfig.EXPLOSION_RADII.furnace));

        for (EntityPlayer player : players) {
            generateTrap(player);
        }
    }
}