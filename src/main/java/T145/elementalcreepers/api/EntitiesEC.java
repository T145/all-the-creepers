package T145.elementalcreepers.api;

import T145.elementalcreepers.api.immutable.RegistryEC;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(RegistryEC.MOD_ID)
public class EntitiesEC {

	private EntitiesEC() {}

	@ObjectHolder(RegistryEC.KEY_CREEPER_FIRE)
	public static EntityEntry CREEPER_FIRE;

	@ObjectHolder(RegistryEC.KEY_CREEPER_WATER)
	public static EntityEntry CREEPER_WATER;

	@ObjectHolder(RegistryEC.KEY_CREEPER_AIR)
	public static EntityEntry CREEPER_AIR;

	@ObjectHolder(RegistryEC.KEY_CREEPER_EARTH)
	public static EntityEntry CREEPER_EARTH;

	@ObjectHolder(RegistryEC.KEY_CREEPER_ORDER)
	public static EntityEntry CREEPER_ORDER;

	@ObjectHolder(RegistryEC.KEY_CREEPER_ENTROPY)
	public static EntityEntry CREEPER_ENTROPY;
}
