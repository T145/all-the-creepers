package T145.elementalcreepers.client.render.model;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpiderCreeper extends ModelCreeper {

	public ModelRenderer leg5;
	public ModelRenderer leg6;

	public ModelSpiderCreeper() {
		this(0.0F);
	}

	public ModelSpiderCreeper(float offset) {
		super(offset);
		this.leg5 = new ModelRenderer(this, 0, 16);
		this.leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		this.leg5.setRotationPoint(-6.0F, 18F, 0.0F);
		this.leg6 = new ModelRenderer(this, 0, 16);
		this.leg6.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		this.leg6.setRotationPoint(6.0F, 18F, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.leg5.render(scale);
		this.leg6.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		this.leg5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg6.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}
}