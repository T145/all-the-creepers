package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.utils.HolidayUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCakeCreeper extends EntityBaseCreeper {

	public EntityCakeCreeper(World world) {
		super(world);
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		pos.setPos(this);

		if (!HolidayUtils.isAprilFools() || rand.nextInt(5) <= 2) {
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