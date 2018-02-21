package T145.elementalcreepers.client.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFriendlyCreeper extends ModelBase {

	public ModelRenderer head;
	public ModelRenderer creeperArmor;
	public ModelRenderer body;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	protected float childYOffset = 16.0F;

	public ModelFriendlyCreeper() {
		this(0.0F);
	}

	public ModelFriendlyCreeper(float par1) {
		byte b0 = 4;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
		this.head.setRotationPoint(0.0F, b0, 0.0F);
		this.creeperArmor = new ModelRenderer(this, 32, 0);
		this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
		this.creeperArmor.setRotationPoint(0.0F, b0, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
		this.body.setRotationPoint(0.0F, b0, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 16);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
		this.leg1.setRotationPoint(-2.0F, 12 + b0, 4.0F);
		this.leg2 = new ModelRenderer(this, 0, 16);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
		this.leg2.setRotationPoint(2.0F, 12 + b0, 4.0F);
		this.leg3 = new ModelRenderer(this, 0, 16);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
		this.leg3.setRotationPoint(-2.0F, 12 + b0, -4.0F);
		this.leg4 = new ModelRenderer(this, 0, 16);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
		this.leg4.setRotationPoint(2.0F, 12 + b0, -4.0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		if (this.isChild) {
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(0.7F, 0.7F, 0.7F);
			GL11.glTranslatef(0.0F, this.childYOffset * par7, 0.0F);
			this.head.render(par7);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
			this.body.render(par7);
			this.leg1.render(par7);
			this.leg2.render(par7);
			this.leg3.render(par7);
			this.leg4.render(par7);
			GL11.glPopMatrix();
		} else {
			this.head.render(par7);
			this.body.render(par7);
			this.leg1.render(par7);
			this.leg2.render(par7);
			this.leg3.render(par7);
			this.leg4.render(par7);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = (par4 / 57.295776F);
		this.head.rotateAngleX = (par5 / 57.295776F);
		this.leg1.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		this.leg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		this.leg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		this.leg4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
	}
}