package com.SirBlobman.gemmary.client.render.item;

import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_AMETHYST;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_RUBY;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_SAPPHIRE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_TALC;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_TANZANITE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_TOPAZ;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_BLOCK_TURQUOISE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_COMPRESSOR;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.ITEM_HYDROTHERMAL_VEIN;
import static com.SirBlobman.gemmary.item.GemmaryItems.*;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.constant.ModInfo;

import java.util.Arrays;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public final class GemmaryItemRenderer {
    public static void registerItems() {
        register(
            GEM_AMETHYST, GEM_RUBY, GEM_SAPPHIRE, GEM_TALC, GEM_TANZANITE, GEM_TOPAZ, GEM_TURQUOISE,
            
            
            DIAMOND_PART, EMERALD_PART,
            
            
            ALUMINUM, BERYLLIUM, CARBON, CHROMIUM, OXYGEN,
            
            
            CLOTH,
            
            AMETHYST_HELMET, AMETHYST_CHESTPLATE, AMETHYST_LEGGINGS, AMETHYST_BOOTS, 
            RUBY_HELMET, RUBY_CHESTPLATE, RUBY_LEGGINGS, RUBY_BOOTS,
            SAPPHIRE_HELMET, SAPPHIRE_CHESTPLATE, SAPPHIRE_LEGGINGS, SAPPHIRE_BOOTS,
            TALC_HELMET, TALC_CHESTPLATE, TALC_LEGGINGS, TALC_BOOTS,
            TANZANITE_HELMET, TANZANITE_CHESTPLATE, TANZANITE_LEGGINGS, TANZANITE_BOOTS,
            TOPAZ_HELMET, TOPAZ_CHESTPLATE, TOPAZ_LEGGINGS, TOPAZ_BOOTS,
            TURQUOISE_HELMET, TURQUOISE_CHESTPLATE, TURQUOISE_LEGGINGS, TURQUOISE_BOOTS
        );
    }
    
    public static void registerItemBlocks() {
        register(
            ITEM_BLOCK_AMETHYST, ITEM_BLOCK_RUBY, ITEM_BLOCK_SAPPHIRE, ITEM_BLOCK_TALC, ITEM_BLOCK_TANZANITE, ITEM_BLOCK_TOPAZ, ITEM_BLOCK_TURQUOISE,
            
            ITEM_COMPRESSOR, ITEM_HYDROTHERMAL_VEIN
        );
    }
    
    public static final void register(Item... items) {
        Arrays.stream(items).forEach(item -> {
            ResourceLocation rl = item.getRegistryName();
            String path = rl.getResourcePath();

            if(item instanceof ItemBlock) path = "block/" + path;
            path = (ConfigGemmary.HD_TEXTURES ? "hd/" : "sd/") + path;
            
            register(item, 0, path);
        });
    }
    
    private static final void register(Item item, int meta, String path) {
        ResourceLocation rl = new ResourceLocation(ModInfo.MODID, path);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }
}