package T145.elementalcreepers.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.entities.ai.EntityAIFriendlyCreeperSwell;
import T145.elementalcreepers.explosion.ExplosionFriendly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFirework;
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
import net.minecraft.entity.passive.AbstractHorse;
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
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFriendlyCreeper extends EntityTameable {

	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityFriendlyCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityFriendlyCreeper.class, DataSerializers.FLOAT);
	private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityFriendlyCreeper.class, DataSerializers.BOOLEAN);

	private float headRotationCourse;
	private float headRotationCourseOld;

	public int lastActiveTime;
	public int timeSinceIgnited;
	public int fuseTime = 30;
	public int explosionRadius = 3;
	public int droppedSkulls;

	public EntityFriendlyCreeper(World world) {
		super(world);
		setSize(0.6F, 1.7F);
		setTamed(false);
	}

	@Override
	protected void initEntityAI() {
		aiSit = new EntityAISit(this);
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

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);

		if (isTamed()) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		} else {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}

		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
		super.setAttackTarget(entitylivingbaseIn);

		if (entitylivingbaseIn == null) {
			setAngry(false);
		} else if (!isTamed()) {
			setAngry(true);
		}
	}

	@Override
	protected void updateAITasks() {
		dataManager.set(DATA_HEALTH_ID, getHealth());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(DATA_HEALTH_ID, getHealth());
		dataManager.register(BEGGING, false);
		dataManager.register(STATE, -1);
		dataManager.register(POWERED, false);
		dataManager.register(IGNITED, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Angry", isAngry());

		if (dataManager.get(POWERED)) {
			compound.setBoolean("powered", true);
		}

		compound.setShort("Fuse", (short) fuseTime);
		compound.setByte("ExplosionRadius", (byte) explosionRadius);
		compound.setBoolean("ignited", hasIgnited());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		setAngry(compound.getBoolean("Angry"));
		dataManager.set(POWERED, compound.getBoolean("powered"));

		if (compound.hasKey("Fuse", 99)) {
			fuseTime = compound.getShort("Fuse");
		}

		if (compound.hasKey("ExplosionRadius", 99)) {
			explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited")) {
			ignite();
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && getAttackTarget() == null && isAngry()) {
			setAngry(false);
		}
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

		super.onUpdate();
		headRotationCourseOld = headRotationCourse;

		if (isBegging()) {
			headRotationCourse += (1.0F - headRotationCourse) * 0.4F;
		} else {
			headRotationCourse += (0.0F - headRotationCourse) * 0.4F;
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source)) {
			return false;
		} else {
			Entity entity = source.getTrueSource();

			if (aiSit != null) {
				aiSit.setSitting(false);
			}

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
				amount = (amount + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

		if (flag) {
			applyEnchantments(this, target);
		}

		return flag;
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);

		if (tamed) {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		} else {
			getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}

		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			world.playSound(player, posX, posY, posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1.0F, rand.nextFloat() * 0.4F + 0.8F);
			player.swingArm(hand);

			if (!world.isRemote) {
				ignite();
				itemstack.damageItem(1, player);
				return true;
			}
		}

		if (isTamed()) {
			if (!itemstack.isEmpty()) {
				if (itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) itemstack.getItem();

					if (itemfood.isWolfsFavoriteMeat() && (dataManager.get(DATA_HEALTH_ID)).floatValue() < 20.0F) {
						if (!player.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}

						heal(itemfood.getHealAmount(itemstack));
						return true;
					}
				} else if (itemstack.getItem() instanceof ItemArmor) {
					ItemArmor armor = (ItemArmor) itemstack.getItem();
					int slot = armor.armorType.getSlotIndex();
					EntityEquipmentSlot equipmentSlot = EntityEquipmentSlot.fromString(armor.armorType.getName());

					if ((slot >= 1 || slot <= 4) && getItemStackFromSlot(equipmentSlot).isEmpty()) {
						setItemStackToSlot(equipmentSlot, itemstack);
						return true;
					}
				}
			}

			if (isOwner(player) && !world.isRemote && !isBreedingItem(itemstack)) {
				aiSit.setSitting(!isSitting());
				isJumping = false;
				navigator.clearPath();
				setAttackTarget((EntityLivingBase) null);
			}
		} else if (itemstack.getItem() == Items.GUNPOWDER && !isAngry()) {
			if (!player.capabilities.isCreativeMode) {
				itemstack.shrink(1);
			}

			if (!world.isRemote) {
				if (rand.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					setTamedBy(player);
					navigator.clearPath();
					setAttackTarget((EntityLivingBase) null);
					aiSit.setSitting(true);
					setHealth(20.0F);
					playTameEffect(true);
					world.setEntityState(this, (byte) 7);
				} else {
					playTameEffect(false);
					world.setEntityState(this, (byte) 6);
				}
			}

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		if (!stack.isEmpty() && stack.getItem() instanceof ItemBlock) {
			return Block.getBlockFromItem(stack.getItem()) instanceof BlockFlower;
		}
		return false;
	}

	public boolean isAngry() {
		return (dataManager.get(TAMED) & 2) != 0;
	}

	public void setAngry(boolean angry) {
		byte b0 = dataManager.get(TAMED);

		if (angry) {
			dataManager.set(TAMED, (byte) (b0 | 2));
		} else {
			dataManager.set(TAMED, (byte) (b0 & -3));
		}
	}

	@Override
	public EntityFriendlyCreeper createChild(EntityAgeable ageable) {
		EntityFriendlyCreeper creeper = new EntityFriendlyCreeper(world);
		UUID uuid = getOwnerId();

		if (uuid != null) {
			setOwnerId(uuid);
			setTamed(true);
		}

		return creeper;
	}

	public void setBegging(boolean beg) {
		dataManager.set(BEGGING, beg);
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		if (otherAnimal == this || !isTamed() || !(otherAnimal instanceof EntityFriendlyCreeper)) {
			return false;
		} else {
			EntityFriendlyCreeper creeper = (EntityFriendlyCreeper) otherAnimal;

			if (!isTamed() || isSitting()) {
				return false;
			} else {
				return isInLove() && isInLove();
			}
		}
	}

	public boolean isBegging() {
		return dataManager.get(BEGGING);
	}

	@Override
	public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
		if (target instanceof EntityFriendlyCreeper) {
			EntityFriendlyCreeper creeper = (EntityFriendlyCreeper) target;

			if (isTamed() && getOwner() == owner) {
				return false;
			}
		}

		if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
			return false;
		} else {
			return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
		}
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
			} else if (cause.getTrueSource() instanceof EntityCreeper && cause.getTrueSource() != this && ((EntityCreeper) cause.getTrueSource()).getPowered() && ((EntityCreeper) cause.getTrueSource()).ableToCauseSkullDrop()) {
				((EntityCreeper) cause.getTrueSource()).incrementDroppedSkulls();
				entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
			}
		}
	}

	public boolean getPowered() {
		return dataManager.get(POWERED);
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float renderTicks) {
		return (lastActiveTime + (timeSinceIgnited - lastActiveTime) * renderTicks) / (fuseTime - 2);
	}

	@Nullable
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
		dataManager.set(POWERED, true);
	}

	public void spawnLingeringCloud() {
		Collection<PotionEffect> collection = getActivePotionEffects();

		if (!collection.isEmpty()) {
			EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(world, posX, posY, posZ);

			cloud.setRadius(2.5F);
			cloud.setRadiusOnUse(-0.5F);
			cloud.setWaitTime(10);
			cloud.setDuration(cloud.getDuration() / 2);
			cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

			for (PotionEffect fx : collection) {
				cloud.addEffect(new PotionEffect(fx));
			}

			world.spawnEntity(cloud);
		}
	}

	public boolean hasIgnited() {
		return dataManager.get(IGNITED);
	}

	public void ignite() {
		dataManager.set(IGNITED, true);
	}

	public boolean ableToCauseSkullDrop() {
		return droppedSkulls < 1 && world.getGameRules().getBoolean("doMobLoot");
	}

	public void incrementDroppedSkulls() {
		++droppedSkulls;
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getActiveTexture() {
		String path = isTamed() ? "textures/entities/friendlycreeper1.png" : "textures/entities/friendlycreeper0.png";
		return new ResourceLocation(ElementalCreepers.MODID, path);
	}


	/////////////////////////////////////////////////////////////////////////


	public void explode() {
		if (!world.isRemote) {
			boolean canGrief = world.getGameRules().getBoolean("mobGriefing");
			int explosionPower = explosionRadius * (getPowered() ? 2 : 1);
			dead = true;
			createExplosion(explosionPower, canGrief);
			setDead();
			spawnLingeringCloud();
		}
	}

	public void createExplosion(int explosionPower, boolean canGrief) {
		if (isTamed()) {
			ExplosionFriendly explosion = new ExplosionFriendly(world, this, posX, posY, posZ, explosionRadius, canGrief);
			explosion.doExplosionA();

			world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_FIREWORK_TWINKLE, SoundCategory.BLOCKS, 0.5F, (1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F) * 0.7F);
			Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFirework.Starter(world, posX, posY + (getPowered() ? 2.5F : 0.5F), posZ, 0, 0, 0, Minecraft.getMinecraft().effectRenderer, generateTag()));
		} else {
			world.createExplosion(this, posX, posY, posZ, explosionRadius, canGrief);
		}
	}

	private NBTTagCompound generateTag() {
		NBTTagCompound fireworkTag = new NBTTagCompound();
		NBTTagCompound fireworkItemTag = new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();
		List<Integer> list = Lists.<Integer>newArrayList();

		list.add(ItemDye.DYE_COLORS[1]);
		list.add(ItemDye.DYE_COLORS[11]);
		list.add(ItemDye.DYE_COLORS[4]);

		for (int i = 0; i < rand.nextInt(3) + 3; i++) {
			list.add(ItemDye.DYE_COLORS[rand.nextInt(15)]);
		}

		int[] colours = new int[list.size()];

		for (int i = 0; i < colours.length; i++) {
			colours[i] = list.get(i);
		}

		fireworkTag.setIntArray("Colors", colours);
		fireworkTag.setBoolean("Flicker", true);
		fireworkTag.setByte("Type", (byte) (getPowered() ? 3 : 4));
		nbttaglist.appendTag(fireworkTag);
		fireworkItemTag.setTag("Explosions", nbttaglist);
		return fireworkItemTag;
	}
}