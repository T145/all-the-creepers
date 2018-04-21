package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityEarthCreeper extends EntityBaseCreeper {

    public EntityEarthCreeper(World world) {
        super(world);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.earth * explosionPower : ModConfig.EXPLOSION_RADII.earth;
        specialExplosion(radius, Blocks.DIRT.getDefaultState());
    }
}