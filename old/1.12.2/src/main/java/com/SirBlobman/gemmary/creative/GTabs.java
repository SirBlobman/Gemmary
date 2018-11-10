package com.SirBlobman.gemmary.creative;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class GTabs {
    public static final CreativeTabs 
    ITEMS = new CreativeTabs("gemmary.items") {
        @Override
        public ItemStack getTabIconItem() {
            Item item = GItems.AMETHYST;
            ItemStack is = new ItemStack(item);
            return is;
        }
    },
    
    BLOCKS = new CreativeTabs("gemmary.blocks") {
        @Override
        public ItemStack getTabIconItem() {
            Block block = GBlocks.AMETHYST_BLOCK;
            ItemStack is = new ItemStack(block);
            return is;
        }
    };
}