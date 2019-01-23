package T145.elementalcreepers.config;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraftforge.common.config.Config;

@Config(modid = ElementalCreepers.MODID, category = "", name = "T145/" + ElementalCreepers.NAME)
@Config.LangKey(ElementalCreepers.MODID)
public class ModConfig {

	@Config.LangKey(ElementalCreepers.MODID + ".config.general")
	public static final CategoryGeneral GENERAL = new CategoryGeneral();

	@Config.LangKey(ElementalCreepers.MODID + ".config.radii")
	public static final CategoryExplosionRadii EXPLOSION_RADII = new CategoryExplosionRadii();

	@Config.LangKey(ElementalCreepers.MODID + ".config.spawnrate")
	public static final CategorySpawnRate SPAWN_RATE = new CategorySpawnRate();
}