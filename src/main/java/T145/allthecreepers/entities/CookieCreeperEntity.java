package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class CookieCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public CookieCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "cookie";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		for (int i = 0; i < 5; ++i) {
			ItemEntity cookie = new ItemEntity(world, x, y, z, new ItemStack(Items.COOKIE));
			world.spawnEntity(cookie);
			cookie.addVelocity(0D, 0.4D, 0D);
		}
	}
}
