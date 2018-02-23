package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.client.render.entity.RenderElementalCreeper;
import T145.elementalcreepers.client.render.entity.RenderSpiderCreeper;
import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.EntityCakeCreeper;
import T145.elementalcreepers.entities.EntityCookieCreeper;
import T145.elementalcreepers.entities.EntityDarkCreeper;
import T145.elementalcreepers.entities.EntityEarthCreeper;
import T145.elementalcreepers.entities.EntityElectricCreeper;
import T145.elementalcreepers.entities.EntityEnderCreeper;
import T145.elementalcreepers.entities.EntityFireCreeper;
import T145.elementalcreepers.entities.EntityFireworkCreeper;
import T145.elementalcreepers.entities.EntityFurnaceCreeper;
import T145.elementalcreepers.entities.EntityGhostCreeper;
import T145.elementalcreepers.entities.EntityHydrogenCreeper;
import T145.elementalcreepers.entities.EntityIceCreeper;
import T145.elementalcreepers.entities.EntityIllusionCreeper;
import T145.elementalcreepers.entities.EntityLightCreeper;
import T145.elementalcreepers.entities.EntityMagmaCreeper;
import T145.elementalcreepers.entities.EntityPsychicCreeper;
import T145.elementalcreepers.entities.EntityReverseCreeper;
import T145.elementalcreepers.entities.EntitySpiderCreeper;
import T145.elementalcreepers.entities.EntitySpringCreeper;
import T145.elementalcreepers.entities.EntityStoneCreeper;
import T145.elementalcreepers.entities.EntityWaterCreeper;
import T145.elementalcreepers.entities.EntityWindCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(ElementalCreepers.MODID)
public class ModLoader {

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ServerLoader {

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
					createBuilder("GhostCreeper")
					.entity(EntityGhostCreeper.class)
					.tracker(80, 3, true)
					.egg(0x0DA70B, 0x101010)
					.build(),
					createBuilder("HydrogenCreeper")
					.entity(EntityHydrogenCreeper.class)
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
					createBuilder("ElectricCreeper")
					.entity(EntityElectricCreeper.class)
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
					.build()
			};

			event.getRegistry().registerAll(entries);

			copyCreeperSpawns(EntityCakeCreeper.class);
			copyCreeperSpawns(EntityCookieCreeper.class);
			copyCreeperSpawns(EntityDarkCreeper.class);
			copyCreeperSpawns(EntityEarthCreeper.class);
			copyCreeperSpawns(EntityEnderCreeper.class);
			copyCreeperSpawns(EntityFireCreeper.class);
			copyCreeperSpawns(EntityFireworkCreeper.class);
			copyCreeperSpawns(EntityFurnaceCreeper.class);
			copyCreeperSpawns(EntityHydrogenCreeper.class);
			copyCreeperSpawns(EntityIceCreeper.class);
			copyCreeperSpawns(EntityIllusionCreeper.class);
			copyCreeperSpawns(EntityLightCreeper.class);
			copyCreeperSpawns(EntityElectricCreeper.class);
			copyCreeperSpawns(EntityMagmaCreeper.class);
			copyCreeperSpawns(EntityPsychicCreeper.class);
			copyCreeperSpawns(EntityReverseCreeper.class);
			copyCreeperSpawns(EntitySpiderCreeper.class);
			copyCreeperSpawns(EntitySpringCreeper.class);
			copyCreeperSpawns(EntityStoneCreeper.class);
			copyCreeperSpawns(EntityWaterCreeper.class);
			copyCreeperSpawns(EntityWindCreeper.class);
		}

		private static int entityID = 0;

		private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
			final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
			final ResourceLocation registryName = new ResourceLocation(ElementalCreepers.MODID, name);
			return builder.id(registryName, entityID++).name(ElementalCreepers.MODID + ":" + name);
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

			if (killedByPlayer && entity instanceof EntityCreeper && !(entity instanceof EntityGhostCreeper)) {
				// && !(entity instanceof EntityFriendlyCreeper)
				if (entity instanceof EntityIllusionCreeper && ((EntityIllusionCreeper) entity).isIllusion()) {
					return;
				}

				if (entity.world.rand.nextInt(100) < ModConfig.ghostCreeperChance) {
					EntityGhostCreeper ghost = new EntityGhostCreeper(entity.world);
					ghost.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
					entity.world.spawnEntity(ghost);
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
					creeper.world.createExplosion(creeper, creeper.posX, creeper.posY - 2.0D, creeper.posZ, creeper.getExplosionRadius() * ((event.getAmount() < 6.0F ? 6.0F : event.getAmount()) / 6.0F), creeper.world.getGameRules().getBoolean("mobGriefing"));
					creeper.setDead();
				}
			}
		}
	}

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	public static class ClientLoader {

		@SubscribeEvent
		public static void onModelRegistration(ModelRegistryEvent event) {
			registerRenderer(EntityCakeCreeper.class, "cakecreeper");
			registerRenderer(EntityCookieCreeper.class, "cookiecreeper");
			registerRenderer(EntityDarkCreeper.class, "darkcreeper");
			registerRenderer(EntityEarthCreeper.class, "earthcreeper");
			registerRenderer(EntityEnderCreeper.class, "endercreeper");
			registerRenderer(EntityFireCreeper.class, "firecreeper");
			registerRenderer(EntityFireworkCreeper.class, "fireworkcreeper");
			registerRenderer(EntityFurnaceCreeper.class, "furnacecreeper");
			RenderingRegistry.registerEntityRenderingHandler(EntityGhostCreeper.class, renderManager -> new RenderElementalCreeper(renderManager, null, true));
			registerRenderer(EntityHydrogenCreeper.class, "hydrogencreeper");
			registerRenderer(EntityIceCreeper.class, "icecreeper");
			registerRenderer(EntityIllusionCreeper.class, "illusioncreeper");
			registerRenderer(EntityLightCreeper.class, "lightcreeper");
			registerRenderer(EntityElectricCreeper.class, "electriccreeper");
			registerRenderer(EntityMagmaCreeper.class, "magmacreeper");
			registerRenderer(EntityPsychicCreeper.class, "psychiccreeper");
			registerRenderer(EntityReverseCreeper.class, "reversecreeper");
			RenderingRegistry.registerEntityRenderingHandler(EntitySpiderCreeper.class, renderManager -> new RenderSpiderCreeper(renderManager));
			registerRenderer(EntitySpringCreeper.class, "springcreeper");
			registerRenderer(EntityStoneCreeper.class, "stonecreeper");
			registerRenderer(EntityWaterCreeper.class, "watercreeper");
			registerRenderer(EntityWindCreeper.class, "windcreeper");
		}

		private static void registerRenderer(Class creeper, String textureName) {
			RenderingRegistry.registerEntityRenderingHandler(creeper, renderManager -> new RenderElementalCreeper(renderManager, textureName));
		}
	}
}