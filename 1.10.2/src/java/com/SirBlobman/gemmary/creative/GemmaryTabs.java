package com.SirBlobman.gemmary.creative;

import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class GemmaryTabs 
{
	public static CreativeTabs ITEMS = new CreativeTabs("GITEMS")
	{
		@Override
		public Item getTabIconItem()
		{
			Item i = GItems.amethyst;
			return i;
		}
	};
}