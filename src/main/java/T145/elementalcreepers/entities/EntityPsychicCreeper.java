package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.explosion.ExplosionPsychic;
import T145.elementalcreepers.explosion.base.ExplosionSpecial;
import net.minecraft.world.World;

public class EntityPsychicCreeper extends EntityBaseCreeper {

    public EntityPsychicCreeper(World world) {
        super(world);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        if (!world.isRemote) {
            int radius = ModConfig.explosionRadii.psychicCreeperRadius * explosionPower;
            ExplosionSpecial explosion = new ExplosionPsychic(world, this, posX, posY, posZ, ModConfig.explosionPower.psychicCreeperPower, radius, false, canGrief);
            explosion.doExplosionA();
        }
    }
}