package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityArmageddonCreeper extends EntityBaseCreeper {

	public EntityArmageddonCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		if (!world.isRemote) {
			for (int i = 0; i < ModConfig.armegeddonCreeperYield; i++) {
				EntityTNTPrimed tnt = new EntityTNTPrimed(world, posX, posY, posZ, this);
				tnt.setPositionAndUpdate(posX, posY, posZ);
				tnt.motionY = 0.5D;
				world.spawnEntity(tnt);
			}
		}
	}
}