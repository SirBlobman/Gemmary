package com.SirBlobman.gemmary.OreDictionary;

import com.SirBlobman.gemmary.items.GemmaryGems;

import net.minecraftforge.oredict.OreDictionary;

public class GemmaryOreDictionary 
{
	public static void defineThings()
	{
		//Gems
		OreDictionary.registerOre("gemAmethyst", GemmaryGems.amethyst);
		OreDictionary.registerOre("gemCorundum", GemmaryGems.corundum);
		OreDictionary.registerOre("gemDiamond", GemmaryGems.real_diamond);
		OreDictionary.registerOre("gemRuby", GemmaryGems.ruby);
		OreDictionary.registerOre("gemSapphire", GemmaryGems.sapphire);
		OreDictionary.registerOre("gemTalc", GemmaryGems.talc);
		OreDictionary.registerOre("gemTanzanite", GemmaryGems.tanzanite);
		OreDictionary.registerOre("gemTopaz", GemmaryGems.topaz);
		OreDictionary.registerOre("gemTurquoise", GemmaryGems.turquoise);
	}
}
