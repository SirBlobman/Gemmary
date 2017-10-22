package com.SirBlobman.gemmary.utility;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtil extends Util {
	public static boolean air(ItemStack is) {
		if(is == null) return true;
		Item i = is.getItem();
		if(i == null) return true;
		boolean air = (i == Items.AIR);
		return air;
	}
}
