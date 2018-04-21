package T145.elementalcreepers.client.render.entity.layers;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.utils.HolidayUtils;
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
public class LayerFestiveCreeper implements LayerRenderer {

    private static final float HEAD_OFFSET = 0.0625F;

    private final RenderLiving renderer;

    public LayerFestiveCreeper(RenderLiving renderLiving) {
        this.renderer = renderLiving;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (ModConfig.GENERAL.festiveSpirit && !entity.isInvisible()) {
            ItemStack stack = getHolidayStack();

            if (!stack.isEmpty()) {
                renderItem(entity, stack, renderer.getMainModel());
            }
        }
    }

    private ItemStack getHolidayStack() {
        if (HolidayUtils.isAprilFools()) {
            return new ItemStack(Blocks.SPONGE);
        }

        if (HolidayUtils.isChristmas()) {
            return new ItemStack(Blocks.SNOW);
        }

        if (HolidayUtils.isEarthDay()) {
            return new ItemStack(Blocks.GRASS);
        }

        if (HolidayUtils.isHalloween()) {
            return new ItemStack(Blocks.PUMPKIN);
        }

        if (HolidayUtils.isValentinesDay()) {
            return new ItemStack(Blocks.WOOL, 1, 14);
        }

        return ItemStack.EMPTY;
    }

    private void renderItem(EntityLivingBase entity, ItemStack stack, ModelBase model) {
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            ((ModelCreeper) model).head.postRender(HEAD_OFFSET);
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