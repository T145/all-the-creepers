package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.client.render.model.ModelSpiderCreeper;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpiderCreeper extends RenderLiving {

	public RenderSpiderCreeper(RenderManager renderManager) {
		super(renderManager, new ModelSpiderCreeper(), 0.5F);
		addLayer(new LayerFestive(this));
		addLayer(new LayerSpiderCharge(this));
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		if (entity instanceof EntityBaseCreeper) {
			EntityBaseCreeper creeper = (EntityBaseCreeper) entity;
			float f1 = creeper.getCreeperFlashIntensity(partialTickTime);
			float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

			f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
			f1 *= f1;
			f1 *= f1;

			float f3 = (1.0F + f1 * 0.4F) * f2;
			float f4 = (1.0F + f1 * 0.1F) / f2;

			GlStateManager.scale(f3, f4, f3);
		}

		super.preRenderCallback(entity, partialTickTime);
	}

	@Override
	protected int getColorMultiplier(EntityLivingBase entity, float lightBrightness, float partialTickTime) {
		if (entity instanceof EntityBaseCreeper) {
			EntityBaseCreeper creeper = (EntityBaseCreeper) entity;
			float f2 = creeper.getCreeperFlashIntensity(partialTickTime);

			if (f2 * 10.0F % 2 == 0) {
				return 0;
			}

			int i = (int) (f2 * 0.2F * 255.0F);
			i = MathHelper.clamp(i, 0, 255);
			return i << 24 | 0xFFFFFF;
		}

		return super.getColorMultiplier(entity, lightBrightness, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(ElementalCreepers.MODID, "textures/entities/spidercreeper.png");
	}
}