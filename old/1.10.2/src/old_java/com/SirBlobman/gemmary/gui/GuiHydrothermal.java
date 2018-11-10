package com.SirBlobman.gemmary.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.SirBlobman.gemmary.gui.container.ContainerHydrothermal;
import com.SirBlobman.gemmary.tile.TileHydrothermal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHydrothermal extends GuiContainer
{
	private final ResourceLocation GUI = new ResourceLocation("gemmary:textures/gui/ahtv.png");
	private TileHydrothermal th;
	
	public GuiHydrothermal(InventoryPlayer ip, TileHydrothermal th)
	{
		super(new ContainerHydrothermal(ip, th));
		xSize = 176;
		ySize = 166;
		this.th = th;
	}
	
	final int WATER_X = 14;
	final int WATER_Y = 17;
	final int WATER_U = 248;
	final int WATER_V = 0;
	final int WATER_W = 8;
	final int WATER_H = 41;
	
	final int BAR_X = 80;
	final int BAR_Y = 35;
	final int BAR_U = 176;
	final int BAR_V = 14;
	final int BAR_W = 24;
	final int BAR_H = 16;
	
	@Override
	public void drawGuiContainerBackgroundLayer(float ticks, int x, int y)
	{
		Minecraft m = Minecraft.getMinecraft();
		TextureManager tm = m.getTextureManager();
		tm.bindTexture(GUI);
		GlStateManager.color(1, 1, 1, 1);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		double progress = th.progressFraction();
		drawTexturedModalRect(guiLeft + BAR_X, guiTop + BAR_Y, BAR_U, BAR_V, (int) (progress * BAR_W), BAR_H);
		
		double remaining = th.waterLeftFraction(0);
		int yOff = (int) ((1 - remaining) * WATER_H);
		drawTexturedModalRect(guiLeft + WATER_X, guiTop + WATER_Y + yOff, WATER_U, WATER_V + yOff, WATER_W, WATER_H + yOff);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
		
		final int LABEL_X = 5;
		final int LABEL_Y = 5;
		FontRenderer fr = fontRendererObj;
		fr.drawString(th.getName(), LABEL_X, LABEL_Y, Color.darkGray.getRGB());
		List<String> hover = new ArrayList<String>();
		if(isIn(guiLeft + BAR_X, guiTop + BAR_Y, BAR_W, BAR_H, x, y))
		{
			hover.add(I18n.format("gui.htv.progress"));
			int progress = (int) (th.progressFraction() * 100);
			hover.add(progress + "%");
		}
		if(isIn(guiLeft + WATER_X, guiTop + WATER_Y, WATER_W, WATER_H, x, y))
		{
			hover.add(I18n.format("gui.htv.water"));
			hover.add(th.waterLeftMilliBuckets(0) + "mb");
		}
		if(!hover.isEmpty()) drawHoveringText(hover, x - guiLeft, y - guiTop, fr);
	}
	
	private boolean isIn(int x, int y, int xs, int ys, int mx, int my)
	{
		boolean b1 = mx >= x;
		boolean b2 = mx <= x + xs;
		boolean b3 = b1 && b2;
		boolean b4 = my >= y;
		boolean b5 = my <= y + ys;
		boolean b6 = b4 && b5;
		boolean b7 = b3 && b6;
		return b7;
	}
}