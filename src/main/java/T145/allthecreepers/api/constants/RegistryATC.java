package T145.allthecreepers.api.constants;

import net.minecraft.util.Identifier;

public class RegistryATC {

	private RegistryATC() {}

	public static final String ID = "allthecreepers";
	public static final String NAME = "All The Creepers";

	public static Identifier getIdentifier(String name) {
		return new Identifier(ID, name);
	}
}
