package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.client.render.model.ModelSpiderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpiderCreeper extends RenderBaseCreeper {

	public RenderSpiderCreeper(RenderManager manager) {
		super(manager, new ModelSpiderCreeper(), "spidercreeper");
	}

	@Override
	protected LayerRenderer getChargeLayer(RenderBaseCreeper renderer) {
		return new LayerBaseCharge(renderer, new ModelSpiderCreeper(2F));
	}
}