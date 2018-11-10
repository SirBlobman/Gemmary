package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.item.Item;

public class ItemGem extends Item {
	private final double mohs;
	public ItemGem(String gem, double mohs) {
		super();
		this.mohs = mohs;
		
		setUnlocalizedName(gem);
		setRegistryName(gem);
		setCreativeTab(GemmaryCreativeTabs.ITEMS);
	}
	
	public double getMohsScaleValue() {return mohs;}
}