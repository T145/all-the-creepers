package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class LightningCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public LightningCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "lightning";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		world.getEntities(LivingEntity.class, this.getAOE(radius, x, y, z), entity -> entity != this)
				.forEach(entity -> ((ServerWorld) world)
						.addLightning(new LightningEntity(world, entity.x, entity.y, entity.z, false)));
	}
}
