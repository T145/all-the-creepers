package T145.allthecreepers.api.immutable;

import net.minecraft.util.Identifier;

public class RegistryATC {

	public static final String ID = "allthecreepers";
	public static final String NAME = "AllTheCreepers";

	public static final String CAKE_CREEPER_ID = "cake_creeper";
	public static final String COOKIE_CREEPER_ID = "cookie_creeper";
	public static final String DEMOLITION_CREEPER_ID = "demolition_creeper";
	public static final String EARTH_CREEPER_ID = "earth_creeper";
	public static final String FIRE_CREEPER_ID = "fire_creeper";
	public static final String FURNACE_CREEPER_ID = "furnace_creeper";
	public static final String LAVA_CREEPER_ID = "lava_creeper";
	public static final String LIGHTNING_CREEPER_ID = "lightning_creeper";
	public static final String LUMINOUS_CREEPER_ID = "luminous_creeper";
	public static final String WATER_CREEPER_ID = "water_creeper";

	public static final Identifier CAKE_CREEPER_IDENTIFIER = new Identifier(ID, CAKE_CREEPER_ID);
	public static final Identifier COOKIE_CREEPER_IDENTIFIER = new Identifier(ID, COOKIE_CREEPER_ID);
	public static final Identifier DEMOLITION_CREEPER_IDENTIFIER = new Identifier(ID, DEMOLITION_CREEPER_ID);
	public static final Identifier EARTH_CREEPER_IDENTIFIER = new Identifier(ID, EARTH_CREEPER_ID);

	private RegistryATC() {}
}
