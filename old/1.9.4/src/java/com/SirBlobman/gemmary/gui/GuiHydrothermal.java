package com.SirBlobman.gemmary.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.container.ContainerHydrothermal;
import com.SirBlobman.gemmary.tile.HydrothermalTE;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHydrothermal extends GuiContainer
{
	private static final ResourceLocation htv = new ResourceLocation("gemmary:textures/gui/ahtv.png");
	private HydrothermalTE tH;
	
	public GuiHydrothermal(InventoryPlayer ip, HydrothermalTE th)
	{
		super(new ContainerHydrothermal(ip, th));
		xSize = 176;
		ySize = 166;
		
		tH = th;
	}
	
	final int WaterXPos = 14;
	final int WaterYPos = 17;
	final int WaterIconU = 248;
	final int WaterIconV = 0;
	final int WaterIconWidth = 8;
	final int WaterIconHeight = 41;
	
	final int ProgXPos = 80;
	final int ProgYPos = 35;
	final int ProgIconU = 176;
	final int ProgIconV = 14;
	final int ProgIconWidth = 24;
	final int ProgIconHeight = 16;
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float pTicks, int x, int y)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(htv);
		GlStateManager.color(10.F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		double Progress = tH.fractionOfProgressComplete();
		drawTexturedModalRect(guiLeft + ProgXPos, guiTop + ProgYPos, ProgIconU, ProgIconV, (int)Progress * ProgIconWidth, ProgIconHeight);
		
		for(int i = 0; i < 1; ++i)
		{
			double waterRemaining = tH.fractionOfWaterRemaining(i);
			int yOffset = (int) ((1 - waterRemaining) * WaterIconHeight);
			
			drawTexturedModalRect(guiLeft + WaterXPos + i, guiTop + WaterYPos + yOffset, WaterIconU, WaterIconV + yOffset, WaterIconWidth, WaterIconHeight + yOffset);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
		
		final int LabelXPos = 5;
		final int LabelYPos = 5;
		fontRendererObj.drawString(tH.getName(), LabelXPos, LabelYPos, Color.darkGray.getRGB());
		
		List<String> hText = new ArrayList<String>();
		
		if(isInRect(guiLeft + ProgXPos, guiTop + ProgYPos, ProgIconWidth, ProgIconHeight, x, y))
		{
			hText.add(GUtil.translate("gui.htv.progress"));
			int progressPercentage = (int) (tH.fractionOfProgressComplete() * 100);
			hText.add(progressPercentage + "%");
		}
		
		for(int i = 0; i < 1; ++i)
		{
			if(isInRect(guiLeft + WaterXPos + i, guiTop + WaterYPos, WaterIconWidth, WaterIconHeight, x, y))
			{
				hText.add(GUtil.translate("gui.htv.water"));
				hText.add(tH.millibucketsOfWaterRemaining(i) + "mb");
			}
		}
		
		if(!hText.isEmpty())
		{
			drawHoveringText(hText, x - guiLeft, y - guiTop, fontRendererObj);
		}
	}
	
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
	{
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}
}