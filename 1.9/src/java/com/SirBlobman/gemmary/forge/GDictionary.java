package com.SirBlobman.gemmary.forge;

import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class GDictionary 
{
	public static void defineItems()
	{
		rO("gemAmethyst", GItems.amethyst);
		rO("gemCorundum", GItems.corundum);
		//rO("gemDiamond", GItems.read_diamond);
		rO("gemRuby", GItems.ruby);
		rO("gemSapphire", GItems.sapphire);
		rO("gemTalc", GItems.talc);
		rO("gemTanzanite", GItems.tanzanite);
		rO("gemTopaz", GItems.topaz);
		rO("gemTurquoise", GItems.turquoise);
	}
	
	private static void rO(String name, Item i)
	{
		OreDictionary.registerOre(name, i);
	}
}
