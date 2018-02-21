package T145.elementalcreepers.config;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraftforge.common.config.Config;

@Config(modid = ElementalCreepers.MODID, name = "T145/" + ElementalCreepers.MODID)
public class ModConfig {

	@Config.LangKey("config.general.domeExplosion")
	@Config.Comment("Whether or not certain explosions are characteristically dome shaped")
	public static boolean domeExplosion;

	@Config.LangKey("config.general.cookiesdropped")
	@Config.Comment("Sets the max number of cookies dropped by the Cookie Creeper")
	@Config.RangeInt(min = 1, max = 64)
	public static int cookieCreeperAmount = 5;

	@Config.LangKey("config.general.creeper.spawn.fire")
	@Config.Comment("Sets the Fire Creeper Spawn weight")
	public static int fireCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.water")
	@Config.Comment("Sets the Water Creeper Spawn weight")
	public static int waterCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.electric")
	@Config.Comment("Sets the Electric Creeper Spawn weight")
	public static int electricCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.cookie")
	@Config.Comment("Sets the Cookie Creeper Spawn weight")
	public static int cookieCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.dark")
	@Config.Comment("Sets the Dark Creeper Spawn weight")
	public static int darkCreeperSpawn = 2;

	@Config.LangKey("config.general.creeper.spawn.light")
	@Config.Comment("Sets the Light Creeper Spawn weight")
	public static int lightCreeperSpawn = 4;

	@Config.LangKey("config.general.creeper.spawn.earth")
	@Config.Comment("Sets the Earth Creeper Spawn weight")
	public static int earthCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.magma")
	@Config.Comment("Sets the Magma Creeper Spawn weight")
	public static int magmaCreeperSpawn = 6;

	@Config.LangKey("config.general.creeper.spawn.reverse")
	@Config.Comment("Sets the Reverse Creeper Spawn weight")
	public static int reverseCreeperSpawn = 6;

	@Config.LangKey("config.general.creeper.spawn.ice")
	@Config.Comment("Sets the Ice Creeper Spawn weight")
	public static int iceCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.psychic")
	@Config.Comment("Sets the Psychic Creeper Spawn weight")
	public static int psychicCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.illusion")
	@Config.Comment("Sets the Illusion Creeper Spawn weight")
	public static int illusionCreeperSpawn = 6;

	@Config.LangKey("config.general.creeper.spawn.spider")
	@Config.Comment("Sets the Spider Creeper Spawn weight")
	public static int spiderCreeperSpawn = 8;

	@Config.LangKey("config.general.creeper.spawn.friendly")
	@Config.Comment("Sets the Friendly Creeper Spawn weight")
	public static int friendlyCreeperSpawn = 1;

	@Config.LangKey("config.general.creeper.spawn.wind")
	@Config.Comment("Sets the Wind Creeper Spawn weight")
	public static int windCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.hydrogen")
	@Config.Comment("Sets the Hydrogen Creeper Spawn weight")
	public static int hydrogenCreeperSpawn = 1;

	@Config.LangKey("config.general.creeper.spawn.ender")
	@Config.Comment("Sets the Ender Creeper Spawn weight")
	public static int enderCreeperSpawn = 4;

	@Config.LangKey("config.general.creeper.spawn.stone")
	@Config.Comment("Sets the Stone Creeper Spawn weight")
	public static int stoneCreeperSpawn = 10;

	@Config.LangKey("config.general.creeper.spawn.solar")
	@Config.Comment("Sets the Solar Creeper Spawn weight")
	public static int solarCreeperSpawn = 4;

	@Config.LangKey("config.general.creeper.spawn.cake")
	@Config.Comment("Sets the cake Creeper Spawn weight")
	public static int cakeCreeperSpawn = 2;

	@Config.LangKey("config.general.creeper.spawn.firework")
	@Config.Comment("Sets the Firework Creeper Spawn weight")
	public static int fireworkCreeperSpawn = 8;

	@Config.LangKey("config.general.creeper.spawn.bigbad")
	@Config.Comment("Sets the Big Bad Creeper Spawn weight")
	public static int bigBadSpawn = 1;

	@Config.LangKey("config.general.creeper.spawn.spring")
	@Config.Comment("Sets the Spring Creeper Spawn weight")
	public static int springCreeperSpawn = 7;

