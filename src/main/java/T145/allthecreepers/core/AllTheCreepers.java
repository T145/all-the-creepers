package T145.allthecreepers.core;

import T145.allthecreepers.api.immutable.CreeperType;
import T145.allthecreepers.api.immutable.RegistryATC;
import T145.allthecreepers.blocks.PureDarkBlock;
import T145.allthecreepers.blocks.PureLightBlock;
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
import T145.allthecreepers.entities.WaterCreeperEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllTheCreepers implements ModInitializer, ClientModInitializer {

	public static final PureDarkBlock PURE_DARK = new PureDarkBlock();
	public static final PureLightBlock PURE_LIGHT = new PureLightBlock();

	private static final EntitySize CREEPER_SIZE = EntitySize.constant(0.6F, 1.7F);
	public static final EntityType<CakeCreeperEntity> CAKE_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.CAKE.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CakeCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<CookieCreeperEntity> COOKIE_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.COOKIE.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CookieCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<DarkCreeperEntity> DARK_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.DARK.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DarkCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<DemolitionCreeperEntity> DEMOLITION_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.DEMOLTIION.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DemolitionCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<EarthCreeperEntity> EARTH_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.EARTH.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EarthCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<FireCreeperEntity> FIRE_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.FIRE.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<FireworkCreeperEntity> FIREWORK_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.FIREWORK.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireworkCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<FurnaceCreeperEntity> FURNACE_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.FURNACE.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FurnaceCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<LavaCreeperEntity> LAVA_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.LAVA.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LavaCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<LightningCreeperEntity> LIGHTNING_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.LIGHTNING.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LightningCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<WaterCreeperEntity> WATER_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.WATER.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, WaterCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<LuminousCreeperEntity> LUMINOUS_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.LUMINOUS.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LuminousCreeperEntity::new).size(CREEPER_SIZE).build());
	public static final EntityType<BallisticCreeperEntity> BALLISTIC_CREEPER = Registry.register(Registry.ENTITY_TYPE, CreeperType.BALLISTIC.getIdentifier(), FabricEntityTypeBuilder.create(EntityCategory.MONSTER, BallisticCreeperEntity::new).size(CREEPER_SIZE).build());

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(RegistryATC.ID, "pure_dark"), PURE_DARK);
		Registry.register(Registry.BLOCK, new Identifier(RegistryATC.ID, "pure_light"), PURE_LIGHT);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(CakeCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(CookieCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(DemolitionCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(EarthCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));

		EntityRendererRegistry.INSTANCE.register(FurnaceCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(LavaCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(LightningCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(WaterCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));

		EntityRendererRegistry.INSTANCE.register(FireCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(BallisticCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(LuminousCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(DarkCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(FireworkCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
	}
}
