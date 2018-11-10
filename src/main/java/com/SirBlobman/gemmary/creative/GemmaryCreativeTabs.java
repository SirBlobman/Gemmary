package com.SirBlobman.gemmary.creative;

import com.SirBlobman.gemmary.block.GemmaryBlocks;
import com.SirBlobman.gemmary.item.GemmaryItems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class GemmaryCreativeTabs {
	public static final CreativeTabs 
	
	ITEMS = new CreativeTabs("gemmary.items") {
		@Override
		public ItemStack getTabIconItem() {
			Item item = GemmaryItems.GEM_AMETHYST;
			ItemStack is = new ItemStack(item);
			return is;
		}
	},
	
	BLOCKS = new CreativeTabs("gemmary.blocks") {
	    @Override
	    public ItemStack getTabIconItem() {
	        Block block = GemmaryBlocks.BLOCK_AMETHYST;
	        ItemStack is = new ItemStack(block);
	        return is;
	    }
	}
	
	;
}