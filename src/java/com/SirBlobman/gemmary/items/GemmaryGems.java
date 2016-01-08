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
	public static Item amethyst;
	public static Item corundum;
	public static Item ruby;
	public static Item random;
	public static Item sapphire;
	public static Item talc;
	public static Item tanzanite;
	public static Item topaz;
	public static Item turquoise;
	
	public static void createGems()
	{
	//These should be created in ABC order
		//Amethyst
		GameRegistry.registerItem(amethyst = new Gem("amethyst"), "amethyst");
		//Corundum
		GameRegistry.registerItem(corundum = new Gem("corundum"), "corundum");
		//Random: This one will have no use in game
		GameRegistry.registerItem(random = new Gem("random").setCreativeTab(null), "random");
		//Ruby
		GameRegistry.registerItem(ruby = new Gem("ruby"), "ruby");
		//Sapphire
		GameRegistry.registerItem(sapphire = new Gem("sapphire"), "sapphire");
		//Talc
		GameRegistry.registerItem(talc = new Gem("talc"), "talc");
		//Tanzanite
		GameRegistry.registerItem(tanzanite = new Gem("tanzanite"), "tanzanite");
		//Topaz
		GameRegistry.registerItem(topaz = new Gem("topaz"), "topaz");
		//Turquoise
		GameRegistry.registerItem(turquoise = new Gem("turquoise"), "turquoise");
	}
}
