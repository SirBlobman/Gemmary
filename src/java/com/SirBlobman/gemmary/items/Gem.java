package com.SirBlobman.gemmary.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Gem extends Item
{
	public Gem(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryGems.Gems);
	}
	public boolean isBeaconPayment(ItemStack stack)
	{
	    return this == GemmaryGems.amethyst || this == GemmaryGems.corundum || this == GemmaryGems.ruby || this == GemmaryGems.sapphire || this == GemmaryGems.talc || this == GemmaryGems.tanzanite || this == GemmaryGems.topaz || this == GemmaryGems.turquoise;
	}
}
