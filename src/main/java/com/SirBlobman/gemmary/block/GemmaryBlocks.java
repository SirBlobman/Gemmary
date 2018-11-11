package com.SirBlobman.gemmary.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public final class GemmaryBlocks {
    public static final BlockGem
        BLOCK_AMETHYST  = new BlockGem("amethyst", 7.0D),
        BLOCK_RUBY      = new BlockGem("ruby", 9.0D),
        BLOCK_SAPPHIRE  = new BlockGem("sapphire", 9.0D),
        BLOCK_TALC      = new BlockGem("talc", 1.0F),
        BLOCK_TANZANITE = new BlockGem("tanzanite", 6.75D),
        BLOCK_TOPAZ     = new BlockGem("topaz", 8.0D),
        BLOCK_TURQUOISE = new BlockGem("turquoise", 5.5D)
    ;
    
    public static final BlockAbstractMachine
        COMPRESSOR = new BlockCompressor(),
        HYDROTHERMAL_VEIN = new BlockHydrothermalVein()
    ;
    
    public static final ItemBlock
        ITEM_BLOCK_AMETHYST     = createItemBlock(BLOCK_AMETHYST),
        ITEM_BLOCK_RUBY         = createItemBlock(BLOCK_RUBY),
        ITEM_BLOCK_SAPPHIRE     = createItemBlock(BLOCK_SAPPHIRE),
        ITEM_BLOCK_TALC         = createItemBlock(BLOCK_TALC),
        ITEM_BLOCK_TANZANITE    = createItemBlock(BLOCK_TANZANITE),
        ITEM_BLOCK_TOPAZ        = createItemBlock(BLOCK_TOPAZ),
        ITEM_BLOCK_TURQUOISE    = createItemBlock(BLOCK_TURQUOISE),
    
        ITEM_COMPRESSOR         = createItemBlock(COMPRESSOR),
        ITEM_HYDROTHERMAL_VEIN  = createItemBlock(HYDROTHERMAL_VEIN)
    ;
    
    public static final void registerAllBlocks(IForgeRegistry<Block> ifr) {
        ifr.registerAll(BLOCK_AMETHYST, BLOCK_RUBY, BLOCK_SAPPHIRE, BLOCK_TALC, BLOCK_TANZANITE, BLOCK_TOPAZ, BLOCK_TURQUOISE);
        ifr.registerAll(COMPRESSOR, HYDROTHERMAL_VEIN);
    }
    
    public static final void registerAllItems(IForgeRegistry<Item> ifr) {
        ifr.registerAll(ITEM_BLOCK_AMETHYST, ITEM_BLOCK_RUBY, ITEM_BLOCK_SAPPHIRE, ITEM_BLOCK_TALC, ITEM_BLOCK_TANZANITE, ITEM_BLOCK_TOPAZ, ITEM_BLOCK_TURQUOISE);
        ifr.registerAll(ITEM_COMPRESSOR, ITEM_HYDROTHERMAL_VEIN);
    }
    
    public static final ItemBlock createItemBlock(Block block) {
        ResourceLocation registryLocation = block.getRegistryName();
        String registryName = registryLocation.getResourcePath();
        String unlocalizedName = block.getUnlocalizedName().substring(5);
        
        ItemBlock ib = new ItemBlock(block);
        ib.setRegistryName(registryName);
        ib.setUnlocalizedName(unlocalizedName);
        return ib;
    }
}
