package com.SirBlobman.gemmary.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiACTE extends GuiContainer 
{
	public GuiACTE(Container container) 
    {
        super(container);

        this.xSize = 176;
        this.ySize = 166;
    }
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(I18n.format("container.ac", new Object[0]), 6, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.ac.inv", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
    {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/atom_combiner.png"));
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
