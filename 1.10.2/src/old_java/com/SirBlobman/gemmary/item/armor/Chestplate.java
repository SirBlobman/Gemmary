package com.SirBlobman.gemmary.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class Chestplate extends Armor
{
	public Chestplate(String gem, ArmorMaterial mat, Item repair)
	{
		super(gem, "chestplate", mat, 1, EntityEquipmentSlot.CHEST, repair);
	}
}