package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.client.render.entity.layers.LayerFestiveCreeper;
import T145.elementalcreepers.config.ModConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAngryCreeper extends RenderCreeper {

	public RenderAngryCreeper(RenderManager manager) {
		super(manager);
		addLayer(new LayerFestiveCreeper(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityCreeper creeper) {
		if (ModConfig.GENERAL.creeperTemper && creeper.getCreeperState() == 1) {
			float red = 1F - (creeper.getCreeperFlashIntensity(0F) / 1.17F + 0.1F);
			GlStateManager.color(1F, red, red);
		}

		return super.getEntityTexture(creeper);
	}
}