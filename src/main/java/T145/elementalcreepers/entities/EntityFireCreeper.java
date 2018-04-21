package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class EntityFireCreeper extends EntityBaseCreeper {

    public EntityFireCreeper(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.fire * explosionPower : ModConfig.EXPLOSION_RADII.fire;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setPos(posX + x, posY + y, posZ + z);

                    if (Blocks.DIRT.canPlaceBlockAt(world, pos) && !Blocks.DIRT.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
                        if (canGrief) {
                            world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                        } else {
                            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

                            for (EntityLivingBase entity : entities) {
                                if (entity != null) {
                                    entity.setFire(500);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}