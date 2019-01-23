package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.api.EntitiesEC;
import T145.elementalcreepers.api.immutable.RegistryEC;
import T145.elementalcreepers.client.render.RenderCreeperElemental;
import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.EntityCreeperAir;
import T145.elementalcreepers.entities.EntityCreeperEarth;
import T145.elementalcreepers.entities.EntityCreeperEntropy;
import T145.elementalcreepers.entities.EntityCreeperFire;
import T145.elementalcreepers.entities.EntityCreeperOrder;
import T145.elementalcreepers.entities.EntityCreeperWater;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

class ModLoader {

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	static class ServerLoader {

		private static int entityId = 0;
		private static int creeperGreen = 894731;

		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			final IForgeRegistry<EntityEntry> reg = event.getRegistry();
			reg.register(EntitiesEC.CREEPER_FIRE = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_FIRE, entityId++).name(RegistryEC.KEY_CREEPER_FIRE).entity(EntityCreeperFire.class).tracker(80, 3, true).egg(creeperGreen, 0xE36F18).build());
			reg.register(EntitiesEC.CREEPER_WATER = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_WATER, entityId++).name(RegistryEC.KEY_CREEPER_WATER).entity(EntityCreeperWater.class).tracker(80, 3, true).egg(creeperGreen, 0x3B73CD).build());
			reg.register(EntitiesEC.CREEPER_AIR = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_AIR, entityId++).name(RegistryEC.KEY_CREEPER_AIR).entity(EntityCreeperAir.class).tracker(80, 3, true).egg(creeperGreen, 0x5FFACB).build());
			reg.register(EntitiesEC.CREEPER_EARTH = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_EARTH, entityId++).name(RegistryEC.KEY_CREEPER_EARTH).entity(EntityCreeperEarth.class).tracker(80, 3, true).egg(creeperGreen, 0x5D3200).build());
			reg.register(EntitiesEC.CREEPER_ORDER = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_ORDER, entityId++).name(RegistryEC.KEY_CREEPER_ORDER).entity(EntityCreeperOrder.class).tracker(80, 3, true).egg(creeperGreen, 0x9E9E9E).build());
			reg.register(EntitiesEC.CREEPER_ENTROPY = EntityEntryBuilder.create().id(RegistryEC.KEY_CREEPER_ENTROPY, entityId).name(RegistryEC.KEY_CREEPER_ENTROPY).entity(EntityCreeperEntropy.class).tracker(80, 3, true).egg(creeperGreen, 0x555555).build());
		}

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ElementalCreepers.MODID)) {
				ConfigManager.sync(ElementalCreepers.MODID, Config.Type.INSTANCE);
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
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperFire.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_FIRE));
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperWater.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_WATER));
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperAir.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_AIR));
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperEarth.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_EARTH));
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperOrder.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_ORDER));
			RenderingRegistry.registerEntityRenderingHandler(EntityCreeperEntropy.class, manager -> new RenderCreeperElemental(manager, RegistryEC.TEXTURE_CREEPER_ENTROPY));
		}
	}
}