package com.SirBlobman.gemmary.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.SirBlobman.gemmary.guicontainer.ContainerHTV;
import com.SirBlobman.gemmary.tiles.AHTV_TE;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHTV extends GuiContainer
{
	private static final ResourceLocation htv = new ResourceLocation("textures/gui/artificial_hydrothermal_vein_gui.png");
	private AHTV_TE tileEntity;
	
	public GuiHTV(InventoryPlayer invPlayer, AHTV_TE tileHTV)
	{
		super(new ContainerHTV(invPlayer, tileHTV));
		xSize = 176; 
		ySize = 165;
		
		this.tileEntity = tileHTV;
	}
	
	//Graphical Elements for the Water Meter. U and V are the position on the actual texture.png
	final int WaterXPos = 27;
	final int WaterYPos = 14;
	final int WaterIconU = 185;
	final int WaterIconV = 0;
	final int WaterIconWidth = 71;
	final int WaterIconHeight = 8;
	
	//Graphical Elements for the Progress Bar
	final int ProgXPos = 87;
	final int ProgYPos = 84;
	final int ProgIconU = 324;
	final int ProgIconV = 13;
	final int ProgIconWidth = 22;
	final int ProgIconHeight = 15;
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(htv);
		GlStateManager.color(1.0F, 1.0F, 1.0F,1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		double Progress = tileEntity.fractionOfProgressComplete();
		drawTexturedModalRect(guiLeft + ProgXPos, guiTop + ProgYPos, ProgIconU, ProgIconV, 
																	(int) (Progress * ProgIconWidth), ProgIconHeight);
		
		for (int i = 0; i < 1; ++i)
		{
			double waterRemaining = tileEntity.fractionOfWaterRemaining(i);
			int yOffset = (int) ((1.0 - waterRemaining) * WaterIconHeight);
			drawTexturedModalRect(guiLeft + WaterXPos + i, guiTop + WaterYPos + yOffset, WaterIconU, WaterIconV + yOffset, WaterIconWidth, WaterIconHeight - yOffset);			
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		final int LabelXPos = 5;
		final int LabelYPos = 5;
		fontRendererObj.drawString(tileEntity.getDisplayName().getUnformattedText(), LabelXPos, LabelYPos, Color.darkGray.getRGB());
		
		List<String> hoveringText = new ArrayList<String>();
		
		if (isInRect(guiLeft + ProgXPos, guiTop + ProgYPos, ProgIconWidth, ProgIconHeight, mouseX, mouseY))
		{
			hoveringText.add(I18n.format("gui.htv.progress", new Object[0]));
			int progressPercentage =(int) (tileEntity.fractionOfProgressComplete() * 100);
			hoveringText.add(progressPercentage + "%");
		}
		
		for (int i = 0; i < 1; ++i)
		{
			if (isInRect(guiLeft + WaterXPos + i, guiTop + WaterYPos, WaterIconWidth, WaterIconHeight, mouseX, mouseY)) 
			{
				hoveringText.add(I18n.format("gui.htv.water", new Object[0]));
				hoveringText.add(tileEntity.millibucketsOfWaterRemaining(i) + "mb");
			}
		}
		
		if (!hoveringText.isEmpty())
		{
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}
	}
	
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
	{
		return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
	}
}
