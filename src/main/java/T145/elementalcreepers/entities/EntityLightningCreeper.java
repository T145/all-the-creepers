package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;

public class EntityLightningCreeper extends EntityBaseCreeper {

	public EntityLightningCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		float radius = getPowered() ? ModConfig.explosionRadii.lightningCreeperRadius * 1.5F : ModConfig.explosionRadii.lightningCreeperRadius;
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

		if (!entities.isEmpty()) {
			for (EntityLivingBase entity : entities) {
				world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false));
			}
		}
	}
}