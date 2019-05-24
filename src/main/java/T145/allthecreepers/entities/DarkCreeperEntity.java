package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.init.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class DarkCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public DarkCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "dark";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public int getExplosionRadius() {
		return ModInit.config.darkCreeperExplosionRadius;
	}

	@Override
	public int getChargedExplosionRadius() {
		return ModInit.config.darkCreeperChargedExplosionRadius;
	}

	private void addStatusEffects(PlayerEntity player) {
		if (!player.hasStatusEffect(StatusEffects.SLOWNESS)) {
			player.addPotionEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 5, false, true, true));
		}

		if (!player.hasStatusEffect(StatusEffects.BLINDNESS)) {
			player.addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 5, false, true, true));
		}
	}

	@Override
	public void detonate(DestructionType destructionType, Explosion simpleExplosion) {
		world.getEntities(PlayerEntity.class, getAOE(isCharged(), getPos())).forEach(player -> addStatusEffects(player));

		int radius = getRadius(isCharged());

		for (int X = -radius; X <= radius; ++X) {
			for (int Y = -radius; Y <= radius; ++Y) {
				for (int Z = -radius; Z <= radius; ++Z) {
					POS.set(X + x, Y + y, Z + z);

					BlockState state = world.getBlockState(POS);
					Block block = state.getBlock();

					if (state.getLuminance() > 1 && canDestroyBlock(POS, this)) {
						Block.dropStacks(state, world, POS);
						block.onDestroyedByExplosion(world, POS, simpleExplosion);
						world.setBlockState(POS, Blocks.AIR.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
