package T145.elementalcreepers.client.render.model;

import T145.elementalcreepers.api.client.IModelCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpiderCreeper extends ModelBase implements IModelCreeper {

	public ModelRenderer head;
	public ModelRenderer creeperArmor;
	public ModelRenderer body;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer leg5;
	public ModelRenderer leg6;

	public ModelSpiderCreeper() {
		this(0.0F);
	}

	public ModelSpiderCreeper(float scale) {
		byte pivotYOffset = 4;
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
		head.setRotationPoint(0.0F, pivotYOffset, 0.0F);
		creeperArmor = new ModelRenderer(this, 32, 0);
		creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
		creeperArmor.setRotationPoint(0.0F, pivotYOffset, 0.0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
		body.setRotationPoint(0.0F, pivotYOffset, 0.0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
		leg1.setRotationPoint(-2.0F, 12 + pivotYOffset, 4.0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
		leg2.setRotationPoint(2.0F, 12 + pivotYOffset, 4.0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
		leg3.setRotationPoint(-2.0F, 12 + pivotYOffset, -4.0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
		leg4.setRotationPoint(2.0F, 12 + pivotYOffset, -4.0F);
		leg5 = new ModelRenderer(this, 0, 16);
		leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg5.setRotationPoint(-6.0F, 12 + pivotYOffset, 0.0F);
		leg6 = new ModelRenderer(this, 0, 16);
		leg6.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leg6.setRotationPoint(6.0F, 12 + pivotYOffset, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		head.render(scale);
		body.render(scale);
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
		leg5.render(scale);
		leg6.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		head.rotateAngleY = (netHeadYaw / 57.295776F);
		head.rotateAngleX = (headPitch / 57.295776F);
		leg1.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		leg2.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
		leg3.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
		leg4.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		leg5.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		leg6.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}

	@Override
	public void postRenderHead() {
		head.postRender(HEAD_OFFSET);
	}
}