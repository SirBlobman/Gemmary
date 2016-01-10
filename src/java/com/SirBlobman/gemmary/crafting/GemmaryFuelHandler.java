package com.SirBlobman.gemmary.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class GemmaryFuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		return 0;
	}
	public int getWaterValue(ItemStack stack)
	{
		return 0;
	}
}
