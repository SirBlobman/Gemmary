package com.SirBlobman.gemmary.items;

import net.minecraft.item.Item;

public class Part extends Item 
{
	public Part(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryGemParts.GemParts);
		this.setMaxStackSize(9);
	}
}
