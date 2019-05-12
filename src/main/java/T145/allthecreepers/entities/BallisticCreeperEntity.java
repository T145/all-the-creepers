package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.entities.goals.ThrowTntGoal;
import net.minecraft.entity.EntityType;
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
	public boolean isImmuneToExplosion() {
		return true; // avoid getting killed by thrown TNT
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(4, new ThrowTntGoal(this));
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		// spawn a big piece of TNT with a slightly longer fuse instead of a bunch of normal TNT
		// (helps save servers from a ton of lag, i.e. Fetching addPacket for removed entity)
	}
}
