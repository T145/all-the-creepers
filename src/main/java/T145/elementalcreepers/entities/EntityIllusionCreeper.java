package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

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

	public void createIllusionsAndJump() {
		if (!world.isRemote) {
			spawnIllusionCreepers();
			motionY = 0.5D;
		}
	}

	protected void spawnIllusionCreepers() {
		for (int i = 0; i < 4; i++) {
			EntityIllusionCreeper entity = new EntityIllusionCreeper(world);
			entity.split = true;
			entity.illusion = true;
			entity.setPositionAndUpdate(posX, posY, posZ);
			entity.motionY = 0.5D;
			world.spawnEntity(entity);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("split", split);
		tag.setBoolean("isIllusion", illusion);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		split = tag.getBoolean("split");
		illusion = tag.getBoolean("isIllusion");
	}

	public boolean isIllusion() {
		return illusion;
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		if (!illusion) {
			int exPower = 2 * explosionPower; // original was 3x
			world.createExplosion(this, posX, posY, posZ, exPower, griefingEnabled);
		} else {
			spawnExplosionParticle();
		}
	}
}