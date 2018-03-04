package T145.elementalcreepers.core;

import java.util.List;
import java.util.Set;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.client.render.entity.RenderAngryCreeper;
import T145.elementalcreepers.client.render.entity.RenderBaseCreeper;
import T145.elementalcreepers.client.render.entity.RenderFriendlyCreeper;
import T145.elementalcreepers.client.render.entity.RenderSpiderCreeper;
import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.EntityBallisticCreeper;
import T145.elementalcreepers.entities.EntityCakeCreeper;
import T145.elementalcreepers.entities.EntityCookieCreeper;
import T145.elementalcreepers.entities.EntityDarkCreeper;
import T145.elementalcreepers.entities.EntityEarthCreeper;
import T145.elementalcreepers.entities.EntityEnderCreeper;
import T145.elementalcreepers.entities.EntityFireCreeper;
import T145.elementalcreepers.entities.EntityFireworkCreeper;
import T145.elementalcreepers.entities.EntityFriendlyCreeper;
import T145.elementalcreepers.entities.EntityFurnaceCreeper;
import T145.elementalcreepers.entities.EntityGhostCreeper;
import T145.elementalcreepers.entities.EntityIceCreeper;
import T145.elementalcreepers.entities.EntityIllusionCreeper;
import T145.elementalcreepers.entities.EntityLightCreeper;
import T145.elementalcreepers.entities.EntityLightningCreeper;
import T145.elementalcreepers.entities.EntityMagmaCreeper;
import T145.elementalcreepers.entities.EntityPsychicCreeper;
import T145.elementalcreepers.entities.EntityReverseCreeper;
import T145.elementalcreepers.entities.EntitySpiderCreeper;
import T145.elementalcreepers.entities.EntitySpringCreeper;
import T145.elementalcreepers.entities.EntityStoneCreeper;
import T145.elementalcreepers.entities.EntityWaterCreeper;
import T145.elementalcreepers.entities.EntityWindCreeper;
import T145.elementalcreepers.entities.EntityZombieCreeper;
import T145.elementalcreepers.lib.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(ElementalCreepers.MODID)
public class ModLoader {

