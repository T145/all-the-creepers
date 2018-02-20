package T145.elementalcreepers.common.entities;

import java.awt.Color;
import java.util.List;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class EntityFireworkCreeper extends EntityBaseCreeper {

	private ItemStack firework;

	public EntityFireworkCreeper(World world) {
		super(world);
	}

	public void getRandomColorFireWork() {
		firework = new ItemStack(Items.FIREWORKS, 1);
		firework.setTagCompound(new NBTTagCompound());
		NBTTagCompound data = new NBTTagCompound();
		data.setByte("Flight", (byte) 1);
		NBTTagList list = new NBTTagList();
		NBTTagCompound fireworkData = new NBTTagCompound();
		fireworkData.setByte("Trail", (byte) 1);
		fireworkData.setByte("Type", (byte) 3);
		fireworkData.setIntArray("Colors", new int[] { new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)).getRGB() });
		list.appendTag(fireworkData);
		data.setTag("Explosions", list);
		firework.getTagCompound().setTag("Fireworks", data);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.fireworkCreeperRadius * explosionPower : ConfigGeneral.fireworkCreeperRadius;

		getRandomColorFireWork();

		if (!world.isRemote) {
			world.spawnEntity(new EntityFireworkRocket(world, posX, posY, posZ, firework.copy()));
		}

		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(radius, radius, radius));

		if (list != null && !list.isEmpty()) {
			for (Entity entity : list) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase entityLiving = (EntityLivingBase) entity;

					if (rand.nextInt(2) == 0 && !world.isRemote) {
						getRandomColorFireWork();
						EntityFireworkRocket rocket = new EntityFireworkRocket(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, firework.copy());
						world.spawnEntity(rocket);
						entityLiving.startRiding(rocket);
					}
				}
			}
		}
	}
}