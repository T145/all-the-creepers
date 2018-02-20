package T145.elementalcreepers.common;

import java.awt.Color;
import java.util.Set;

import T145.elementalcreepers.client.render.entitiy.RenderBaseCreeper;
import T145.elementalcreepers.common.config.ConfigGeneral;
import T145.elementalcreepers.common.entities.EntityBirthdayCreeper;
import T145.elementalcreepers.common.entities.EntityCookieCreeper;
import T145.elementalcreepers.common.entities.EntityDarkCreeper;
import T145.elementalcreepers.common.entities.EntityEarthCreeper;
import T145.elementalcreepers.common.entities.EntityElectricCreeper;
import T145.elementalcreepers.common.entities.EntityFireCreeper;
import T145.elementalcreepers.common.entities.EntityFireworkCreeper;
import T145.elementalcreepers.common.entities.EntityFurnaceCreeper;
import T145.elementalcreepers.common.entities.EntityGhostCreeper;
import T145.elementalcreepers.common.entities.EntityIceCreeper;
import T145.elementalcreepers.common.entities.EntityLightCreeper;
import T145.elementalcreepers.common.entities.EntityMagmaCreeper;
import T145.elementalcreepers.common.entities.EntityPsychicCreeper;
import T145.elementalcreepers.common.entities.EntityReverseCreeper;
import T145.elementalcreepers.common.entities.EntitySolarCreeper;
import T145.elementalcreepers.common.entities.EntitySpringCreeper;
import T145.elementalcreepers.common.entities.EntityStoneCreeper;
import T145.elementalcreepers.common.entities.EntityWaterCreeper;
import T145.elementalcreepers.common.entities.EntityWindCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(ElementalCreepers.MODID)
public class ModLoader {

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ServerLoader {

		public static final BiomeDictionary.Type[] TYPE_LIST = { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN };
		public static final int CREEPER_GREEN = new Color(894731).getRGB();

		private static int entityId = 0;

		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			final IForgeRegistry<EntityEntry> registry = event.getRegistry();

			/*
		    registerEntity(EntityFriendlyCreeper.class, getCreeperName("FriendlyCreeper"), creeperEggGreen, new Color(215, 113, 211).getRGB(), false, true); // ghost
		    registerEntity(EntityIllusionCreeper.class, getCreeperName("IllusionCreeper"), creeperEggGreen, new Color(158, 158, 158).getRGB());
		    registerEntity(EntitySpiderCreeper.class, getCreeperName("SpiderCreeper"), creeperEggGreen, Color.red.getRGB()); // psy

		    registerEntity(EntityHydrogenCreeper.class, getCreeperName("HydrogenCreeper"), creeperEggGreen, Color.YELLOW.getRGB());
		    registerEntity(EntityEnderCreeper.class, getCreeperName("EnderCreeper"), creeperEggGreen, Color.MAGENTA.getRGB()); // stone

		    registerEntity(EntityBigBadCreep.class, getCreeperName("BigBadCreep"), creeperEggGreen, creeperEggGreen, false, true);
		    registerEntity(EntitySilverCreeper.class, getCreeperName("SilverCreeper"), creeperEggGreen, Color.LIGHT_GRAY.getRGB());*/

			final EntityEntry[] entries = {
					createBuilder("FireCreeper")
					.entity(EntityFireCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(227, 111, 24).getRGB())
					.build(),
					createBuilder("WaterCreeper")
					.entity(EntityWaterCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(59, 115, 205).getRGB())
					.build(),
					createBuilder("ElectricCreeper")
					.entity(EntityElectricCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(251, 234, 57).getRGB())
					.build(),
					createBuilder("CookieCreeper")
					.entity(EntityCookieCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(202, 147, 98).getRGB())
					.build(),
					createBuilder("DarkCreeper")
					.entity(EntityDarkCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(50, 50, 50).getRGB())
					.build(),
					createBuilder("LightCreeper")
					.entity(EntityLightCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(255, 244, 125).getRGB())
					.build(),
					createBuilder("EarthCreeper")
					.entity(EntityEarthCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(93, 50, 0).getRGB())
					.build(),
					createBuilder("MagmaCreeper")
					.entity(EntityMagmaCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(165, 0, 16).getRGB())
					.build(),
					createBuilder("ReverseCreeper")
					.entity(EntityReverseCreeper.class)
					.tracker(80, 3, false)
					.egg(Color.BLACK.getRGB(), CREEPER_GREEN)
					.build(),
					createBuilder("IceCreeper")
					.entity(EntityIceCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, Color.WHITE.getRGB())
					.build(),
					createBuilder("GhostCreeper")
					.entity(EntityGhostCreeper.class)
					.tracker(80, 3, false)
					.egg(99999, 99999)
					.build(),
					createBuilder("PsychicCreeper")
					.entity(EntityPsychicCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(121, 51, 142).getRGB())
					.build(),
					createBuilder("WindCreeper")
					.entity(EntityWindCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(95, 250, 203).getRGB())
					.build(),
					createBuilder("StoneCreeper")
					.entity(EntityStoneCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, Color.DARK_GRAY.getRGB())
					.build(),
					createBuilder("SolarCreeper")
					.entity(EntitySolarCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(0, 25, 100).getRGB())
					.build(),
					createBuilder("CakeCreeper")
					.entity(EntityBirthdayCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, new Color(184, 93, 39).getRGB())
					.build(),
					createBuilder("FireworkCreeper")
					.entity(EntityFireworkCreeper.class)
					.tracker(80, 3, false)
					.egg(Color.BLUE.getRGB(), CREEPER_GREEN)
					.build(),
					createBuilder("SpringCreeper")
					.entity(EntitySpringCreeper.class)
					.tracker(80, 3, false)
					.egg(CREEPER_GREEN, Color.PINK.getRGB())
					.build(),
					createBuilder("FurnaceCreeper")
					.entity(EntityFurnaceCreeper.class)
					.tracker(80, 3, false)
					.egg(Color.DARK_GRAY.getRGB(), CREEPER_GREEN)
					.build()
			};

			registry.registerAll(entries);

			addOverWorldSpawn(EntityFireCreeper.class, ConfigGeneral.fireCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityWaterCreeper.class, ConfigGeneral.waterCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityElectricCreeper.class, ConfigGeneral.electricCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityCookieCreeper.class, ConfigGeneral.cookieCreeperSpawn, 1, 2);
			addOverWorldSpawn(EntityDarkCreeper.class, ConfigGeneral.darkCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityLightCreeper.class, ConfigGeneral.lightCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityEarthCreeper.class, ConfigGeneral.earthCreeperSpawn, 1, 3);
			addNetherSpawn(EntityMagmaCreeper.class, ConfigGeneral.magmaCreeperSpawn, 1, 2);
			addOverWorldSpawn(EntityReverseCreeper.class, ConfigGeneral.reverseCreeperSpawn, 1, 1);
			addOverWorldSpawn(EntityIceCreeper.class, ConfigGeneral.iceCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityPsychicCreeper.class, ConfigGeneral.psychicCreeperSpawn, 1, 3);
			//addOverWorldSpawn(EntityIllusionCreeper.class, ConfigGeneral.illusionCreeperSpawn, 1, 1);
			//addOverWorldSpawn(EntitySpiderCreeper.class, ConfigGeneral.spiderCreeperSpawn, 1, 3);
			//addOverWorldSpawn(EntityFriendlyCreeper.class, ConfigGeneral.friendlyCreeperSpawn, 1, 2, EnumCreatureType.CREATURE);
			addOverWorldSpawn(EntityWindCreeper.class, ConfigGeneral.windCreeperSpawn, 1, 2);
			//addOverWorldSpawn(EntityHydrogenCreeper.class, ConfigGeneral.hydrogenCreeperSpawn, 1, 1);
			//addOverWorldSpawn(EntityEnderCreeper.class, ConfigGeneral.enderCreeperSpawn, 1, 2);
			//addEndSpawn(EntityEnderCreeper.class, ConfigGeneral.enderCreeperSpawn * 5, 1, 3);
			addOverWorldSpawn(EntityStoneCreeper.class, ConfigGeneral.stoneCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntitySolarCreeper.class, ConfigGeneral.solarCreeperSpawn, 1, 1);
			addOverWorldSpawn(EntityBirthdayCreeper.class, ConfigGeneral.cakeCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityFireworkCreeper.class, ConfigGeneral.fireworkCreeperSpawn, 1, 3);
			//addOverWorldSpawn(EntityBigBadCreep.class, ConfigGeneral.bigBadSpawn, 1, 1);
			addOverWorldSpawn(EntitySpringCreeper.class, ConfigGeneral.springCreeperSpawn, 1, 3);
			addOverWorldSpawn(EntityFurnaceCreeper.class, ConfigGeneral.furnaceCreeperSpawn, 1, 3);
		}

