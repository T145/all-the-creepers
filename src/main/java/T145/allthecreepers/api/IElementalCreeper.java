package T145.allthecreepers.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public interface IElementalCreeper extends IEntityRendererProvider {

	final BlockPos.Mutable POS = new BlockPos.Mutable(BlockPos.ORIGIN);

	boolean canDetonate();

	int getExplosionRadius();
	int getChargedExplosionRadius();

	default int getRadius(boolean charged) {
		return charged ? getChargedExplosionRadius() : getExplosionRadius();
	}

	void detonate(Explosion.DestructionType destructionType, Explosion simpleExplosion);

	default void createClientSideEffects(World world, double x, double y, double z) {}

	default void createServerSideEffects(World world, double x, double y, double z) {}

	default BoundingBox getAOE(boolean charged, double posX, double posY, double posZ) {
		int radius = getRadius(charged);
		return new BoundingBox(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
	}

	default BoundingBox getAOE(boolean charged, Vec3d pos) {
		return getAOE(charged, pos.x, pos.y, pos.z);
	}

	default BoundingBox getAOE(boolean charged, BlockPos pos) {
		return getAOE(charged, pos.getX(), pos.getY(), pos.getZ());
	}

	default double getDomeBound(double x, double y, double z) {
		return Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D));
	}

	default boolean canPlaceBlock(BlockState state, BlockPos pos, CreeperEntity creeper) {
		World world = creeper.world;
		BlockState currState = world.getBlockState(pos);

		if (!state.canPlaceAt(world, pos)) {
			return false;
		}

		return currState.isAir() || state.getPistonBehavior() == PistonBehavior.DESTROY;
	}

	default boolean canDestroyBlock(BlockPos pos, CreeperEntity creeper) {
		World world = creeper.world;
		BlockState state = world.getBlockState(pos);

		if (state.isAir() || state.getHardness(world, pos) < 0) {
			return false; // the block is unbreakable
		}

		return state.getPistonBehavior() == PistonBehavior.DESTROY
				|| state.getBlock().getBlastResistance() < Blocks.OBSIDIAN.getBlastResistance();
	}

	default void specialBlast(BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		if (creeper.isCharged()) {
			int radius = getChargedExplosionRadius();

			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++y) {
					for (int z = -radius; z <= radius; ++z) {
						POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

						if (canPlaceBlock(state, POS, creeper)) {
							creeper.world.setBlockState(POS, state, 3);
						}
					}
				}
			}
		} else {
			if (creeper.getRand().nextBoolean()) {
				wildBlast(state, creeper, suffocateEntities);
			} else {
				domeBlast(state, creeper, suffocateEntities);
			}
		}
	}

	default void domeBlast(BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		int radius = getRadius(creeper.isCharged());

		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

					if (canPlaceBlock(state, POS, creeper) && getDomeBound(x, y, z) <= radius && creeper.getRand().nextInt(4) < 3) {
						creeper.world.setBlockState(POS, state, 3);
					}
				}
			}
		}
	}

	default void wildBlast(BlockState state, CreeperEntity creeper, boolean suffocateEntities) {
		int radius = getRadius(creeper.isCharged());

		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					POS.set(x + creeper.x, y + creeper.y, z + creeper.z);

					if (canPlaceBlock(state, POS, creeper) && creeper.getRand().nextBoolean() && !creeper.world.getBlockState(POS.down()).isAir()) {
						creeper.world.setBlockState(POS, state, 3);
					}
				}
			}
		}
	}
}
