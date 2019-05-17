package T145.allthecreepers.entities;

import T145.allthecreepers.api.BlocksATC;
import T145.allthecreepers.api.creepers.IElementalCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class DarkCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public DarkCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "dark";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		radius -= radius / 3;

		for (int X = -radius; X <= radius; ++X) {
			for (int Y = -radius; Y <= radius; ++Y) {
				for (int Z = -radius; Z <= radius; ++Z) {
					POS.set(X + x, Y + y, Z + z);

					BlockState state = world.getBlockState(POS);
					Block block = state.getBlock();

					if (state.getLuminance() > 1) {
						Block.dropStacks(state, world, POS);
						block.onDestroyedByExplosion(world, POS, simpleExplosion);
					}

					if (state.isAir() && (state == BlocksATC.pureLight.getDefaultState() || state != BlocksATC.pureDark.getDefaultState())) {
						world.setBlockState(POS, BlocksATC.pureDark.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
