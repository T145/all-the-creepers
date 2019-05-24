package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.init.ModInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FireworkEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.FireworkItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FireworkCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public FireworkCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "firework";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public int getExplosionRadius() {
		return ModInit.config.fireCreeperExplosionRadius;
	}

	@Override
	public int getChargedExplosionRadius() {
		return ModInit.config.fireworkCreeperChargedExplosionRadius;
	}

	private ItemStack createFirework(int type) {
		ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
		ItemStack starFirework = new ItemStack(Items.FIREWORK_STAR);
		CompoundTag starExplosionTag = starFirework.getOrCreateSubCompoundTag("Explosion");
		DyeColor color = DyeColor.byId(random.nextInt(DyeColor.values().length));

		starExplosionTag.putIntArray("Colors", new int[] { color.getFireworkColor() });
		starExplosionTag.putByte("Type", (byte) type);

		CompoundTag fireworkTag = firework.getOrCreateSubCompoundTag("Fireworks");
		ListTag explosionTags = new ListTag();
		CompoundTag explosionTag = starFirework.getSubCompoundTag("Explosion");

		if (explosionTag != null) {
			explosionTags.add(explosionTag);
		}

		fireworkTag.putByte("Flight", (byte) 2);

		if (!explosionTags.isEmpty()) {
			fireworkTag.put("Explosions", explosionTags);
		}

		return firework;
	}

	@Override
	public void detonate(DestructionType destructionType, Explosion simpleExplosion) {
		world.spawnEntity(new FireworkEntity(world, x, y, z, createFirework(FireworkItem.Type.CREEPER.getId())));

		world.getEntities(LivingEntity.class, this.getAOE(isCharged(), x, y, z), victim -> victim != this).forEach(entity -> {
			if (entity.isAlive() && !(entity instanceof HostileEntity)) {
				FireworkEntity firework = new FireworkEntity(world, entity.x, entity.y, entity.z, createFirework(random.nextInt(5)));

				world.spawnEntity(firework);
				entity.startRiding(firework);
			}
		});
	}
}
