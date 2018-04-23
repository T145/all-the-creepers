package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;

import java.util.List;

public class EntityLightningCreeper extends EntityBaseCreeper {

    public EntityLightningCreeper(World world) {
        super(world);
    }

    @Override
    public void detonate() {
        double radius = getPowered() ? ModConfig.EXPLOSION_RADII.lightningCharged : ModConfig.EXPLOSION_RADII.lightning;
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

        for (EntityLivingBase entity : entities) {
            world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false));
        }
    }
}