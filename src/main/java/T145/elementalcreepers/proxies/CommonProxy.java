package T145.elementalcreepers.proxies;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashSet;

public class CommonProxy {

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

    public HashSet<Block> getRockSet() {
        return ROCK_SET;
    }

    public void preInit(FMLPreInitializationEvent event) { }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}