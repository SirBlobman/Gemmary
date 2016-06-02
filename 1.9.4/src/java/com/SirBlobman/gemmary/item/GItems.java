package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public final class GItems 
{
//Gems
	public static Item amethyst = new Gem("amethyst", 7.0);
	public static Item corundum = new Gem("corundum", 9.0);
	public static Item ruby = new Gem("ruby", 9.0);
	public static Item sapphire = new Gem("sapphire", 9.0);
	public static Item talc = new Gem("talc", 1.0);
	public static Item tanzanite = new Gem("tanzanite", 6.5);
	public static Item topaz = new Gem("topaz", 8.0);
	public static Item turquoise = new Gem("turquoise", 5.0);
	
//Gem Parts
	public static Item diamondPart = new GemPart("diamond_part");
	public static Item emeraldPart = new GemPart("emerald_part");
	
//Elements
	public static Item aluminum = new Element("aluminum");
	public static Item beryllium = new Element("beryllium");
	public static Item carbon = new Element("carbon");
	public static Item hydrogen = new Element("hydrogen");
	public static Item oxygen = new Element("oxygen");
	
//Other
	public static Item heatedWaterContainer = new Item().setUnlocalizedName("heated_water_container").setCreativeTab(GemmaryTabs.Items).setRegistryName("heated_water_container");
	public static Item cloth = new Cloth("cloth");
	public static Item recipeBook = new RecipeBook("recipe_book");
	
	public static final void createGems()
	{
		rDusty(amethyst);
		rDusty(corundum);
		rDusty(ruby);
		rDusty(sapphire);
		rDusty(talc);
		rDusty(tanzanite);
		rDusty(topaz);
		rDusty(turquoise);
	}
	
	public static final void createGemParts()
	{
		r(diamondPart);
		r(emeraldPart);
	}
	
	public static final void createElements()
	{
		r(aluminum);
		r(beryllium);
		r(carbon);
		r(hydrogen);
		r(oxygen);
	}
	
	public static final void createItems()
	{
		r(heatedWaterContainer);
		r(cloth);
		r(recipeBook);
	}
	
	private static void r(Item i)
	{
		GameRegistry.register(i);
	}
	
	private static void rDusty(Item i)
	{
		r(i);
		GameRegistry.addShapelessRecipe(new ItemStack(i, 1, 0), new Object[] {new ItemStack(i, 1, 1), new ItemStack(GItems.cloth, 1, OreDictionary.WILDCARD_VALUE)});
	}
}
