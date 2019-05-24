package T145.allthecreepers.entities.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.PrimedTntEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ThrowTntGoal extends Goal {

	private final CreeperEntity creeper;
	private LivingEntity target;
	int attackCooldown;

	public ThrowTntGoal(CreeperEntity creeper) {
		this.creeper = creeper;
	}

	@Override
	public boolean canStart() {
		target = creeper.getTarget();
		return target != null && target.isAlive() && creeper.canSee(target) && creeper.distanceTo(target) > 4D && creeper.distanceTo(target) < 144D;
	}

	@Override
	public void stop() {
		target = null;
		attackCooldown = 0;
	}

	public void spawnTnt(double x, double y, double z) {
		if (creeper.getIgnited()) {
			return;
		}

		World world = creeper.world;

		if (!world.isClient) {
			PrimedTntEntity tnt = new PrimedTntEntity(world, creeper.x, creeper.y, creeper.z, creeper);
			tnt.addVelocity((x - tnt.x) / 18D, (y - tnt.y) / 18D + 0.5D, (z - tnt.z) / 18D);
			// do not setVelocity as the constructor does so already
			// maybe some setFuse here
			world.spawnEntity(tnt);
		}

		creeper.playSound(SoundEvents.ENTITY_TNT_PRIMED, 1F, 1F);
		attackCooldown = creeper.isCharged() ? 40 : 60;
	}

	private void spawnTntTowardsTarget() {
		spawnTnt(target.x, target.y, target.z);
	}

	@Override
	public void tick() {
		if (target != null && --attackCooldown <= 0) {
			spawnTntTowardsTarget();
		}
	}
}