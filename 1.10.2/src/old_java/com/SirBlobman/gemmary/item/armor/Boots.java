package com.SirBlobman.gemmary.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class Boots extends Armor
{
	public Boots(String gem, ArmorMaterial mat, Item repair)
	{
		super(gem, "boots", mat, 1, EntityEquipmentSlot.FEET, repair);
	}
}