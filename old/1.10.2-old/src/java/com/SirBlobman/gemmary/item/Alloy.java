package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Alloy extends Item 
{
	private ItemStack creates;
	private final String gem, atom;
	
	public Alloy(Item gemi, Item atomi, Item creates)
	{
		super();
		gem = gemi.getUnlocalizedName();
		atom = atomi.getUnlocalizedName();
		setUnlocalizedName(this.gem + "+" + this.atom + "_alloy");
		setRegistryName("alloy/" + gem.substring(5) + "+" + atom.substring(5));
		setCreativeTab(GemmaryTabs.Items);
		setMaxStackSize(16);
		this.creates = new ItemStack(creates);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> lore, boolean adv)
	{
		lore.add("If you compress this, you will get: " + creates.getDisplayName());
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format(gem + ".name").trim() + " §r+ " + I18n.format(atom + ".name").trim();
	}
}
