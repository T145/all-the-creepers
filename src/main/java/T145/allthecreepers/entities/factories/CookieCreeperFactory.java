package T145.allthecreepers.entities.factories;

import T145.allthecreepers.entities.CookieCreeperEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class CookieCreeperFactory implements EntityType.EntityFactory<CreeperEntity> {

	@Override
	public CreeperEntity create(EntityType<CreeperEntity> entityType, World world) {
		return new CookieCreeperEntity(entityType, world);
	}
}
