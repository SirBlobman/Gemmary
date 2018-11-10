package com.SirBlobman.gemmary.item.block;

import com.SirBlobman.gemmary.block.Chalk;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ChalkItem extends ItemBlock
{
	final String c;
	
	public ChalkItem(String color, Chalk c)
	{
		super(c);
		this.c = color;
		setRegistryName(c.getRegistryName());
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format("tile.chalk.name", I18n.format("color." + c)).trim();
	}
}