package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.util.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCakeCreeper extends EntityBaseCreeper {

	private boolean canSpawnCake;

	public EntityCakeCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		canSpawnCake = Constants.MONTH != 10 || Constants.DAY != 12 || Constants.MONTH != 4 || Constants.DAY != 10;
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		pos.setPos(this);

		if (canSpawnCake || rand.nextInt(5) <= 2) {
			if (Blocks.CAKE.canPlaceBlockAt(world, pos)) {
				world.setBlockState(pos, Blocks.CAKE.getDefaultState(), 3);
			}

			for (EnumFacing dir : EnumFacing.HORIZONTALS) {
				BlockPos torchPos = pos.offset(dir);

				if (Blocks.TORCH.canPlaceBlockAt(world, torchPos)) {
					world.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(world, torchPos, dir, 0, 0, 0, 0, null, null), 3);
				}
			}
		}
	}
}