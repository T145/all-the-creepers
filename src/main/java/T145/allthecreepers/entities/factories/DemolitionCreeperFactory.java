package T145.allthecreepers.entities.factories;

import T145.allthecreepers.entities.DemolitionCreeperEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class DemolitionCreeperFactory implements EntityType.EntityFactory<CreeperEntity> {

	@Override
	public CreeperEntity create(EntityType<CreeperEntity> entityType, World world) {
		return new DemolitionCreeperEntity(entityType, world);
	}
}
