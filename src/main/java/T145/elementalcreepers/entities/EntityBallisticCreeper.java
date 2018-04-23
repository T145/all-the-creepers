package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.ai.EntityAIThrowTNT;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import java.util.List;

public class EntityBallisticCreeper extends EntityBaseCreeper {

    private EntityAIThrowTNT throwingAI = new EntityAIThrowTNT(this, 8D);

    public EntityBallisticCreeper(World world) {
        super(world);
        tasks.addTask(4, throwingAI); // avoid ocelots first to nuke them!
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true; // be sure the thrown TNT doesn't kill the creeper
    }

    @Override
    public void explode(boolean canGrief) {
        double radius = getPowered() ? ModConfig.EXPLOSION_RADII.ballisticCharged : ModConfig.EXPLOSION_RADII.ballistic;
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

        for (EntityLivingBase entity : entities) {
            throwingAI.throwTNT(entity, true);
        }
    }
}