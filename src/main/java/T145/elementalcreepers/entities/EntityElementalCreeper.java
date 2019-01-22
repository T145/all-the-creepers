package T145.elementalcreepers.entities;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public abstract class EntityElementalCreeper extends EntityCreeper {

	protected static final Random rand = new Random();
	protected final MutableBlockPos pos = new MutableBlockPos(BlockPos.ORIGIN);
	protected final boolean isWild;

	public EntityElementalCreeper(World world) {
		super(world);
		isWild = rand.nextBoolean();
	}

	@Override
	public void explode() {
		if (!world.isRemote) {
			dead = diesAfterExplosion();
			detonate();
			isDead = diesAfterExplosion();
			spawnLingeringCloud();
		}
	}

	public boolean diesAfterExplosion() {
		return true;
	}

	public abstract void detonate();

	protected AxisAlignedBB getAOE() {
		return new AxisAlignedBB(posX - explosionRadius, posY - explosionRadius, posZ - explosionRadius, posX + explosionRadius, posY + explosionRadius, posZ + explosionRadius);
	}

	protected void createExplosion(IBlockState state) {
		Block block = state.getBlock();

		for (int x = -explosionRadius; x <= explosionRadius; ++x) {
			for (int y = -explosionRadius; y <= explosionRadius; ++y) {
				for (int z = -explosionRadius; z <= explosionRadius; ++z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					boolean wild = isWild && block.canPlaceBlockAt(world, pos) && !block.canPlaceBlockAt(world, pos.down()) && rand.nextBoolean();
					boolean dome = block.canPlaceBlockAt(world, pos) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= explosionRadius && rand.nextInt(4) < 3;

					if (wild || dome) {
						world.setBlockState(pos, state, 3);
					}
				}
			}
		}
	}
}