	@Config.LangKey("config.general.creeper.spawn.silverfish")
	@Config.Comment("Sets the Silverfish Creeper Spawn weight")
	public static int silverCreeperSpawn = 4;

	@Config.LangKey("config.general.creeper.spawn.furnace")
	@Config.Comment("Sets the Furnace Creeper Spawn weight")
	public static int furnaceCreeperSpawn = 1;


	@Config.LangKey("config.general.creeper.radius.water")
	@Config.Comment("Sets the Water Creeper explosion radius")
	public static int waterCreeperRadius = 4;

	@Config.LangKey("config.general.creeper.radius.fire")
	@Config.Comment("Sets the Fire Creeper explosion radius")
	public static int fireCreeperRadius = 6;

	@Config.LangKey("config.general.creeper.radius.ice")
	@Config.Comment("Sets the Ice Creeper explosion radius")
	public static int iceCreeperRadius = 8;

	@Config.LangKey("config.general.creeper.radius.electric")
	@Config.Comment("Sets the Electric Creeper explosion radius")
	public static int electricCreeperRadius = 5;

	@Config.LangKey("config.general.creeper.radius.earth")
	@Config.Comment("Sets the Earth Creeper explosion radius")
	public static int earthCreeperRadius = 8;

	@Config.LangKey("config.general.creeper.power.psychic")
	@Config.Comment("Sets the Psychic Creeper explosion power")
	public static int psychicCreeperPower = 8;

	@Config.LangKey("config.general.creeper.radius.psychic")
	@Config.Comment("Sets the Psychic Creeper explosion radius")
	public static int psychicCreeperRadius = 5;

	@Config.LangKey("config.general.creeper.radius.magma")
	@Config.Comment("Sets the Magma Creeper explosion radius")
	public static int magmaCreeperRadius = 3;

	@Config.LangKey("config.general.creeper.radius.ghost")
	@Config.Comment("Sets the Ghost Creeper explosion radius")
	public static int ghostCreeperRadius = 5;

	@Config.LangKey("config.general.creeper.ghostchance")
	@Config.Comment("Percent chance of a ghost creeper spawning")
	@Config.RangeInt(min = 1, max = 100)
	public static int ghostCreeperChance = 35;

	@Config.LangKey("config.general.creeper.radius.light")
	@Config.Comment("Sets the Light Creeper explosion radius")
	public static int lightCreeperRadius = 4;

	@Config.LangKey("config.general.creeper.radius.dark")
	@Config.Comment("Sets the Dark Creeper explosion radius")
	public static int darkCreeperRadius = 12;

	@Config.LangKey("config.general.creeper.radius.reverse")
	@Config.Comment("Sets the Reverse Creeper explosion radius")
	public static int reverseCreeperRadius = 8;

	@Config.LangKey("config.general.creeper.radius.spider")
	@Config.Comment("Sets the Spider Creeper explosion radius")
	public static int spiderCreeperRadius = 12;

	@Config.LangKey("config.general.creeper.power.wind")
	@Config.Comment("Sets the Wind Creeper explosion power")
	public static int windCreeperPower = 3;

	@Config.LangKey("config.general.creeper.radius.wind")
	@Config.Comment("Sets the Wind Creeper explosion radius")
	public static int windCreeperRadius = 5;

	@Config.LangKey("config.general.creeper.radius.hydrogen")
	@Config.Comment("Sets the Hydrogen (Bomb) Creeper explosion radius")
	public static int hydrogenCreeperRadius = 64;

	@Config.LangKey("config.general.creeper.radius.stone")
	@Config.Comment("Sets the Stone Creeper explosion radius")
	public static int stoneCreeperRadius = 8;

	@Config.LangKey("config.general.creeper.radius.firework")
	@Config.Comment("Sets the Firework Creeper explosion radius")
	public static int fireworkCreeperRadius = 5;

	@Config.LangKey("config.general.creeper.bigbadamount")
	@Config.Comment("Sets the max number of BIg Bad Creepers that can be spawned")
	public static int bigBadAmount = 7;

	@Config.LangKey("config.general.creeper.radius.spring")
	@Config.Comment("Sets the Spring Creeper explosion radius")
	public static int springCreeperPower = 2;

	@Config.LangKey("config.general.creeper.radius.furnace")
	@Config.Comment("Sets the Furnace Creeper explosion radius")
	public static int furnaceCreeperRadius = 3;
}