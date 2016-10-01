package com.SirBlobman.gemmary.item.armor;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class Armor extends ItemArmor
{
	private String gem, type;
	public final ItemStack repair;
	
	public Armor(String gem, String type, ArmorMaterial mat, int i, EntityEquipmentSlot slot, Item repair)
	{
		super(mat, i, slot);
		this.gem = gem;
		this.type = type;
		setUnlocalizedName("armor/" + gem + "_" + type);
		setRegistryName(gem + "_" + type);
		setCreativeTab(GemmaryTabs.ARMOR);
		canRepair = true;
		this.repair = new ItemStack(repair);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		String name1 = I18n.format("item." + gem + ".name").trim();
		String name2 = I18n.format("armor." + type, name1).trim();
		return name2;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack is, ItemStack is2)
	{
		return repair == is2 ? true : super.getIsRepairable(is, is2);
	}
}