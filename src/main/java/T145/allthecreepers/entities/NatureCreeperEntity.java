package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalCreeper;
import T145.allthecreepers.init.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.sapling.AcaciaSaplingGenerator;
import net.minecraft.block.sapling.BirchSaplingGenerator;
import net.minecraft.block.sapling.DarkOakSaplingGenerator;
import net.minecraft.block.sapling.JungleSaplingGenerator;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.block.sapling.SpruceSaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class NatureCreeperEntity extends CreeperEntity implements IElementalCreeper {

	private static final SaplingGenerator[] TREE_GENERATORS = new SaplingGenerator[] {
			new AcaciaSaplingGenerator(),
			new BirchSaplingGenerator(),
			new DarkOakSaplingGenerator(),
			new JungleSaplingGenerator(),
			new OakSaplingGenerator(),
			new SpruceSaplingGenerator()
	};

	public NatureCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public String getTextureName() {
		return "wacky/nature";
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public int getExplosionRadius() {
		return ModInit.config.natureCreeperExplosionRadius;
	}

	@Override
	public int getChargedExplosionRadius() {
		return ModInit.config.natureCreeperChargedExplosionRadius;
	}

	@Override
	public void detonate(DestructionType destructionType, Explosion simpleExplosion) {
		BlockPos pos = getBlockPos();
		BlockState state = world.getBlockState(pos);

		if (Blocks.OAK_SAPLING.canPlaceAt(state, world, pos)) {
			SaplingGenerator generator = TREE_GENERATORS[random.nextInt(TREE_GENERATORS.length)];
			// state = air, so if the generation fails nothing else happens
			generator.generate(world, pos, state, random);

			pos = pos.offset(Direction.DOWN);

			for (Direction dir : Direction.Type.HORIZONTAL) {
				POS.set(pos.offset(dir));

				// to compensate for large tree generation
				if (!world.getBlockState(POS.offset(Direction.UP)).isAir()) {
					POS.set(POS.offset(dir));
				}

				state = world.getBlockState(POS);
				Block block = state.getBlock();

				if (block instanceof Fertilizable) {
					Fertilizable fertileBlock = (Fertilizable) block;

					if (fertileBlock.isFertilizable(world, POS, state, true)
							&& fertileBlock.canGrow(world, random, POS, state)) {
						fertileBlock.grow(world, random, POS, state);
					}
				}
			}
		} else {
			int radius = 4;
			BlockState sand = Blocks.SAND.getDefaultState();
			BlockState sandstone = Blocks.SANDSTONE.getDefaultState();

			for (int X = -radius; X <= radius; ++X) {
				for (int Y = -radius; Y < radius; ++Y) {
					for (int Z = -radius; Z <= radius; ++Z) {
						if (Math.sqrt(Math.pow(X, 2.0D) + Math.pow(Y, 2.0D) + Math.pow(Z, 2.0D)) <= radius) {
							POS.set(X + x, Y + y, Z + z);

							state = world.getBlockState(POS.offset(Direction.DOWN));

							if (Y < 0) {
								if (state == sandstone || state == sand) {
									world.setBlockState(POS, Blocks.SAND.getDefaultState(), 3);
								} else {
									world.setBlockState(POS, Blocks.SANDSTONE.getDefaultState(), 3);
								}
							} else if (random.nextInt(10) < 1 && (state == sand || state.getBlock() instanceof CactusBlock)) {
								// %20 chance for a cactus
								boolean hasNeighboringCactus = false;

								for (Direction dir : Direction.Type.HORIZONTAL) {
									if (world.getBlockState(POS.offset(dir)).getBlock() instanceof CactusBlock) {
										hasNeighboringCactus = true;
										break;
									}
								}

								if (!hasNeighboringCactus) {
									world.setBlockState(POS, Blocks.CACTUS.getDefaultState(), 3);

									pos = POS.offset(Direction.UP);

									// %30 chance to get a slightly taller cactus
									if (random.nextInt(10) < 2 && world.getBlockState(pos).isAir()) {
										world.setBlockState(pos, Blocks.CACTUS.getDefaultState(), 3);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
