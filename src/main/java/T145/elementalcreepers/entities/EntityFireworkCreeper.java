package T145.elementalcreepers.entities;

import java.awt.Color;
import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class EntityFireworkCreeper extends EntityBaseCreeper {

	public EntityFireworkCreeper(World world) {
		super(world);
	}

	private ItemStack getRandomFirework() {
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
	public void detonate() {
		world.spawnEntity(new EntityFireworkRocket(world, posX, posY, posZ, getRandomFirework()));

		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(getPowered() ? ModConfig.EXPLOSION_RADII.fireworkCharged : ModConfig.EXPLOSION_RADII.firework), entity -> entity != this);

		for (EntityLivingBase entity : entities) {
			EntityFireworkRocket rocket = new EntityFireworkRocket(world, entity.posX, entity.posY, entity.posZ, getRandomFirework());
			world.spawnEntity(rocket);
			entity.startRiding(rocket);
		}
	}
}