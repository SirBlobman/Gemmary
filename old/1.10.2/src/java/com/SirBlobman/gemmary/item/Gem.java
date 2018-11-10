package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Gem extends Item
{
	private double mohs;
	
	public Gem(String type, double mohs)
	{
		super();
		setUnlocalizedName(type);
		setRegistryName(Gemmary.MODID, type);
		setHasSubtypes(true);
		setCreativeTab(GemmaryTabs.ITEMS);
		this.mohs = mohs;
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack is) {return !dusty(is);}
	
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		String oname = getUnlocalizedName();
		int meta = is.getItemDamage();
		boolean b1 = (meta == 0);
		String add = b1 ? "" : "_dusty";
		String name = oname + add;
		return name;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String un = getUnlocalizedName(is);
		String name = I18n.format(getUnlocalizedName() + ".name");
		if(un.endsWith("_dusty")) return I18n.format("info.dusty", name).trim();
		return name;
	}
	
	@Override
	public void getSubItems(Item i, CreativeTabs ct, List<ItemStack> li)
	{
		li.add(new ItemStack(i, 1, 0));
		li.add(new ItemStack(i, 1, 1));
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer ep, List<String> lore, boolean b)
	{
		double MOHS = getMohs();
		GUtil.setMohs(is.getItem(), MOHS);
		if(is.getItemDamage() == 1) lore.add(I18n.format("lore.dusty_gems"));
	}
	
	public double getMohs() {return mohs;}
	public boolean dusty(ItemStack is) {return (is.getItemDamage() == 1);}
}