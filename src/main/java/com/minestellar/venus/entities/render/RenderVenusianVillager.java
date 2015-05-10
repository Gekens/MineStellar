package com.minestellar.venus.entities.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minestellar.venus.MinestellarVenus;
import com.minestellar.venus.entities.EntityVenusianVillager;
import com.minestellar.venus.entities.model.ModelAlienVillager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVenusianVillager extends RenderLiving {
	private static final ResourceLocation villagerTexture = new ResourceLocation(MinestellarVenus.TEXTURE_PREFIX + "textures/model/venusVillager.png");

	protected ModelAlienVillager villagerModel;

	public RenderVenusianVillager() {
		super(new ModelAlienVillager(0.0F), 0.5F);
		this.villagerModel = (ModelAlienVillager) this.mainModel;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderVillager((EntityVenusianVillager) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		this.renderVillager((EntityVenusianVillager) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110902_a(EntityVenusianVillager par1EntityVillager) {
		return RenderVenusianVillager.villagerTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.func_110902_a((EntityVenusianVillager) par1Entity);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		this.preRenderVillager((EntityVenusianVillager) par1EntityLivingBase, par2);
	}

	protected void preRenderVillager(EntityVenusianVillager par1EntityVillager, float par2) {
		float f1 = 0.9375F;

		if (par1EntityVillager.getGrowingAge() < 0) {
			f1 = (float) (f1 * 0.5D);
			this.shadowSize = 0.25F;
		} else {
			this.shadowSize = 0.5F;
		}

		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
		this.renderVillagerEquipedItems((EntityVenusianVillager) par1EntityLivingBase, par2);
	}

	@Override
	public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
		this.renderVillager((EntityVenusianVillager) par1EntityLivingBase, par2, par4, par6, par8, par9);
	}

	public void renderVillager(EntityVenusianVillager par1EntityVillager, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(par1EntityVillager, par2, par4, par6, par8, par9);
	}

	protected void renderVillagerEquipedItems(EntityVenusianVillager par1EntityVillager, float par2) {
		super.renderEquippedItems(par1EntityVillager, par2);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
		return this.shouldVillagerRenderPass((EntityVenusianVillager) par1EntityLivingBase, par2, par3);
	}

	protected int shouldVillagerRenderPass(EntityVenusianVillager par1EntityVillager, int par2, float par3) {
		return -1;
	}
}
