package T145.elementalcreepers.entities;

import java.util.Calendar;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCakeCreeper extends EntityBaseCreeper {

	private boolean canSpawnCake;

	public EntityCakeCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		int month = Calendar.getInstance().get(2);
		int day = Calendar.getInstance().get(5);
		canSpawnCake = month != 10 || day != 12 || month != 4 || day != 10;
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int x = MathHelper.floor(posX);
		int y = MathHelper.floor(posY);
		int z = MathHelper.floor(posZ);
		BlockPos pos = new BlockPos(x, y, z);

		if (canSpawnCake || rand.nextInt(5) <= 2) {
			if (Blocks.CAKE.canPlaceBlockAt(world, pos)) {
				world.setBlockState(pos, Blocks.CAKE.getDefaultState(), 3);
			}

			BlockPos torchPos = pos.add(1, 0, 0);

			if (Blocks.TORCH.canPlaceBlockAt(world, torchPos)) {
				world.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(world, torchPos, EnumFacing.EAST, 0, 0, 0, 0, null, null), 3);
			}

			torchPos = pos.add(-1, 0, 0);

			if (Blocks.TORCH.canPlaceBlockAt(world, torchPos)) {
				world.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(world, torchPos, EnumFacing.WEST, 0, 0, 0, 0, null, null), 3);
			}

			torchPos = pos.add(0, 0, 1);

			if (Blocks.TORCH.canPlaceBlockAt(world, torchPos)) {
				world.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(world, torchPos, EnumFacing.SOUTH, 0, 0, 0, 0, null, null), 3);
			}

			torchPos = pos.add(0, 0, -1);

			if (Blocks.TORCH.canPlaceBlockAt(world, torchPos)) {
				world.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(world, torchPos, EnumFacing.NORTH, 0, 0, 0, 0, null, null), 3);
			}
		}
	}
}