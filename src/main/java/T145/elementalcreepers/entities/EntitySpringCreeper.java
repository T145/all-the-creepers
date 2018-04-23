package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySpringCreeper extends EntityBaseCreeper {

    private boolean airborne;

    public EntitySpringCreeper(World world) {
        super(world);
    }

    public boolean isAirborne() {
        return airborne;
    }

    @Override
    public boolean diesAfterExplosion() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (airborne && !world.isRemote) {
            spawnExplosionParticle();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        airborne = tag.getBoolean("Airborne");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setBoolean("Airborne", airborne);
    }

    @Override
    public void explode(boolean canGrief) { // launches the creeper
        spawnExplosionParticle();

        if (!airborne) {
            motionY = 1.5D;
            airborne = true;
        }
    }
}