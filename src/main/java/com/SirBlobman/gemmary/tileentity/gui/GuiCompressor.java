package com.SirBlobman.gemmary.tileentity.gui;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.constant.ModInfo;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.tileentity.container.ContainerCompressor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCompressor extends GuiContainer {
    private static final ResourceLocation SD_TEXTURES = new ResourceLocation(ModInfo.MODID, "textures/sd/gui/compressor.png");
    private static final ResourceLocation HD_TEXTURES = new ResourceLocation(ModInfo.MODID, "textures/hd/gui/compressor.png");
    
    /*
     * Coordinate Information:
     * X: x-coordinate in GUI
     * Y: y-coordinate in GUI
     * 
     * U: x-coordinate in texture
     * V: y-coordinate in texture
     * 
     * W: width in GUI
     * H: height in GUI
     */
    private static final int
        PROGRESS_BAR_X = 77, PROGRESS_BAR_Y = 30, 
        PROGRESS_BAR_U = 176, PROGRESS_BAR_V = 18,
        PROGRESS_BAR_W = 24, PROGRESS_BAR_H = 17,
        
        FLAME_X = 13, FLAME_Y = 40,
        FLAME_U = 176, FLAME_V = 0,
        FLAME_W = 18, FLAME_H = 18
    ;
    
    private final TileEntityCompressor tileEntity;
    public GuiCompressor(InventoryPlayer inv, TileEntityCompressor tileEntity) {
        super(new ContainerCompressor(inv, tileEntity));
        this.tileEntity = tileEntity;
        
        this.xSize = 176;
        this.ySize = 166;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float ticks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, ticks);
        renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float ticks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        TextureManager textureManager = this.mc.getTextureManager();
        int scaleX = ((this.width - this.xSize) / 2);
        int scaleY = ((this.height - this.ySize) / 2);
        textureManager.bindTexture(ConfigGemmary.HD_TEXTURES ? HD_TEXTURES : SD_TEXTURES);   
        drawTexturedModalRect(scaleX, scaleY, 0, 0, this.xSize, this.ySize);
        
        
        if(this.tileEntity.isBurning()) {
            double fuelRemainingFraction = this.tileEntity.getFractionRemainingFuel();
            int fuelWidth = (int) (FLAME_W * fuelRemainingFraction);
            drawTexturedModalRect(scaleX + FLAME_X, scaleY + FLAME_Y, FLAME_U, FLAME_V, fuelWidth, FLAME_H);
        }
        
        double progressBarFraction = this.tileEntity.getFractionCompressTimeComplete();
        int progressBarWidth = (int) (PROGRESS_BAR_W * progressBarFraction);
        drawTexturedModalRect(scaleX + PROGRESS_BAR_X, scaleY + PROGRESS_BAR_Y, PROGRESS_BAR_U, PROGRESS_BAR_V, progressBarWidth, PROGRESS_BAR_H);
    }
    
    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String guiName = I18n.format(this.tileEntity.getName());
        int guiNameX = (this.xSize / 2 - this.fontRenderer.getStringWidth(guiName) / 2);
        this.fontRenderer.drawString(guiName, guiNameX, 6, 0x555555); //Dark Gray
        
        List<String> hoverTextList = new ArrayList<>();
        if(isInRectangle(this.guiLeft + PROGRESS_BAR_X, this.guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_W, PROGRESS_BAR_H, mouseX, mouseY)) {
            String progress = I18n.format("gui.gemmary.compressor.progress");
            int percent = (int) (this.tileEntity.getFractionCompressTimeComplete() * 100.0D);
            hoverTextList.add(progress);
            hoverTextList.add(percent + "%");
        }
        
        if(isInRectangle(this.guiLeft + FLAME_X, this.guiTop + FLAME_Y, FLAME_W, FLAME_H, mouseX, mouseY)) {
            String fuel = I18n.format("gui.gemmary.compressor.fuel");
            int timeLeft = (int) Math.round(this.tileEntity.getSecondsRemainingFuel());
            String seconds = timeLeft + "s";
            hoverTextList.add(fuel);
            hoverTextList.add(seconds);
        }
        
        if(!hoverTextList.isEmpty()) drawHoveringText(hoverTextList, mouseX - this.guiLeft, mouseY - this.guiTop, this.fontRenderer);
    }
    
    private boolean isInRectangle(int minX, int minY, int plusX, int plusY, int mouseX, int mouseY) {
        int maxX = (minX + plusX);
        int maxY = (minY + plusY);
        
        boolean checkMinX = (mouseX >= minX);
        boolean checkMaxX = (mouseX <= maxX);
        boolean checkX = (checkMinX && checkMaxX);
        
        boolean checkMinY = (mouseY >= minY);
        boolean checkMaxY = (mouseY <= maxY);
        boolean checkY = (checkMinY && checkMaxY);
        
        return (checkX && checkY);
    }
}