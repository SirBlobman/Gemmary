package com.SirBlobman.gemmary.client.render.block;

import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_AMETHYST;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_RUBY;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_SAPPHIRE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_TALC;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_TANZANITE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_TOPAZ;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.BLOCK_TURQUOISE;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.COMPRESSOR;
import static com.SirBlobman.gemmary.block.GemmaryBlocks.HYDROTHERMAL_VEIN;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class GemmaryBlockRenderer {    
    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent e) {
        GemmaryHDMapper ghm = new GemmaryHDMapper();
        
        List<Block> blocks = Lists.newArrayList(
            BLOCK_AMETHYST, BLOCK_RUBY, BLOCK_SAPPHIRE, BLOCK_TALC, BLOCK_TANZANITE, BLOCK_TOPAZ, BLOCK_TURQUOISE,
            
            COMPRESSOR, HYDROTHERMAL_VEIN
        );
        blocks.forEach(block -> ModelLoader.setCustomStateMapper(block, ghm));
    }
}