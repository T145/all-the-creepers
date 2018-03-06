package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaCreeper extends EntityBaseCreeper {

	public EntityMagmaCreeper(World world) {
		super(world);
		isImmuneToFire = true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isWet()) {
			attackEntityFrom(DamageSource.DROWN, 1.0F);
		}

		if (world.isRemote || !world.getGameRules().getBoolean("mobGriefing")) {
			return;
		}

		for (int i, j, k, l = 0; l < 4; ++l) {
			i = MathHelper.floor(posX + ((l % 2 * 2 - 1) * 0.25F));
			j = MathHelper.floor(posY);
			k = MathHelper.floor(posZ + ((l / 2 % 2 * 2 - 1) * 0.25F));
			pos.setPos(i, j, k);

			if (world.getBlockState(pos).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(world, pos)) {
				world.setBlockState(pos, Blocks.FIRE.getDefaultState());
			}
		}
	}

	@Override
	public void createExplosion(int explosionPower, boolean canGrief) {
		int radius = getPowered() ? ModConfig.explosionRadii.magmaCreeperRadius * explosionPower : ModConfig.explosionRadii.magmaCreeperRadius;

		if (ModConfig.general.domeExplosion) {
			domeExplosion(radius, Blocks.LAVA);
		} else {
			wildExplosion(radius, Blocks.LAVA);
		}
	}
}