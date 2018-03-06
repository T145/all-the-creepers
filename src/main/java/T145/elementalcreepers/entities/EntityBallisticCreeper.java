package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.ai.EntityAIThrowTNT;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityBallisticCreeper extends EntityBaseCreeper {

	protected EntityAIThrowTNT throwingAI = new EntityAIThrowTNT(this, 8D);

	public EntityBallisticCreeper(World world) {
		super(world);
		tasks.addTask(4, throwingAI); // avoid ocelots first to nuke them!
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true; // be sure the thrown TNT doesn't kill the creeper
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		float radius = getPowered() ? ModConfig.explosionRadii.hydrogenCreeperRadius * 1.5F : ModConfig.explosionRadii.hydrogenCreeperRadius;
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

		if (!entities.isEmpty()) {
			for (EntityLivingBase entity : entities) {
				throwingAI.throwTNT(entity, true);
			}
		}
	}
}