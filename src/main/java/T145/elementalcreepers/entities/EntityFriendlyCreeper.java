package T145.elementalcreepers.entities;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.Nullable;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.api.IEntityCreeper;
import T145.elementalcreepers.entities.ai.EntityAIFriendlyCreeperSwell;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFriendlyCreeper extends EntityTameable implements IEntityCreeper {

	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityFriendlyCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ANGRY = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 30;
	private int explosionRadius = 3;
	private int droppedSkulls;
	private float headRotationCourse;
	private float headRotationCourseOld;

	public EntityFriendlyCreeper(World world) {
		super(world);
		setSize(0.6F, 1.7F);
		setTamed(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * BEGIN EntityCreeper.class
	 * 
	 * @see net.minecraft.entity.monster.EntityCreeper
	 */

	protected void initEntityAI() {
		this.aiSit = new EntityAISit(this);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAIFriendlyCreeperSwell(this));
		tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
		tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		tasks.addTask(7, new EntityAIMate(this, 1.0D));
		tasks.addTask(8, new EntityAIWander(this, 1.0D));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(10, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	public int getMaxFallHeight() {
		return getAttackTarget() == null ? 3 : 3 + (int) (getHealth() - 1.0F);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		super.fall(distance, damageMultiplier);
		timeSinceIgnited = (int) (timeSinceIgnited + distance * 1.5F);

		if (timeSinceIgnited > fuseTime - 5) {
			timeSinceIgnited = fuseTime - 5;
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(STATE, -1);
		dataManager.register(POWERED, false);
		dataManager.register(IGNITED, false);
		dataManager.register(ANGRY, false);
		dataManager.register(BEGGING, Boolean.valueOf(false));
	}

	public static void registerFixesCreeper(DataFixer fixer) {
		EntityCreeper.registerFixesCreeper(fixer);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (((Boolean) dataManager.get(POWERED)).booleanValue()) {
			compound.setBoolean("powered", true);
		}

		compound.setShort("Fuse", (short) fuseTime);
		compound.setByte("ExplosionRadius", (byte) explosionRadius);
		compound.setBoolean("ignited", hasIgnited());
		compound.setBoolean("angry", isAngry());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		dataManager.set(POWERED, Boolean.valueOf(compound.getBoolean("powered")));

		if (compound.hasKey("Fuse", 99)) {
			fuseTime = compound.getShort("Fuse");
		}

		if (compound.hasKey("ExplosionRadius", 99)) {
			explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited")) {
			ignite();
		}

		setAngry(compound.getBoolean("angry"));
	}

	@Override
	public void onUpdate() {
		if (isEntityAlive()) {
			lastActiveTime = timeSinceIgnited;

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

			if (timeSinceIgnited >= fuseTime) {
				timeSinceIgnited = fuseTime;
				explode();
			}
		}

		if (isSitting()) {
			rotationPitch = 45.0F;
		}

		super.onUpdate();

		headRotationCourseOld = headRotationCourse;

		if (isBegging()) {
			headRotationCourse += (1.0F - headRotationCourse) * 0.4F;
		} else {
			headRotationCourse += (0.0F - headRotationCourse) * 0.4F;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (world.getGameRules().getBoolean("doMobLoot")) {
			if (cause.getTrueSource() instanceof EntitySkeleton) {
				int i = Item.getIdFromItem(Items.RECORD_13);
				int j = Item.getIdFromItem(Items.RECORD_WAIT);
				int k = i + rand.nextInt(j - i + 1);
				dropItem(Item.getItemById(k), 1);
			} else if (cause.getTrueSource() instanceof EntityFriendlyCreeper && cause.getTrueSource() != this && ((EntityFriendlyCreeper) cause.getTrueSource()).getPowered() && ((EntityFriendlyCreeper) cause.getTrueSource()).ableToCauseSkullDrop()) {
				((EntityFriendlyCreeper) cause.getTrueSource()).incrementDroppedSkulls();
				entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
			}
		}
	}

	public boolean getPowered() {
		return dataManager.get(POWERED);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public float getCreeperFlashIntensity(float intensity) {
		return (lastActiveTime + (timeSinceIgnited - lastActiveTime) * intensity) / (fuseTime - 2);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableList.ENTITIES_CREEPER;
	}

	public int getCreeperState() {
		return dataManager.get(STATE);
	}

	public void setCreeperState(int state) {
		dataManager.set(STATE, state);
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) {
		super.onStruckByLightning(lightningBolt);
		dataManager.set(POWERED, Boolean.valueOf(true));
	}

	private void explode() {
		if (!world.isRemote) {
			boolean flag = world.getGameRules().getBoolean("mobGriefing");
			float f = getPowered() ? 2.0F : 1.0F;
			dead = true;
			createExplosion(explosionRadius * f, flag);
			setDead();
			spawnLingeringCloud();
		}
	}

	private void spawnLingeringCloud() {
		Collection<PotionEffect> potionfx = getActivePotionEffects();

		if (!potionfx.isEmpty()) {
			EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(world, posX, posY, posZ);
			cloud.setRadius(2.5F);
			cloud.setRadiusOnUse(-0.5F);
			cloud.setWaitTime(10);
			cloud.setDuration(cloud.getDuration() / 2);
			cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

			for (PotionEffect potion : potionfx) {
				cloud.addEffect(new PotionEffect(potion));
			}

			world.spawnEntity(cloud);
		}
	}

	public boolean hasIgnited() {
		return dataManager.get(IGNITED).booleanValue();
	}

	public void ignite() {
		dataManager.set(IGNITED, Boolean.valueOf(true));
	}

	public boolean ableToCauseSkullDrop() {
		return droppedSkulls < 1 && world.getGameRules().getBoolean("doMobLoot");
	}

	public void incrementDroppedSkulls() {
		++droppedSkulls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * END EntityCreeper.class
	 * 
	 * @see net.minecraft.entity.monster.EntityCreeper
	 */

	public boolean isAngry() {
		return dataManager.get(ANGRY);
	}

	public void setAngry(boolean angry) {
		dataManager.set(ANGRY, angry);
	}

	public boolean isBegging() {
		return dataManager.get(BEGGING);
	}

	public void setBegging(boolean beg) {
		dataManager.set(BEGGING, Boolean.valueOf(beg));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && getAttackTarget() == null && isAngry()) {
			setAngry(false);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);

		if (isTamed()) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		} else {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}
	}

	@Override
	public void setAttackTarget(EntityLivingBase target) {
		super.setAttackTarget(target);

		if (target == null) {
			setAngry(false);
		} else if (!isTamed()) {
			setAngry(true);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source)) {
			return false;
		}

		Entity entity = source.getTrueSource();

		if (aiSit != null) {
			aiSit.setSitting(false);
		}

		if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
			amount = (amount + 1) / 2;
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		int i = isTamed() ? 4 : 2;
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
	}

	public void createExplosion(float explosionRadius, boolean griefingEnabled) {
		if (isTamed()) {
			// do friendly explosion
		} else {
			world.createExplosion(this, posX, posY, posZ, explosionRadius, griefingEnabled);
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		if (!stack.isEmpty() && stack.getItem() instanceof ItemBlock) {
			return Block.getBlockFromItem(stack.getItem()) instanceof BlockFlower;
		}
		return false;
	}

	@Override
	public boolean canMateWith(EntityAnimal tameable) {
		if (tameable == this || !isTamed() || !(tameable instanceof EntityFriendlyCreeper)) {
			return false;
		}

		EntityFriendlyCreeper creeper = (EntityFriendlyCreeper) tameable;
		return creeper.isTamed();
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityAgeable) {
		EntityFriendlyCreeper child = new EntityFriendlyCreeper(world);
		UUID id = getOwnerId();

		if (id != null) {
			child.setOwnerId(id);
			child.setTamed(true);
		}

		return child;
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);

		if (tamed) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		} else {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}
	}

	public void shrinkStack(EntityPlayer player, ItemStack stack) {
		if (!player.capabilities.isCreativeMode) {
			stack.shrink(1);

			if (stack.isEmpty()) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote && !stack.isEmpty() && stack.getItem() == Items.SPAWN_EGG) {
			EntityAgeable baby = createChild(this);

			if (baby != null) {
				baby.setGrowingAge(41536);
				baby.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
				world.spawnEntity(baby);

				if (stack.hasDisplayName()) {
					baby.setCustomNameTag(stack.getDisplayName());
				}

				shrinkStack(player, stack);
				return true;
			}
		}

		if (isTamed()) {
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) stack.getItem();

					if (getHealth() < 20.0F) {
						heal(itemfood.getHealAmount(stack));
						shrinkStack(player, stack);
						return true;
					}
				} else if (stack.getItem() instanceof ItemArmor) {
					ItemArmor armor = (ItemArmor) stack.getItem();
					int slot = armor.armorType.getSlotIndex();
					EntityEquipmentSlot equipmentSlot = EntityEquipmentSlot.fromString(armor.armorType.getName());

					if ((slot >= 1 || slot <= 4) && getItemStackFromSlot(equipmentSlot).isEmpty()) {
						setItemStackToSlot(equipmentSlot, stack);
						return true;
					}
				}
			}

			if (isOwner(player) && !world.isRemote && !isBreedingItem(stack)) {
				aiSit.setSitting(!isSitting());
				isJumping = false;
				setAttackTarget(null);
				setRevengeTarget(null);
			}
		} else if (!stack.isEmpty()) {

			// base creeper interaction
			if (stack.getItem() == Items.FLINT_AND_STEEL) {
				world.playSound(player, posX, posY, posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, rand.nextFloat() * 0.4F + 0.8F);
				player.swingArm(hand);

				if (!world.isRemote) {
					ignite();
					stack.damageItem(1, player);
					return true;
				}
			}

			if (stack.getItem() == Items.GUNPOWDER && !isAngry()) {
				shrinkStack(player, stack);

				if (!world.isRemote) {
					if (rand.nextInt(3) == 0) {
						setTamed(true);
						setAttackTarget(null);
						aiSit.setSitting(true);
						setHealth(20.0F);
						setOwnerId(player.getUniqueID());
						playTameEffect(true);
						world.setEntityState(this, (byte) 7);
					} else {
						playTameEffect(false);
						world.setEntityState(this, (byte) 6);
					}
				}
			}
		}

		return super.processInteract(player, hand);
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getActiveTexture() {
		String path = isTamed() ? "textures/entities/friendlycreeper1.png" : "textures/entities/friendlycreeper0.png";
		return new ResourceLocation(ElementalCreepers.MODID, path);
	}
}