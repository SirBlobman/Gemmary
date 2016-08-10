package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeFuel extends Item 
{
	public CreativeFuel()
	{
		setUnlocalizedName("creative_fuel");
		setRegistryName("creative_fuel");
		setMaxStackSize(1);
		setCreativeTab(GemmaryTabs.Items);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack is) {return true;}
	
	@Override
	public ItemStack getContainerItem(ItemStack is)
	{
		ItemStack copy = is.copy();
		copy.stackSize = 1;
		return copy;
	}
}