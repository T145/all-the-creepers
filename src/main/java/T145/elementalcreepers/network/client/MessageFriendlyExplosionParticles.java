package T145.elementalcreepers.network.client;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.network.MessageBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class MessageFriendlyExplosionParticles extends MessageBase {

    private double x;
    private double y;
    private double z;
    private NBTTagCompound fireworkData;

    public MessageFriendlyExplosionParticles(double x, double y, double z, NBTTagCompound fireworkData) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.fireworkData = fireworkData;
    }

    public MessageFriendlyExplosionParticles() {}

    @Override
    public void serialize(PacketBuffer buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeCompoundTag(fireworkData);
    }

    @Override
    public void deserialize(PacketBuffer buffer) throws IOException {
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        fireworkData = buffer.readCompoundTag();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage process(MessageContext context) {
        WorldClient world = ElementalCreepers.proxy.getClientWorld();
        ParticleManager manager = Minecraft.getMinecraft().effectRenderer;
        ParticleFirework.Starter firework = new ParticleFirework.Starter(world, x, y, z, 0, 0, 0, manager, fireworkData);
        manager.addEffect(firework);
        return null;
    }
}