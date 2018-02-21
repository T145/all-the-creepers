package T145.elementalcreepers.config;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ElementalCreepers.MODID)
public class ConfigHandler {

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(ElementalCreepers.MODID)) {
			ConfigManager.load(ElementalCreepers.MODID, Config.Type.INSTANCE);
		}
	}
}