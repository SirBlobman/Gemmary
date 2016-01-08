package com.SirBlobman.gemmary.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemmaryRandomItems 
{
	public static final CreativeTabs Other = new CreativeTabs("Other")
	{
		@Override
		public Item getTabIconItem()
		{
			return GemmaryRandomItems.RecipeBook;
		}
	};
	
	//Random Item Objects
	public static Item RecipeBook;
	
	//Random Item Creator
	public static void createRandomItems()
	{
		GameRegistry.registerItem(RecipeBook = new RecipeBook("recipe_book"), "recipe_book");
	}
}
