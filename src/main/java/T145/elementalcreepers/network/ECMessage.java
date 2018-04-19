package T145.elementalcreepers.network;

import T145.elementalcreepers.ElementalCreepers;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public abstract class ECMessage implements IMessage {

    @Override
    public final void toBytes(ByteBuf buf) {
        serialize(new PacketBuffer(buf));
    }

    @Override
    public final void fromBytes(ByteBuf buf) {
        try {
            deserialize(new PacketBuffer(buf));
        } catch (IOException err) {
            ElementalCreepers.LOG.catching(err);
        }
    }

    public abstract void serialize(PacketBuffer buffer);

    public abstract void deserialize(PacketBuffer buffer) throws IOException;

    public abstract void process(MessageContext ctx);
}