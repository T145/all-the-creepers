package T145.elementalcreepers.network;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.network.client.MessageFriendlyExplosionParticles;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ElementalCreepers.MODID);

    private static byte id = 0;

    public static void sendToAllAround(MessageBase message, World world, BlockPos pos) {
        INSTANCE.sendToAllAround(message, getTargetPoint(world, pos));
    }

    private static NetworkRegistry.TargetPoint getTargetPoint(World world, BlockPos pos) {
        return new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 64D);
    }

    public static void registerMessages() {
        registerMessage(MessageFriendlyExplosionParticles.class, Side.CLIENT);
    }

    private static void registerMessage(Class<? extends MessageBase> message, Side side) {
        INSTANCE.registerMessage((m, ctx) -> {
            IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
            thread.addScheduledTask(() -> m.process(ctx));
            return null;
        }, message, id++, side);
    }
}