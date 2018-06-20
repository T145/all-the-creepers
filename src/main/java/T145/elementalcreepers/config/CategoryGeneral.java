package T145.elementalcreepers.config;

import net.minecraftforge.common.config.Config;

public class CategoryGeneral {

	@Config.Comment("Whether or not explosions move flying players (doesn't include spectators)")
	public boolean explosionsMoveFlyingPlayers = true;

	@Config.Comment("Toggles Vazkii's old Creeper Temper effect across all creepers")
	public boolean creeperTemper;

	@Config.Comment("Sends you an in-game notification if an update is available")
	public boolean checkForUpdates = true;

	@Config.Comment("Whether or not to enable holiday features")
	public boolean festiveSpirit = true;

	@Config.Comment("Whether or not elemental creepers should spawn like normal creepers or basically overwrite them")
	@Config.RequiresMcRestart
	public boolean reasonableSpawnRates;

	@Config.Comment("Whether or not to target every close & visible mob if the creeper can throw TNT")
	public boolean ballisticCreeperAI;

	@Config.Comment("Whether or not certain explosions are characteristically dome shaped")
	public boolean domeExplosion;

	@Config.Comment("Whether or not placed blocks get updated upon generation; i.e. fluid blocks flow")
	public boolean updatePlacedBlocks = true;

	@Config.Comment("Sets the max number of cookies dropped by the Cookie Creeper")
	@Config.RangeInt(min = 1, max = 64)
	public int cookieCreeperAmount = 5;

	@Config.Comment("Percent chance of a ghost creeper spawning")
	@Config.RangeInt(min = 1, max = 100)
	public int ghostCreeperChance = 35;

	@Config.Comment("Range in which the zombie creeper looks for fallen allies")
	public int zombieCreeperRange = 8;
}