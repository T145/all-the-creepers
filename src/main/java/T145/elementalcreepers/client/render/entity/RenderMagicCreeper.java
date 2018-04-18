package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.client.render.entity.layers.LayerBaseCharge;
import T145.elementalcreepers.client.render.model.ModelMagicCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMagicCreeper extends RenderBaseCreeper {

    public RenderMagicCreeper(RenderManager manager) {
        super(manager, new ModelMagicCreeper(), "magiccreeper");
    }

    @Override
    protected LayerRenderer getChargeLayer(RenderBaseCreeper renderer) {
        return new LayerBaseCharge(renderer, new ModelMagicCreeper(2F));
    }
}