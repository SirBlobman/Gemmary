package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class Armor extends ItemArmor
{
	String gem, type;
	ItemStack repair;
	
	public Armor(String gem, String type, ArmorMaterial mat, int i, EntityEquipmentSlot slot, Item repair)
	{
		super(mat, i, slot);
		this.gem = gem;
		this.type = type;
		setRegistryName("armor/" + gem + "_" + type);
		setUnlocalizedName(gem + "_" + type);
		setCreativeTab(GemmaryTabs.Armor);
		canRepair = true;
		this.repair = new ItemStack(repair);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack item)
	{
		return I18n.format("armor." + type, I18n.format("item." + gem + ".name")).trim();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack is, ItemStack is2)
	{
		return repair == is2 ? true : super.getIsRepairable(is, is2);
	}
}