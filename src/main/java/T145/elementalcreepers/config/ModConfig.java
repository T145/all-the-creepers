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
	public static final General general = new General();

	@Config.LangKey(ElementalCreepers.MODID + ".config.creepers.explosion.radii")
	public static final CreeperExplosionRadii explosionRadii = new CreeperExplosionRadii();

	@Config.LangKey(ElementalCreepers.MODID + ".config.creepers.explosion.power")
	public static final CreeperExplosionPower explosionPower = new CreeperExplosionPower();

	@Config.LangKey(ElementalCreepers.MODID + ".config.creepers.spawnrate")
	public static final CreeperSpawnRate spawnRate = new CreeperSpawnRate();

	public static class General {

		@Config.Comment("Whether or not holiday features")
		@Config.RequiresMcRestart
		public boolean festiveSpirit = true;

		@Config.Comment("Whether or not elemental creepers should spawn like normal creepers or basically overwrite them")
		@Config.RequiresMcRestart
		public boolean reasonableSpawnRates;

		@Config.Comment("Whether or not to target every close & visible mob if the creeper can throw TNT")
		@Config.RequiresWorldRestart
		public boolean ballisticCreeperAI;

		@Config.Comment("Whether or not certain explosions are characteristically dome shaped")
		@Config.RequiresWorldRestart
		public boolean domeExplosion = true;

		@Config.Comment("Whether or not placed blocks get updated upon generation; i.e. fluid blocks flow")
		@Config.RequiresWorldRestart
		public boolean updatePlacedBlocks = true;

		@Config.Comment("Sets the max number of cookies dropped by the Cookie Creeper")
		@Config.RangeInt(min = 1, max = 64)
		@Config.RequiresWorldRestart
		public int cookieCreeperAmount = 5;

		@Config.Comment("Percent chance of a ghost creeper spawning")
		@Config.RangeInt(min = 1, max = 100)
		@Config.RequiresWorldRestart
		public int ghostCreeperChance = 35;

		@Config.Comment("Range in which the zombie creeper looks for fallen allies")
		@Config.RequiresWorldRestart
		public int zombieCreeperRange = 8;
	}

	public static class CreeperExplosionRadii {

		@Config.Comment("Sets the Water Creeper explosion radius")
		public int waterCreeperRadius = 4;

		@Config.Comment("Sets the Fire Creeper explosion radius")
		public int fireCreeperRadius = 6;

		@Config.Comment("Sets the Ice Creeper explosion radius")
		public int iceCreeperRadius = 8;

		@Config.Comment("Sets the Electric Creeper explosion radius")
		public int electricCreeperRadius = 5;

		@Config.Comment("Sets the Earth Creeper explosion radius")
		public int earthCreeperRadius = 8;

		@Config.Comment("Sets the Ender Creeper explosion radius")
		public int enderCreeperRadius = 8;

		@Config.Comment("Sets the Psychic Creeper explosion radius")
		public int psychicCreeperRadius = 5;

		@Config.Comment("Sets the Magma Creeper explosion radius")
		public int magmaCreeperRadius = 3;

		@Config.Comment("Sets the Ghost Creeper explosion radius")
		public int ghostCreeperRadius = 5;

		@Config.Comment("Sets the Light Creeper explosion radius")
		public int lightCreeperRadius = 4;

		@Config.Comment("Sets the Dark Creeper explosion radius")
		public int darkCreeperRadius = 12;

		@Config.Comment("Sets the Reverse Creeper explosion radius")
		public int reverseCreeperRadius = 8;

		@Config.Comment("Sets the Spider Creeper explosion radius")
		public int spiderCreeperRadius = 12;

		@Config.Comment("Sets the Wind Creeper explosion radius")
		public int windCreeperRadius = 5;

		@Config.Comment("Sets the Hydrogen (Bomb) Creeper explosion radius")
		public int hydrogenCreeperRadius = 64;

		@Config.Comment("Sets the Stone Creeper explosion radius")
		public int stoneCreeperRadius = 8;

		@Config.Comment("Sets the Firework Creeper explosion radius")
		public int fireworkCreeperRadius = 5;

		@Config.Comment("Sets the Furnace Creeper explosion radius")
		public int furnaceCreeperRadius = 3;
	}

	public static class CreeperExplosionPower {

		@Config.Comment("Sets the Psychic Creeper explosion power")
		public int psychicCreeperPower = 8;

		@Config.Comment("Sets the Wind Creeper explosion power")
		public int windCreeperPower = 3;

		@Config.Comment("Sets the Spring Creeper explosion radius")
		public int springCreeperPower = 3;
	}

	public static class CreeperSpawnRate {

		@Config.Comment("Sets the Fire Creeper Spawn weight")
		public int fireCreeperSpawn = 10;

		@Config.Comment("Sets the Water Creeper Spawn weight")
		public int waterCreeperSpawn = 10;

		@Config.Comment("Sets the Electric Creeper Spawn weight")
		public int electricCreeperSpawn = 10;

		@Config.Comment("Sets the Cookie Creeper Spawn weight")
		public int cookieCreeperSpawn = 10;

		@Config.Comment("Sets the Dark Creeper Spawn weight")
		public int darkCreeperSpawn = 2;

		@Config.Comment("Sets the Light Creeper Spawn weight")
		public int lightCreeperSpawn = 4;

		@Config.Comment("Sets the Earth Creeper Spawn weight")
		public int earthCreeperSpawn = 10;

		@Config.Comment("Sets the Magma Creeper Spawn weight")
		public int magmaCreeperSpawn = 6;

		@Config.Comment("Sets the Reverse Creeper Spawn weight")
		public int reverseCreeperSpawn = 6;

		@Config.Comment("Sets the Ice Creeper Spawn weight")
		public int iceCreeperSpawn = 10;

		@Config.Comment("Sets the Psychic Creeper Spawn weight")
		public int psychicCreeperSpawn = 10;

		@Config.Comment("Sets the Illusion Creeper Spawn weight")
		public int illusionCreeperSpawn = 6;

		@Config.Comment("Sets the Spider Creeper Spawn weight")
		public int spiderCreeperSpawn = 8;

		@Config.Comment("Sets the Friendly Creeper Spawn weight")
		public int friendlyCreeperSpawn = 1;

		@Config.Comment("Sets the Wind Creeper Spawn weight")
		public int windCreeperSpawn = 10;

		@Config.Comment("Sets the Hydrogen Creeper Spawn weight")
		public int hydrogenCreeperSpawn = 1;

		@Config.Comment("Sets the Ender Creeper Spawn weight")
		public int enderCreeperSpawn = 4;

		@Config.Comment("Sets the Stone Creeper Spawn weight")
		public int stoneCreeperSpawn = 10;

		@Config.Comment("Sets the cake Creeper Spawn weight")
		public int cakeCreeperSpawn = 2;

		@Config.Comment("Sets the Firework Creeper Spawn weight")
		public int fireworkCreeperSpawn = 8;

		@Config.Comment("Sets the Spring Creeper Spawn weight")
		public int springCreeperSpawn = 7;

		@Config.Comment("Sets the Furnace Creeper Spawn weight")
		public int furnaceCreeperSpawn = 1;

		@Config.Comment("Sets the Ender Creeper Spawn weight")
		public int zombieCreeperSpawn = 4;
	}

	public static void sync() {
		ConfigManager.sync(ElementalCreepers.MODID, Config.Type.INSTANCE);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(ElementalCreepers.MODID)) {
			sync();
		}
	}
}