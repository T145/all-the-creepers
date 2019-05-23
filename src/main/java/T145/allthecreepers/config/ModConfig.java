package T145.allthecreepers.config;

import T145.allthecreepers.api.constants.RegistryATC;
import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1.annotation.ConfigEntry;

@Config(name = "T145." + RegistryATC.ID)
@Config.Gui.Background(RegistryATC.ID + ":icon.png")
public class ModConfig implements ConfigData {

	@ConfigEntry.Category(RegistryATC.CATEGORY_DEFAULT)
	public final boolean debug = false;

	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int ballisticCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int cakeCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int cookieCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int darkCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int demolitionCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int earthCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int fireCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int fireworkCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int furnaceCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int lavaCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int lightningCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int luminousCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int natureCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int partyCreeperSpawnWeight = 100;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int waterCreeperSpawnWeight = 100;

	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int ballisticCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int cakeCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int cookieCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int darkCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int demolitionCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int earthCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int fireCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int fireworkCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int furnaceCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int lavaCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int lightningCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int luminousCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int natureCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int partyCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int waterCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int ballisticCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int cakeCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int cookieCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int darkCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int demolitionCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int earthCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int fireCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int fireworkCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int furnaceCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int lavaCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int lightningCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int luminousCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int natureCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int partyCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int waterCreeperSpawnMaxGroupSize = 4;
}
