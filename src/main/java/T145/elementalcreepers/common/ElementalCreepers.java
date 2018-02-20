package T145.elementalcreepers.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = ElementalCreepers.MODID, name = ElementalCreepers.NAME, version = ElementalCreepers.VERSION)
public class ElementalCreepers {

	public static final String MODID = "elementalcreepers";
	public static final String NAME = "Elemental Creepers";
	public static final String VERSION = "$version";
	public static final Logger LOG = LogManager.getLogger(MODID);
}