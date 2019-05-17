package T145.allthecreepers.entities;

import T145.allthecreepers.api.creepers.IElementalCreeper;
import T145.allthecreepers.entities.goals.ThrowTntGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class BallisticCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public BallisticCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "ballistic";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(4, new ThrowTntGoal(this));
	}

	@Override
	protected void initAttributes() {
		super.initAttributes(); // make it move a little faster
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
	}

	@Override
	public boolean isImmuneToExplosion() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		// spawn a big piece of TNT with a slightly longer fuse instead of a bunch of normal TNT
		// (helps save servers from a ton of lag, i.e. Fetching addPacket for removed entity)

		// for now we'll just cause a big explosion since TNT entities don't like me
		this.world.createExplosion(this, this.x, this.y, this.z, 8 * (isCharged() ? 2 : 1), destructionType);
	}
}
