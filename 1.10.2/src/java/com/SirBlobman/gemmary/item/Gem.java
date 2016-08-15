package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Gem extends Item 
{
	public double MohsValue;
	
	public Gem(String name, double mohsvalue)
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setHasSubtypes(true);
		setCreativeTab(GemmaryTabs.Items);
		MohsValue = mohsvalue;
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack is)
	{
		return true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		String newname = super.getUnlocalizedName() + (is.getItemDamage() == 0 ? "" : "_dusty");
		return newname;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		if(getUnlocalizedName(is).contains("dusty")) return I18n.format("info.dusty", I18n.format(getUnlocalizedName() + ".name")).trim();
		
		return I18n.format(getUnlocalizedName() + ".name").trim();
	}
	
	@Override
	public void getSubItems(Item i, CreativeTabs t, List<ItemStack> l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List<String> lore, boolean b)
	{
		double Mohs = MohsValue;
		GUtil.setMohsScaleOfItem(is.getItem(), Mohs);
		if(is.getItemDamage() == 1) lore.add(I18n.format("lore.dusty_gems", new Object[0]));
	}
	
	public double getMohsValue()
	{
		return MohsValue;
	}
}