package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

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
        fireworkData.setIntArray("Colors", new int[]{new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)).getRGB()});

        list.appendTag(fireworkData);
        data.setTag("Explosions", list);
        firework.getTagCompound().setTag("Fireworks", data);

        return firework;
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.fireworkCreeperRadius * explosionPower : ModConfig.EXPLOSION_RADII.fireworkCreeperRadius;

        if (!world.isRemote) {
            world.spawnEntity(new EntityFireworkRocket(world, posX, posY, posZ, getRandomFirework()));
        }

        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

        if (!entities.isEmpty()) {
            for (EntityLivingBase entity : entities) {
                if (rand.nextInt(2) == 0 && !world.isRemote) {
                    EntityFireworkRocket rocket = new EntityFireworkRocket(world, entity.posX, entity.posY, entity.posZ, getRandomFirework());
                    world.spawnEntity(rocket);
                    entity.startRiding(rocket);
                }
            }
        }
    }
}