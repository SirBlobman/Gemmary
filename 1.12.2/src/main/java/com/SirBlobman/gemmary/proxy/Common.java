package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.forge.GOreDict;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.proxy.network.GemmaryGuiHandler;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
    public void pre(FMLPreInitializationEvent e) {
        GameRegistry.registerTileEntity(TileEntityCompressor.class, "compressor");
        GameRegistry.registerTileEntity(TileEntityHydrothermalVein.class, "hydrothermal_vein");
    }
    
    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.INSTANCE, new GemmaryGuiHandler());
    }
    
    public void post(FMLPostInitializationEvent e) {
        GOreDict.registerItems();
        GOreDict.registerBlocks();
    }
    
    public void items(IForgeRegistry<Item> ifr) {
        GItems.registerItems(ifr);
        GBlocks.registerItems(ifr);
    }
    
    public void blocks(IForgeRegistry<Block> ifr) {
        GBlocks.registerBlocks(ifr);
    }
}