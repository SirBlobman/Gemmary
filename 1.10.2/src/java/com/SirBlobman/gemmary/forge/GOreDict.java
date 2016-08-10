package com.SirBlobman.gemmary.forge;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public final class GOreDict 
{
	public static final void createOreDictionaryEntries()
	{
	//Gems
		reg("gemAmethyst", GItems.amethyst);
		reg("gemCorundum", GItems.corundum);
		reg("gemRuby", GItems.ruby);
		reg("gemSapphire", GItems.sapphire);
		reg("gemTalc", GItems.talc);
		reg("gemTanzanite", GItems.tanzanite);
		reg("gemTopaz", GItems.topaz);
		reg("gemTurquoise", GItems.turquoise);
	//Gem Parts
		reg("nuggetDiamond", GItems.diamondPart);
		reg("nuggetEmerald", GItems.emeraldPart);
	//Atoms
		for(Item i : GUtil.getElements()) {reg("atom", i);}
	//Ores
		reg("oreAmethyst", GBlocks.amethystOre);
		reg("oreCorundum", GBlocks.corundumOre);
		reg("oreRuby", GBlocks.rubyOre);
		reg("oreSapphire", GBlocks.sapphireOre);
		reg("oreTalc", GBlocks.talcOre);
		reg("oreTanzanite", GBlocks.tanzaniteOre);
		reg("oreTopaz", GBlocks.topazOre);
		reg("oreTurquoise", GBlocks.turquoiseOre);
	//Blocks
		reg("blockAmethyst", GBlocks.amethyst);
		reg("blockCorundum", GBlocks.corundum);
		reg("blockRuby", GBlocks.ruby);
		reg("blockSapphire", GBlocks.sapphire);
		reg("blockTalc", GBlocks.talc);
		reg("blockTanzanite", GBlocks.tanzanite);
		reg("blockTopaz", GBlocks.topaz);
		reg("blockTurquoise", GBlocks.turquoise);
		reg("tnt", Blocks.TNT);
	}
	
	private static void reg(String name, Item i)
	{
		OreDictionary.registerOre(name, i);
	}
	
	private static void reg(String name, Block b)
	{
		OreDictionary.registerOre(name, b);
	}
}