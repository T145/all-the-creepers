package T145.allthecreepers.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IElementalCreeper extends IEntityRendererProvider {

	final BlockPos.Mutable POS = new BlockPos.Mutable(BlockPos.ORIGIN);

	boolean canDetonate();

	void detonate(Explosion.DestructionType destructionType, byte radius, Explosion simpleExplosion);

	default void createClientSideEffects(World world, double x, double y, double z) {}

	default void createServerSideEffects(World world, double x, double y, double z) {}

	default BoundingBox getAOE(byte radius, double posX, double posY, double posZ) {
		return new BoundingBox(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
	}

	default boolean canDestroy(BlockPos pos, CreeperEntity creeper, boolean skipHardnessCheck) {
		World world = creeper.world;
		BlockState state = world.getBlockState(pos);

		return state.isAir()
				|| state.getPistonBehavior() == PistonBehavior.DESTROY
				|| skipHardnessCheck // we know it's a solid block here, so only real explosions should break stuff
				|| state.getHardness(world, pos) < Blocks.OBSIDIAN.getDefaultState().getHardness(world, pos);
	}

	default void specialBlast(byte radius, BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		if (creeper.isCharged()) {
			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++y) {
					for (int z = -radius; z <= radius; ++z) {
						POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

						if (canDestroy(POS, creeper, true)) {
							creeper.world.setBlockState(POS, state, 3);
						}
					}
				}
			}
		} else {
			if (creeper.getRand().nextBoolean()) {
				wildBlast(radius, state, creeper, suffocateEntities);
			} else {
				domeBlast(radius, state, creeper, suffocateEntities);
			}
		}
	}

	default void domeBlast(byte radius, BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

					if (canDestroy(POS, creeper, true)) {
						double bound = Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D));

						if (bound <= radius && creeper.getRand().nextInt(4) < 3) {
							creeper.world.setBlockState(POS, state, 3);
						}
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

					if (canDestroy(POS, creeper, true) && creeper.getRand().nextBoolean() && !creeper.world.getBlockState(POS.down()).isAir()) {
						creeper.world.setBlockState(POS, state, 3);
					}
				}
			}
		}
	}
}
