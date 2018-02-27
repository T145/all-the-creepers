package T145.elementalcreepers.entities.ai;

import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFriendlyCreeperSwell extends EntityAIBase {

	private final EntityFriendlyCreeper swellingCreeper;
	private Entity creeperAttackTarget;

	public EntityAIFriendlyCreeperSwell(EntityFriendlyCreeper par1EntityCreeper) {
		this.swellingCreeper = par1EntityCreeper;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		Entity var1 = this.swellingCreeper.getAttackTarget();
		return (this.swellingCreeper.getCreeperState() > 0) || ((var1 != null) && (this.swellingCreeper.getDistance(var1) < 9.0D));
	}

	@Override
	public void startExecuting() {
		this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
	}

	@Override
	public void resetTask() {
		this.creeperAttackTarget = null;
	}

	@Override
	public void updateTask() {
		this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
		if (this.creeperAttackTarget == null) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (this.swellingCreeper.getDistance(this.creeperAttackTarget) > 49.0D) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
			this.swellingCreeper.setCreeperState(-1);
		} else {
			this.swellingCreeper.setCreeperState(1);
		}
	}
}
