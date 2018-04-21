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
    public void createExplosion(int explosionPower, boolean canGrief) {
        float radius = getPowered() ? ModConfig.EXPLOSION_RADII.lightningCreeperRadius * 1.5F : ModConfig.EXPLOSION_RADII.lightningCreeperRadius;
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

        if (!entities.isEmpty()) {
            for (EntityLivingBase entity : entities) {
                world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false));
            }
        }
    }
}