package T145.elementalcreepers.network.client;

import T145.elementalcreepers.network.ECMessage;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class MessageFriendlyParticles extends ECMessage {

    private static final Random RANDOM = new Random();

    private BlockPos pos;
    private boolean powered;

    public MessageFriendlyParticles() {}

    public MessageFriendlyParticles(BlockPos pos, boolean powered) {
        this.pos = pos;
        this.powered = powered;
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeBoolean(powered);
    }

    @Override
    public void deserialize(PacketBuffer buffer) {
        pos = buffer.readBlockPos();
        powered = buffer.readBoolean();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void process(MessageContext ctx) {
        World world = Minecraft.getMinecraft().world;

        if (world != null) {
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFirework.Starter(world, pos.getX(), pos.getY() + (powered ? 2.5F : 0.5F), pos.getZ(), 0, 0, 0, Minecraft.getMinecraft().effectRenderer, generateTag()));
        }
    }

    private NBTTagCompound generateTag() {
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