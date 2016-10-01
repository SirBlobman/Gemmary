package com.SirBlobman.gemmary.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class Helmet extends Armor
{
	public Helmet(String gem, ArmorMaterial mat, Item repair)
	{
		super(gem, "helmet", mat, 1, EntityEquipmentSlot.HEAD, repair);
	}
}