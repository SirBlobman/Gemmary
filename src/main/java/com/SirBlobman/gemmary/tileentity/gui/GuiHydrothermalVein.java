package com.SirBlobman.gemmary.tileentity.gui;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.constant.ModInfo;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;
import com.SirBlobman.gemmary.tileentity.container.ContainerHydrothermalVein;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHydrothermalVein extends GuiContainer {
    private static final ResourceLocation SD_TEXTURES = new ResourceLocation(ModInfo.MODID, "textures/sd/gui/hydrothermal_vein.png");
    private static final ResourceLocation HD_TEXTURES = new ResourceLocation(ModInfo.MODID, "textures/hd/gui/hydrothermal_vein.png");
    
    private static final int
        PROGRESS_BAR_X = 72,  PROGRESS_BAR_Y = 35,
        PROGRESS_BAR_U = 176, PROGRESS_BAR_V = 0,
        PROGRESS_BAR_W = 24,  PROGRESS_BAR_H = 16,
        
        WATER_X = 12,  WATER_Y = 12,
        WATER_U = 184, WATER_V = 16,
        WATER_W = 8,   WATER_H = 41,
        
        LAVA_X = 156, LAVA_Y = 12,
        LAVA_U = 176, LAVA_V = 16,
        LAVA_W = 8,   LAVA_H = 41
    ;
    
    private final TileEntityHydrothermalVein tileEntity;
    public GuiHydrothermalVein(InventoryPlayer inv, TileEntityHydrothermalVein tileEntity) {
        super(new ContainerHydrothermalVein(inv, tileEntity));
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
    public void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int scaleX = ((this.width - this.xSize) / 2);
        int scaleY = ((this.height - this.ySize) / 2);
        TextureManager textureManager = this.mc.getTextureManager();
        textureManager.bindTexture(ConfigGemmary.HD_TEXTURES ? HD_TEXTURES : SD_TEXTURES);
        drawTexturedModalRect(scaleX, scaleY, 0, 0, this.xSize, this.ySize);
        
        if(this.tileEntity.hasWater()) {
            int waterLeft = WATER_H - getWaterScaled();
            int waterX = scaleX + WATER_X;
            int waterY = scaleY + WATER_Y + waterLeft;
            int waterU = WATER_U;
            int waterV = WATER_V + waterLeft;
            int waterW = WATER_W;
            int waterH = WATER_H;
            drawTexturedModalRect(waterX, waterY, waterU, waterV, waterW, waterH - waterLeft);
        }
        
        if(this.tileEntity.hasLava()) {
            int lavaLeft = LAVA_H - getLavaScaled();
            int lavaX = scaleX + LAVA_X;
            int lavaY = scaleY + LAVA_Y + lavaLeft;
            int lavaU = LAVA_U;
            int lavaV = LAVA_V + lavaLeft;
            int lavaW = LAVA_W;
            int lavaH = LAVA_H;
            drawTexturedModalRect(lavaX, lavaY, lavaU, lavaV, lavaW, lavaH - lavaLeft);
        }
        
        double progressFraction = this.tileEntity.getFractionProgressCompleted();
        int progressWidth = (int) (PROGRESS_BAR_W * progressFraction);
        drawTexturedModalRect(scaleX + PROGRESS_BAR_X, scaleY + PROGRESS_BAR_Y, PROGRESS_BAR_U, PROGRESS_BAR_V, progressWidth, PROGRESS_BAR_H);
    }
    
    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String guiName = I18n.format(this.tileEntity.getName());
        int guiNameX = (this.xSize / 2 - this.fontRenderer.getStringWidth(guiName) / 2);
        this.fontRenderer.drawString(guiName, guiNameX, 6, 0x555555); //Dark Gray

        int scaleX = ((this.width - this.xSize) / 2);
        int scaleY = ((this.height - this.ySize) / 2);
        
        List<String> hoverList = new ArrayList<>();
        if(isInRectangle(scaleX + PROGRESS_BAR_X, scaleY + PROGRESS_BAR_Y, PROGRESS_BAR_W, PROGRESS_BAR_H, mouseX, mouseY)) {
            String time = I18n.format("gui.gemmary.hydrothermal_vein.progress");
            int percent = (int) (this.tileEntity.getFractionProgressCompleted() * 100.0D);
            hoverList.add(time);
            hoverList.add(percent + "%");
        }
        
        if(isInRectangle(scaleX + WATER_X, scaleY + WATER_Y, WATER_W, WATER_H, mouseX, mouseY)) {
            String water = I18n.format("gui.gemmary.hydrothermal_vein.water");
            int milliBuckets = this.tileEntity.getField(0);
            hoverList.add(water);
            hoverList.add(milliBuckets + "mb");
        }
        
        if(isInRectangle(scaleX + LAVA_X, scaleY + LAVA_Y, LAVA_W, LAVA_H, mouseX, mouseY)) {
            String lava = I18n.format("gui.gemmary.hydrothermal_vein.lava");
            int milliBuckets = this.tileEntity.getField(1);
            hoverList.add(lava);
            hoverList.add(milliBuckets + "mb");
        }
        
        if(!hoverList.isEmpty()) {
            int hoverX = mouseX - scaleX;
            int hoverY = mouseY - scaleY;
            drawHoveringText(hoverList, hoverX, hoverY, this.fontRenderer);
        }
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
    
    private int getWaterScaled() {
        double waterFraction = this.tileEntity.getFractionWaterRemaining();
        double waterHeight = WATER_H;
        double waterScale = (waterFraction * waterHeight);
        return (int) waterScale;
    }
    
    private int getLavaScaled() {
        double lavaFraction = this.tileEntity.getFractionLavaRemaining();
        double lavaHeight = WATER_H;
        double lavaScale = (lavaFraction * lavaHeight);
        return (int) lavaScale;
    }
}