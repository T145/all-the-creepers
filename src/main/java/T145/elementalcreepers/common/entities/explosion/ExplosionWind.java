package T145.elementalcreepers.common.entities.explosion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExplosionWind {

	public boolean isFlaming;
	public boolean isSmoking = true;
	public double explosionX;
	public double explosionY;
	public double explosionZ;
	public Entity exploder;
	public float explosionSize;
	public int explosionPower;
	public List affectedBlockPositions = new ArrayList();
	private int field_77289_h = 16;
	private Random explosionRNG = new Random();
	private World worldObj;
	private Map field_77288_k = new HashMap();

	public ExplosionWind(World world, Entity entity, double xPos, double yPos, double zPos, float size, int power) {
		worldObj = world;
		exploder = entity;
		explosionSize = size;
		explosionX = xPos;
		explosionY = yPos;
		explosionZ = zPos;
		explosionPower = power;
	}

	public void doExplosionA() {
		float f = explosionSize;

		for (int i = 0; i < field_77289_h; i++) {
			for (int j = 0; j < field_77289_h; j++) {
				for (int k = 0; k < field_77289_h; k++) {
					if ((i == 0) || (i == field_77289_h - 1) || (j == 0) || (j == field_77289_h - 1) || (k == 0) || (k == field_77289_h - 1)) {
						double d3 = i / (field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d4 = j / (field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d5 = k / (field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
						d3 /= d6;
						d4 /= d6;
						d5 /= d6;
						float f1 = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
						double d0 = explosionX;
						double d1 = explosionY;
						double d2 = explosionZ;

						for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
							d0 += d3 * f2;
							d1 += d4 * f2;
							d2 += d5 * f2;
						}
					}
				}
			}
		}

		explosionSize *= 2.0F;
		int i = MathHelper.floor(explosionX - explosionSize - 1.0D);
		int j = MathHelper.floor(explosionX + explosionSize + 1.0D);
		int k = MathHelper.floor(explosionY - explosionSize - 1.0D);
		int l1 = MathHelper.floor(explosionY + explosionSize + 1.0D);
		int i2 = MathHelper.floor(explosionZ - explosionSize - 1.0D);
		int j2 = MathHelper.floor(explosionZ + explosionSize + 1.0D);
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, new AxisAlignedBB(i, k, i2, j, l1, j2));
		Vec3d vec3 = new Vec3d(explosionX, explosionY, explosionZ);

		for (int k2 = 0; k2 < list.size(); k2++) {
			Entity entity = (Entity) list.get(k2);
			double d7 = entity.getDistance(explosionX, explosionY, explosionZ) / explosionSize;

			if (d7 <= 1.0D) {
				double d0 = entity.posX - explosionX;
				double d1 = entity.posY + entity.getEyeHeight() - explosionY;
				double d2 = entity.posZ - explosionZ;
				double d8 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

				if (d8 != 0.0D) {
					d0 /= d8;
					d1 /= d8;
					d2 /= d8;
					double d9 = worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
					double d10 = (1.0D - d7) * d9;
					double d11 = entity instanceof EntityLivingBase ? EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10) : 1;
					entity.attackEntityFrom(DamageSource.GENERIC, 1.0E-4F);
					entity.motionX += d0 * d11 * explosionPower;
					entity.motionY += d1 * d11 * 1.01D;
					entity.motionZ += d2 * d11 * explosionPower;

					if ((entity instanceof EntityPlayer)) {
						field_77288_k.put(entity, new Vec3d(d0 * d10, d1 * d10, d2 * d10));
					}
				}
			}
		}

		explosionSize = f;
	}
}