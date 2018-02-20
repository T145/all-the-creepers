package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySpringCreeper extends EntityBaseCreeper {

	private boolean isSprung;
	private int radius;

	public EntitySpringCreeper(World world) {
		super(world);
		this.explosionRadius = ConfigGeneral.springCreeperPower;
	}

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
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		radius = (getPowered() ? (int) (explosionRadius * 1.5F) : explosionRadius);

		if (world.isRemote) {
			spawnExplosionParticle();
		}

		if (!isSprung()) {
			motionY = 1.5D;
			isSprung = true;
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

	public int getExplosionRadius() {
		return radius;
	}
}