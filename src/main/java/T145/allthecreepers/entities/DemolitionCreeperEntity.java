package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;

public class DemolitionCreeperEntity extends CreeperEntity implements IElementalCreeper {

	public DemolitionCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "demolition";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate(DestructionType destructionType, byte radius, Explosion simpleExplosion) {
		for (int X = -radius; X <= radius; ++X) {
			for (int Y = -radius; Y <= radius; ++Y) {
				for (int Z = -radius; Z <= radius; ++Z) {
					if (getDomeBound(X, Y, Z) <= radius) {
						POS.set(x + X, y + Y, z + Z);

						if (canDestroyBlock(POS, this)) {
							BlockState state = world.getBlockState(POS);
							Block block = state.getBlock();

							if (block.shouldDropItemsOnExplosion(simpleExplosion) && world instanceof ServerWorld) {
								BlockEntity be = block.hasBlockEntity() ? world.getBlockEntity(POS) : null;
								LootContext.Builder loot = (new LootContext.Builder((ServerWorld)world)).setRandom(world.random).put(LootContextParameters.POSITION, POS).put(LootContextParameters.TOOL, ItemStack.EMPTY).putNullable(LootContextParameters.BLOCK_ENTITY, be);

								if (destructionType == Explosion.DestructionType.DESTROY) {
									loot.put(LootContextParameters.EXPLOSION_RADIUS, (float) radius);
								}

								Block.dropStacks(state, loot);
							}

							world.setBlockState(POS, Blocks.AIR.getDefaultState(), 3);
							block.onDestroyedByExplosion(world, POS, simpleExplosion);
						}
					}
				}
			}
		}
	}
}
