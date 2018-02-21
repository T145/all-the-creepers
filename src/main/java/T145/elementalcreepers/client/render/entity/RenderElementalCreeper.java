package T145.elementalcreepers.client.render.entity;

import T145.elementalcreepers.ElementalCreepers;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElementalCreeper extends RenderCreeper {

	private final ResourceLocation entityTexture;
	private final boolean translucent;

	public RenderElementalCreeper(RenderManager renderManager, String textureName, boolean translucent) {
		super(renderManager);
		this.translucent = translucent;

		if (StringUtils.isNullOrEmpty(textureName)) {
			this.entityTexture = new ResourceLocation("textures/entity/creeper/creeper.png");
		} else {
			this.entityTexture = new ResourceLocation(ElementalCreepers.MODID, "textures/entities/" + textureName + ".png");
		}

		addLayer(new LayerSpecialEvent(this));
	}

	public RenderElementalCreeper(RenderManager renderManager, String textureName) {
		this(renderManager, textureName, false);
	}

	@Override
	public void doRender(EntityCreeper entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity != null) {
			if (translucent) {
				GlStateManager.pushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(770, 771);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.3F);
			}

			super.doRender(entity, x, y, z, entityYaw, partialTicks);

			if (translucent) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity) {
		return entityTexture;
	}
}