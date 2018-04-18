package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityFurnaceCreeper extends EntityBaseCreeper {

    public EntityFurnaceCreeper(World world) {
        super(world);
    }

    private void generateTrap(EntityPlayer player) {
        IBlockState wall = Blocks.STONEBRICK.getDefaultState();
        IBlockState gate = Blocks.IRON_BARS.getDefaultState();
        IBlockState lava = Blocks.LAVA.getDefaultState();

        if (!world.isRemote) {
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 3; y++) {
                    for (int z = -1; z < 2; z++) {
                        pos.setPos(player.posX + x, player.getEntityBoundingBox().minY + y, player.posZ + z);

                        if (x == -1 && z == 0 && y == 1) {
                            world.setBlockState(pos, gate);
                        } else if (x == 0 && z == 0 && y == 0) {
                            world.setBlockState(pos, lava);
                        } else if (x != 0 || z != 0 || y != 1) {
                            world.setBlockState(pos, wall);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.explosionRadii.furnaceCreeperRadius * explosionPower : ModConfig.explosionRadii.furnaceCreeperRadius;
        AxisAlignedBB bb = new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, bb);

        if (!players.isEmpty()) {
            for (EntityPlayer player : players) {
                generateTrap(player);
            }
        }
    }
}