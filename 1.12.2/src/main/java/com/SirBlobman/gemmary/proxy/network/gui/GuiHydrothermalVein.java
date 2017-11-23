package com.SirBlobman.gemmary.proxy.network.gui;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.proxy.network.container.ContainerHydrothermalVein;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;
import com.SirBlobman.gemmary.utility.Util;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHydrothermalVein extends GuiContainer {
    private static final ResourceLocation NORMAL_TEXTURES = new ResourceLocation(Gemmary.MODID, "textures/gui/hydrothermal_vein.png");
    private static final ResourceLocation FANCY_TEXTURES  = new ResourceLocation(Gemmary.MODID, "textures/fancy/gui/hydrothermal_vein.png");
    private TileEntityHydrothermalVein tileEntity;
    
    private static final int
        PROGRESS_BAR_X  = 72,   PROGRESS_BAR_Y  = 35,
        PROGRESS_BAR_W  = 24,   PROGRESS_BAR_H  = 16,
        PROGRESS_BAR_U  = 176,  PROGRESS_BAR_V  = 14,
        
        WATER_X         = 12,   WATER_Y         = 12,
        WATER_W         = 8,    WATER_H         = 41,
        WATER_U         = 248,  WATER_V         = 0,

        LAVA_X          = 156,  LAVA_Y          = 12,
        LAVA_W          = 8,    LAVA_H          = 41,
        LAVA_U          = 240,  LAVA_V          = 0;
    
    public GuiHydrothermalVein(InventoryPlayer ip, TileEntityHydrothermalVein tileEntity) {
        super(new ContainerHydrothermalVein(ip, tileEntity));
        this.tileEntity = tileEntity;
        xSize = 176; ySize = 166;
    }
    
    @Override
    public void drawScreen(int mx, int my, float ticks) {
        drawDefaultBackground();
        super.drawScreen(mx, my, ticks);
        renderHoveredToolTip(mx, my);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
        TextureManager tm = mc.getTextureManager();
        if(GConfig.FANCY_TEXTURES) tm.bindTexture(FANCY_TEXTURES);
        else tm.bindTexture(NORMAL_TEXTURES);
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        
        double progress = tileEntity.getProgressCompletedFraction();
        drawTexturedModalRect(guiLeft + PROGRESS_BAR_X, guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_U, PROGRESS_BAR_V, (int) (progress * PROGRESS_BAR_W), PROGRESS_BAR_H);
        
        if(tileEntity.hasWater()) {
            int k = (WATER_H - getWaterLeftScaled());
            drawTexturedModalRect(guiLeft + WATER_X, guiTop + WATER_Y + k, WATER_U, WATER_V + k, WATER_W, WATER_H - k);
        }
        
        if(tileEntity.hasLava()) {
            int k = (LAVA_H - getLavaLeftScaled());
            drawTexturedModalRect(guiLeft + LAVA_X, guiTop + LAVA_Y + k, LAVA_U, LAVA_V + k, LAVA_W, LAVA_H - k);
        }
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        String name = I18n.format(tileEntity.getName());
        fontRenderer.drawString(name, (xSize / 2 - fontRenderer.getStringWidth(name) / 2), 6, Util.getRGB(85, 85, 85));
        
        List<String> hover = Util.newList();
        if(isIn(guiLeft + PROGRESS_BAR_X, guiTop + PROGRESS_BAR_Y, PROGRESS_BAR_W, PROGRESS_BAR_H, mx, my)) {
            String time = I18n.format("gui.gemmary.compressor.progress");
            int percent = (int) (tileEntity.getProgressCompletedFraction() * 100);
            hover.add(time);
            hover.add(percent + "%");
        }
        
        if(isIn(guiLeft + WATER_X, guiTop + WATER_Y, WATER_W, WATER_H, mx, my)) {
            String water = I18n.format("gui.gemmary.hydrothermal_vein.water");
            int mbuckets = (int) (tileEntity.getWaterRemainingFraction() * 2500.0D);
            hover.add(water);
            hover.add(mbuckets + "mb");
        }
        
        if(isIn(guiLeft + LAVA_X, guiTop + LAVA_Y, LAVA_W, LAVA_H, mx, my)) {
            String water = I18n.format("gui.gemmary.hydrothermal_vein.lava");
            int mbuckets = (int) (tileEntity.getLavaRemainingFraction() * 2500.0D);
            hover.add(water);
            hover.add(mbuckets + "mb");
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
    
    private int getWaterLeftScaled() {
        double water = tileEntity.getWaterRemainingFraction();
        double height = WATER_H;
        double scale = (water * height);
        return (int) scale;
    }
    
    private int getLavaLeftScaled() {
        double lava = tileEntity.getLavaRemainingFraction();
        double height = LAVA_H;
        double scale = (lava * height);
        return (int) scale;
    }
}