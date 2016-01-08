package com.SirBlobman.gemmary.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemmaryElements 
{
	//Creative Tab
	public static final CreativeTabs Elements = new CreativeTabs("Elements")
	{
		@Override
		public Item getTabIconItem() 
		{
			return GemmaryElements.oxygen;
		}		
	};
	
	//Element Objects
	public static Item aluminum;
	public static Item carbon;
	public static Item chromium;
	public static Item oxygen;
	
	//Element Creator
	public static void createChemicalElements()
	{
		GameRegistry.registerItem(aluminum = new Atom("aluminum_element"), "aluminum_element");
		GameRegistry.registerItem(carbon = new Atom("carbon_element"), "carbon_element");
		GameRegistry.registerItem(chromium = new Atom("chromium_element"), "chromium_element");
		GameRegistry.registerItem(oxygen = new Atom("oxygen_element"), "oxygen_element");
	}
}
