package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FurnaceCreeperEntity extends CreeperEntity implements IElementalCreeper {

	/*
	 * TODO:
	 * - Add flame particle effects around its head
	 * - Maybe add extinguish mechanic, which will prevent lava from spawning in the middle,
	 *   stop flame particles and change the mob model texture to one w/out fire
	 *   (but maybe doubles its AOE or maybe just increases by half)
	 * - Maybe tweak its speed to be a little slower
	 * - Make it drown if wet (can tie into extinguish effect)
	 */

	public FurnaceCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "furnace";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	private void setState(BlockState destState) {
		BlockState state = world.getBlockState(POS);

		if (state.isAir() || state.getBlock().getBlastResistance() < Blocks.OBSIDIAN.getBlastResistance()) {
			world.setBlockState(POS, destState, 3);
		}
	}

	private Direction getLeftDir(Direction dir) {
		switch (dir) {
		case NORTH:
			return Direction.WEST;
		case SOUTH:
			return Direction.EAST;
		case EAST:
			return Direction.SOUTH;
		case WEST:
			return Direction.NORTH;
		default:
			return dir;
		}
	}

	private BooleanProperty getHorizontalProperty(Direction dir) {
		switch (dir) {
		case NORTH:
			return Properties.NORTH_BOOL;
		case EAST:
			return Properties.EAST_BOOL;
		case WEST:
			return Properties.WEST_BOOL;
		default:
			return Properties.SOUTH_BOOL;
		}
	}

	private void generateTrap(PlayerEntity player) {
		Direction front = player.getHorizontalFacing();
		BlockState mouth = Blocks.IRON_TRAPDOOR.getDefaultState().with(TrapdoorBlock.HALF, BlockHalf.TOP);
		BlockState heart = Blocks.LAVA.getDefaultState();
		BlockState wall = Blocks.STONE_BRICKS.getDefaultState();

		for (int x = -1; x < 2; ++x) {
			for (int y = -1; y < 3; ++y) {
				for (int z = -1; z < 2; ++z) {
					POS.set(player.x + x, player.y + y, player.z + z);

					if (x == 0 && z == 0 && (y == 0 || y == 1)) {
						setState(heart);
					} else if (x == 0 && y == 2 && z == 0) {
						setState(mouth);
					} else {
						setState(wall);
					}
				}
			}
		}

		POS.set(player);
		POS.set(POS.offset(front));
		POS.set(POS.offset(Direction.UP));
		Direction left = getLeftDir(front);
		setState(Blocks.IRON_BARS.getDefaultState().with(this.getHorizontalProperty(left), true).with(this.getHorizontalProperty(left.getOpposite()), true));
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		for (PlayerEntity player : world.getEntities(PlayerEntity.class, this.getAOE(radius, x, y, z))) {
			generateTrap(player);
		}
	}
}
