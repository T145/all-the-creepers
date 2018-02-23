package T145.elementalcreepers.client.render.entity;

import java.util.Calendar;

import T145.elementalcreepers.client.render.model.ModelFriendlyCreeper;
import T145.elementalcreepers.client.render.model.ModelSpiderCreeper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFestive implements LayerRenderer {

	private final RenderLiving renderer;

	public LayerFestive(RenderLiving renderLiving) {
		this.renderer = renderLiving;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entity != null && !entity.isInvisible()) {
			int day = Calendar.getInstance().get(5);
			int month = Calendar.getInstance().get(2);
			ItemStack stack = ItemStack.EMPTY;

			if (month == 9 && day == 31) {
				stack = new ItemStack(Blocks.PUMPKIN, 1);
			}

			if (month == 10 && day == 12) {
				stack = new ItemStack(Blocks.SPONGE, 1);
			}

			if (month == 11 && day == 25) {
				stack = new ItemStack(Blocks.SNOW);
			}

			if (!stack.isEmpty() /* && (ModConfig.special) */) {
				renderItem(entity, stack, renderer.getMainModel());
			}
		}
	}

	private void renderItem(EntityLivingBase entity, ItemStack stack, ModelBase model) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();

			if (model instanceof ModelCreeper) {
				((ModelCreeper) model).head.postRender(0.0625F);
			} else if (model instanceof ModelSpiderCreeper) {
				((ModelSpiderCreeper) model).head.postRender(0.0625F);
			} else if (model instanceof ModelFriendlyCreeper) {
				((ModelFriendlyCreeper) model).head.postRender(0.0625F);
			}

			float scale = 0.675F;
			GlStateManager.translate(0F, -0.34375F, 0F);
			GlStateManager.scale(scale, -scale, scale);
			Minecraft.getMinecraft().getItemRenderer().renderItem(entity, stack, ItemCameraTransforms.TransformType.HEAD);
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}