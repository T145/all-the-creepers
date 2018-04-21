package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class EntityEnderCreeper extends EntityBaseCreeper {

    public EntityEnderCreeper(World world) {
        super(world);
    }

    @Override
    public void onLivingUpdate() {
        if (world.isRemote) {
            for (int i = 0; i < 2; ++i) {
                world.spawnParticle(EnumParticleTypes.PORTAL, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        float radius = getPowered() ? ModConfig.EXPLOSION_RADII.ender * 1.5F : ModConfig.EXPLOSION_RADII.ender;
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

        if (!entities.isEmpty()) {
            for (EntityLivingBase entity : entities) {
                teleportEntityRandomly(entity, 32);
            }
        }
    }

    private void teleportEntityRandomly(Entity entity, double maxDist) {
        World world = entity.getEntityWorld();

        if (world.isRemote) {
            return;
        }

        double deltaYaw = 0.0d;
        double deltaPitch = 0.0d;
        double x = 0.0d;
        double y = 0.0d;
        double z = 0.0d;
        maxDist = maxDist - (world.rand.nextFloat() * maxDist / 2.0d);

        // Try to find a free spot (non-colliding with blocks)
        for (int i = 0; i < 20; i++) {
            deltaYaw = world.rand.nextFloat() * 2d * Math.PI;
            deltaPitch = world.rand.nextFloat() * 0.5d * Math.PI; // only from the same level upwards
            x = entity.posX;
            y = entity.posY;
            z = entity.posZ;
            x += Math.cos(deltaPitch) * Math.cos(deltaYaw) * maxDist;
            z += Math.cos(deltaPitch) * Math.sin(deltaYaw) * maxDist;
            y += Math.sin(deltaPitch) * maxDist;

            if (world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty()) {
                teleportEntityTo((WorldServer) world, entity, x, y, z);
                return;
            }
        }
    }

    private void teleportEntityTo(WorldServer world, Entity entity, double x, double y, double z) {
        double prevPosX = entity.posX;
        double prevPosY = entity.posY;
        double prevPosZ = entity.posZ;

        entity.setPositionAndUpdate(x, y, z);

        for (int j = 0; j < 128; ++j) {
            double d6 = j / 127.0D;
            float f = (world.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (world.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (world.rand.nextFloat() - 0.5F) * 0.2F;
            double d3 = prevPosX + (entity.posX - prevPosX) * d6 + (world.rand.nextDouble() - 0.5D) * entity.width * 2.0D;
            double d4 = prevPosY + (entity.posY - prevPosY) * d6 + world.rand.nextDouble() * entity.height;
            double d5 = prevPosZ + (entity.posZ - prevPosZ) * d6 + (world.rand.nextDouble() - 0.5D) * entity.width * 2.0D;
            world.spawnParticle(EnumParticleTypes.PORTAL, false, d3, d4, d5, 1, f, f1, f2, 0D);
        }

        if (entity instanceof EntityCreature) {
            ((EntityCreature) entity).getNavigator().clearPath();
        }

        world.playSound(null, x, y, z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1F, 1F);
    }
}