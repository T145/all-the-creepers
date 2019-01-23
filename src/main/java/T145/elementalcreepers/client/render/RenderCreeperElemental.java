package T145.elementalcreepers.client.render;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;

public class RenderCreeperElemental extends RenderCreeper {

	protected final ResourceLocation texture;

	public RenderCreeperElemental(RenderManager manager, ResourceLocation texture) {
		super(manager);
		this.texture = texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity) {
		return texture;
	}
}
