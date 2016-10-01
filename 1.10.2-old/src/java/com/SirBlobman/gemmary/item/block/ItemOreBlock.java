package com.SirBlobman.gemmary.item.block;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOreBlock extends ItemBlock
{
	final String g;
	
	public ItemOreBlock(String gem, Block b)
	{
		super(b);
		this.g = gem;
		setRegistryName(b.getRegistryName());
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format("tile.gem_ore.name", I18n.format("item." + g + ".name")).trim();
	}
}