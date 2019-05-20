package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.init.ModInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.Direction;
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
		world.setBlockState(getBlockPos(), Blocks.GLOWSTONE.getDefaultState(), 3);

		if (isCharged()) {
			world.setBlockState(getBlockPos().offset(Direction.UP), Blocks.GLOWSTONE.getDefaultState(), 3);
		}

		radius += radius / 3;

		for (int X = -radius; X <= radius; ++X) {
			for (int Y = -radius; Y < radius; ++Y) {
				for (int Z = -radius; Z <= radius; ++Z) {
					POS.set(X + x, Y + y, Z + z);

					BlockState currState = world.getBlockState(POS);

					if (currState.isAir() && random.nextInt(5) == 0 && Math.sqrt(Math.pow(X, 2.0D) + Math.pow(Y, 2.0D) + Math.pow(Z, 2.0D)) <= radius) {
						world.setBlockState(POS, ModInit.PURE_LIGHT.getDefaultState(), 3);
					}
				}
			}
		}
	}
}

