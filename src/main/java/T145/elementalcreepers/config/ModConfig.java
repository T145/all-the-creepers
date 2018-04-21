package T145.elementalcreepers.config;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ElementalCreepers.MODID)
@Config(modid = ElementalCreepers.MODID, category = "", name = "T145/" + ElementalCreepers.NAME)
@Config.LangKey(ElementalCreepers.MODID)
public class ModConfig {

    @Config.LangKey(ElementalCreepers.MODID + ".config.general")
    public static final CategoryGeneral GENERAL = new CategoryGeneral();

    @Config.LangKey(ElementalCreepers.MODID + ".config.radii")
    public static final CategoryExplosionRadii EXPLOSION_RADII = new CategoryExplosionRadii();

    @Config.LangKey(ElementalCreepers.MODID + ".config.power")
    public static final CategoryExplosionPower EXPLOSION_POWER = new CategoryExplosionPower();

    @Config.LangKey(ElementalCreepers.MODID + ".config.spawnrate")
    public static final CategorySpawnRate SPAWN_RATE = new CategorySpawnRate();

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ElementalCreepers.MODID)) {
            ConfigManager.sync(ElementalCreepers.MODID, Config.Type.INSTANCE);
        }
    }
}