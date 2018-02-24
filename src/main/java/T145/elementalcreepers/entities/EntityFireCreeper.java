package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
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
		int radius = getPowered() ? ModConfig.explosionRadii.fireCreeperRadius * explosionPower : ModConfig.explosionRadii.fireCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					pos.setPos(posX + x, posY + y, posZ + z);

					if (Blocks.DIRT.canPlaceBlockAt(world, pos) && !Blocks.DIRT.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
						if (griefingEnabled) {
							world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						} else {
							List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius));

							for (EntityLivingBase entity : entities) {
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