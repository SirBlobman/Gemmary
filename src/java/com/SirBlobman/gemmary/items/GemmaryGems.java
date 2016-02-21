package com.SirBlobman.gemmary.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GemmaryGems 
{
	//Creative Tab
	public static final CreativeTabs Gems = new CreativeTabs("Gems")
	{
		@Override
		public Item getTabIconItem() 
		{
			return GemmaryGems.random;
		}		
	};
	
	//Gem Objects
	//public static Item object = new Gem("gem_name", mohsScale);
	public static Item amethyst = new Gem("amethyst", 7.0);
	public static Item corundum = new Gem("corundum", 9.0);
	public static Item random = new Gem("random", 0.0).setCreativeTab(null);
	public static Item real_diamond = new Gem("diamond", 10.0);
	public static Item ruby = new Gem("ruby", 9.0);
	public static Item sapphire = new Gem("sapphire", 9.0);
	public static Item talc = new Gem("talc", 1.0);
	public static Item tanzanite = new Gem("tanzanite", 6.75);
	public static Item topaz = new Gem("topaz", 8.0);
	public static Item turquoise = new Gem("turquoise", 5.5);
	
	public static void createGems()
	{
	//These should be created in ABC order
		r(amethyst);
		r(corundum);
		r(real_diamond);
		r(ruby);
		r(random);
		r(sapphire);
		r(talc);
		r(tanzanite);
		r(topaz);
		r(turquoise);
	}
	
	public static void r(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
