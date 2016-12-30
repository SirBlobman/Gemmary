package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Atom extends Item
{
	public Atom(String element)
	{
		super();
		setUnlocalizedName(element);
		setRegistryName(element);
		setCreativeTab(GemmaryTabs.ITEMS);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> lore, boolean b)
	{
		lore.add(I18n.format("lore.atoms.1", I18n.format(getUnlocalizedName() + ".name")));
		lore.add(I18n.format("lore.atoms.2"));
	}
}