package com.SirBlobman.gemmary.items;

import net.minecraft.item.ItemArmor;

public class Armor extends ItemArmor
{
	public Armor(String name, ArmorMaterial mat, int rIndex, int aType)
	{
		super(mat, rIndex, aType);
		this.setUnlocalizedName(name);
		this.setCreativeTab(GemmaryArmor.Armor);
	}
}
