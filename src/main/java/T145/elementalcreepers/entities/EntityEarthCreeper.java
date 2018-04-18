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
        int radius = getPowered() ? ModConfig.explosionRadii.earthCreeperRadius * explosionPower : ModConfig.explosionRadii.earthCreeperRadius;

        if (ModConfig.general.domeExplosion) {
            domeExplosion(radius, Blocks.DIRT);
        } else {
            wildExplosion(radius, Blocks.DIRT);
        }
    }
}