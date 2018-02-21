package T145.elementalcreepers.entities;

import java.util.Collections;
import java.util.HashSet;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class EntityStoneCreeper extends EntityBaseCreeper {

	private HashSet<Block> rocks = new HashSet<>();

	public EntityStoneCreeper(World world) {
		super(world);

		for (ItemStack stack : OreDictionary.getOres("cobblestone")) {
			rocks.add(Block.getBlockFromItem(stack.getItem()));
		}

		for (ItemStack stack : OreDictionary.getOres("stone")) {
			rocks.add(Block.getBlockFromItem(stack.getItem()));
		}

		rocks.add(Blocks.MOSSY_COBBLESTONE);
		rocks.add(Blocks.STONE_BRICK_STAIRS);
		rocks.add(Blocks.STONE_BUTTON);
		rocks.add(Blocks.STONE_PRESSURE_PLATE);
		rocks.add(Blocks.STONE_STAIRS);
		rocks.add(Blocks.STONEBRICK);
		rocks.add(Blocks.STONE_SLAB);
		rocks.add(Blocks.COBBLESTONE_WALL);
		rocks.add(Blocks.DOUBLE_STONE_SLAB);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ModConfig.stoneCreeperRadius * explosionPower : ModConfig.stoneCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
					IBlockState blockState = world.getBlockState(pos);

					if (blockState != null && blockState.getBlock() != null) {
						Block rock = blockState.getBlock();

						if (rock != null && rocks.contains(rock) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius) {
							rock.dropBlockAsItem(world, pos, blockState, 0);
							world.setBlockToAir(pos);
							rock.onBlockDestroyedByExplosion(world, pos, new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, Collections.emptyList()));
						}
					}
				}
			}
		}
	}
}