		private static int entityID = 0;

		/**
		 * Create an {@link EntityEntryBuilder} with the specified unlocalised/registry
		 * name and an automatically-assigned network ID.
		 *
		 * @param name
		 *            The name
		 * @param <E>
		 *            The entity type
		 * @return The builder
		 */
		private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
			final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
			final ResourceLocation registryName = new ResourceLocation(ElementalCreepers.MODID, name);
			return builder.id(registryName, entityID++).name(ElementalCreepers.MODID + ":" + name);
		}

		/**
		 * Get an array of {@link Biome}s with the specified
		 * {@link BiomeDictionary.Type}.
		 *
		 * @param type
		 *            The Type
		 * @return An array of Biomes
		 */
		private static Biome[] getBiomes(final BiomeDictionary.Type type) {
			return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
		}

		/**
		 * Add a spawn list entry for {@code classToAdd} in each {@link Biome} with an
		 * entry for {@code classToCopy} using the same weight and group count.
		 *
		 * @param classToAdd
		 *            The class to add spawn entries for
		 * @param creatureTypeToAdd
		 *            The EnumCreatureType to add spawn entries for
		 * @param classToCopy
		 *            The class to copy spawn entries from
		 * @param creatureTypeToCopy
		 *            The EnumCreatureType to copy spawn entries from
		 */
		private static void copySpawns(final Class<? extends EntityLiving> classToAdd, final EnumCreatureType creatureTypeToAdd, final Class<? extends EntityLiving> classToCopy, final EnumCreatureType creatureTypeToCopy) {
			for (final Biome biome : ForgeRegistries.BIOMES) {
				biome.getSpawnableList(creatureTypeToCopy).stream().filter(entry -> entry.entityClass == classToCopy).findFirst().ifPresent(spawnListEntry -> biome.getSpawnableList(creatureTypeToAdd).add(new Biome.SpawnListEntry(classToAdd, spawnListEntry.itemWeight, spawnListEntry.minGroupCount, spawnListEntry.maxGroupCount)));
			}
		}

		public static void addOverWorldSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max, EnumCreatureType type) {
			for (int i = 0; i < TYPE_LIST.length; i++) {
				Set<Biome> biomes = BiomeDictionary.getBiomes(TYPE_LIST[i]);
				EntityRegistry.addSpawn(entityClass, spawnprob, min, max, type, biomes.toArray(new Biome[biomes.size()]));
			}
		}

		public static void addOverWorldSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			for (int i = 0; i < TYPE_LIST.length; i++) {
				Set<Biome> biomes = BiomeDictionary.getBiomes(TYPE_LIST[i]);
				EntityRegistry.addSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
			}
		}

		public static void addNetherSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			Set<Biome> biomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER);
			EntityRegistry.addSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
		}

		public static void addEndSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			Set<Biome> biomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.END);
			EntityRegistry.addSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
		}
	}

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ClientLoader {

		/*
	    registerEntity(EntityFriendlyCreeper.class, getCreeperName("FriendlyCreeper"), creeperEggGreen, new Color(215, 113, 211).getRGB(), false, true);
	    registerEntity(EntityIllusionCreeper.class, getCreeperName("IllusionCreeper"), creeperEggGreen, new Color(158, 158, 158).getRGB());
	    registerEntity(EntitySpiderCreeper.class, getCreeperName("SpiderCreeper"), creeperEggGreen, Color.red.getRGB());

	    registerEntity(EntityHydrogenCreeper.class, getCreeperName("HydrogenCreeper"), creeperEggGreen, Color.YELLOW.getRGB());
	    registerEntity(EntityEnderCreeper.class, getCreeperName("EnderCreeper"), creeperEggGreen, Color.MAGENTA.getRGB());

	    registerEntity(EntityBigBadCreep.class, getCreeperName("BigBadCreep"), creeperEggGreen, creeperEggGreen, false, true);
	    registerEntity(EntitySilverCreeper.class, getCreeperName("SilverCreeper"), creeperEggGreen, Color.LIGHT_GRAY.getRGB());*/

		@SubscribeEvent
		public static void onModelRegistration(ModelRegistryEvent event) {
			registerRenderer(EntityFireCreeper.class, "firecreeper");
			registerRenderer(EntityWaterCreeper.class, "watercreeper");
			registerRenderer(EntityElectricCreeper.class, "electriccreeper");
			registerRenderer(EntityCookieCreeper.class, "cookiecreeper");
			registerRenderer(EntityDarkCreeper.class, "darkcreeper");
			registerRenderer(EntityLightCreeper.class, "lightcreeper");
			registerRenderer(EntityEarthCreeper.class, "earthcreeper");
			registerRenderer(EntityMagmaCreeper.class, "magmacreeper");
			registerRenderer(EntityReverseCreeper.class, "reversecreeper");
			registerRenderer(EntityIceCreeper.class, "icecreeper");
			registerRenderer(EntityGhostCreeper.class, "ghostcreeper");
			registerRenderer(EntityPsychicCreeper.class, "psychiccreeper");
			registerRenderer(EntityWindCreeper.class, "windcreeper");
			registerRenderer(EntityStoneCreeper.class, "stonecreeper");
			registerRenderer(EntitySolarCreeper.class, "solarcreeper");
			registerRenderer(EntityBirthdayCreeper.class, "cakecreeper");
			registerRenderer(EntityFireworkCreeper.class, "fireworkcreeper");
			registerRenderer(EntitySpringCreeper.class, "springcreeper");
			registerRenderer(EntityFurnaceCreeper.class, "furnacecreeper");
		}

		private static void registerRenderer(Class creeper, String textureName) {
			RenderingRegistry.registerEntityRenderingHandler(creeper, renderManager -> new RenderBaseCreeper(renderManager, textureName));
		}
	}
}