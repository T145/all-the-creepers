package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.client.render.model.ModelSpiderCreeper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSpiderCharge implements LayerRenderer<EntityCreeper> {

	private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final RenderSpiderCreeper creeperRenderer;
	private final ModelSpiderCreeper creeperModel = new ModelSpiderCreeper(2.0F);

	public LayerSpiderCharge(RenderSpiderCreeper creeperRenderer) {
		this.creeperRenderer = creeperRenderer;
	}

	@Override
	public void doRenderLayer(EntityCreeper entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entity.getPowered()) {
			boolean flag = entity.isInvisible();
			GlStateManager.depthMask(!flag);
			creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			float f = entity.ticksExisted + partialTicks;
			GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
			GlStateManager.matrixMode(5888);
			GlStateManager.enableBlend();
			float f1 = 0.5F;
			GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
			GlStateManager.disableLighting();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			creeperModel.setModelAttributes(creeperRenderer.getMainModel());
			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			creeperModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(5888);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.depthMask(flag);
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}