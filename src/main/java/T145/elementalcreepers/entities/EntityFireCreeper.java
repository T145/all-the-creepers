package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;

public class EntityFireCreeper extends EntityBaseCreeper {

    public EntityFireCreeper(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public void detonate() {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.fireCharged : ModConfig.EXPLOSION_RADII.fire;

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    MUTABLE_POS.setPos(posX + x, posY + y, posZ + z);

                    if (Blocks.DIRT.canPlaceBlockAt(world, MUTABLE_POS) && !Blocks.DIRT.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
                        if (ForgeEventFactory.getMobGriefingEvent(world, this)) {
                            world.setBlockState(MUTABLE_POS, Blocks.FIRE.getDefaultState());
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