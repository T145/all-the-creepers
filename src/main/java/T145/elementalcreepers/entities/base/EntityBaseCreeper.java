package T145.elementalcreepers.entities.base;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
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

    protected void createPlatform(EntityLivingBase living, World worldIn, BlockPos pos, Block base, Block liquid, Block flowingLiquid) {
        if (living.onGround) {
            float f = (float) Math.min(16, 2);
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(BlockPos.ORIGIN);

            for (BlockPos.MutableBlockPos mutablePos1 : BlockPos.getAllInBoxMutable(pos.add((double) (-f), -1.0D, (double) (-f)), pos.add((double) f, -1.0D, (double) f))) {
                if (mutablePos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double) (f * f)) {
                    mutablePos.setPos(mutablePos1.getX(), mutablePos1.getY() + 1, mutablePos1.getZ());
                    IBlockState iblockstate = worldIn.getBlockState(mutablePos);

                    if (iblockstate.getMaterial() == Material.AIR) {
                        IBlockState iblockstate1 = worldIn.getBlockState(mutablePos1);

                        if (iblockstate1.getMaterial() == liquid.getDefaultState().getMaterial() && (iblockstate1.getBlock() == liquid || iblockstate1.getBlock() == flowingLiquid) && iblockstate1.getValue(BlockLiquid.LEVEL) == 0 && worldIn.mayPlace(base, mutablePos1, false, EnumFacing.DOWN, null)) {
                            worldIn.setBlockState(mutablePos1, base.getDefaultState());
                            worldIn.scheduleUpdate(mutablePos1.toImmutable(), base, MathHelper.getInt(living.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}