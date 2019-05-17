package T145.allthecreepers.api.creepers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface IEntityRendererProvider {

	@Environment(EnvType.CLIENT)
	String getTextureName();
}
