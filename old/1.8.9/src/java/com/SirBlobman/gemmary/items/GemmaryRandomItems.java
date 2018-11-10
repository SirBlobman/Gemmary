package com.SirBlobman.gemmary.items;

import com.SirBlobman.gemmary.blocks.RandomBlocks;

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
	
	public static final CreativeTabs OtherBlocks = new CreativeTabs("OtherBlocks")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(RandomBlocks.atomCombiner);
		}
	};
	
	//Random Item Objects
	public static Item RecipeBook;
	public static Item Cloth;
	public static Item HeatedWaterContainer;
	
	//Random Item Creator
	public static void createRandomItems()
	{
		GameRegistry.registerItem(RecipeBook = new RecipeBook("recipe_book"), "recipe_book");
		GameRegistry.registerItem(Cloth = new Cloth("cloth"), "cloth");
		GameRegistry.registerItem(HeatedWaterContainer = new Container("heated_water_container"), "heated_water_container");
	}
}
