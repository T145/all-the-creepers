package T145.elementalcreepers.entities.base;

import java.lang.reflect.InvocationTargetException;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public abstract class EntityBaseCreeper extends EntityCreeper {

	protected final MutableBlockPos pos = new MutableBlockPos();

	public EntityBaseCreeper(World world) {
		super(world);
	}

	public void explode() {
		if (!world.isRemote) {
			boolean griefingEnabled = world.getGameRules().getBoolean("mobGriefing");
			int explosionPower = getPowered() ? 2: 1;

			createExplosion(explosionPower, griefingEnabled);

			if (diesAfterExplosion()) {
				dead = true;
				setDead();
			}

			// NOTE: May need some packet handling here to generate the particles
			try {
				ReflectionHelper.findMethod(EntityCreeper.class, "spawnLingeringCloud", "func_190741_do").invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException err) {
				ElementalCreepers.LOG.catching(err);
			}
		}
	}

	public abstract void createExplosion(int explosionPower, boolean griefingEnabled);

	public boolean diesAfterExplosion() {
		return true;
	}

	@Override
	public void onUpdate() {
		if (isEntityAlive()) {
			int timeSinceIgnited = ReflectionHelper.getPrivateValue(EntityCreeper.class, this, "timeSinceIgnited", "field_70833_d");
			ReflectionHelper.setPrivateValue(EntityCreeper.class, this, timeSinceIgnited, "lastActiveTime", "field_70834_e");

			if (hasIgnited()) {
				setCreeperState(1);
			}

			int i = getCreeperState();

			if (i > 0 && timeSinceIgnited == 0) {
				playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
			}

			timeSinceIgnited += i;

			if (timeSinceIgnited < 0) {
				timeSinceIgnited = 0;
			}

			int fuseTime = ReflectionHelper.getPrivateValue(EntityCreeper.class, this, "fuseTime", "field_82225_f");

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;
				explode();
			}

			ReflectionHelper.setPrivateValue(EntityCreeper.class, this, timeSinceIgnited, "timeSinceIgnited", "field_70833_d");
		}

		onNormalUpdate();
	}

	public void onNormalUpdate() {
		if (ForgeHooks.onLivingUpdate(this)) {
			return;
		}

		if (!world.isRemote) {
			setFlag(6, isGlowing());
		}

		onEntityUpdate();
		updateActiveHand();

		if (!world.isRemote) {
			int i = getArrowCountInEntity();

			if (i > 0) {
				if (arrowHitTimer <= 0) {
					arrowHitTimer = 20 * (30 - i);
				}

				--arrowHitTimer;

				if (arrowHitTimer <= 0) {
					setArrowCountInEntity(i - 1);
				}
			}

			NonNullList<ItemStack> handInventory = (NonNullList<ItemStack>) ReflectionHelper.getPrivateValue(EntityLivingBase.class, this, "handInventory", "field_184630_bs");
			NonNullList<ItemStack> armorArray = (NonNullList<ItemStack>) ReflectionHelper.getPrivateValue(EntityLivingBase.class, this, "armorArray", "field_184631_bt");

			for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
				ItemStack itemstack;

				switch (entityequipmentslot.getSlotType()) {
				case HAND:
					itemstack = handInventory.get(entityequipmentslot.getIndex());
					break;
				case ARMOR:
					itemstack = armorArray.get(entityequipmentslot.getIndex());
					break;
				default:
					continue;
				}

				ItemStack itemstack1 = getItemStackFromSlot(entityequipmentslot);

				if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
					if (!ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack)) {
						((WorldServer) world).getEntityTracker().sendToTracking(this, new SPacketEntityEquipment(getEntityId(), entityequipmentslot, itemstack1));
					}

					MinecraftForge.EVENT_BUS.post(new LivingEquipmentChangeEvent(this, entityequipmentslot, itemstack, itemstack1));

					if (!itemstack.isEmpty()) {
						getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers(entityequipmentslot));
					}

					if (!itemstack1.isEmpty()) {
						getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers(entityequipmentslot));
					}

					switch (entityequipmentslot.getSlotType()) {
					case HAND:
						handInventory.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
						break;
					case ARMOR:
						armorArray.set(entityequipmentslot.getIndex(), itemstack1.isEmpty() ? ItemStack.EMPTY : itemstack1.copy());
						// break;
					}
				}

				// write the inv lists back to their private values after modification
				ReflectionHelper.setPrivateValue(EntityLivingBase.class, this, handInventory, "handInventory", "field_184630_bs");
				ReflectionHelper.setPrivateValue(EntityLivingBase.class, this, armorArray, "armorArray", "field_184631_bt");
			}

			if (ticksExisted % 20 == 0) {
				getCombatTracker().reset();
			}

			if (!glowing) {
				boolean flag = isPotionActive(MobEffects.GLOWING);

				if (getFlag(6) != flag) {
					setFlag(6, flag);
				}
			}
		}

		onLivingUpdate();
		double d0 = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f3 = (float) (d0 * d0 + d1 * d1);
		float f4 = renderYawOffset;
		float f5 = 0.0F;
		prevOnGroundSpeedFactor = onGroundSpeedFactor;
		float f = 0.0F;

		if (f3 > 0.0025000002F) {
			f = 1.0F;
			f5 = (float) Math.sqrt((double) f3) * 3.0F;
			float f1 = (float) MathHelper.atan2(d1, d0) * (180F / (float) Math.PI) - 90.0F;
			float f2 = MathHelper.abs(MathHelper.wrapDegrees(rotationYaw) - f1);

			if (95.0F < f2 && f2 < 265.0F) {
				f4 = f1 - 180.0F;
			} else {
				f4 = f1;
			}
		}

		if (swingProgress > 0.0F) {
			f4 = rotationYaw;
		}

		if (!onGround) {
			f = 0.0F;
		}

		onGroundSpeedFactor += (f - onGroundSpeedFactor) * 0.3F;
		world.profiler.startSection("headTurn");
		f5 = updateDistance(f4, f5);
		world.profiler.endSection();
		world.profiler.startSection("rangeChecks");

		while (rotationYaw - prevRotationYaw < -180.0F) {
			prevRotationYaw -= 360.0F;
		}

		while (rotationYaw - prevRotationYaw >= 180.0F) {
			prevRotationYaw += 360.0F;
		}

		while (renderYawOffset - prevRenderYawOffset < -180.0F) {
			prevRenderYawOffset -= 360.0F;
		}

		while (renderYawOffset - prevRenderYawOffset >= 180.0F) {
			prevRenderYawOffset += 360.0F;
		}

		while (rotationPitch - prevRotationPitch < -180.0F) {
			prevRotationPitch -= 360.0F;
		}

		while (rotationPitch - prevRotationPitch >= 180.0F) {
			prevRotationPitch += 360.0F;
		}

		while (rotationYawHead - prevRotationYawHead < -180.0F) {
			prevRotationYawHead -= 360.0F;
		}

		while (rotationYawHead - prevRotationYawHead >= 180.0F) {
			prevRotationYawHead += 360.0F;
		}

		world.profiler.endSection();
		movedDistance += f5;

		if (isElytraFlying()) {
			++ticksElytraFlying;
		} else {
			ticksElytraFlying = 0;
		}

		if (!world.isRemote) {
			updateLeashedState();

			if (ticksExisted % 5 == 0) {
				boolean flag = !(getControllingPassenger() instanceof EntityLiving);
				boolean flag1 = !(getRidingEntity() instanceof EntityBoat);
				tasks.setControlFlag(1, flag);
				tasks.setControlFlag(4, flag && flag1);
				tasks.setControlFlag(2, flag);
			}
		}

		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	public void domeExplosion(int radius, Block block, int meta) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					pos.setPos(posX + x, posY + y, posZ + z);

					if (block.canPlaceBlockAt(world, pos) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && rand.nextInt(4) < 3) {
						world.setBlockState(pos, block.getStateFromMeta(meta), 2);
					}
				}
			}
		}
	}

	public void domeExplosion(int radius, Block block) {
		domeExplosion(radius, block, 0);
	}

	public void wildExplosion(int radius, Block block, int meta) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					pos.setPos(posX + x, posY + y, posZ + z);

					if (block.canPlaceBlockAt(world, pos) && !block.canPlaceBlockAt(world, new BlockPos(posX + x, posY + y - 1, posZ + z)) && rand.nextBoolean()) {
						world.setBlockState(pos, block.getStateFromMeta(meta), 2);
					}
				}
			}
		}
	}

	public void wildExplosion(int radius, Block block) {
		wildExplosion(radius, block, 0);
	}
}