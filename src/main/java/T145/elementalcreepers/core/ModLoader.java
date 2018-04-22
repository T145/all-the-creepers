package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.api.Registries;
import T145.elementalcreepers.client.render.entity.RenderAngryCreeper;
import T145.elementalcreepers.client.render.entity.RenderBaseCreeper;
import T145.elementalcreepers.client.render.entity.RenderFriendlyCreeper;
import T145.elementalcreepers.client.render.entity.RenderSpiderCreeper;
import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.*;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
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
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

@GameRegistry.ObjectHolder(ElementalCreepers.MODID)
public class ModLoader {

    @EventBusSubscriber(modid = ElementalCreepers.MODID)
    static class ServerLoader {

        private static final BiomeDictionary.Type[] VALID_BIOME_TYPES = {BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN};

        private static int entityId;

        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {

            // TODO: Add unique egg colors
            final EntityEntry[] entries = {
                    createCreeperBuilder("CakeCreeper")
                            .entity(EntityCakeCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("CookieCreeper")
                            .entity(EntityCookieCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("DarkCreeper")
                            .entity(EntityDarkCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("EarthCreeper")
                            .entity(EntityEarthCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("EnderCreeper")
                            .entity(EntityEnderCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("FireCreeper")
                            .entity(EntityFireCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("FireworkCreeper")
                            .entity(EntityFireworkCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("FurnaceCreeper")
                            .entity(EntityFurnaceCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("FriendlyCreeper")
                            .entity(EntityFriendlyCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("GhostCreeper")
                            .entity(EntityGhostCreeper.class)
                            .build(),
                    createCreeperBuilder("BallisticCreeper")
                            .entity(EntityBallisticCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("IceCreeper")
                            .entity(EntityIceCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("IllusionCreeper")
                            .entity(EntityIllusionCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("LightCreeper")
                            .entity(EntityLightCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("LightningCreeper")
                            .entity(EntityLightningCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("LavaCreeper")
                            .entity(EntityLavaCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("PsychicCreeper")
                            .entity(EntityPsychicCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("ReverseCreeper")
                            .entity(EntityReverseCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("SpiderCreeper")
                            .entity(EntitySpiderCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("SpringCreeper")
                            .entity(EntitySpringCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("DemolitionCreeper")
                            .entity(EntityDemolitionCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("WaterCreeper")
                            .entity(EntityWaterCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("WindCreeper")
                            .entity(EntityWindCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build(),
                    createCreeperBuilder("ZombieCreeper")
                            .entity(EntityZombieCreeper.class)
                            .egg(0x0DA70B, 0x101010)
                            .build()
            };

            for (EntityEntry entry : entries) {
                event.getRegistry().register(entry);
                Registries.CREEPER_REGISTRY.add(entry);
            }

            if (ModConfig.GENERAL.reasonableSpawnRates) {
                addOverworldSpawn(EntityFireCreeper.class, ModConfig.SPAWN_RATE.fire, 3);
                addOverworldSpawn(EntityWaterCreeper.class, ModConfig.SPAWN_RATE.water, 3);
                addOverworldSpawn(EntityLightningCreeper.class, ModConfig.SPAWN_RATE.lightning, 3);
                addOverworldSpawn(EntityCookieCreeper.class, ModConfig.SPAWN_RATE.cookie, 2);
                addOverworldSpawn(EntityDarkCreeper.class, ModConfig.SPAWN_RATE.dark, 3);
                addOverworldSpawn(EntityLightCreeper.class, ModConfig.SPAWN_RATE.light, 3);
                addOverworldSpawn(EntityEarthCreeper.class, ModConfig.SPAWN_RATE.earth, 3);
                addOverworldSpawn(EntityReverseCreeper.class, ModConfig.SPAWN_RATE.reverse, 1);
                addOverworldSpawn(EntityIceCreeper.class, ModConfig.SPAWN_RATE.ice, 3);
                addOverworldSpawn(EntityPsychicCreeper.class, ModConfig.SPAWN_RATE.psychic, 3);
                addOverworldSpawn(EntityIllusionCreeper.class, ModConfig.SPAWN_RATE.illusion, 1);
                addOverworldSpawn(EntitySpiderCreeper.class, ModConfig.SPAWN_RATE.spider, 3);
                addOverworldSpawn(EntityWindCreeper.class, ModConfig.SPAWN_RATE.wind, 2);
                addOverworldSpawn(EntityBallisticCreeper.class, ModConfig.SPAWN_RATE.ballistic, 1);
                addOverworldSpawn(EntityEnderCreeper.class, ModConfig.SPAWN_RATE.ender, 2);
                addOverworldSpawn(EntityDemolitionCreeper.class, ModConfig.SPAWN_RATE.demolition, 3);
                addOverworldSpawn(EntityCakeCreeper.class, ModConfig.SPAWN_RATE.cake, 3);
                addOverworldSpawn(EntityFireworkCreeper.class, ModConfig.SPAWN_RATE.firework, 3);
                addOverworldSpawn(EntitySpringCreeper.class, ModConfig.SPAWN_RATE.spring, 3);
                addOverworldSpawn(EntityFurnaceCreeper.class, ModConfig.SPAWN_RATE.furnace, 3);
                addOverworldSpawn(EntityZombieCreeper.class, ModConfig.SPAWN_RATE.zombie, 1);
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
                copyCreeperSpawns(EntityLavaCreeper.class);
                copyCreeperSpawns(EntityPsychicCreeper.class);
                copyCreeperSpawns(EntityReverseCreeper.class);
                copyCreeperSpawns(EntitySpiderCreeper.class);
                copyCreeperSpawns(EntitySpringCreeper.class);
                copyCreeperSpawns(EntityDemolitionCreeper.class);
                copyCreeperSpawns(EntityWaterCreeper.class);
                copyCreeperSpawns(EntityWindCreeper.class);
                copyCreeperSpawns(EntityZombieCreeper.class);
            }

            addOverworldSpawn(EntityFriendlyCreeper.class, ModConfig.SPAWN_RATE.friendly, 2, EnumCreatureType.CREATURE);
            addNetherSpawn(EntityFireCreeper.class, ModConfig.SPAWN_RATE.fire, 3);
            addNetherSpawn(EntityLavaCreeper.class, ModConfig.SPAWN_RATE.lava, 2);
            addEndSpawn(EntityEnderCreeper.class, ModConfig.SPAWN_RATE.ender * 5, 3);

            // TODO: Add dimension blacklist/whitelist
        }

        private static <C extends Entity> EntityEntryBuilder<C> createCreeperBuilder(final String name) {
            final EntityEntryBuilder<C> builder = EntityEntryBuilder.create();
            final ResourceLocation registryName = new ResourceLocation(ElementalCreepers.MODID, name);
            return builder
                    .id(registryName, entityId++)
                    .name(ElementalCreepers.MODID + ":" + name)
                    .tracker(80, 3, true);
        }

        static void addOverworldSpawn(Class<? extends EntityLiving> entityClass, int probability, int max, EnumCreatureType type) {
            for (BiomeDictionary.Type biomeType : VALID_BIOME_TYPES) {
                EntityRegistry.addSpawn(entityClass, probability, 1, max, type, BiomeDictionary.getBiomes(biomeType).toArray(new Biome[0]));
            }
        }

        static void addOverworldSpawn(Class<? extends EntityLiving> entityClass, int probability, int max) {
            addOverworldSpawn(entityClass, probability, max, EnumCreatureType.MONSTER);
        }

        static void addNetherSpawn(Class<? extends EntityLiving> entityClass, int probability, int max) {
            EntityRegistry.addSpawn(entityClass, probability, 1, max, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
        }

        static void addEndSpawn(Class<? extends EntityLiving> entityClass, int probability, int max) {
            EntityRegistry.addSpawn(entityClass, probability, 1, max, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(BiomeDictionary.Type.END).toArray(new Biome[0]));
        }

        private static void copyCreeperSpawns(final Class<? extends EntityLiving> classToAdd) {
            for (final Biome biome : ForgeRegistries.BIOMES) {
                biome.getSpawnableList(EnumCreatureType.MONSTER).stream().filter(entry -> entry.entityClass == EntityCreeper.class).findFirst().ifPresent(spawnListEntry -> biome.getSpawnableList(EnumCreatureType.MONSTER).add(new Biome.SpawnListEntry(classToAdd, spawnListEntry.itemWeight, spawnListEntry.minGroupCount, spawnListEntry.maxGroupCount)));
            }
        }

        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event) {
            DamageSource damage = event.getSource();
            Entity trueSource = damage.getTrueSource();
            EntityLivingBase entity = event.getEntityLiving();
            boolean killedByPlayer = damage.getDamageType().equals("player") || trueSource instanceof EntityPlayer;

            if (killedByPlayer && entity instanceof EntityCreeper && !(entity instanceof EntityGhostCreeper) && !(entity instanceof EntityFriendlyCreeper)) {
                if (entity instanceof EntityIllusionCreeper && ((EntityIllusionCreeper) entity).isIllusion()) {
                    return;
                }

                if (entity.world.rand.nextInt(100) < ModConfig.GENERAL.ghostCreeperChance) {
                    EntityGhostCreeper ghost = new EntityGhostCreeper(entity.world);
                    ghost.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
                    entity.world.spawnEntity(ghost);
                }
            }

            // TODO: Add HashSet entity blacklist
            if (entity instanceof IMob) {
                int radius = ModConfig.GENERAL.zombieCreeperRange;
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

            if (entity instanceof EntitySpringCreeper && !entity.world.isRemote && damage == DamageSource.FALL) {
                EntitySpringCreeper creeper = (EntitySpringCreeper) entity;

                if (creeper.isSprung()) {
                    creeper.world.createExplosion(creeper, creeper.posX, creeper.posY - 2.0D, creeper.posZ, creeper.getExplosionPower() * ((event.getAmount() < 6.0F ? 6.0F : event.getAmount()) / 6.0F), creeper.world.getGameRules().getBoolean("mobGriefing"));
                    creeper.setDead();
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerLogIn(PlayerLoggedInEvent event) {
            if (ModConfig.GENERAL.checkForUpdates && UpdateChecker.hasUpdate()) {
                event.player.sendMessage(UpdateChecker.getUpdateNotification());
            }
        }
    }

    @EventBusSubscriber(value = Side.CLIENT, modid = ElementalCreepers.MODID)
    static class ClientLoader {

        @SubscribeEvent
        public static void onModelRegistration(ModelRegistryEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, RenderAngryCreeper::new);
            registerRenderer(EntityCakeCreeper.class, "cakecreeper");
            registerRenderer(EntityCookieCreeper.class, "cookiecreeper");
            registerRenderer(EntityDarkCreeper.class, "darkcreeper");
            registerRenderer(EntityEarthCreeper.class, "earthcreeper");
            registerRenderer(EntityEnderCreeper.class, "endercreeper");
            registerRenderer(EntityFireCreeper.class, "firecreeper");
            registerRenderer(EntityFireworkCreeper.class, "fireworkcreeper");
            registerRenderer(EntityFurnaceCreeper.class, "furnacecreeper");
            RenderingRegistry.registerEntityRenderingHandler(EntityGhostCreeper.class, manager -> new RenderBaseCreeper(manager, true));
            RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyCreeper.class, RenderFriendlyCreeper::new);
            registerRenderer(EntityBallisticCreeper.class, "hydrogencreeper");
            registerRenderer(EntityIceCreeper.class, "icecreeper");
            registerRenderer(EntityIllusionCreeper.class, "illusioncreeper");
            registerRenderer(EntityLightCreeper.class, "lightcreeper");
            registerRenderer(EntityLightningCreeper.class, "lightningcreeper");
            registerRenderer(EntityLavaCreeper.class, "magmacreeper");
            registerRenderer(EntityPsychicCreeper.class, "psychiccreeper");
            registerRenderer(EntityReverseCreeper.class, "reversecreeper");
            RenderingRegistry.registerEntityRenderingHandler(EntitySpiderCreeper.class, RenderSpiderCreeper::new);
            registerRenderer(EntitySpringCreeper.class, "springcreeper");
            registerRenderer(EntityDemolitionCreeper.class, "eucreeper");
            registerRenderer(EntityWaterCreeper.class, "watercreeper");
            registerRenderer(EntityWindCreeper.class, "windcreeper");
            registerRenderer(EntityZombieCreeper.class, "zombiecreeper");
        }

        private static void registerRenderer(Class<? extends EntityBaseCreeper> creeper, String textureName) {
            RenderingRegistry.registerEntityRenderingHandler(creeper, manager -> new RenderBaseCreeper(manager, textureName));
        }
    }
}