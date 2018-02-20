package T145.elementalcreepers.common.entities;

import java.util.List;

import T145.elementalcreepers.common.ElementalCreepers;
import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFurnaceCreeper extends EntityBaseCreeper {

	public EntityFurnaceCreeper(World world) {
		super(world);
	}

	public void generateTrap(EntityPlayer player) {
		IBlockState wall = Blocks.STONEBRICK.getDefaultState();
		IBlockState gate = Blocks.IRON_BARS.getDefaultState();
		IBlockState lava = Blocks.LAVA.getDefaultState();

		if (!world.isRemote) {
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 3; y++) {
					for (int z = -1; z < 2; z++) {
						BlockPos pos = new BlockPos(player.posX + x, Math.floor(player.getEntityBoundingBox().minY + y), player.posZ + z);

						if (world.isAirBlock(pos)) {
							if (x == -1 && z == 0 && y == 1) {
								world.setBlockState(pos, gate);
							} else if (x == 0 && z == 0 && y == 0) {
								world.setBlockState(pos, lava);
							} else if (x != 0 || z != 0 || y != 1) {
								world.setBlockState(pos, wall);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.furnaceCreeperRadius * explosionPower : ConfigGeneral.furnaceCreeperRadius;
		List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius));

		if (!players.isEmpty()) {
			for (EntityPlayer player : players) {
				generateTrap(player);
			}
		}
	}
}