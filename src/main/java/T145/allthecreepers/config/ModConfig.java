package T145.allthecreepers.config;

import T145.allthecreepers.api.constants.RegistryATC;
import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1.annotation.ConfigEntry;

@Config(name = "T145/" + RegistryATC.ID)
@Config.Gui.Background(RegistryATC.ID + ":icon.png")
public class ModConfig implements ConfigData {

	@ConfigEntry.Gui.PrefixText
	public final boolean debug = true;

	@ConfigEntry.Gui.PrefixText
	public final int ballisticCreeperSpawnWeight = 100;
	public final int ballisticCreeperSpawnMaxGroupSize = 4;
	public final int ballisticCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int cakeCreeperSpawnWeight = 100;
	public final int cakeCreeperSpawnMaxGroupSize = 4;
	public final int cakeCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int cookieCreeperSpawnWeight = 100;
	public final int cookieCreeperSpawnMaxGroupSize = 4;
	public final int cookieCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int darkCreeperSpawnWeight = 100;
	public final int darkCreeperSpawnMaxGroupSize = 4;
	public final int darkCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int demolitionCreeperSpawnWeight = 100;
	public final int demolitionCreeperSpawnMaxGroupSize = 4;
	public final int demolitionCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int earthCreeperSpawnWeight = 100;
	public final int earthCreeperSpawnMaxGroupSize = 4;
	public final int earthCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int fireCreeperSpawnWeight = 100;
	public final int fireCreeperSpawnMaxGroupSize = 4;
	public final int fireCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int fireworkCreeperSpawnWeight = 100;
	public final int fireworkCreeperSpawnMaxGroupSize = 4;
	public final int fireworkCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int furnaceCreeperSpawnWeight = 100;
	public final int furnaceCreeperSpawnMaxGroupSize = 4;
	public final int furnaceCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int lavaCreeperSpawnWeight = 100;
	public final int lavaCreeperSpawnMaxGroupSize = 4;
	public final int lavaCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int lightningCreeperSpawnWeight = 100;
	public final int lightningCreeperSpawnMaxGroupSize = 4;
	public final int lightningCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int luminousCreeperSpawnWeight = 100;
	public final int luminousCreeperSpawnMaxGroupSize = 4;
	public final int luminousCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int natureCreeperSpawnWeight = 100;
	public final int natureCreeperSpawnMaxGroupSize = 4;
	public final int natureCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int partyCreeperSpawnWeight = 100;
	public final int partyCreeperSpawnMaxGroupSize = 4;
	public final int partyCreeperSpawnMinGroupSize = 4;

	@ConfigEntry.Gui.PrefixText
	public final int waterCreeperSpawnWeight = 100;
	public final int waterCreeperSpawnMaxGroupSize = 4;
	public final int waterCreeperSpawnMinGroupSize = 4;
}
