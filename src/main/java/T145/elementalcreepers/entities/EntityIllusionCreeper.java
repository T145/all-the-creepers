package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityIllusionCreeper extends EntityBaseCreeper {

	private boolean split;
	private boolean illusion;

	public EntityIllusionCreeper(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote && !illusion) {
			EntityPlayer player = world.getClosestPlayerToEntity(this, 8.0D);

			if (!split && player != null && !player.capabilities.isCreativeMode) {
				createIllusionsAndJump();
				split = true;
			}
		}

		super.onUpdate();
	}

	private void createIllusionsAndJump() {
		if (!world.isRemote) {
			spawnIllusionCreepers();
			motionY = 0.5D;
		}
	}

	private void spawnIllusionCreepers() {
		for (int k = 0; k < 4; ++k) {
			float f = ((k % 2) - 0.5F) * 1 / 4.0F;
			float f1 = ((k / 2) - 0.5F) * 1 / 4.0F;
			EntityIllusionCreeper creeper = new EntityIllusionCreeper(world);

			if (hasCustomName()) {
				creeper.setCustomNameTag(getCustomNameTag());
			}

			if (isNoDespawnRequired()) {
				creeper.enablePersistence();
			}

			creeper.split = true;
			creeper.illusion = true;
			creeper.motionY = 0.5D;
			creeper.setLocationAndAngles(posX + f, posY + 0.5D, posZ + f1, rand.nextFloat() * 360.0F, 0.0F);
			world.spawnEntity(creeper);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("Split", split);
		tag.setBoolean("Illusion", illusion);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		split = tag.getBoolean("Split");
		illusion = tag.getBoolean("Illusion");
	}

	public boolean isIllusion() {
		return illusion;
	}

	@Override
	public void detonate() {
		if (!illusion) {
			int explosionPower = explosionRadius * (getPowered() ? 2 : 1);
			world.createExplosion(this, posX, posY, posZ, explosionPower * 2, ForgeEventFactory.getMobGriefingEvent(world, this));
		} else {
			spawnExplosionParticle();
		}
	}
}