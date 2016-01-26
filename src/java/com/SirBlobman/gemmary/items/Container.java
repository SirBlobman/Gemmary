package com.SirBlobman.gemmary.items;

import net.minecraft.item.Item;

public class Container extends Item 
{
	public Container(String name)
	{
		this.setUnlocalizedName(name);
		this.setCreativeTab(GemmaryRandomItems.Other);
	}
}
