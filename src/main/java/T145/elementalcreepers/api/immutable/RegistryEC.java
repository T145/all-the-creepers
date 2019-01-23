package T145.elementalcreepers.api.immutable;

import net.minecraft.util.ResourceLocation;

public class RegistryEC {

	private RegistryEC() {}

	public static final String MOD_ID = "elementalcreepers";
	public static final String MOD_NAME = "ElementalCreepers";

	public static final String KEY_CREEPER_FIRE = MOD_ID + ":FireCreeper";
	public static final String KEY_CREEPER_WATER = MOD_ID + ":WaterCreeper";
	public static final String KEY_CREEPER_AIR = MOD_ID + ":AirCreeper";
	public static final String KEY_CREEPER_EARTH = MOD_ID + ":EarthCreeper";
	public static final String KEY_CREEPER_ORDER = MOD_ID + ":OrderCreeper";
	public static final String KEY_CREEPER_ENTROPY = MOD_ID + ":EntropyCreeper";

	public static final ResourceLocation TEXTURE_CREEPER_FIRE = new ResourceLocation(MOD_ID, "textures/entities/firecreeper.png");
	public static final ResourceLocation TEXTURE_CREEPER_WATER = new ResourceLocation(MOD_ID, "textures/entities/watercreeper.png");
	public static final ResourceLocation TEXTURE_CREEPER_AIR = new ResourceLocation(MOD_ID, "textures/entities/windcreeper.png");
	public static final ResourceLocation TEXTURE_CREEPER_EARTH = new ResourceLocation(MOD_ID, "textures/entities/earthcreeper.png");
	public static final ResourceLocation TEXTURE_CREEPER_ORDER = new ResourceLocation(MOD_ID, "textures/entities/illusioncreeper.png");
	public static final ResourceLocation TEXTURE_CREEPER_ENTROPY = new ResourceLocation(MOD_ID, "textures/entities/eucreeper.png");
}