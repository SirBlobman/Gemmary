package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.custom.ItemElement;
import com.SirBlobman.gemmary.item.custom.ItemGem;
import com.SirBlobman.gemmary.item.custom.ItemGemPart;
import com.SirBlobman.gemmary.render.block.GemmaryStateMapper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class GRendering {
    public static void items() {
        reg(GItems.AMETHYST, GItems.RUBY, GItems.SAPPHIRE, GItems.TALC, GItems.TANZANITE, GItems.TOPAZ, GItems.TURQUOISE);
        reg(GItems.DIAMOND_PART, GItems.EMERALD_PART);
        reg(GItems.ALUMINUM, GItems.BERYLLIUM, GItems.CARBON, GItems.CHROMIUM, GItems.OXYGEN);
        reg(GItems.CLOTH);
        
        //Armor
        reg(
            GItems.AMETHYST_HELMET, GItems.AMETHYST_CHESTPLATE, GItems.AMETHYST_LEGGINGS, GItems.AMETHYST_BOOTS,
            GItems.RUBY_HELMET, GItems.RUBY_CHESTPLATE, GItems.RUBY_LEGGINGS, GItems.RUBY_BOOTS,
            GItems.SAPPHIRE_HELMET, GItems.SAPPHIRE_CHESTPLATE, GItems.SAPPHIRE_LEGGINGS, GItems.SAPPHIRE_BOOTS,
            GItems.TALC_HELMET, GItems.TALC_CHESTPLATE, GItems.TALC_LEGGINGS, GItems.TALC_BOOTS,
            GItems.TANZANITE_HELMET, GItems.TANZANITE_CHESTPLATE, GItems.TANZANITE_LEGGINGS, GItems.TANZANITE_BOOTS,
            GItems.TOPAZ_HELMET, GItems.TOPAZ_CHESTPLATE, GItems.TOPAZ_LEGGINGS, GItems.TOPAZ_BOOTS,
            GItems.TURQUOISE_HELMET, GItems.TURQUOISE_CHESTPLATE, GItems.TURQUOISE_LEGGINGS, GItems.TURQUOISE_BOOTS
        );
    }
    
    public static void blocks() {
        reg(GBlocks.AMETHYST_BLOCK, GBlocks.RUBY_BLOCK, GBlocks.SAPPHIRE_BLOCK, GBlocks.TALC_BLOCK, GBlocks.TANZANITE_BLOCK, GBlocks.TOPAZ_BLOCK, GBlocks.TURQUOISE_BLOCK);
        reg(GBlocks.COMPRESSOR, GBlocks.AUTO_ATOM_GATHERER, GBlocks.ATOM_GATHERER, GBlocks.HYDROTHERMAL_VEIN);
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
            else if(i instanceof ItemArmor) path = "armor/" + path;

            if(GConfig.FANCY_TEXTURES) path = "fancy/" + path;
            reg(i, 0, path);
        }
    }
    
    private static void reg(Item i, int meta, String name) {
        ResourceLocation rl = new ResourceLocation(Gemmary.MODID, name);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
    }
    
    @SubscribeEvent
    public void customBlockStates(ModelRegistryEvent e) {
        GemmaryStateMapper gsm = new GemmaryStateMapper();
        
        //Gem Blocks
        ModelLoader.setCustomStateMapper(GBlocks.AMETHYST_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.RUBY_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.SAPPHIRE_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.TALC_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.TANZANITE_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.TOPAZ_BLOCK, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.TURQUOISE_BLOCK, gsm);
        
        //Machines
        ModelLoader.setCustomStateMapper(GBlocks.ATOM_GATHERER, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.AUTO_ATOM_GATHERER, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.COMPRESSOR, gsm);
        ModelLoader.setCustomStateMapper(GBlocks.HYDROTHERMAL_VEIN, gsm);
    }
}