package T145.allthecreepers.client.render.entities;

import T145.allthecreepers.api.creepers.IEntityRendererProvider;
import T145.allthecreepers.api.immutable.RegistryATC;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ElementalCreeperRenderer extends CreeperEntityRenderer {

	public ElementalCreeperRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected Identifier getTexture(CreeperEntity creeper) {
		if (creeper instanceof IEntityRendererProvider) {
			return new Identifier(RegistryATC.ID, String.format("textures/entities/creepers/%s.png", ((IEntityRendererProvider) creeper).getTextureName()));
		} else {
			return this.method_3899(creeper);
		}
	}
}
