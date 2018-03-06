package T145.elementalcreepers.entities;

import java.util.List;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import T145.elementalcreepers.lib.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMagicCreeper extends EntityBaseCreeper {

	/*
	 * Potential features:
	 * - Buff nearby hostile mobs w/ good potion effects
	 * - Throw random potions at the player until w/in a certain horizontal range; then descend and explode
	 */

	private double acceleration = 0.0D;

	public EntityMagicCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		if (world.isRemote) {
			for (int i = 0; i < 2; ++i) {
				world.spawnParticle(onGround || getAttackTarget() != null ? EnumParticleTypes.CRIT_MAGIC : EnumParticleTypes.CRIT, posX + (rand.nextDouble() * 2.0D - 1.0D) * width, posY + rand.nextDouble() * height + 0.1D, posZ + (rand.nextDouble() * 2.0D - 1.0D) * width, 0.0D, 0.0D, 0.0D);
			}
		} else if (getCreeperState() < 1) {
			acceleration += 0.1D;

			if (acceleration >= 360.0D) {
				acceleration = 0.0D;
			}

			Entity target = getAttackTarget();
			double modY = 0.04D * Math.sin(acceleration);

			if (target != null) {
				setNoGravity(false);

				if (!onGround) {
					motionY -= modY;
				}

				double d0 = target.posX - posX;
				double d1 = target.posZ - posZ;
				double d3 = d0 * d0 + d1 * d1;

				if (d3 > 9.0D) {
					double d5 = (double) MathHelper.sqrt(d3);
					motionX += (d0 / d5 * 0.5D - motionX) * 0.6D;
					motionZ += (d1 / d5 * 0.5D - motionZ) * 0.6D;
				}
			} else {
				boolean canAscend = !atMaxRangeFromGround();

				setNoGravity(canAscend);

				if (canAscend) {
					motionY += modY;
				}

				if (!hasNoGravity()) {
					motionY = 0.0D;
				}
			}
		}

		if (motionX * motionX + motionZ * motionZ > 0.05D) {
			rotationYaw = (float) MathHelper.atan2(motionZ, motionX) * (180F / (float) Math.PI) - 90.0F;
		}

		super.onLivingUpdate();
	}

	private boolean atMaxRangeFromGround() {
		boolean b1 = world.isAirBlock(pos.setPos(posX, posY, posZ).down());
		boolean b2 = world.isAirBlock(pos.down(2));
		boolean b3 = world.isAirBlock(pos.down(3));
		boolean b4 = world.getBlockState(pos.down(4)).getMaterial().isSolid();
		return b1 && b2 && b3 && b4;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	public Potion getMagicCreeperPotion() {
		return Constants.HARMFUL_POTIONS[rand.nextInt(Constants.HARMFUL_POTIONS.length)];
	}

	private void castRandomSpell(EntityLivingBase target) {
		byte durMod = 10;

		if (world.getDifficulty() == EnumDifficulty.NORMAL) {
			durMod = 15;
		} else if (world.getDifficulty() == EnumDifficulty.HARD) {
			durMod = 20;
		}

		target.addPotionEffect(new PotionEffect(getMagicCreeperPotion(), durMod * 30, 0));
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		int radius = getPowered() ? ModConfig.explosionRadii.magicCreeperRadius * explosionPower : ModConfig.explosionRadii.magicCreeperRadius;
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, getAreaOfEffect(radius), entity -> entity != this);

		if (!entities.isEmpty()) {
			for (EntityLivingBase entity : entities) {
				castRandomSpell(entity);
			}
		}

		//world.createExplosion(this, posX, posY, posZ, explosionPower, canGrief);
	}
}