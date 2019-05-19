package T145.allthecreepers.client.init;

import T145.allthecreepers.client.render.entities.ElementalCreeperRenderer;
import T145.allthecreepers.entities.CakeCreeperEntity;
import T145.allthecreepers.entities.CookieCreeperEntity;
import T145.allthecreepers.entities.FireworkCreeperEntity;
import T145.allthecreepers.entities.PartyCreeperEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

public class ClientModInit implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(CakeCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(CookieCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(FireworkCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(PartyCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
	}
}
