package T145.allthecreepers.api.immutable;

import net.minecraft.util.Identifier;

public enum CreeperType {

	AIR(), // replacement for wind & psychic
	BALLISTIC(),
	CAKE(),
	COOKIE(),
	DARK(),
	DEMOLTIION(),
	EARTH(),
	ENDER(),
	FIRE(),
	FIREWORK(),
	FURNACE(),
	ICE(),
	ILLUSION(),
	INVERSE(),
	LAVA(),
	LIGHTNING(),
	LUMINOUS(),
	OBSIDIAN(), // * as the ice counterpart
	//PSYCHIC(),
	//ROCK(),
	SPIDER(),
	SPRING(),
	//WARP(),
	WATER(),
	//WIND()
	;

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public String getId() {
		return String.format("%s_creeper", toString());
	}

	public Identifier getIdentifier() {
		return new Identifier(RegistryATC.ID, getId());
	}
}
