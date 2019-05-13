package T145.allthecreepers.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PureDarkBlock extends TransparentBlock {

	public PureDarkBlock() {
		super(FabricBlockSettings.of(Material.AIR, DyeColor.BLACK).dropsNothing().build());
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public boolean isAir(BlockState state) {
		return true;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return true;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public ItemStack getPickStack(BlockView view, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && !(entity instanceof HostileEntity)) {
			LivingEntity victim = (LivingEntity) entity;

			if (!victim.hasStatusEffect(StatusEffects.SLOWNESS)) {
				victim.addPotionEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 5, false, true, true));
			}

			if (!victim.hasStatusEffect(StatusEffects.BLINDNESS)) {
				victim.addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 5, false, true, true));
			}
		}
	}
}
