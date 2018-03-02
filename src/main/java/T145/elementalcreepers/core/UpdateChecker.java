package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class UpdateChecker {

	private UpdateChecker() {}

	public static ForgeVersion.CheckResult getResult() {
		return ForgeVersion.getResult(FMLCommonHandler.instance().findContainerFor(ElementalCreepers.MODID));
	}

	public static boolean hasUpdate() {
		ForgeVersion.CheckResult result = getResult();

		if (result.status == ForgeVersion.Status.PENDING) {
			ElementalCreepers.LOG.warn("Cannot check for updates, found status: PENDING!");
			return false;
		}

		return result.status.isAnimated();
	}

	public static String getLatestVersion() {
		return getResult().target.toString();
	}

	public static String getUpdateNotification() {
		return "Elemental Creepers has an update! Download " + getLatestVersion() + "!";
	}
}