package T145.elementalcreepers.entities;

import java.util.Collections;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityStoneCreeper extends EntityBaseCreeper {

	public EntityStoneCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ModConfig.explosionRadii.stoneCreeperRadius * explosionPower : ModConfig.explosionRadii.stoneCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
					IBlockState state = world.getBlockState(pos);

					if (state != null && state.getBlock() != null) {
						Block rock = state.getBlock();

						if (rock != null && Constants.ROCK_SET.contains(rock) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius) {
							rock.dropBlockAsItem(world, pos, state, 0);
							world.setBlockToAir(pos);
							rock.onBlockDestroyedByExplosion(world, pos, new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, Collections.emptyList()));
						}
					}
				}
			}
		}
	}
}