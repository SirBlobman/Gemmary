package com.SirBlobman.gemmary.forge;

import org.apache.commons.lang3.StringUtils;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.block.GemBlock;
import com.SirBlobman.gemmary.item.Atom;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.Gem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public final class OreDict
{
	public static void ores()
	{
		for(Gem g : GUtil.gems())
		{
			String name = g.getUnlocalizedName().substring(5);
			name = StringUtils.capitalize(name);
			reg("gem" + name, g);
		}
		
		reg("nuggetDiamond", GItems.diamond);
		reg("nuggetEmerald", GItems.emerald);
		
		for(Atom a : GUtil.atoms()) reg("atom", a);
		for(GemBlock gb : GUtil.gemBlocks())
		{
			Item gem = gb.getGem();
			String name = gem.getUnlocalizedName().substring(5);
			name = StringUtils.capitalize(name);
			reg("block" + name, gb);
		}

		reg("oreAmethyst", GBlocks.ameOre);
		reg("oreCorundum", GBlocks.corOre);
		reg("oreRuby", GBlocks.rubOre);
		reg("oreSapphire", GBlocks.sapOre);
		reg("oreTalc", GBlocks.talOre);
		reg("oreTanzanite", GBlocks.tanOre);
		reg("oreTopaz", GBlocks.topOre);
		reg("oreTurquoise", GBlocks.turOre);
	}

	private static void reg(String name, Item i) {OreDictionary.registerOre(name, i);}
	private static void reg(String name, Block b) {OreDictionary.registerOre(name, b);}
}