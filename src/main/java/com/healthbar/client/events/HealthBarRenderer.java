package com.healthbar.client.events;

import org.lwjgl.opengl.GL11;

import com.healthbar.main.Healthbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthBarRenderer {
	
	private static final ResourceLocation HEALTH_BAR = new ResourceLocation(Healthbar.MODID, "textures/healthbar.png");
	
	private static float xMul, yMul;
	
	@SubscribeEvent
	public void renderHealthBar(RenderLivingEvent.Specials.Post<EntityLiving> e)
	{
		float healthProcent = e.entity.getHealth() / e.entity.getMaxHealth();
        GlStateManager.pushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.translate((float)e.x, (float)e.y + e.entity.height + 0.28F, (float)e.z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX * (Minecraft.getMinecraft().getRenderManager().options.thirdPersonView == 2 ? -1 : 1), 1.0F, 0.0F, 0.0F);
        GL11.glRotated(180, 0, 0, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(HEALTH_BAR);
        GlStateManager.translate(-0.62 * getXMul(), 0, 0.001);
        GL11.glScaled(1.0/100.0 * getXMul(), 1.0/128.0 * getYMul(), 1);
        drawScaledCustomSizeModalRect(0, 0, 0, 0, 0, (int) (128), 11, 128, 32);
        GlStateManager.translate(0, -1, -0.001);
        drawScaledCustomSizeModalRect(0, 0, 0, 0, 11, (int) (128 * healthProcent), 21, 128, 32);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GlStateManager.popMatrix();	
	}
	
	private static void drawScaledCustomSizeModalRect(double x, double y, double z, float u, float v, int maxU, int maxV, float tileWidth, float tileHeight)
	{
		float f = (float) (1.0/128.0);
        float f1 = (float) (1.0/32.0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0.0F), (double)(y + (float)maxV), z).tex((double)((float)(u + 0) * f), (double)((float)(v + maxV) * f1)).endVertex();
        worldrenderer.pos((double)(x + (float)maxU), (double)(y + (float)maxV), z).tex((double)((float)(u + maxU) * f), (double)((float)(v + maxV) * f1)).endVertex();
        worldrenderer.pos((double)(x + (float)maxU), (double)(y + 0.0F), z).tex((double)((float)(u + maxU) * f), (double)((float)(v + 0) * f1)).endVertex();
        worldrenderer.pos((double)(x + 0.0F), (double)(y + 0.0F), z).tex((double)((float)(u + 0) * f), (double)((float)(v + 0) * f1)).endVertex();
        tessellator.draw();
	}
	
	public static void setXMul(float xMul)
	{
		HealthBarRenderer.xMul = xMul;
	}
	
	public static void setYMul(float yMul)
	{
		HealthBarRenderer.yMul = yMul;
	}
	
	public static float getXMul()
	{
		return yMul;
	}
	
	public static float getYMul()
	{
		return xMul;
	}
}
