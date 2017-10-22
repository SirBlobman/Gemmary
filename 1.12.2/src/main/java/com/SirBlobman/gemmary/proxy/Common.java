package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.forge.GOreDict;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
    public void pre(FMLPreInitializationEvent e) {
        
    }
    
    public void init(FMLInitializationEvent e) {
        
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