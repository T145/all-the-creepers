package T145.elementalcreepers.lib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Constants {

	private Constants() {}

	public static final HashSet<Block> ROCK_SET = new HashSet<>();
	public static final List<Class<? extends EntityCreeper> > CREEPER_LIST = new ArrayList<>();
	public static final List<String> TEXTURE_LIST = new ArrayList<>();

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

		CREEPER_LIST.add(EntityCreeper.class);
	}
}