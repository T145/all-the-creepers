package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLightningCreeper extends EntityBaseCreeper {

	public EntityLightningCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		float radius = getPowered() ? ModConfig.electricCreeperRadius * 1.5F : ModConfig.electricCreeperRadius;
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius));

		for (EntityLivingBase entity : entities) {
			if (entity != null && !(entity instanceof IMob)) {
				world.spawnEntity(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, griefingEnabled));
			}
		}
	}
}