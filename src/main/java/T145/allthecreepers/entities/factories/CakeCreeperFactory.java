package T145.allthecreepers.entities.factories;

import T145.allthecreepers.entities.CakeCreeperEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class CakeCreeperFactory implements EntityType.EntityFactory<CreeperEntity> {

	@Override
	public CreeperEntity create(EntityType<CreeperEntity> entityType, World world) {
		return new CakeCreeperEntity(entityType, world);
	}
}
