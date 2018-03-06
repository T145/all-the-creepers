package T145.elementalcreepers.entities;

import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFlashCreeper extends EntityBaseCreeper {

	public EntityFlashCreeper(World world) {
		super(world);
		setHealth(30F);
		fuseTime = 10;
	}

	@Override
	public boolean diesAfterExplosion() {
		return false;
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		EntityLivingBase target = getAttackTarget();

		if (!world.isRemote) {
			world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 0.1F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 2.0F);
			((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, false, posX, posY, posZ, 1, 0D, 0D, 0D, 0D);

			if (target != null) {
				double d0 = target.posX + this.world.rand.nextFloat();
				double d1 = target.posY + this.world.rand.nextFloat();
				double d2 = target.posZ + this.world.rand.nextFloat();
				double d3 = d0 - this.posX;
				double d4 = d1 - this.posY;
				double d5 = d2 - this.posZ;
				double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
				d3 = d3 / d6;
				d4 = d4 / d6;
				d5 = d5 / d6;
				double d7 = 0.5D / (d6 / explosionPower + 0.1D);
				d7 = d7 * (this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
				d3 = d3 * d7;
				d4 = d4 * d7;
				d5 = d5 * d7;
				((WorldServer) world).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, false, (d0 + this.posX) / 2.0D, (d1 + this.posY) / 2.0D, (d2 + this.posZ) / 2.0D, 1, d3, d4, d5, 0D);
				((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, false, d0, d1, d2, 1, d3, d4, d5, 0D);

				byte diffMod = 3;

				if (world.getDifficulty() == EnumDifficulty.NORMAL) {
					diffMod = 7;
				} else if (world.getDifficulty() == EnumDifficulty.HARD) {
					diffMod = 15;
				}

				target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, diffMod * 10, 0));
			}
		}
	}
}