package T145.elementalcreepers.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagicCreeper extends ModelBase {

    public ModelRenderer brim;
    public ModelRenderer hat;
    public ModelRenderer head;
    public ModelRenderer creeperArmor;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelMagicCreeper() {
        this(0.0F);
    }

    public ModelMagicCreeper(float p_i46366_1_) {
        this.textureWidth = 69;
        this.textureHeight = 45;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head.setTextureSize(69, 45);
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body.setTextureSize(69, 45);
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
        this.leg3.setTextureSize(69, 45);
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
        this.leg4.setTextureSize(69, 45);
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(0.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg2.setRotationPoint(-4.0F, 18.0F, 4.0F);
        this.leg2.setTextureSize(69, 45);
        this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg1.setRotationPoint(2.0F, 18.0F, 4.0F);
        this.leg1.setTextureSize(69, 45);
        this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        this.brim = new ModelRenderer(this, 0, 32);
        this.brim.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12);
        this.brim.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.brim.setTextureSize(69, 45);
        this.setRotation(this.brim, 0.0F, 0.0F, 0.0F);
        this.hat = new ModelRenderer(this, 33, 0);
        this.hat.addBox(-4.5F, -6.0F, -4.5F, 9, 6, 9);
        this.hat.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.hat.setTextureSize(69, 45);
        this.setRotation(this.hat, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelRenderer renderer, float rotateAngleX, float rotateAngleY, float rotateAngleZ) {
        renderer.rotateAngleX = rotateAngleX;
        renderer.rotateAngleY = rotateAngleY;
        renderer.rotateAngleZ = rotateAngleZ;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
        this.leg1.render(scale);
        this.leg2.render(scale);
        this.leg3.render(scale);
        this.leg4.render(scale);
        this.brim.render(scale);
        this.hat.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.brim.rotateAngleY = this.head.rotateAngleY;
        this.brim.rotateAngleX = this.head.rotateAngleX;
        this.hat.rotateAngleY = this.head.rotateAngleY;
        this.hat.rotateAngleX = this.head.rotateAngleX;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}