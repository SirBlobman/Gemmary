package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.item.Item;

public class GemPart extends Item {
	public GemPart(String gem) {
		super();
		String name = gem + "_part";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.ITEMS);
	}
}