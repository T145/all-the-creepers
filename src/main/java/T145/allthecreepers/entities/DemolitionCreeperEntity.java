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
		for (int x = -radius; x <= radius; ++x) {
			for (int y = -radius; y <= radius; ++y) {
				for (int z = -radius; z <= radius; ++z) {
					if (Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius) {
						POS.set(this.x + x, this.y + y, this.z + z);

						BlockState state = world.getBlockState(POS);
						Block block = state.getBlock();

						if (!state.isAir() && block.getBlastResistance() < Blocks.OBSIDIAN.getBlastResistance()) {
							if (block.shouldDropItemsOnExplosion(simpleExplosion) && this.world instanceof ServerWorld) {
								BlockEntity blockEntity_1 = block.hasBlockEntity() ? this.world.getBlockEntity(POS) : null;
								LootContext.Builder loot = (new LootContext.Builder((ServerWorld)this.world)).setRandom(this.world.random).put(LootContextParameters.POSITION, POS).put(LootContextParameters.TOOL, ItemStack.EMPTY).putNullable(LootContextParameters.BLOCK_ENTITY, blockEntity_1);

								if (destructionType == Explosion.DestructionType.DESTROY) {
									loot.put(LootContextParameters.EXPLOSION_RADIUS, (float) radius);
								}

								Block.dropStacks(state, loot);
							}

							this.world.setBlockState(POS, Blocks.AIR.getDefaultState(), 3);
							block.onDestroyedByExplosion(this.world, POS, simpleExplosion);
						}
					}
				}
			}
		}
	}
}
