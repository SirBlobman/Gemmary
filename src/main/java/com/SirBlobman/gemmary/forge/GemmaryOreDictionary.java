package com.SirBlobman.gemmary.forge;

import com.SirBlobman.gemmary.block.GemmaryBlocks;
import com.SirBlobman.gemmary.item.GemmaryItems;

import net.minecraftforge.oredict.OreDictionary;

public final class GemmaryOreDictionary {
    public static final void registerAllItems() {
        OreDictionary.registerOre("gemAmethyst", GemmaryItems.GEM_AMETHYST);
        OreDictionary.registerOre("gemRuby", GemmaryItems.GEM_RUBY);
        OreDictionary.registerOre("gemSapphire", GemmaryItems.GEM_SAPPHIRE);
        OreDictionary.registerOre("gemTalc", GemmaryItems.GEM_TALC);
        OreDictionary.registerOre("gemTanzanite", GemmaryItems.GEM_TANZANITE);
        OreDictionary.registerOre("gemTopaz", GemmaryItems.GEM_TOPAZ);
        OreDictionary.registerOre("gemTurquoise", GemmaryItems.GEM_TURQUOISE);
    }
    
    public static final void registerAllBlocks() {
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_AMETHYST);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_RUBY);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_SAPPHIRE);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_TALC);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_TANZANITE);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_TOPAZ);
        OreDictionary.registerOre("blockAmethyst", GemmaryBlocks.BLOCK_TURQUOISE);
    }
}