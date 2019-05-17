package T145.allthecreepers.core;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;

import T145.allthecreepers.api.BlocksATC;
import T145.allthecreepers.api.EntitiesATC;
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
import T145.allthecreepers.entities.NatureCreeperEntity;
import T145.allthecreepers.entities.PartyCreeperEntity;
import T145.allthecreepers.entities.WaterCreeperEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllTheCreepers implements ModInitializer, ClientModInitializer {

	private static String classToRegistryName(Class<? extends Entity> entityClass) {
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName()).replace("_entity", StringUtils.EMPTY);
	}

	private static EntityType<?> createEntity(Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary, FabricEntityTypeBuilder<?> builder) {
		String name = classToRegistryName(entityClass);
		Identifier id = new Identifier(RegistryATC.ID, name);
		EntityType<?> entityType = Registry.register(Registry.ENTITY_TYPE, id, builder.build());
		Item spawnEgg = new SpawnEggItem(entityType, eggPrimary, eggSecondary, new Item.Settings().itemGroup(ItemGroup.MISC));
		Registry.register(Registry.ITEM, new Identifier(RegistryATC.ID, String.format("%s_spawn_egg", name)), spawnEgg);
		return entityType;
	}

	@Override
	public void onInitialize() {
		BlocksATC.pureDark = Registry.register(Registry.BLOCK, new Identifier(RegistryATC.ID, "pure_dark"), new PureDarkBlock());
		BlocksATC.pureLight = Registry.register(Registry.BLOCK, new Identifier(RegistryATC.ID, "pure_light"), new PureLightBlock());

		EntitySize size = EntitySize.constant(0.6F, 1.7F);

		EntitiesATC.ballisticCreeper = createEntity(BallisticCreeperEntity.class, 0x195906, 0xd2d000, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, BallisticCreeperEntity::new).size(size));
		EntitiesATC.cakeCreeper = createEntity(CakeCreeperEntity.class, 0xba5003, 0xeae9eb, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CakeCreeperEntity::new).size(size));
		EntitiesATC.cookieCreeper = createEntity(CookieCreeperEntity.class, 0xbd7a3f, 0xd5a981, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CookieCreeperEntity::new).size(size));
		EntitiesATC.darkCreeper = createEntity(DarkCreeperEntity.class, 0x0, 0x383838, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DarkCreeperEntity::new).size(size));
		EntitiesATC.demolitionCreeper = createEntity(DemolitionCreeperEntity.class, 0x414041, 0xde0000, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DemolitionCreeperEntity::new).size(size));
		EntitiesATC.earthCreeper = createEntity(EarthCreeperEntity.class, 0x412000, 0x20b218, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EarthCreeperEntity::new).size(size));
		EntitiesATC.fireCreeper = createEntity(FireCreeperEntity.class, 0xff8539, 0xff995a, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireCreeperEntity::new).size(size).setImmuneToFire());
		EntitiesATC.fireworkCreeper = createEntity(FireworkCreeperEntity.class, 0x00eab4, 0x4104cd, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireworkCreeperEntity::new).size(size));
		EntitiesATC.furnaceCreeper = createEntity(FurnaceCreeperEntity.class, 0x8a8a8a, 0xfc0000, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FurnaceCreeperEntity::new).size(size).setImmuneToFire());
		EntitiesATC.lavaCreeper = createEntity(LavaCreeperEntity.class, 0x9c2029, 0xcd385a, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LavaCreeperEntity::new).size(size).setImmuneToFire());
		EntitiesATC.lightningCreeper = createEntity(LightningCreeperEntity.class, 0xf6f28b, 0xbdba29, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LightningCreeperEntity::new).size(size));
		EntitiesATC.luminousCreeper = createEntity(LuminousCreeperEntity.class, 0xfff220, 0xf6f6d5, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LuminousCreeperEntity::new).size(size));
		EntitiesATC.natureCreeper = createEntity(NatureCreeperEntity.class, 0x39b231, 0x628152, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, NatureCreeperEntity::new).size(size));
		EntitiesATC.partyCreeper = createEntity(PartyCreeperEntity.class, 0xff50b4, 0xf64808, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, PartyCreeperEntity::new).size(size));
		EntitiesATC.waterCreeper = createEntity(WaterCreeperEntity.class, 0x294c9c, 0x5a7dc5, FabricEntityTypeBuilder.create(EntityCategory.MONSTER, WaterCreeperEntity::new).size(size));
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
		EntityRendererRegistry.INSTANCE.register(PartyCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(NatureCreeperEntity.class, ((dispatcher, context) -> new ElementalCreeperRenderer(dispatcher)));
	}
}
