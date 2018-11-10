package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.item.Item;

public class ItemGemPart extends Item{
	public ItemGemPart(String gem) {
		super();
		String name = gem + "_part";
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(GemmaryCreativeTabs.ITEMS);
	}
}