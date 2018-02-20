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

public class ExplosionPsychic {

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

	public ExplosionPsychic(World world, Entity entity, double xPos, double yPos, double zPos, float size, int power) {
		this.worldObj = world;
		this.exploder = entity;
		this.explosionSize = size;
		this.explosionX = xPos;
		this.explosionY = yPos;
		this.explosionZ = zPos;
		this.explosionPower = power;
	}

	public void doExplosionA() {
		float f = this.explosionSize;
		for (int i = 0; i < this.field_77289_h; i++) {
			for (int j = 0; j < this.field_77289_h; j++) {
				for (int k = 0; k < this.field_77289_h; k++) {
					if ((i == 0) || (i == this.field_77289_h - 1) || (j == 0) || (j == this.field_77289_h - 1)
							|| (k == 0) || (k == this.field_77289_h - 1)) {
						double d3 = i / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d4 = j / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d5 = k / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
						double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
						d3 /= d6;
						d4 /= d6;
						d5 /= d6;
						float f1 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
						double d0 = this.explosionX;
						double d1 = this.explosionY;
						double d2 = this.explosionZ;
						for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
							d0 += d3 * f2;
							d1 += d4 * f2;
							d2 += d5 * f2;
						}
					}
				}
			}
		}
		this.explosionSize *= 2.0F;
		int i = MathHelper.floor(this.explosionX - this.explosionSize - 1.0D);
		int j = MathHelper.floor(this.explosionX + this.explosionSize + 1.0D);
		int k = MathHelper.floor(this.explosionY - this.explosionSize - 1.0D);
		int l1 = MathHelper.floor(this.explosionY + this.explosionSize + 1.0D);
		int i2 = MathHelper.floor(this.explosionZ - this.explosionSize - 1.0D);
		int j2 = MathHelper.floor(this.explosionZ + this.explosionSize + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(i, k, i2, j, l1, j2));
		Vec3d vec3 = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);
		for (int k2 = 0; k2 < list.size(); k2++) {
			Entity entity = (Entity) list.get(k2);
			double d7 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / this.explosionSize;
			if (d7 <= 1.0D) {
				double d0 = entity.posX - this.explosionX;
				double d1 = entity.posY + entity.getEyeHeight() - this.explosionY;
				double d2 = entity.posZ - this.explosionZ;
				double d8 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				if (d8 != 0.0D) {
					d0 /= d8;
					d1 /= d8;
					d2 /= d8;
					double d9 = this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
					double d10 = (1.0D - d7) * d9;
					double d11 = entity instanceof EntityLivingBase ? EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10) : 1;
					entity.attackEntityFrom(DamageSource.GENERIC, 1.0E-4F);
					entity.motionX += d0 * d11;
					entity.motionY += d1 * d11 * this.explosionPower;
					entity.motionZ += d2 * d11;
					if ((entity instanceof EntityPlayer)) {
						this.field_77288_k.put(entity, new Vec3d(d0 * d10, d1 * d10, d2 * d10));
					}
				}
			}
		}
		this.explosionSize = f;
	}
}