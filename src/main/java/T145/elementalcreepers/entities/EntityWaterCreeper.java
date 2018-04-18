package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityWaterCreeper extends EntityBaseCreeper {

    public EntityWaterCreeper(World world) {
        super(world);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.explosionRadii.waterCreeperRadius * explosionPower : ModConfig.explosionRadii.waterCreeperRadius;
        specialExplosion(radius, Blocks.WATER.getDefaultState());
    }
}