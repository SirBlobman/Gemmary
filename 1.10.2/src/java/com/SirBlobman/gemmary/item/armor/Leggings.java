package com.SirBlobman.gemmary.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class Leggings extends Armor
{
	public Leggings(String gem, ArmorMaterial mat, Item repair)
	{
		super(gem, "leggings", mat, 2, EntityEquipmentSlot.LEGS, repair);
	}
}