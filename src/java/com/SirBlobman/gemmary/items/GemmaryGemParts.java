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
	public static Item DiamondPart;
	public static Item EmeraldPart;
	
	//Gem Part Creator
	public static void createGemParts()
	{
		GameRegistry.registerItem(DiamondPart = new Part("diamond_part"), "diamond_part");
		GameRegistry.registerItem(EmeraldPart = new Part("emerald_part"), "emerald_part");
	}
}
