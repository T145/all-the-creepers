package T145.elementalcreepers.common.entities;

import java.util.List;

import T145.elementalcreepers.common.config.ConfigGeneral;
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
		int radius = (int) (getPowered() ? ConfigGeneral.electricCreeperRadius * 1.5F : ConfigGeneral.electricCreeperRadius);
		List<EntityLivingBase> entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(radius, radius, radius));

		for (EntityLivingBase entity : entityList) {
			if (entity != null && !(entity instanceof IMob)) {
				world.spawnEntity(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, griefingEnabled));
			}
		}
	}
}