package com.SirBlobman.gemmary.creative.tab;

import java.util.List;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
		
		@Override
		public void displayAllRelevantItems(List<ItemStack> items)
		{
			for(Item i : ForgeRegistries.ITEMS)
			{
				if(i != null)
				{
					if(isItemBucket(i)) i.getSubItems(i, this, items);
				}	
			}
		}
		
		private boolean isItemBucket(Item i)
		{
			if(i instanceof ItemBucket) return true;
			if(i instanceof UniversalBucket) return true;
			return false;
		}
	};
}