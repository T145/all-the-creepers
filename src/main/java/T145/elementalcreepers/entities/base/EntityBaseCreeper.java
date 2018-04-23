package T145.elementalcreepers.entities.base;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;

public abstract class EntityBaseCreeper extends EntityCreeper {

    protected final MutableBlockPos MUTABLE_POS = new MutableBlockPos(BlockPos.ORIGIN);
    protected final Explosion SIMPLE_EXPLOSION = new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, new ArrayList<>());

    public EntityBaseCreeper(World world) {
        super(world);
    }

    @Override
    public void explode() {
        if (!world.isRemote) {
            dead = diesAfterExplosion();
            explode(ForgeEventFactory.getMobGriefingEvent(world, this));
            isDead = diesAfterExplosion();
            spawnLingeringCloud();
        }
    }

    public abstract void explode(boolean canGrief);

    public boolean diesAfterExplosion() {
        return true;
    }

    protected AxisAlignedBB getAreaOfEffect(double radius) {
        return new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
    }

    protected void specialExplosion(int radius, IBlockState state) {
        if (ModConfig.GENERAL.domeExplosion) {
            domeExplosion(radius, state);
        } else {
            wildExplosion(radius, state);
        }
    }

    protected void domeExplosion(int radius, IBlockState state) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);

                    if (state.getBlock().canPlaceBlockAt(world, MUTABLE_POS) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && rand.nextInt(4) < 3) {
                        world.setBlockState(MUTABLE_POS, state, ModConfig.GENERAL.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }

    protected void wildExplosion(int radius, IBlockState state) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);
                    Block block = state.getBlock();

                    if (block.canPlaceBlockAt(world, MUTABLE_POS) && !block.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
                        world.setBlockState(MUTABLE_POS, state, ModConfig.GENERAL.updatePlacedBlocks ? 3 : 2);
                    }
                }
            }
        }
    }

    protected void createPlatformOverLiquid(EntityLivingBase entity, Block platform, Block liquid, Block flowingLiquid) {
        if (entity.onGround) {
            World world = entity.world;
            BlockPos pos = entity.getPosition();
            float f = (float) Math.min(16, 2);
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(BlockPos.ORIGIN);

            for (BlockPos.MutableBlockPos mutablePos1 : BlockPos.getAllInBoxMutable(pos.add(-f, -1.0D, -f), pos.add(f, -1.0D, f))) {
                if (mutablePos1.distanceSqToCenter(entity.posX, entity.posY, entity.posZ) <= (f * f)) {
                    mutablePos.setPos(mutablePos1.getX(), mutablePos1.getY() + 1, mutablePos1.getZ());
                    IBlockState iblockstate = world.getBlockState(mutablePos);

                    if (iblockstate.getMaterial() == Material.AIR) {
                        IBlockState iblockstate1 = world.getBlockState(mutablePos1);

                        if (iblockstate1.getMaterial() == liquid.getDefaultState().getMaterial() && (iblockstate1.getBlock() == liquid || iblockstate1.getBlock() == flowingLiquid) && iblockstate1.getValue(BlockLiquid.LEVEL) == 0 && world.mayPlace(platform, mutablePos1, false, EnumFacing.DOWN, null)) {
                            world.setBlockState(mutablePos1, platform.getDefaultState());
                            world.scheduleUpdate(mutablePos1.toImmutable(), platform, MathHelper.getInt(entity.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}