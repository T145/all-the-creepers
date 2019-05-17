package T145.allthecreepers.entities;

import T145.allthecreepers.api.creepers.IElementalCreeper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class CakeCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public CakeCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "cake";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		if (Blocks.CAKE.canPlaceAt(Blocks.CAKE.getDefaultState(), world, this.getBlockPos())) {
			world.setBlockState(this.getBlockPos(), Blocks.CAKE.getDefaultState());

			for (Direction dir : Direction.Type.HORIZONTAL) {
				BlockPos torchPos = this.getBlockPos().offset(dir);

				if (Blocks.TORCH.canPlaceAt(world.getBlockState(torchPos), world, torchPos)) {
					world.setBlockState(torchPos, Blocks.TORCH.getDefaultState());
				}
			}
		}
	}
}
