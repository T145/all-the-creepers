package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCreeperAir extends EntityCreeperElemental {

	public EntityCreeperAir(World world) {
		super(world);
		this.explosionRadius = ModConfig.EXPLOSION_RADII.wind;
	}

	@Override
	public void detonate() {
		for (Entity entity : world.getEntitiesWithinAABB(EntityLivingBase.class, getAOE(), entity -> entity != this)) {
			if (!entity.isImmuneToExplosions()) {
				float radius = this.getExplosionRadius();
				double scale = entity.getDistance(posX, posY, posZ) / radius;

				if (scale <= 1.0D) {
					double x = entity.posX - posX;
					double y = entity.posY + entity.getEyeHeight() - posY;
					double z = entity.posZ = posZ;
					double div = MathHelper.sqrt(x * x + y * y + z * z);

					if (div != 0.0D) {
						x /= div;
						y /= div;
						z /= div;

						Vec3d vec = new Vec3d(posX, posY, posZ);
						double density = world.getBlockDensity(vec, entity.getEntityBoundingBox());
						double densityScale = (1.0D - scale) * density;
						double protec = densityScale;

						if (entity instanceof EntityLivingBase) {
							protec = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, densityScale);
						}

						entity.motionX += x * protec;
						entity.motionY += y * protec * radius;
						entity.motionZ += z * protec;

						// knock back player if necessary
					}
				}
			}
		}
	}
}
