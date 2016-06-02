package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Element extends Item
{
	public Element(String name)
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.Items);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List<String> lore, boolean adv)
	{
		lore.add(I18n.format("lore.elements.1", new Object[] {GUtil.translate(getUnlocalizedName() + ".name")}));
		lore.add(GUtil.translate("lore.elements.2"));
	}
}