package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.custom.ItemElement;
import com.SirBlobman.gemmary.item.custom.ItemGem;
import com.SirBlobman.gemmary.item.custom.ItemGemPart;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public final class GRendering {
    public static void items() {
        reg(GItems.AMETHYST, GItems.RUBY, GItems.SAPPHIRE, GItems.TALC, GItems.TANZANITE, GItems.TOPAZ, GItems.TURQUOISE);
        reg(GItems.DIAMOND_PART, GItems.EMERALD_PART);
        reg(GItems.ALUMINUM, GItems.BERYLLIUM, GItems.CARBON, GItems.CHROMIUM, GItems.OXYGEN);
    }
    
    public static void blocks() {
        reg(GBlocks.AMETHYST_BLOCK, GBlocks.RUBY_BLOCK, GBlocks.SAPPHIRE_BLOCK, GBlocks.TALC_BLOCK, GBlocks.TANZANITE_BLOCK, GBlocks.TOPAZ_BLOCK, GBlocks.TURQUOISE_BLOCK);
    }
    
    private static void reg(Block... bb) {
        Item[] ii = new Item[bb.length];
        for(int i = 0; i < bb.length; i++) {
            Block b = bb[i];
            Item ib = Item.getItemFromBlock(b);
            ii[i] = ib;
        } reg(ii);
    }
    
    private static void reg(Item... ii) {
        for(Item i : ii) {
            ResourceLocation rl = i.getRegistryName();
            String path = rl.getResourcePath();
            
            if(i instanceof ItemBlock) path = "block/" + path;
            else if(i instanceof ItemGem || i instanceof ItemGemPart) path = "gem/" + path;
            else if(i instanceof ItemElement) path = "element/" + path;

            if(GConfig.FANCY_TEXTURES) path = "fancy/" + path;
            reg(i, 0, path);
        }
    }
    
    private static void reg(Item i, int meta, String name) {
        ResourceLocation rl = new ResourceLocation(Gemmary.MODID, name);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
    }
}