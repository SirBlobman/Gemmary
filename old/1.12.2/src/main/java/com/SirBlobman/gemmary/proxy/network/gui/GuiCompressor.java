package com.SirBlobman.gemmary.proxy.network.gui;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.proxy.network.container.ContainerCompressor;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.utility.Util;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCompressor extends GuiContainer {
    private static final ResourceLocation NORMAL_TEXTURES = new ResourceLocation(Gemmary.MODID, "textures/gui/compressor.png");
    private static final ResourceLocation FANCY_TEXTURES = new ResourceLocation(Gemmary.MODID, "textures/fancy/gui/compressor.png");
    private TileEntityCompressor tileCompressor;
    
    private static final int
        PROGRESS_BAR_X = 77,    PROGRESS_BAR_Y = 30, 
        PROGRESS_BAR_U = 176,   PROGRESS_BAR_V = 18, 
        PROGRESS_BAR_W = 24,    PROGRESS_BAR_H = 17,
        
        FLAME_X        = 13,    FLAME_Y        = 40, 
        FLAME_U        = 176,   FLAME_V        = 0, 
        FLAME_W        = 18,    FLAME_H        = 18;
        
    
    public GuiCompressor(InventoryPlayer ip, TileEntityCompressor tileCompressor) {
        super(new ContainerCompressor(ip, tileCompressor));
        this.tileCompressor = tileCompressor;
        xSize = 176; ySize = 166;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
        TextureManager tm = mc.getTextureManager();
        if(GConfig.FANCY_TEXTURES) tm.bindTexture(FANCY_TEXTURES);
        else tm.bindTexture(NORMAL_TEXTURES);
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        
        double progress = tileCompressor.getCompressTimeCompleteFraction();
        drawTexturedModalRect(guiLeft + PROGRESS_BAR_X, guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_U, PROGRESS_BAR_V, (int) (progress * PROGRESS_BAR_W), PROGRESS_BAR_H);
        
        if(TileEntityCompressor.isBurning(tileCompressor)) {
            int k = (FLAME_H - getBurnLeftScaled());
            drawTexturedModalRect(guiLeft + FLAME_X, guiTop + FLAME_Y + k, FLAME_U, FLAME_V + k, FLAME_W, FLAME_H - k);
        }
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        String name = I18n.format(tileCompressor.getName());
        fontRenderer.drawString(name, (xSize / 2 - fontRenderer.getStringWidth(name) / 2), 6, Util.getRGB(85, 85, 85));
        
        List<String> hover = Util.newList();
        if(isIn(guiLeft + PROGRESS_BAR_X, guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_W, PROGRESS_BAR_H, mx, my)) {
            String time = I18n.format("gui.gemmary.compressor.progress");
            int percent = (int) (tileCompressor.getCompressTimeCompleteFraction() * 100);
            hover.add(time);
            hover.add(percent + "%");
        }
        
        if(isIn(guiLeft + FLAME_X, guiTop + FLAME_Y, FLAME_W, FLAME_H, mx, my)) {
            String fuel = I18n.format("gui.gemmary.compressor.fuel");
            hover.add(fuel);
            String seconds = Util.cropDecimal(tileCompressor.getFuelRemainingSeconds(), 2);
            hover.add(seconds + "s");
        }
        
        if(!hover.isEmpty()) drawHoveringText(hover, mx - guiLeft, my - guiTop, fontRenderer);
    }
    
    private boolean isIn(int x, int y, int xs, int ys, int mx, int my) {
        boolean b1 = (mx >= x), 
                b2 = (mx <= x + xs),
                b3 = (b1 && b2),
                b4 = (my >= y), 
                b5 = (my <= y + ys),
                b6 = (b4 && b5);
        return (b3 && b6);
    }
    
    private int getBurnLeftScaled() {
        int i = tileCompressor.getField(1);
        if(i == 0) i = 200;

        return (int) ((double) (tileCompressor.getField(0) * (FLAME_H - 1)) / (double) i);
    }
}