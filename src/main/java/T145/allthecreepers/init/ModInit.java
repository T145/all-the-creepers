package T145.allthecreepers.init;

import java.util.List;

import T145.allthecreepers.api.constants.RegistryATC;
import T145.allthecreepers.config.ModConfig;
import T145.allthecreepers.entities.CakeCreeperEntity;
import T145.allthecreepers.entities.CookieCreeperEntity;
import T145.allthecreepers.entities.FireworkCreeperEntity;
import T145.allthecreepers.entities.PartyCreeperEntity;
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
	public static final EntityType<CakeCreeperEntity> CAKE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CakeCreeperEntity::new).size(size).build();
	public static final EntityType<CookieCreeperEntity> COOKIE_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, CookieCreeperEntity::new).size(size).build();
	public static final EntityType<FireworkCreeperEntity> FIREWORK_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, FireworkCreeperEntity::new).size(size).build();
	public static final EntityType<PartyCreeperEntity> PARTY_CREEPER = FabricEntityTypeBuilder.create(EntityCategory.MONSTER, PartyCreeperEntity::new).size(size).build();

	private SpawnEggItem getSpawnEgg(EntityType<?> type, int backgroundColor, int foregroundColor) {
		return new SpawnEggItem(type, backgroundColor, foregroundColor, new Item.Settings().itemGroup(ItemGroup.MISC));
	}

	private void registerCreeper(EntityType<? extends CreeperEntity> type, String name, int eggBackgroundColor, int eggForegroundColor) {
		Registry.register(Registry.ENTITY_TYPE, RegistryATC.getIdentifier(name), type);
		Registry.register(Registry.ITEM, RegistryATC.getIdentifier(String.format("%s_spawn_egg", name)), getSpawnEgg(type, eggBackgroundColor, eggForegroundColor));
	}

	private static void registerSpawns(List<SpawnEntry> spawns, ModConfig config) {
		if (config.cakeCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(CAKE_CREEPER, config.cakeCreeperSpawnWeight, config.cakeCreeperSpawnMinGroupSize, config.cakeCreeperSpawnMaxGroupSize));
		}

		if (config.cookieCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(COOKIE_CREEPER, config.cookieCreeperSpawnWeight, config.cookieCreeperSpawnMinGroupSize, config.cookieCreeperSpawnMaxGroupSize));
		}

		if (config.fireworkCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(FIREWORK_CREEPER, config.fireworkCreeperSpawnWeight, config.fireworkCreeperSpawnMinGroupSize, config.fireworkCreeperSpawnMaxGroupSize));
		}

		if (config.partyCreeperSpawnWeight > 0) {
			spawns.add(new SpawnEntry(PARTY_CREEPER, config.partyCreeperSpawnWeight, config.partyCreeperSpawnMinGroupSize, config.partyCreeperSpawnMaxGroupSize));
		}
	}

	@Override
	public void onInitialize() {
		ModConfig config = AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new).getConfig();

		registerCreeper(CAKE_CREEPER, "cake_creeper", 0xba5003, 0xeae9eb);
		registerCreeper(COOKIE_CREEPER, "cookie_creeper",  0xbd7a3f, 0xd5a981);
		registerCreeper(FIREWORK_CREEPER, "firework_creeper", 0x00eab4, 0x4104cd);
		registerCreeper(PARTY_CREEPER, "party_creeper", 0xff50b4, 0xf64808);

		for (final Biome biome : Registry.BIOME) {
			List<SpawnEntry> spawns = biome.getEntitySpawnList(EntityCategory.MONSTER);
			spawns.stream().filter(spawn -> spawn.type == EntityType.CREEPER).findFirst().ifPresent(spawn -> registerSpawns(spawns, config));

			if (config.debug) {
				System.out.println(String.format("Registered to biome: %s", biome.getClass()));
				spawns.stream().forEach(System.out::println);
			}
		}
	}
}
