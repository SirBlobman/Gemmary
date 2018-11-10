package com.SirBlobman.gemmary.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemmaryGemParts 
{
	public static final CreativeTabs GemParts = new CreativeTabs("Gem_Parts")
	{
		@Override 
		public Item getTabIconItem()
		{
			return GemmaryGemParts.DiamondPart;
		}
	};
	
	//Gem Part Objects
	public static Item DiamondPart = new Part("diamond_part");
	public static Item EmeraldPart = new Part("emerald_part");
	
	//Gem Part Creator
	public static void createGemParts()
	{
		r(DiamondPart);
		r(EmeraldPart);
	}
	
	private static void r(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
