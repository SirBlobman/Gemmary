package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.SirBlobman.gemmary.utility.Util;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class Gem extends Item {
	private final double mohs;
	public Gem(String name, double mohs) {
		super();
		this.mohs = mohs;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHasSubtypes(true);
		setCreativeTab(GemmaryTabs.ITEMS);
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack is) {
		boolean b = dusty(is);
		return b;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		boolean dusty = dusty(is);
		String oname = getUnlocalizedName();
		String add = dusty ? "_dusty" : "";
		String name = oname + add;
		return name;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is) {
		String un = getUnlocalizedName(is);
		if(un.endsWith("_dusty")) {
			String name = super.getUnlocalizedName(is) + ".name";
			String trim = name.trim();
			String info = Util.format("info.dusty", trim);
			return info;
		} else {
			String name = super.getUnlocalizedName(is);
			return name;
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs ct, NonNullList<ItemStack> list) {
		ItemStack is1 = new ItemStack(this, 1, 0);
		ItemStack is2 = new ItemStack(this, 1, 1);
		list.add(is1); list.add(is2);
	}
	
	@Override
	public void addInformation(ItemStack is, World w, List<String> list, ITooltipFlag itf) {
		if(dusty(is)) {
			String add = I18n.format("lore.dusty_gems");
			list.add(add);
		}
	}
	
	public double mohs() {return mohs;}
	public boolean dusty(ItemStack is) {
		int dam = is.getItemDamage();
		if(dam == 1) return true;
		else return false;
	}
}