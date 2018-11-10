package com.SirBlobman.gemmary.creative;

import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public final class GemmaryTabs {
	public static final CreativeTabs ITEMS = new CreativeTabs("GItems") {
		@Override
		public ItemStack getTabIconItem() {
			Item i = GItems.AMETHYST;
			ItemStack is = new ItemStack(i);
			return is;
		}
	};
	
	public static final CreativeTabs BUCKETS = new CreativeTabs("GBuckets") {
		@Override
		public ItemStack getTabIconItem() {
			Item i = Items.BUCKET;
			ItemStack is = new ItemStack(i);
			return is;
		}
		
		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> list) {
			IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
			ITEMS.forEach(i -> {
				boolean ib = (i instanceof ItemBucket);
				boolean im = (i instanceof ItemBucketMilk);
				boolean ub = (i instanceof UniversalBucket);
				boolean b1 = (ib || im || ub) ;
				if(b1) {
					ItemStack is = new ItemStack(i);
					list.add(is);
				}
			});
			super.displayAllRelevantItems(list);
		}
	};
}