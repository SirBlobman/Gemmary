package com.SirBlobman.gemmary.item.block;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGemBlock extends ItemBlock
{
	final String g;
	
	public ItemGemBlock(String gem, Block b)
	{
		super(b);
		setRegistryName(b.getRegistryName());
		this.g = gem;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format("tile.gem_block.name", I18n.format("item." + g + ".name")).trim();
	}
}