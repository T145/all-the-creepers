package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.config.ModConfig;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;

class ModLoader {

	@EventBusSubscriber(modid = ElementalCreepers.MODID)
	static class ServerLoader {

		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
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
			
		}
	}
}