package T145.elementalcreepers.common.entities;

import java.util.ArrayList;

import T145.elementalcreepers.common.config.ConfigGeneral;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDarkCreeper extends EntityBaseCreeper {

	public EntityDarkCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		if (world.isDaytime() && !world.isRemote && !isChild()) {
			float brightness = getBrightness();
			if (brightness > 0.5F && rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F && world.canSeeSky(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))) && !getPowered()) {
				setFire(8);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int radius = getPowered() ? ConfigGeneral.darkCreeperRadius * explosionPower : ConfigGeneral.darkCreeperRadius;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
					IBlockState state = world.getBlockState(pos);

					if (state != null && state.getBlock() != null) {
						Block block = state.getBlock();

						if (block != null && block.getLightValue(state) > 0.5F) {
							block.dropBlockAsItem(world, pos, state, 0);
							world.setBlockToAir(pos);
							block.onBlockDestroyedByExplosion(world, pos, new Explosion(world, this, 0.0D, 0.0D, 0.0D, 0.0F, new ArrayList<>()));
						}
					}
				}
			}
		}
	}
}