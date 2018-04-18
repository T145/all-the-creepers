package T145.elementalcreepers.entities.base;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public abstract class EntityBaseCreeper extends EntityCreeper {

    protected final MutableBlockPos pos = new MutableBlockPos();

    public EntityBaseCreeper(World world) {
        super(world);
    }

    @Override
    public void explode() {
        if (!world.isRemote) {
            boolean canGrief = world.getGameRules().getBoolean("mobGriefing");
            int explosionPower = explosionRadius * (getPowered() ? 2 : 1);
            dead = diesAfterExplosion();
            createExplosion(explosionPower, canGrief);
            isDead = diesAfterExplosion();
            spawnLingeringCloud();
        }
    }

    public abstract void createExplosion(int explosionPower, boolean canGrief);

    public boolean diesAfterExplosion() {
        return true;
    }

    public AxisAlignedBB getAreaOfEffect(double radius) {
        return new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
    }

    public void domeExplosion(int radius, Block block, int meta) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);

                    if (block.canPlaceBlockAt(world, pos) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && rand.nextInt(4) < 3) {
                        world.setBlockState(pos, block.getStateFromMeta(meta), ModConfig.general.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }

    public void domeExplosion(int radius, Block block) {
        domeExplosion(radius, block, 0);
    }

    public void wildExplosion(int radius, Block block, int meta) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);

                    if (block.canPlaceBlockAt(world, pos) && !block.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
                        world.setBlockState(pos, block.getStateFromMeta(meta), ModConfig.general.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }

    public void wildExplosion(int radius, Block block) {
        wildExplosion(radius, block, 0);
    }
}