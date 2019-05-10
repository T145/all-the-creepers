package T145.allthecreepers.core;

import T145.allthecreepers.api.immutable.RegistryATC;
import T145.allthecreepers.client.render.entities.ElementalCreeperRenderer;
import T145.allthecreepers.entities.CakeCreeperEntity;
import T145.allthecreepers.entities.CookieCreeperEntity;
import T145.allthecreepers.entities.DemolitionCreeperEntity;
import T145.allthecreepers.entities.EarthCreeperEntity;
import T145.allthecreepers.entities.factories.CakeCreeperFactory;
import T145.allthecreepers.entities.factories.CookieCreeperFactory;
import T145.allthecreepers.entities.factories.DemolitionCreeperFactory;
import T145.allthecreepers.entities.factories.EarthCreeperFactory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.registry.Registry;

public class AllTheCreepers implements ModInitializer, ClientModInitializer {

	private final static EntitySize CREEPER_SIZE = EntitySize.constant(0.6F, 1.7F);
	public final static EntityType<CreeperEntity> CAKE_CREEPER = Registry.register(Registry.ENTITY_TYPE, RegistryATC.CAKE_CREEPER_IDENTIFIER, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, new CakeCreeperFactory()).size(CREEPER_SIZE).build());
	public final static EntityType<CreeperEntity> COOKIE_CREEPER = Registry.register(Registry.ENTITY_TYPE, RegistryATC.COOKIE_CREEPER_IDENTIFIER, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, new CookieCreeperFactory()).size(CREEPER_SIZE).build());
	public final static EntityType<CreeperEntity> DEMOLITION_CREEPER = Registry.register(Registry.ENTITY_TYPE, RegistryATC.DEMOLITION_CREEPER_IDENTIFIER, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, new DemolitionCreeperFactory()).size(CREEPER_SIZE).build());
	public final static EntityType<CreeperEntity> EARTH_CREEPER = Registry.register(Registry.ENTITY_TYPE, RegistryATC.EARTH_CREEPER_IDENTIFIER, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, new EarthCreeperFactory()).size(CREEPER_SIZE).build());

	@Override
	public void onInitialize() {}

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(CakeCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(CookieCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(DemolitionCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(EarthCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
	}
}
