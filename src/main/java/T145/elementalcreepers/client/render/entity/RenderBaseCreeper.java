package T145.elementalcreepers.client.render.entity;

import org.lwjgl.opengl.GL11;

import T145.elementalcreepers.ElementalCreepers;
import T145.elementalcreepers.client.render.entity.layers.LayerBaseCharge;
import T145.elementalcreepers.client.render.entity.layers.LayerFestiveCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBaseCreeper extends RenderAngryCreeper {

	private final ResourceLocation texture;
	private final boolean translucent;

	private RenderBaseCreeper(RenderManager manager, ModelBase model, String textureName, boolean translucent) {
		super(manager);
		this.mainModel = model;
		this.translucent = translucent;

		if (StringUtils.isNullOrEmpty(textureName)) {
			this.texture = new ResourceLocation("textures/entity/creeper/creeper.png");
		} else {
			this.texture = new ResourceLocation(ElementalCreepers.MODID, "textures/entities/" + textureName + ".png");
		}

		layerRenderers.clear();
		addLayer(new LayerFestiveCreeper(this));
		addLayer(getChargeLayer(this));
	}

	public RenderBaseCreeper(RenderManager manager, String textureName, boolean translucent) {
		this(manager, new ModelCreeper(), textureName, translucent);
	}

	public RenderBaseCreeper(RenderManager manager, boolean translucent) {
		this(manager, new ModelCreeper(), null, translucent);
	}

	RenderBaseCreeper(RenderManager manager, ModelBase model, String textureName) {
		this(manager, model, textureName, false);
	}

	public RenderBaseCreeper(RenderManager manager, String textureName) {
		this(manager, new ModelCreeper(), textureName);
	}

	protected LayerRenderer getChargeLayer(RenderBaseCreeper renderer) {
		return new LayerBaseCharge(renderer);
	}

	@Override
	public void doRender(EntityCreeper creeper, double x, double y, double z, float entityYaw, float partialTicks) {
		if (translucent) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.3F);
		}

		super.doRender(creeper, x, y, z, entityYaw, partialTicks);

		if (translucent) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public ResourceLocation getEntityTexture(EntityCreeper creeper) {
		super.getEntityTexture(creeper);
		return texture;
	}
}