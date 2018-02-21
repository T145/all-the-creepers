package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySpringCreeper extends EntityBaseCreeper {

	private boolean isSprung;
	private float radius;

	public EntitySpringCreeper(World world) {
		super(world);
	}

	@Override
	public boolean diesAfterExplosion() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isSprung() && !world.isRemote) {
			spawnExplosionParticle();
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		isSprung = tag.getBoolean("isSprung");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("isSprung", isSprung);
	}

	public boolean isSprung() {
		return isSprung;
	}

	public float getExplosionRadius() {
		return radius;
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		radius = getPowered() ? ModConfig.springCreeperPower * 1.5F : ModConfig.springCreeperPower;

		if (world.isRemote) {
			spawnExplosionParticle();
		}

		if (!isSprung()) {
			motionY = 1.5D;
			isSprung = true;
		}
	}
}