package T145.allthecreepers.api;

import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IElementalCreeper extends IEntityRendererProvider {

	final BlockPos.Mutable POS = new BlockPos.Mutable(BlockPos.ORIGIN);

	boolean canDetonate();

	void detonate(Explosion.DestructionType destructionType, byte radius, Explosion simpleExplosion);

	default void generateParticles(World world, double x, double y, double z) {}

	default BoundingBox getAOE(byte radius, double posX, double posY, double posZ) {
		return new BoundingBox(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
	}

	default void specialBlast(byte radius, BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		if (creeper.getRand().nextBoolean()) {
			wildBlast(radius, state, creeper, suffocateEntities);
		} else {
			domeBlast(radius, state, creeper, suffocateEntities);
		}
	}

	default void domeBlast(byte radius, BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

					BlockState currState = creeper.world.getBlockState(POS);

					if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && creeper.getRand().nextInt(4) < 3 && currState.isAir() && state.getBlock().canPlaceAt(currState, creeper.world, POS)) {
						//if (suffocateEntities || !creeper.world.getEntities(LivingEntity.class, new BoundingBox(POS)).isEmpty())
						creeper.world.setBlockState(POS, state, 3);
					}
				}
			}
		}
	}

	default void wildBlast(byte radius, BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

					BlockState currState = creeper.world.getBlockState(POS);
					//Block block = state.getBlock();

					boolean param1 = currState.isAir();
					boolean param2 = !creeper.world.getBlockState(POS.down()).isAir();

					if (creeper.getRand().nextBoolean() && param1 && param2) {
						//if (suffocateEntities || !creeper.world.getEntities(LivingEntity.class, new BoundingBox(POS)).isEmpty())
						creeper.world.setBlockState(POS, state, 3);
					}
				}
			}
		}
	}
}
