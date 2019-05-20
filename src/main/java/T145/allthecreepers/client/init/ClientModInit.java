package T145.allthecreepers.client.init;

import T145.allthecreepers.client.render.entities.ElementalCreeperRenderer;
import T145.allthecreepers.entities.BallisticCreeperEntity;
import T145.allthecreepers.entities.CakeCreeperEntity;
import T145.allthecreepers.entities.CookieCreeperEntity;
import T145.allthecreepers.entities.DarkCreeperEntity;
import T145.allthecreepers.entities.DemolitionCreeperEntity;
import T145.allthecreepers.entities.EarthCreeperEntity;
import T145.allthecreepers.entities.FireCreeperEntity;
import T145.allthecreepers.entities.FireworkCreeperEntity;
import T145.allthecreepers.entities.FurnaceCreeperEntity;
import T145.allthecreepers.entities.LavaCreeperEntity;
import T145.allthecreepers.entities.LightningCreeperEntity;
import T145.allthecreepers.entities.LuminousCreeperEntity;
import T145.allthecreepers.entities.NatureCreeperEntity;
import T145.allthecreepers.entities.PartyCreeperEntity;
import T145.allthecreepers.entities.WaterCreeperEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

public class ClientModInit implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(BallisticCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(CakeCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(CookieCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(DarkCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(DemolitionCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(EarthCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(FireCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(FireworkCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(FurnaceCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(LavaCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(LightningCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(LuminousCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(NatureCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(PartyCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(WaterCreeperEntity.class,
				((manager, context) -> new ElementalCreeperRenderer(manager)));
	}
}
