package T145.elementalcreepers.common.entities;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntitySolarCreeper extends EntityBaseCreeper {

	public EntitySolarCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		explosionRadius = 3;

		if (!world.isRemote) {
			if (world.isDaytime()) {
				if (world.canSeeSky(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY + 3.0D), MathHelper.floor(posZ)))) {
					int i1 = world.getLightFor(EnumSkyBlock.SKY, new BlockPos((int) posX, (int) posY + 3, (int) posZ)) - world.getSkylightSubtracted();
					float j = world.getCelestialAngleRadians(1.0F);

					if (j < 3.1415927F) {
						j += (0.0F - j) * 0.2F;
					} else {
						j += (6.2831855F - j) * 0.2F;
					}

					i1 = Math.round(i1 * MathHelper.cos(j));
					if (i1 < 0) {
						i1 = 0;
					}

					if (i1 > 15) {
						i1 = 15;
					}

					explosionRadius = (3 + i1 / 3);
				}
			} else {
				explosionRadius -= 1;
			}

			if ((world.isRaining()) || (world.isThundering())) {
				explosionRadius -= 1;
			}
		}

		int radius = explosionRadius * explosionPower;
		world.createExplosion(this, posX, posY, posZ, radius, griefingEnabled);

		if (getPowered() ? radius == 16 : radius == 8) {
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						BlockPos pos = new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z);
						if ((Blocks.DIRT.canPlaceBlockAt(world, pos)) && (!Blocks.DIRT.canPlaceBlockAt(world, new BlockPos((int) posX + x, (int) posY + y - 1, (int) posZ + z))) && (rand.nextBoolean())) {
							world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}
	}
}