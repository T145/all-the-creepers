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
	public final int ballisticCreeperSpawnWeight = 25;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int cakeCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int cookieCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int darkCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int demolitionCreeperSpawnWeight = 25;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int earthCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int fireCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int fireworkCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int furnaceCreeperSpawnWeight = 25;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int lavaCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int lightningCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int luminousCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int natureCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int partyCreeperSpawnWeight = 50;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_WEIGHT)
	public final int waterCreeperSpawnWeight = 50;

	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int ballisticCreeperSpawnMinGroupSize = 2;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int cakeCreeperSpawnMinGroupSize = 2;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int cookieCreeperSpawnMinGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int darkCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int demolitionCreeperSpawnMinGroupSize = 2;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int earthCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int fireCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int fireworkCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int furnaceCreeperSpawnMinGroupSize = 2;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int lavaCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int lightningCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int luminousCreeperSpawnMinGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int natureCreeperSpawnMinGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int partyCreeperSpawnMinGroupSize = 2;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MIN_SIZE)
	public final int waterCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int ballisticCreeperSpawnMaxGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int cakeCreeperSpawnMaxGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int cookieCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int darkCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int demolitionCreeperSpawnMaxGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int earthCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int fireCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int fireworkCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int furnaceCreeperSpawnMaxGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int lavaCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int lightningCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int luminousCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int natureCreeperSpawnMaxGroupSize = 4;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int partyCreeperSpawnMaxGroupSize = 3;
	@ConfigEntry.Category(RegistryATC.CATEGORY_SPAWN_MAX_SIZE)
	public final int waterCreeperSpawnMaxGroupSize = 4;
}
