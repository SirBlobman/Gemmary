package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.item.Item;

public class GemPart extends Item
{
	public GemPart(String name)
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.Items);
		setMaxStackSize(9);
	}
}
