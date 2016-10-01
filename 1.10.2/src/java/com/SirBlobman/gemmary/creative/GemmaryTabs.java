package com.SirBlobman.gemmary.creative;


import java.util.Iterator;
import java.util.List;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.block.GemBlock;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public final class GemmaryTabs 
{
	public static final CreativeTabs BLOCKS = new CreativeTabs("GBLOCKS")
	{
		@Override
		public Item getTabIconItem() 
		{
			GemBlock gb = GBlocks.amethyst;
			Item i = Item.getItemFromBlock(gb);
			return i;
		}
	};
	
	public static final CreativeTabs ITEMS = new CreativeTabs("GITEMS")
	{
		@Override
		public Item getTabIconItem() {return GItems.amethyst;}
	};
	
	public static final CreativeTabs ARMOR = new CreativeTabs("GARMOR")
	{
		@Override
		public Item getTabIconItem() {return GItems.aChestplate;}
	};
	
	public static final CreativeTabs BUCKETS = new CreativeTabs("GBUCKETS")
	{
		@Override
		public Item getTabIconItem() {return Items.BUCKET;}
		
		@Override
		public void displayAllRelevantItems(List<ItemStack> list)
		{
			IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
			Iterator<Item> items = ITEMS.iterator();
			while(items.hasNext())
			{
				Item i = items.next();
				boolean b1 = i instanceof ItemBucket;
				boolean b2 = i instanceof ItemBucketMilk;
				boolean b3 = i instanceof UniversalBucket;
				if(b1 || b2)
				{
					ItemStack is = new ItemStack(i);
					list.add(is);
				}
				if(b3) i.getSubItems(i, BUCKETS, list);
			}
		}
	};
}