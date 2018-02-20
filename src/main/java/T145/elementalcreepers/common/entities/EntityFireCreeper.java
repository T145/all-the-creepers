package T145.elementalcreepers.common.entities;

import java.util.List;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFireCreeper extends EntityBaseCreeper {

	public EntityFireCreeper(World world) {
		super(world);
		this.isImmuneToFire = true;
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.fireCreeperRadius * explosionPower : ConfigGeneral.fireCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);

					if (Blocks.DIRT.canPlaceBlockAt(world, pos) && !Blocks.DIRT.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
						if (griefingEnabled) {
							world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						} else {
							List<EntityLivingBase> entityList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(radius, radius, radius));

							for (EntityLivingBase entity : entityList) {
								if (entity != null) {
									entity.setFire(500);
								}
							}
						}
					}
				}
			}
		}
	}
}