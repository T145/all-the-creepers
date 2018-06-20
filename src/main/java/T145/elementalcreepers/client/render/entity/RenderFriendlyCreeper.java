package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.client.render.entity.layers.LayerFestiveCreeper;
import T145.elementalcreepers.client.render.entity.layers.LayerFriendlyCharge;
import T145.elementalcreepers.client.render.model.ModelFriendlyCreeper;
import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFriendlyCreeper extends RenderLiving<EntityFriendlyCreeper> {

	public RenderFriendlyCreeper(RenderManager renderManager) {
		super(renderManager, new ModelFriendlyCreeper(), 0.5F);
		addLayer(new LayerFestiveCreeper(this));
		addLayer(new LayerFriendlyCharge(this));
	}

	@Override
	public void preRenderCallback(EntityFriendlyCreeper creeper, float partialTickTime) {
		float f = creeper.getCreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		GlStateManager.scale(f2, f3, f2);
	}

	@Override
	public int getColorMultiplier(EntityFriendlyCreeper creeper, float lightBrightness, float partialTickTime) {
		float f = creeper.getCreeperFlashIntensity(partialTickTime);

		if ((int) (f * 10.0F) % 2 == 0) {
			return 0;
		} else {
			int i = (int) (f * 0.2F * 255.0F);
			i = MathHelper.clamp(i, 0, 255);
			return i << 24 | 822083583;
		}
	}

	@Override
	public ResourceLocation getEntityTexture(EntityFriendlyCreeper creeper) {
		if (ModConfig.GENERAL.creeperTemper && creeper.getCreeperState() == 1) {
			float red = 1F - (creeper.getCreeperFlashIntensity(0F) / 1.17F + 0.1F);
			GlStateManager.color(1F, red, red);
		}

		return creeper.getActiveTexture();
	}
}