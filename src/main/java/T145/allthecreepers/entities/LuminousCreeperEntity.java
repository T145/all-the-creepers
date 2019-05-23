package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class LuminousCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public LuminousCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "luminous";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		this.specialBlast(radius, Blocks.GLOWSTONE.getDefaultState(), this, false);
	}
}

