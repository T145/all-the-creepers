package T145.elementalcreepers.core;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraft.util.text.*;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class UpdateChecker {

    private UpdateChecker() {
    }

    public static ForgeVersion.CheckResult getResult() {
        return ForgeVersion.getResult(FMLCommonHandler.instance().findContainerFor(ElementalCreepers.MODID));
    }

    public static boolean hasUpdate() {
        ForgeVersion.CheckResult result = getResult();

        if (result.status == ForgeVersion.Status.PENDING) {
            ElementalCreepers.LOG.warn("Cannot check for updates! Found status PENDING!");
            return false;
        }

        return result.status.isAnimated();
    }

    public static String getLatestVersion() {
        return getResult().target.toString();
    }

    public static ITextComponent getUpdateNotification() {
        ITextComponent prefix = new TextComponentTranslation("elementalcreepers.client.update.prefix").setStyle(new Style().setColor(TextFormatting.GREEN));
        ITextComponent base = new TextComponentTranslation("elementalcreepers.client.update").setStyle(new Style().setColor(TextFormatting.GOLD));
        ITextComponent postfix = new TextComponentString(TextFormatting.AQUA + getLatestVersion() + TextFormatting.GOLD + "!");
        return prefix.appendSibling(base).appendSibling(postfix);
    }
}