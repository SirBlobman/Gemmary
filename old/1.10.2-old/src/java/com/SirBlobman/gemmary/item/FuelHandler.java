package com.SirBlobman.gemmary.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack is)
	{
		Item i = is.getItem();
		if(i instanceof CreativeFuel)
		{
			return Integer.MAX_VALUE;
		}
		return 0;
	}
}
