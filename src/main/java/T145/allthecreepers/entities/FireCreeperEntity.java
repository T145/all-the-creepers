package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FireCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public FireCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "fire";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + this.x, y + this.y, z + this.z);

					BlockState state = world.getBlockState(POS);

					if (random.nextBoolean() && state.isAir() && !world.getBlockState(POS.down()).isAir()) {
						world.setBlockState(POS, Blocks.FIRE.getDefaultState());
					}
				}
			}
		}
	}
}
