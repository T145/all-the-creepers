package T145.allthecreepers.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface IEntityRendererProvider {

	@Environment(EnvType.CLIENT)
	String getTextureName();
}
