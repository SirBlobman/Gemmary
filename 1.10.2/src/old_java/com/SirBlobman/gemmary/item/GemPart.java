package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.item.Item;

public class GemPart extends Item
{
	public GemPart(String gem)
	{
		super();
		setUnlocalizedName(gem + "_part");
		setRegistryName(gem + "_part");
		setCreativeTab(GemmaryTabs.ITEMS);
		setMaxStackSize(9);
	}
}