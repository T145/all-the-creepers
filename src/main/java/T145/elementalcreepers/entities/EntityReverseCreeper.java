package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityReverseCreeper extends EntityBaseCreeper {

    public EntityReverseCreeper(World world) {
        super(world);
    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float p_174816_5_) {
        return getExplosionResistance(explosion, world, pos, state) < getExplosionResistance(explosion, world, pos, Blocks.BEDROCK.getDefaultState());
    }

    @Override
    public void detonate() {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.reverseCharged : ModConfig.EXPLOSION_RADII.reverse;
        IBlockState[][][] states = new IBlockState[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];
        TileEntity[][][] tiles = new TileEntity[radius * 2 + 2][radius * 2 + 2][radius * 2 + 2];

        for (int x = -radius - 1; x <= radius; x++) {
            for (int y = -radius - 1; y <= radius; y++) {
                for (int z = -radius - 1; z <= radius; z++) {
                    int ax = x + radius + 1;
                    int ay = y + radius + 1;
                    int az = z + radius + 1;
                    double ex = posX + x;
                    double ey = posY + y;
                    double ez = posZ + z;
                    MUTABLE_POS.setPos(ex, ey, ez);

                    IBlockState state = world.getBlockState(MUTABLE_POS);

                    if (canExplosionDestroyBlock(SIMPLE_EXPLOSION, world, MUTABLE_POS, world.getBlockState(MUTABLE_POS), 0)) {
                        states[ax][ay][az] = null;

                        if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && ey > -1) {
                            states[ax][ay][az] = state;
                            tiles[ax][ay][az] = world.getTileEntity(MUTABLE_POS);
                        }
                    }
                }
            }
        }

        for (int x = -radius - 1; x <= radius; x++) {
            for (int y = -radius - 1; y <= radius; y++) {
                for (int z = -radius - 1; z <= radius; z++) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);
                    IBlockState state = states[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];
                    TileEntity te = tiles[x + radius + 1][2 * radius - (y + radius)][z + radius + 1];

                    if (state != null && posY + y > 0) {
                        world.setBlockState(MUTABLE_POS, state, 3);

                        if (te != null) {
                            world.setTileEntity(MUTABLE_POS, te);
                        }
                    }
                }
            }
        }
    }
}