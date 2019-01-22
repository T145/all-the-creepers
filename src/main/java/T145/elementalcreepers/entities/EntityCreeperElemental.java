package T145.elementalcreepers.entities;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class EntityCreeperElemental extends EntityCreeper {

	protected static final Random rand = new Random();
	protected final MutableBlockPos pos = new MutableBlockPos(BlockPos.ORIGIN);
	protected final Explosion expl = new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, false, false);
	protected final boolean isWild;

	public EntityCreeperElemental(World world) {
		super(world);
		isWild = rand.nextBoolean();
	}

	public float getExplosionRadius() {
		return explosionRadius * (getPowered() ? 2F : 1F);
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
		float radius = getExplosionRadius();
		return new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
	}

	protected void createExplosion(IBlockState state) {
		float radius = getExplosionRadius();
		Block block = state.getBlock();

		for (float x = -radius; x <= radius; ++x) {
			for (float y = -radius; y <= radius; ++y) {
				for (float z = -radius; z <= radius; ++z) {
					pos.setPos(posX + x, posY + y, posZ + z);

					boolean wild = isWild && block.canPlaceBlockAt(world, pos) && !block.canPlaceBlockAt(world, pos.down()) && rand.nextBoolean();
					boolean dome = block.canPlaceBlockAt(world, pos) && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius && rand.nextInt(4) < 3;

					if (wild || dome) {
						world.setBlockState(pos, state, 3);
					}
				}
			}
		}
	}
}