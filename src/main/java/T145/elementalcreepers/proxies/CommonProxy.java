package T145.elementalcreepers.proxies;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.network.ECMessage;
import T145.elementalcreepers.network.client.MessageFriendlyParticles;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import scala.xml.Elem;

import javax.annotation.Nullable;
import java.util.HashSet;

public class CommonProxy implements IMessageHandler<ECMessage, IMessage> {

    private static final HashSet<Block> ROCK_SET = new HashSet<>();

    static {
        for (ItemStack stack : OreDictionary.getOres("cobblestone")) {
            ROCK_SET.add(Block.getBlockFromItem(stack.getItem()));
        }

        for (ItemStack stack : OreDictionary.getOres("stone")) {
            ROCK_SET.add(Block.getBlockFromItem(stack.getItem()));
        }

        ROCK_SET.add(Blocks.MOSSY_COBBLESTONE);
        ROCK_SET.add(Blocks.STONE_BRICK_STAIRS);
        ROCK_SET.add(Blocks.STONE_BUTTON);
        ROCK_SET.add(Blocks.STONE_PRESSURE_PLATE);
        ROCK_SET.add(Blocks.STONE_STAIRS);
        ROCK_SET.add(Blocks.STONEBRICK);
        ROCK_SET.add(Blocks.STONE_SLAB);
        ROCK_SET.add(Blocks.COBBLESTONE_WALL);
        ROCK_SET.add(Blocks.DOUBLE_STONE_SLAB);
    }

    private int messageId;

    public HashSet<Block> getRockSet() {
        return ROCK_SET;
    }

    @Nullable
    @Override
    public IMessage onMessage(ECMessage message, MessageContext ctx) {
        IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
        thread.addScheduledTask(() -> message.process(ctx));
        return null;
    }

    private void registerMessage(Class<? extends ECMessage> message, Side side) {
        ElementalCreepers.network.registerMessage(this, message, messageId++, side);
    }

    public void preInit(FMLPreInitializationEvent event) {
        ElementalCreepers.network = NetworkRegistry.INSTANCE.newSimpleChannel(ElementalCreepers.MODID);
        registerMessage(MessageFriendlyParticles.class, Side.CLIENT);
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}