package T145.allthecreepers.client.init;

import java.util.function.Function;

import T145.allthecreepers.api.constants.RegistryATC;
import T145.allthecreepers.config.ModConfig;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class ModMenuInit implements ModMenuApi {

	@Override
	public String getModId() {
		return RegistryATC.ID;
	}

	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return screen -> AutoConfig.getConfigScreen(ModConfig.class, screen).get();
	}
}
