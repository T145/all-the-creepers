package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityElectricCreeper extends EntityBaseCreeper {

	public EntityElectricCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		float radius = getPowered() ? ModConfig.explosionRadii.electricCreeperRadius * 1.5F : ModConfig.explosionRadii.electricCreeperRadius;
		AxisAlignedBB bb = new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, bb);

		if (!entities.isEmpty()) {
			for (Entity entity : entities) {
				if (entity instanceof EntityLivingBase && !(entity instanceof IMob)) {
					world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false));
				}
			}
		}
	}
}