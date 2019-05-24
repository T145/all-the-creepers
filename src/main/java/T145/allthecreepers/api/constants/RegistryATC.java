package T145.allthecreepers.api.constants;

import net.minecraft.util.Identifier;

public class RegistryATC {

	private RegistryATC() {}

	public static final String ID = "allthecreepers";
	public static final String NAME = "All The Creepers";

	public static final String CATEGORY_DEFAULT = "default";
	public static final String CATEGORY_SPAWN_WEIGHT = "spawn.weight";
	public static final String CATEGORY_SPAWN_MIN_SIZE = "spawn.size.min";
	public static final String CATEGORY_SPAWN_MAX_SIZE = "spawn.size.max";
	public static final String CATEGORY_EXPLOSION_RADII = "radii";

	public static Identifier getIdentifier(String name) {
		return new Identifier(ID, name);
	}
}
