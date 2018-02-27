package T145.elementalcreepers.entities;

import java.awt.Color;
import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityFireworkCreeper extends EntityBaseCreeper {

	public EntityFireworkCreeper(World world) {
		super(world);
	}

	protected ItemStack getRandomFirework() {
		ItemStack firework = new ItemStack(Items.FIREWORKS);
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

		return firework;
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		int radius = getPowered() ? ModConfig.explosionRadii.fireworkCreeperRadius * explosionPower : ModConfig.explosionRadii.fireworkCreeperRadius;

		if (!world.isRemote) {
			world.spawnEntity(new EntityFireworkRocket(world, posX, posY, posZ, getRandomFirework()));
		}

		AxisAlignedBB bounds = new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, bounds);

		if (!entities.isEmpty()) {
			for (Entity entity : entities) {
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase entityLiving = (EntityLivingBase) entity;

					if (rand.nextInt(2) == 0 && !world.isRemote) {
						EntityFireworkRocket rocket = new EntityFireworkRocket(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, getRandomFirework());
						world.spawnEntity(rocket);
						entityLiving.startRiding(rocket);
					}
				}
			}
		}
	}
}