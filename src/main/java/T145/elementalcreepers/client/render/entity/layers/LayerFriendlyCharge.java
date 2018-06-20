package T145.elementalcreepers.client.render.entity.layers;

import org.lwjgl.opengl.GL11;

import T145.elementalcreepers.client.render.model.ModelFriendlyCreeper;
import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFriendlyCharge implements LayerRenderer<EntityFriendlyCreeper> {

	private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final RenderLiving renderer;
	private final ModelFriendlyCreeper model = new ModelFriendlyCreeper(2F);

	public LayerFriendlyCharge(RenderLiving renderer) {
		this.renderer = renderer;
	}

	@Override
	public void doRenderLayer(EntityFriendlyCreeper creeper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (creeper.getPowered()) {
			boolean flag = creeper.isInvisible();
			GlStateManager.depthMask(!flag);
			renderer.bindTexture(LIGHTNING_TEXTURE);
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.loadIdentity();
			float f = creeper.ticksExisted + partialTicks;
			GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
			GlStateManager.enableBlend();
			GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
			GlStateManager.disableLighting();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			model.setModelAttributes(renderer.getMainModel());
			Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
			model.render(creeper, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
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