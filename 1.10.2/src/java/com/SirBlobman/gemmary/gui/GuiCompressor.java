package com.SirBlobman.gemmary.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.SirBlobman.gemmary.gui.container.ContainerCompressor;
import com.SirBlobman.gemmary.tile.TileCompressor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCompressor extends GuiContainer
{
	private final String resource = "gemmary:textures/gui/compressor.png";
	private final ResourceLocation GUI = new ResourceLocation(resource);
	private TileCompressor te;
	
	public GuiCompressor(InventoryPlayer ip, TileCompressor tc)
	{
		super(new ContainerCompressor(ip, tc));
		xSize = 176;
		ySize = 207;
		te = tc;
	}
	
	final int BAR_X = 49;
	final int BAR_Y = 60;
	final int BAR_U = 0;
	final int BAR_V = 207;
	final int BAR_W = 80;
	final int BAR_H = 18;
	
	final int FLAME_X = 31;
	final int FLAME_Y = 60;
	final int FLAME_U = 176;
	final int FLAME_V = 0;
	final int FLAME_W = 18;
	final int FLAME_H = 18;
	final int FLAME_S = 18;
	
	@Override
	public void drawGuiContainerBackgroundLayer(float ticks, int x, int y)
	{
		Minecraft m = Minecraft.getMinecraft();
		TextureManager tm = m.getTextureManager();
		tm.bindTexture(GUI);
		
		GlStateManager.color(1, 1, 1, 1);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		double progress = te.compresstimeCompleteFraction();
		drawTexturedModalRect(guiLeft + BAR_X, guiTop + BAR_Y, BAR_U, BAR_V, (int) (progress * BAR_W), BAR_H);
		
		for(int i = 0; i < te.FUEL_SLOTS; ++i)
		{
			double remaining = te.fuelFractionRemaining(i);
			int yOff = (int) ((1.0 - remaining) * FLAME_H);
			drawTexturedModalRect(guiLeft + FLAME_X + FLAME_S * i, guiTop + FLAME_Y + yOff, FLAME_U, FLAME_V + yOff, FLAME_W, FLAME_H - yOff);
		}
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mx, int my)
	{
		super.drawGuiContainerForegroundLayer(mx, my);
		final int LABEL_X = 72;
		final int LABEL_Y = 5;
		FontRenderer fr = fontRendererObj;
		String label = I18n.format(te.getName());
		fr.drawString(label, LABEL_X, LABEL_Y, Color.darkGray.getRGB());
		
		List<String> hover = new ArrayList<String>();
		if(isIn(guiLeft + BAR_X, guiTop + BAR_Y, BAR_W, BAR_H, mx, my))
		{
			String time = I18n.format("gui.compressor.time");
			hover.add(time);
			int percent = (int) (te.compresstimeCompleteFraction() * 100);
			hover.add(percent + "%");
		}
		
		for(int i = 0; i < te.FUEL_SLOTS; ++i)
		{
			if(isIn(guiLeft + FLAME_X + FLAME_S * i, guiTop + FLAME_Y, FLAME_W, FLAME_H, mx, my))
			{
				String fuel = I18n.format("gui.compressor.fuel");
				hover.add(fuel);
				hover.add(te.fuelSecondsRemaining(i) + "s");
			}
		}
		
		if(!hover.isEmpty())
		{
			drawHoveringText(hover, mx - guiLeft, my - guiTop, fr);
		}
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