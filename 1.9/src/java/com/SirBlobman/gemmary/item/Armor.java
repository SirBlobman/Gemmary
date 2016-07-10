package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class Armor extends ItemArmor
{
	public Armor(String gem, String type, ArmorMaterial mat, int i, EntityEquipmentSlot slot)
	{
		super(mat, i, slot);
		setUnlocalizedName(gem + "_" + type);
		setRegistryName("armor/" + gem + "_" + type);
		setCreativeTab(GemmaryTabs.Armor);
	}
}
