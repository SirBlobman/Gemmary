package com.SirBlobman.gemmary.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.SirBlobman.gemmary.container.ContainerCompressor;
import com.SirBlobman.gemmary.tile.CompressorTE;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompressor extends GuiContainer
{
	private static final ResourceLocation gui = new ResourceLocation("gemmary:textures/gui/compressor.png");
	private CompressorTE te;
	
	public GuiCompressor(InventoryPlayer iP, CompressorTE tC)
	{
		super(new ContainerCompressor(iP, tC));
		
		xSize = 176;
		ySize = 207;
		
		te = tC;
	}
	
	final int CompressBarXPos = 49;
	final int CompressBarYPos = 60;
	final int CompressBarIconU = 0;
	final int CompressBarIconV = 207;
	final int CompressBarWidth = 80;
	final int CompressBarHeight = 18;
	
	final int FlameXPos = 31;
	final int FlameYPos = 60;
	final int FlameIconU = 176;
	final int FlameIconV = 0;
	final int FlameWidth = 18;
	final int FlameHeight = 18;
	final int FlameXSpacing = 18;
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float pTicks, int x, int y)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
		
		//Red, Green, Blue, Alpha
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		double compressProgress = te.fractionOfCompressTimeComplete();
		drawTexturedModalRect(guiLeft + CompressBarXPos, guiTop + CompressBarYPos, CompressBarIconU, CompressBarIconV, (int)(compressProgress * CompressBarWidth), CompressBarHeight);
		
		for(int i = 0; i < CompressorTE.FuelSlotsCount; ++i)
		{
			double compressRemaining = te.fractionOfFuelRemaining(i);
			int yOffset = (int)((1.0 -compressRemaining) * FlameHeight);
			drawTexturedModalRect(guiLeft + FlameXPos + FlameXSpacing * i, guiTop + FlameYPos + yOffset, FlameIconU, FlameIconV + yOffset, FlameWidth, FlameHeight - yOffset);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		final int LabelXPos = 72;
		final int LabelYPos = 5;
		fontRendererObj.drawString(I18n.format(te.getName()), LabelXPos, LabelYPos, Color.darkGray.getRGB());
		
		List<String> hoveringText = new ArrayList<String>();
		Object par = new Object[0];
		if(isInRect(guiLeft + CompressBarXPos, guiTop + CompressBarYPos, CompressBarWidth, CompressBarHeight, mouseX, mouseY))
		{
			hoveringText.add(I18n.format("gui.c.compresstime", par));
			int compressPercentage = (int)(te.fractionOfCompressTimeComplete() * 100);
			hoveringText.add(compressPercentage + "%");
		}
		
		for(int i = 0; i < CompressorTE.FuelSlotsCount; ++i)
		{
			if(isInRect(guiLeft + FlameXPos + FlameXSpacing * i, guiTop + FlameYPos, FlameWidth, FlameHeight, mouseX, mouseY))
			{
				hoveringText.add(I18n.format("gui.c.fuel", par));
				hoveringText.add(te.secondsOfFuelRemaining(i) + "s");
			}
		}
		if(!hoveringText.isEmpty())
		{
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}
	}
	
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
	{
		return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
	}
}
