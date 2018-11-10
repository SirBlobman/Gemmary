package com.SirBlobman.gemmary.item.block;

import com.SirBlobman.gemmary.block.Crystals;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCrystal extends ItemBlock
{
	final String t;
	public ItemCrystal(String type, Crystals c)
	{
		super(c);
		setRegistryName(c.getRegistryName());
		this.t = type;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format("tile.crystal.name", I18n.format("item." + t + ".name")).trim();
	}
}