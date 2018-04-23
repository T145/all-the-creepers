package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.explosion.ExplosionPsychic;
import T145.elementalcreepers.explosion.base.ExplosionSpecial;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityPsychicCreeper extends EntityBaseCreeper {

    public EntityPsychicCreeper(World world) {
        super(world);
    }

    @Override
    public void detonate() {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.psychicCharged : ModConfig.EXPLOSION_RADII.psychic;
        ExplosionSpecial explosion = new ExplosionPsychic(world, this, posX, posY, posZ, ModConfig.EXPLOSION_POWER.psychic, radius, false, ForgeEventFactory.getMobGriefingEvent(world, this));
        explosion.doExplosionA();
    }
}