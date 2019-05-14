package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.core.AllTheCreepers;
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

	private void spawnCreepers(EntityType<? extends CreeperEntity> type) {
		for (int offset = 0; offset < random.nextInt(2) + 1; ++offset) {
			float xOffset = ((offset % 2) - 0.5F) * 1 / 4.0F;
			float zOffset = ((offset / 2) - 0.5F) * 1 / 4.0F;
			CreeperEntity creeper = type.create(world);

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
		spawnCreepers(AllTheCreepers.CAKE_CREEPER);
		spawnCreepers(AllTheCreepers.COOKIE_CREEPER);
		spawnCreepers(AllTheCreepers.FIREWORK_CREEPER);
	}
}
