package T145.elementalcreepers.network.client;

import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import T145.elementalcreepers.network.ECMessage;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class MessageFriendlyParticles extends ECMessage {

    private static final Random RANDOM = new Random();

    private int entityId;

    public MessageFriendlyParticles() {}

    public MessageFriendlyParticles(EntityFriendlyCreeper creeper) {
        this.entityId = creeper.getEntityId();
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        buffer.writeVarInt(entityId);
    }

    @Override
    public void deserialize(PacketBuffer buffer) {
        entityId = buffer.readVarInt();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void process(MessageContext ctx) {
        World world = Minecraft.getMinecraft().world;

        if (world != null) {
            Entity entity = world.getEntityByID(entityId);

            if (entity instanceof EntityFriendlyCreeper) {
                EntityFriendlyCreeper creeper = (EntityFriendlyCreeper) entity;
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFirework.Starter(world, creeper.posX, creeper.posY + (creeper.getPowered() ? 2.5F : 0.5F), creeper.posZ, 0, 0, 0, Minecraft.getMinecraft().effectRenderer, generateTag(creeper.getPowered())));
            }
        }
    }

    private NBTTagCompound generateTag(boolean powered) {
        NBTTagCompound fireworkTag = new NBTTagCompound();
        NBTTagCompound fireworkItemTag = new NBTTagCompound();
        NBTTagList fireworkTags = new NBTTagList();
        List<Integer> list = Lists.newArrayList();

        list.add(ItemDye.DYE_COLORS[1]);
        list.add(ItemDye.DYE_COLORS[11]);
        list.add(ItemDye.DYE_COLORS[4]);

        for (int i = 0; i < RANDOM.nextInt(3) + 3; i++) {
            list.add(ItemDye.DYE_COLORS[RANDOM.nextInt(15)]);
        }

        int[] colours = new int[list.size()];

        for (int i = 0; i < colours.length; i++) {
            colours[i] = list.get(i);
        }

        fireworkTag.setIntArray("Colors", colours);
        fireworkTag.setBoolean("Flicker", true);
        fireworkTag.setByte("Type", (byte) (powered ? 3 : 4));
        fireworkTags.appendTag(fireworkTag);
        fireworkItemTag.setTag("Explosions", fireworkTags);
        return fireworkItemTag;
    }
}