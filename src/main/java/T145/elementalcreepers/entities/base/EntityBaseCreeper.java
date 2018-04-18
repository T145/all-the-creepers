package T145.elementalcreepers.entities.base;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

    protected AxisAlignedBB getAreaOfEffect(double radius) {
        return new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
    }

    protected void specialExplosion(int radius, IBlockState state) {
        if (ModConfig.general.domeExplosion) {
            domeExplosion(radius, state);
        } else {
            wildExplosion(radius, state);
        }
    }

    protected void domeExplosion(int radius, IBlockState state) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);

                    if (state.getBlock().canPlaceBlockAt(world, pos) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && rand.nextInt(4) < 3) {
                        world.setBlockState(pos, state, ModConfig.general.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }

    protected void wildExplosion(int radius, IBlockState state) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);
                    Block block = state.getBlock();

                    if (block.canPlaceBlockAt(world, pos) && !block.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
                        world.setBlockState(pos, state, ModConfig.general.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }
}