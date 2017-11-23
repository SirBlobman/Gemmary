package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.block.custom.*;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public final class GBlocks {
    public static final BlockGem
        AMETHYST_BLOCK          = new BlockGem(GItems.AMETHYST, 2),
        RUBY_BLOCK              = new BlockGem(GItems.RUBY, 2),
        SAPPHIRE_BLOCK          = new BlockGem(GItems.SAPPHIRE, 2),
        TALC_BLOCK              = new BlockGem(GItems.TALC, 0),
        TANZANITE_BLOCK         = new BlockGem(GItems.TANZANITE, 2),
        TOPAZ_BLOCK             = new BlockGem(GItems.TOPAZ, 2),
        TURQUOISE_BLOCK         = new BlockGem(GItems.TURQUOISE, 2);
    
    public static final BlockCompressor COMPRESSOR = new BlockCompressor();
    public static final BlockAtomGatherer AUTO_ATOM_GATHERER = new BlockAtomGatherer(true);
    public static final BlockAtomGatherer ATOM_GATHERER = new BlockAtomGatherer(false);
    public static final BlockHydrothermalVein HYDROTHERMAL_VEIN = new BlockHydrothermalVein();
    
    public static final ItemBlock
        ITEM_AMETHYST_BLOCK     = new QuickItemBlock(AMETHYST_BLOCK),
        ITEM_RUBY_BLOCK         = new QuickItemBlock(RUBY_BLOCK),
        ITEM_SAPPHIRE_BLOCK     = new QuickItemBlock(SAPPHIRE_BLOCK),
        ITEM_TALC_BLOCK         = new QuickItemBlock(TALC_BLOCK),
        ITEM_TANZANITE_BLOCK    = new QuickItemBlock(TANZANITE_BLOCK),
        ITEM_TOPAZ_BLOCK        = new QuickItemBlock(TOPAZ_BLOCK),
        ITEM_TURQUOISE_BLOCK    = new QuickItemBlock(TURQUOISE_BLOCK),
        ITEM_COMPRESSOR         = new QuickItemBlock(COMPRESSOR),
        ITEM_AUTO_ATOM_GATHERER = new QuickItemBlock(AUTO_ATOM_GATHERER),
        ITEM_ATOM_GATHERER      = new QuickItemBlock(ATOM_GATHERER),
        ITEM_HYDROTHERMAL_VEIN  = new QuickItemBlock(HYDROTHERMAL_VEIN);
    
    public static void registerBlocks(IForgeRegistry<Block> ifr) {
        ifr.registerAll(AMETHYST_BLOCK, RUBY_BLOCK, SAPPHIRE_BLOCK, TALC_BLOCK, TANZANITE_BLOCK, TOPAZ_BLOCK, TURQUOISE_BLOCK);
        ifr.registerAll(COMPRESSOR, AUTO_ATOM_GATHERER, ATOM_GATHERER, HYDROTHERMAL_VEIN);
    }
    
    public static void registerItems(IForgeRegistry<Item> ifr) {
        ifr.registerAll(ITEM_AMETHYST_BLOCK, ITEM_RUBY_BLOCK, ITEM_SAPPHIRE_BLOCK, ITEM_TALC_BLOCK, ITEM_TANZANITE_BLOCK, ITEM_TOPAZ_BLOCK, ITEM_TURQUOISE_BLOCK);
        ifr.registerAll(ITEM_COMPRESSOR, ITEM_AUTO_ATOM_GATHERER, ITEM_ATOM_GATHERER, ITEM_HYDROTHERMAL_VEIN);
    }
}