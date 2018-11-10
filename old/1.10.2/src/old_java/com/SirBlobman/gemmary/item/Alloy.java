package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Alloy extends Item
{
	private ItemStack creates;
	private final String ngem,natom;
	
	public Alloy(Item gem, Item atom, Item creates)
	{
		super();
		this.ngem = gem.getUnlocalizedName();
		this.natom = atom.getUnlocalizedName();
		this.creates = new ItemStack(creates);
		setUnlocalizedName(ngem + "+" + natom + "_alloy");
		setRegistryName("alloy/" + ngem.substring(5) + "+" + natom.substring(5));
		setCreativeTab(GemmaryTabs.ITEMS);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> lore, boolean b)
	{
		lore.add(I18n.format("gui.compress", creates.getDisplayName()));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String name1 = I18n.format(ngem + ".name").trim();
		String name2 = I18n.format(natom + ".name").trim();
		String display = (name1 + " §r+ " + name2).trim();
		return display;
	}
}