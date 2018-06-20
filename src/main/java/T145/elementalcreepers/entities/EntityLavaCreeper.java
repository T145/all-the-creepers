package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityLavaCreeper extends EntityBaseCreeper {

	public EntityLavaCreeper(World world) {
		super(world);
		isImmuneToFire = true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isWet()) {
			attackEntityFrom(DamageSource.DROWN, 1.0F);
		}

		if (world.isRemote) {
			return;
		}

		createPlatformOverLiquid(this, Blocks.COBBLESTONE, Blocks.WATER, Blocks.FLOWING_WATER);

		if (!ForgeEventFactory.getMobGriefingEvent(world, this)) {
			return;
		}

		for (int i, j, k, l = 0; l < 4; ++l) {
			i = MathHelper.floor(posX + ((l % 2 * 2 - 1) * 0.25F));
			j = MathHelper.floor(posY);
			k = MathHelper.floor(posZ + ((l / 2 % 2 * 2 - 1) * 0.25F));
			MUTABLE_POS.setPos(i, j, k);

			if (world.getBlockState(MUTABLE_POS).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(world, MUTABLE_POS)) {
				world.setBlockState(MUTABLE_POS, Blocks.FIRE.getDefaultState());
			}
		}
	}

	@Override
	public void detonate() {
		specialExplosion(getPowered() ? ModConfig.EXPLOSION_RADII.lavaCharged : ModConfig.EXPLOSION_RADII.lava, Blocks.LAVA.getDefaultState());
	}
}