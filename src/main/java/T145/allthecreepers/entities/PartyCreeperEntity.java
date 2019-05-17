package T145.allthecreepers.entities;

import T145.allthecreepers.api.EntitiesATC;
import T145.allthecreepers.api.creepers.IElementalCreeper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class PartyCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public PartyCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "wacky/party";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	private void spawnCreepers(EntityType<?> entityType) {
		for (int offset = 0; offset < random.nextInt(2) + 1; ++offset) {
			float xOffset = ((offset % 2) - 0.5F) * 1 / 4.0F;
			float zOffset = ((offset / 2) - 0.5F) * 1 / 4.0F;
			CreeperEntity creeper = (CreeperEntity) entityType.create(world);

			if (hasCustomName()) {
				creeper.setCustomName(getCustomName());
			}

			if (isPersistent()) {
				creeper.setPersistent();
			}

			creeper.setPositionAndAngles(x + xOffset, y + 0.5D, z + zOffset, random.nextFloat() * 360.0F, 0.0F);
			world.spawnEntity(creeper);
			creeper.addVelocity(0D, 0.5D, 0D);
		}
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		this.addVelocity(0D, 0.5D, 0D);
		spawnCreepers(EntitiesATC.cakeCreeper);
		spawnCreepers(EntitiesATC.cookieCreeper);
		spawnCreepers(EntitiesATC.fireworkCreeper);
	}
}
