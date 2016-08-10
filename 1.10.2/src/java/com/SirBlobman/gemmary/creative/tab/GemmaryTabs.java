package com.SirBlobman.gemmary.creative.tab;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class GemmaryTabs 
{
	public static final CreativeTabs Blocks = new CreativeTabs("GBlocks")
	{
		@Override
		public Item getTabIconItem() 
		{
			return Item.getItemFromBlock(GBlocks.amethyst);
		}		
	};
	
	public static final CreativeTabs Items = new CreativeTabs("GItems")
	{
		@Override
		public Item getTabIconItem()
		{
			return GItems.amethyst;
		}
	};
	
	public static final CreativeTabs Armor = new CreativeTabs("GArmor")
	{
		@Override
		public Item getTabIconItem()
		{
			return GItems.amethystHelmet;
		}
	};
	
	public static final CreativeTabs Buckets = new CreativeTabs("GBuckets")
	{
		@Override
		public Item getTabIconItem()
		{
			return net.minecraft.init.Items.BUCKET;
		}
	};
}