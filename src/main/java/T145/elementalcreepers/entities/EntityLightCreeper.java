package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityLightCreeper extends EntityBaseCreeper {

    public EntityLightCreeper(World world) {
        super(world);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.lightCreeperRadius * explosionPower : ModConfig.EXPLOSION_RADII.lightCreeperRadius;
        specialExplosion(radius, Blocks.GLOWSTONE.getDefaultState());
    }
}