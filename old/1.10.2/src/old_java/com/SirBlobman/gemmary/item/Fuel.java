package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class Fuel extends Item implements IFuelHandler
{
	public Fuel()
	{
		setUnlocalizedName("fuel");
		setRegistryName("fuel");
		setMaxStackSize(1);
		setCreativeTab(GemmaryTabs.ITEMS);
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
	
	@Override
	public int getBurnTime(ItemStack is)
	{
		Item i = is.getItem();
		if(i instanceof Fuel) return Integer.MAX_VALUE;
		return 0;
	}
}