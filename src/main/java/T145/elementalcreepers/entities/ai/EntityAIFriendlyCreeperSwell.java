package T145.elementalcreepers.entities.ai;

import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFriendlyCreeperSwell extends EntityAIBase {

    private final EntityFriendlyCreeper creeper;
    private Entity target;

    public EntityAIFriendlyCreeperSwell(EntityFriendlyCreeper creeper) {
        this.creeper = creeper;
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return creeper.getCreeperState() > 0 || (creeper.getAttackTarget() != null && creeper.getDistance(creeper.getAttackTarget()) < 9.0D);
    }

    @Override
    public void startExecuting() {
        target = creeper.getAttackTarget();
    }

    @Override
    public void resetTask() {
        target = null;
    }

    @Override
    public void updateTask() {
        target = creeper.getAttackTarget();
        if (target == null) {
            creeper.setCreeperState(-1);
        } else if (creeper.getDistance(target) > 49.0D) {
            creeper.setCreeperState(-1);
        } else if (!creeper.getEntitySenses().canSee(target)) {
            creeper.setCreeperState(-1);
        } else {
            creeper.setCreeperState(1);
        }
    }
}
