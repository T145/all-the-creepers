package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityCreeperFire extends EntityCreeperElemental {

	public EntityCreeperFire(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.fire;
		this.isImmuneToFire = true;
	}

	@Override
	public void detonate() {
		float radius = this.getExplosionRadius();

		for (float x = -radius; x <= radius; ++x) {
			for (float y = -radius; y <= radius; ++y) {
				for (float z = -radius; z <= radius; ++z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					if (Blocks.FIRE.canPlaceBlockAt(world, pos) && rand.nextBoolean()) {
						if (ForgeEventFactory.getMobGriefingEvent(world, this)) {
							world.setBlockState(pos, Blocks.FIRE.getDefaultState());
						} else {
							for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, getAOE(), entity -> entity != this)) {
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