	private ModLoader() {}

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ServerLoader {

		private ServerLoader() {}

		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			final EntityEntry[] entries = {
					createBuilder("CakeCreeper")
					.entity(EntityCakeCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("CookieCreeper")
					.entity(EntityCookieCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("DarkCreeper")
					.entity(EntityDarkCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("EarthCreeper")
					.entity(EntityEarthCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("EnderCreeper")
					.entity(EntityEnderCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("FireCreeper")
					.entity(EntityFireCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("FireworkCreeper")
					.entity(EntityFireworkCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("FurnaceCreeper")
					.entity(EntityFurnaceCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("FriendlyCreeper")
					.entity(EntityFriendlyCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("GhostCreeper")
					.entity(EntityGhostCreeper.class)
					.tracker(80, 3, true)
					.build(),
					createBuilder("BallisticCreeper")
					.entity(EntityBallisticCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("IceCreeper")
					.entity(EntityIceCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("IllusionCreeper")
					.entity(EntityIllusionCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("LightCreeper")
					.entity(EntityLightCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("LightningCreeper")
					.entity(EntityLightningCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("MagmaCreeper")
					.entity(EntityMagmaCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("PsychicCreeper")
					.entity(EntityPsychicCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("ReverseCreeper")
					.entity(EntityReverseCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("SpiderCreeper")
					.entity(EntitySpiderCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("SpringCreeper")
					.entity(EntitySpringCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("StoneCreeper")
					.entity(EntityStoneCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("WaterCreeper")
					.entity(EntityWaterCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("WindCreeper")
					.entity(EntityWindCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("ZombieCreeper")
					.entity(EntityZombieCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build()
			};

			for (EntityEntry entry : entries) {
				event.getRegistry().register(entry);
				Constants.CREEPER_LIST.add((Class<? extends EntityCreeper>) entry.getEntityClass());
			}

			if (ModConfig.general.reasonableSpawnRates) {
				addOverworldSpawn(EntityFireCreeper.class, ModConfig.spawnRate.fireCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityWaterCreeper.class, ModConfig.spawnRate.waterCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityLightningCreeper.class, ModConfig.spawnRate.electricCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityCookieCreeper.class, ModConfig.spawnRate.cookieCreeperSpawn, 1, 2);
				addOverworldSpawn(EntityDarkCreeper.class, ModConfig.spawnRate.darkCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityLightCreeper.class, ModConfig.spawnRate.lightCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityEarthCreeper.class, ModConfig.spawnRate.earthCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityReverseCreeper.class, ModConfig.spawnRate.reverseCreeperSpawn, 1, 1);
				addOverworldSpawn(EntityIceCreeper.class, ModConfig.spawnRate.iceCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityPsychicCreeper.class, ModConfig.spawnRate.psychicCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityIllusionCreeper.class, ModConfig.spawnRate.illusionCreeperSpawn, 1, 1);
				addOverworldSpawn(EntitySpiderCreeper.class, ModConfig.spawnRate.spiderCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityWindCreeper.class, ModConfig.spawnRate.windCreeperSpawn, 1, 2);
				addOverworldSpawn(EntityBallisticCreeper.class, ModConfig.spawnRate.hydrogenCreeperSpawn, 1, 1);
				addOverworldSpawn(EntityEnderCreeper.class, ModConfig.spawnRate.enderCreeperSpawn, 1, 2);
				addOverworldSpawn(EntityStoneCreeper.class, ModConfig.spawnRate.stoneCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityCakeCreeper.class, ModConfig.spawnRate.cakeCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityFireworkCreeper.class, ModConfig.spawnRate.fireworkCreeperSpawn, 1, 3);
				addOverworldSpawn(EntitySpringCreeper.class, ModConfig.spawnRate.springCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityFurnaceCreeper.class, ModConfig.spawnRate.furnaceCreeperSpawn, 1, 3);
				addOverworldSpawn(EntityZombieCreeper.class, ModConfig.spawnRate.zombieCreeperSpawn, 1, 1);
			} else {
				copyCreeperSpawns(EntityCakeCreeper.class);
				copyCreeperSpawns(EntityCookieCreeper.class);
				copyCreeperSpawns(EntityDarkCreeper.class);
				copyCreeperSpawns(EntityEarthCreeper.class);
				copyCreeperSpawns(EntityEnderCreeper.class);
				copyCreeperSpawns(EntityFireCreeper.class);
				copyCreeperSpawns(EntityFireworkCreeper.class);
				copyCreeperSpawns(EntityFurnaceCreeper.class);
				copyCreeperSpawns(EntityBallisticCreeper.class);
				copyCreeperSpawns(EntityIceCreeper.class);
				copyCreeperSpawns(EntityIllusionCreeper.class);
				copyCreeperSpawns(EntityLightCreeper.class);
				copyCreeperSpawns(EntityLightningCreeper.class);
				copyCreeperSpawns(EntityMagmaCreeper.class);
				copyCreeperSpawns(EntityPsychicCreeper.class);
				copyCreeperSpawns(EntityReverseCreeper.class);
				copyCreeperSpawns(EntitySpiderCreeper.class);
				copyCreeperSpawns(EntitySpringCreeper.class);
				copyCreeperSpawns(EntityStoneCreeper.class);
				copyCreeperSpawns(EntityWaterCreeper.class);
				copyCreeperSpawns(EntityWindCreeper.class);
				copyCreeperSpawns(EntityZombieCreeper.class);
			}

			addOverworldSpawn(EntityFriendlyCreeper.class, ModConfig.spawnRate.friendlyCreeperSpawn, 1, 2, EnumCreatureType.CREATURE);
			addNetherSpawn(EntityFireCreeper.class, ModConfig.spawnRate.fireCreeperSpawn, 1, 3);
			addNetherSpawn(EntityMagmaCreeper.class, ModConfig.spawnRate.magmaCreeperSpawn, 1, 2);
			addEndSpawn(EntityEnderCreeper.class, ModConfig.spawnRate.enderCreeperSpawn * 5, 1, 3);

			// TODO: Add dimension blacklist/whitelist
		}

		private static int entityID = 0;

		private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
			final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
			final ResourceLocation registryName = new ResourceLocation(ElementalCreepers.MODID, name);
			return builder.id(registryName, entityID++).name(ElementalCreepers.MODID + ":" + name);
		}

		private static BiomeDictionary.Type[] validOverworldBiomeTypes = { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN };

		public static void addOverworldSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max, EnumCreatureType type) {
			for (BiomeDictionary.Type biomeType : validOverworldBiomeTypes) {
				Set<Biome> biomeSet = BiomeDictionary.getBiomes(biomeType);
				EntityRegistry.addSpawn(entityClass, spawnprob, min, max, type, biomeSet.toArray(new Biome[biomeSet.size()]));
			}
		}

		public static void addOverworldSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			addOverworldSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER);
		}

		public static void addNetherSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			Set<Biome> biomeSet = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER);
			EntityRegistry.addSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER, biomeSet.toArray(new Biome[biomeSet.size()]));
		}

		public static void addEndSpawn(Class<? extends EntityLiving> entityClass, int spawnprob, int min, int max) {
			Set<Biome> biomeSet = BiomeDictionary.getBiomes(BiomeDictionary.Type.END);
			EntityRegistry.addSpawn(entityClass, spawnprob, min, max, EnumCreatureType.MONSTER, biomeSet.toArray(new Biome[biomeSet.size()]));
		}

		private static Biome[] getBiomes(final BiomeDictionary.Type type) {
			return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
		}

		private static void copySpawns(final Class<? extends EntityLiving> classToAdd, final EnumCreatureType creatureTypeToAdd, final Class<? extends EntityLiving> classToCopy, final EnumCreatureType creatureTypeToCopy) {
			for (final Biome biome : ForgeRegistries.BIOMES) {
				biome.getSpawnableList(creatureTypeToCopy).stream().filter(entry -> entry.entityClass == classToCopy).findFirst().ifPresent(spawnListEntry -> biome.getSpawnableList(creatureTypeToAdd).add(new Biome.SpawnListEntry(classToAdd, spawnListEntry.itemWeight, spawnListEntry.minGroupCount, spawnListEntry.maxGroupCount)));
			}
		}

		private static void copyCreeperSpawns(final Class<? extends EntityLiving> classToAdd) {
			copySpawns(classToAdd, EnumCreatureType.MONSTER, EntityCreeper.class, EnumCreatureType.MONSTER);
		}

		@SubscribeEvent
		public static void onEntityDeath(LivingDeathEvent event) {
			DamageSource damage = event.getSource();
			Entity immediateSource = damage.getImmediateSource();
			Entity trueSource = damage.getTrueSource();
			EntityLivingBase entity = event.getEntityLiving();
			boolean killedByPlayer = damage.getDamageType().equals("player") || trueSource instanceof EntityPlayer;

			if (killedByPlayer && entity instanceof EntityCreeper && !(entity instanceof EntityGhostCreeper) && !(entity instanceof EntityFriendlyCreeper)) {
				if (entity instanceof EntityIllusionCreeper && ((EntityIllusionCreeper) entity).isIllusion()) {
					return;
				}

				if (entity.world.rand.nextInt(100) < ModConfig.general.ghostCreeperChance) {
					EntityGhostCreeper ghost = new EntityGhostCreeper(entity.world);
					ghost.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
					entity.world.spawnEntity(ghost);
				}
			}

			// TODO: Add HashSet entity blacklist
			if (entity instanceof EntityLivingBase && entity instanceof IMob) {
				int radius = ModConfig.general.zombieCreeperRange;
				AxisAlignedBB bb = new AxisAlignedBB(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius);
				List<EntityZombieCreeper> zombles = entity.world.getEntitiesWithinAABB(EntityZombieCreeper.class, bb, creature -> entity != creature);

				if (!zombles.isEmpty()) {
					if (zombles.size() == 1) {
						zombles.get(0).addCreeper();
					} else {
						// we have more, and determine which is closest
						float dist = Float.POSITIVE_INFINITY;
						EntityZombieCreeper closest = null;

						for (EntityZombieCreeper zomble : zombles) {
							float newDist = entity.getDistance(zomble);

							if (newDist < dist) {
								dist = newDist;
								closest = zomble;
							}
						}

						if (closest != null) {
							closest.addCreeper();
						}
					}
				}
			}
		}

		@SubscribeEvent
		public static void onEntityHurt(LivingHurtEvent event) {
			Entity entity = event.getEntity();
			DamageSource damage = event.getSource();

			if (entity instanceof EntitySpringCreeper && damage == DamageSource.FALL) {
				EntitySpringCreeper creeper = (EntitySpringCreeper) entity;

				if (!creeper.world.isRemote && creeper.isSprung()) {
					creeper.world.createExplosion(creeper, creeper.posX, creeper.posY - 2.0D, creeper.posZ, creeper.getExplosionPower() * ((event.getAmount() < 6.0F ? 6.0F : event.getAmount()) / 6.0F), creeper.world.getGameRules().getBoolean("mobGriefing"));
					creeper.setDead();
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerJoinedWorld(PlayerLoggedInEvent event) {
			if (ModConfig.general.checkForUpdates && UpdateChecker.hasUpdate()) {
				event.player.sendMessage(UpdateChecker.getUpdateNotification());
			}
		}
	}

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ClientLoader {

		private ClientLoader() {}

		@SubscribeEvent
		public static void onModelRegistration(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, manager -> new RenderAngryCreeper(manager));
			registerRenderer(EntityCakeCreeper.class, "cakecreeper");
			registerRenderer(EntityCookieCreeper.class, "cookiecreeper");
			registerRenderer(EntityDarkCreeper.class, "darkcreeper");
			registerRenderer(EntityEarthCreeper.class, "earthcreeper");
			registerRenderer(EntityEnderCreeper.class, "endercreeper");
			registerRenderer(EntityFireCreeper.class, "firecreeper");
			registerRenderer(EntityFireworkCreeper.class, "fireworkcreeper");
			registerRenderer(EntityFurnaceCreeper.class, "furnacecreeper");
			RenderingRegistry.registerEntityRenderingHandler(EntityGhostCreeper.class, manager -> new RenderBaseCreeper(manager, true));
			RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyCreeper.class, manager -> new RenderFriendlyCreeper(manager));
			registerRenderer(EntityBallisticCreeper.class, "hydrogencreeper");
			registerRenderer(EntityIceCreeper.class, "icecreeper");
			registerRenderer(EntityIllusionCreeper.class, "illusioncreeper");
			registerRenderer(EntityLightCreeper.class, "lightcreeper");
			registerRenderer(EntityLightningCreeper.class, "electriccreeper");
			registerRenderer(EntityMagmaCreeper.class, "magmacreeper");
			registerRenderer(EntityPsychicCreeper.class, "psychiccreeper");
			registerRenderer(EntityReverseCreeper.class, "reversecreeper");
			RenderingRegistry.registerEntityRenderingHandler(EntitySpiderCreeper.class, manager -> new RenderSpiderCreeper(manager));
			registerRenderer(EntitySpringCreeper.class, "springcreeper");
			registerRenderer(EntityStoneCreeper.class, "stonecreeper");
			registerRenderer(EntityWaterCreeper.class, "watercreeper");
			registerRenderer(EntityWindCreeper.class, "windcreeper");
			registerRenderer(EntityZombieCreeper.class, "zombiecreeper");
		}

		private static void registerRenderer(Class creeper, String textureName) {
			RenderingRegistry.registerEntityRenderingHandler(creeper, manager -> new RenderBaseCreeper(manager, textureName));
		}
	}
}