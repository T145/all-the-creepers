package T145.allthecreepers.init;

import java.util.List;

import T145.allthecreepers.api.constants.RegistryATC;
import T145.allthecreepers.config.ModConfig;
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
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnEntry;

public class ModInit implements ModInitializer {

	private static final EntitySize size = EntitySize.constant(0.6F, 1.7F);
	public static final EntityType<BallisticCreeperEntity> BALLISTIC_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, BallisticCreeperEntity::new).setImmuneToFire().size(size).build();
	public static final EntityType<CakeCreeperEntity> CAKE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CakeCreeperEntity::new).size(size).build();
	public static final EntityType<CookieCreeperEntity> COOKIE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CookieCreeperEntity::new).size(size).build();
	public static final EntityType<DarkCreeperEntity> DARK_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DarkCreeperEntity::new).size(size).build();
	public static final EntityType<DemolitionCreeperEntity> DEMOLITION_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, DemolitionCreeperEntity::new).size(size).build();
	public static final EntityType<EarthCreeperEntity> EARTH_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, EarthCreeperEntity::new).size(size).build();
	public static final EntityType<FireCreeperEntity> FIRE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireCreeperEntity::new).setImmuneToFire().size(size).build();
	public static final EntityType<FireworkCreeperEntity> FIREWORK_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireworkCreeperEntity::new).size(size).build();
	public static final EntityType<FurnaceCreeperEntity> FURNACE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FurnaceCreeperEntity::new).setImmuneToFire().size(size).build();
	public static final EntityType<LavaCreeperEntity> LAVA_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LavaCreeperEntity::new).setImmuneToFire().size(size).build();
	public static final EntityType<LightningCreeperEntity> LIGHTNING_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LightningCreeperEntity::new).size(size).build();
	public static final EntityType<LuminousCreeperEntity> LUMINOUS_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, LuminousCreeperEntity::new).size(size).build();
	public static final EntityType<NatureCreeperEntity> NATURE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, NatureCreeperEntity::new).size(size).build();
	public static final EntityType<PartyCreeperEntity> PARTY_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, PartyCreeperEntity::new).size(size).build();
	public static final EntityType<WaterCreeperEntity> WATER_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, WaterCreeperEntity::new).setImmuneToFire().size(size).build();

	private SpawnEggItem getSpawnEgg(EntityType<?> type, int backgroundColor, int foregroundColor) {
		return new SpawnEggItem(type, backgroundColor, foregroundColor, new Item.Settings().itemGroup(ItemGroup.MISC));
	}

	private void registerCreeper(EntityType<? extends CreeperEntity> type, String name, int eggBackgroundColor, int eggForegroundColor) {
		Registry.register(Registry.ENTITY_TYPE, RegistryATC.getIdentifier(name), type);
		Registry.register(Registry.ITEM, RegistryATC.getIdentifier(String.format("%s_spawn_egg", name)), getSpawnEgg(type, eggBackgroundColor, eggForegroundColor));
	}

	private static void registerSpawns(List<SpawnEntry> spawns, ModConfig config) {
		if (config.ballisticCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(BALLISTIC_CREEPER, config.ballisticCreeperSpawnWeight, config.ballisticCreeperSpawnMinGroupSize, config.ballisticCreeperSpawnMaxGroupSize));
		}

		if (config.cakeCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(CAKE_CREEPER, config.cakeCreeperSpawnWeight, config.cakeCreeperSpawnMinGroupSize, config.cakeCreeperSpawnMaxGroupSize));
		}

		if (config.cookieCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(COOKIE_CREEPER, config.cookieCreeperSpawnWeight, config.cookieCreeperSpawnMinGroupSize, config.cookieCreeperSpawnMaxGroupSize));
		}

		if (config.darkCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(DARK_CREEPER, config.darkCreeperSpawnWeight, config.darkCreeperSpawnMinGroupSize, config.darkCreeperSpawnMaxGroupSize));
		}

		if (config.demolitionCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(DEMOLITION_CREEPER, config.demolitionCreeperSpawnWeight, config.demolitionCreeperSpawnMinGroupSize, config.demolitionCreeperSpawnMaxGroupSize));
		}

		if (config.earthCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(EARTH_CREEPER, config.earthCreeperSpawnWeight, config.earthCreeperSpawnMinGroupSize, config.earthCreeperSpawnMaxGroupSize));
		}

		if (config.fireCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(FIRE_CREEPER, config.fireCreeperSpawnWeight, config.fireCreeperSpawnMinGroupSize, config.fireCreeperSpawnMaxGroupSize));
		}

		if (config.fireworkCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(FIREWORK_CREEPER, config.fireworkCreeperSpawnWeight, config.fireworkCreeperSpawnMinGroupSize, config.fireworkCreeperSpawnMaxGroupSize));
		}

		if (config.furnaceCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(FURNACE_CREEPER, config.furnaceCreeperSpawnWeight, config.furnaceCreeperSpawnMinGroupSize, config.furnaceCreeperSpawnMaxGroupSize));
		}

		if (config.lavaCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(LAVA_CREEPER, config.lavaCreeperSpawnWeight, config.lavaCreeperSpawnMinGroupSize, config.lavaCreeperSpawnMaxGroupSize));
		}

		if (config.lightningCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(LIGHTNING_CREEPER, config.lightningCreeperSpawnWeight, config.lightningCreeperSpawnMinGroupSize, config.lightningCreeperSpawnMaxGroupSize));
		}

		if (config.luminousCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(LUMINOUS_CREEPER, config.luminousCreeperSpawnWeight, config.luminousCreeperSpawnMinGroupSize, config.luminousCreeperSpawnMaxGroupSize));
		}

		if (config.natureCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(NATURE_CREEPER, config.natureCreeperSpawnWeight, config.natureCreeperSpawnMinGroupSize, config.natureCreeperSpawnMaxGroupSize));
		}

		if (config.partyCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(PARTY_CREEPER, config.partyCreeperSpawnWeight, config.partyCreeperSpawnMinGroupSize, config.partyCreeperSpawnMaxGroupSize));
		}

		if (config.waterCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(WATER_CREEPER, config.waterCreeperSpawnWeight, config.waterCreeperSpawnMinGroupSize, config.waterCreeperSpawnMaxGroupSize));
		}
	}

	@Override
	public void onInitialize() {
		ModConfig config = AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new).getConfig();

		registerCreeper(BALLISTIC_CREEPER, "ballistic_creeper", 0x195906, 0xd2d000);
		registerCreeper(CAKE_CREEPER, "cake_creeper", 0xba5003, 0xeae9eb);
		registerCreeper(COOKIE_CREEPER, "cookie_creeper",  0xbd7a3f, 0xd5a981);
		registerCreeper(DARK_CREEPER, "dark_creeper", 0x0, 0x383838);
		registerCreeper(DEMOLITION_CREEPER, "demolition_creeper", 0x414041, 0xde0000);
		registerCreeper(EARTH_CREEPER, "earth_creeper", 0x412000, 0x20b218);
		registerCreeper(FIRE_CREEPER, "fire_creeper", 0xff8539, 0xff995a);
		registerCreeper(FIREWORK_CREEPER, "firework_creeper", 0x00eab4, 0x4104cd);
		registerCreeper(FURNACE_CREEPER, "furnace_creeper", 0x8a8a8a, 0xfc0000);
		registerCreeper(LAVA_CREEPER, "lava_creeper", 0x9c2029, 0xcd385a);
		registerCreeper(LIGHTNING_CREEPER, "lightning_creeper", 0xf6f28b, 0xbdba29);
		registerCreeper(LUMINOUS_CREEPER, "luminous_creeper", 0xfff220, 0xf6f6d5);
		registerCreeper(NATURE_CREEPER, "nature_creeper", 0x39b231, 0x628152);
		registerCreeper(PARTY_CREEPER, "party_creeper", 0xff50b4, 0xf64808);
		registerCreeper(WATER_CREEPER, "water_creeper", 0x294c9c, 0x5a7dc5);

		for (final Biome biome : Registry.BIOME) {
			List<SpawnEntry> spawns = biome.getEntitySpawnList(EntityCategory.MONSTER);
			spawns.stream().filter(spawn -> spawn.type == EntityType.CREEPER).findFirst().ifPresent(spawn -> registerSpawns(spawns, config));

			if (config.debug) {
				System.out.println(String.format("[allthecreepers] Registered to biome: %s", biome.getClass()));
				spawns.stream().forEach(System.out::println);
			}
		}
	}
}
