package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.block.ChalkItem;
import com.SirBlobman.gemmary.item.block.ItemCrystal;
import com.SirBlobman.gemmary.item.block.ItemGemBlock;
import com.SirBlobman.gemmary.item.block.ItemOreBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GBlocks 
{
/*
 * Gem Blocks
 * Hardness = mohsScale / 2
 * Example: Diamond mohsScale is 10, so hardness is 5.0F
 * Usage: new GemBlock("block_name", hardness);
 * Example: new GemBlock("diamond_block", 5.0F)
 */
	public static GemBlock amethyst = new GemBlock("amethyst", 3.5F);
	public static GemBlock corundum = new GemBlock("corundum", 4.5F);
	public static GemBlock ruby = new GemBlock("ruby", 4.5F);
	public static GemBlock sapphire = new GemBlock("sapphire", 4.5F);
	public static GemBlock talc = new GemBlock("talc", 0.5F);
	public static GemBlock tanzanite = new GemBlock("tanzanite", 3.25F);
	public static GemBlock topaz = new GemBlock("topaz", 4.0F);
	public static GemBlock turquoise = new GemBlock("turquoise", 2.5F);
	
/*
 * Ore Blocks
 * Hardness = mohsScale / 2
 * Harvest Tool = "pickaxe" "axe" or "shovel"
 * Harvest Level: 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond
 * Usage: new OreBlock("block_name", material, drop, drop_meta, least_dropped, most_dropped, harvestTool, harvestLevel, hardness);
 */
	public static OreBlock amethystOre = new OreBlock("amethyst", Material.ROCK, GItems.amethyst, 1, 3, 4, "pickaxe", 2, 3.5F);
	public static OreBlock corundumOre = new OreBlock("corundum", Material.ROCK, GItems.corundum, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlock rubyOre = new OreBlock("ruby", Material.ROCK, GItems.ruby, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlock sapphireOre = new OreBlock("sapphire", Material.ROCK, GItems.sapphire, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlockFalling talcOre = new OreBlockFalling("talc", GItems.talc, 1, 3, 4, "shovel", 0, 0.5F);
	public static OreBlock tanzaniteOre = new OreBlock("tanzanite", Material.ROCK, GItems.tanzanite, 1, 3, 4, "pickaxe", 2, 3.25F);
	public static OreBlock topazOre = new OreBlock("topaz", Material.ROCK, GItems.topaz, 1, 3, 4, "pickaxe", 2, 4.0F);
	public static OreBlock turquoiseOre = new OreBlock("turquoise", Material.ROCK, GItems.turquoise, 1, 3, 4, "pickaxe", 1, 2.5F);
	
/*
 * Crystals
 * These blocks can be broken by a level 1 pickaxe, and usually spawn in caves
 * Usage: new Crystals("type", drop, meta, least, most);
 */
	public static Crystals quartzCrystals = new Crystals("quartz", Items.QUARTZ, 0, 1, 4);
	public static Crystals diamondCrystals = new Crystals("diamond", Items.DIAMOND, 0, 1, 4);
	
/*
 * Other Blocks
 * TileEntities, Randomness or Containers
 * These won't usually have a hardness
 */
	public static Block compressor = new Compressor("normal");
	public static Block superCompressor = new Compressor("fast");
	public static Block ahtv = new HydrothermalVein();
	public static Block diamondTNT = new DiamondTNT();
	public static Chalk whiteChalk = new Chalk("white");
	public static Block atomGatherer = new AtomGatherer(false);
	public static Block autoAtomGatherer = new AtomGatherer(true);
	
	public static final void createGemBlocks()
	{
		rGemBlock("amethyst", amethyst);
		rGemBlock("corundum", corundum);
		rGemBlock("ruby", ruby);
		rGemBlock("sapphire", sapphire);
		rGemBlock("talc", talc);
		rGemBlock("tanzanite", tanzanite);
		rGemBlock("topaz", topaz);
		rGemBlock("turquoise", turquoise);
	}
	
	public static final void createOres()
	{
		rOreBlock("amethyst", amethystOre);
		rOreBlock("corundum", corundumOre);
		rOreBlock("ruby", rubyOre);
		rOreBlock("sapphire", sapphireOre);
		rOreBlock("talc", talcOre);
		rOreBlock("tanzanite", tanzaniteOre);
		rOreBlock("topaz", topazOre);
		rOreBlock("turquoise", turquoiseOre);
	}
	
	public static final void createCrystals()
	{
		rCrystal("quartz", quartzCrystals);
		rCrystal("diamond", diamondCrystals);
	}
	
	public static final void createOtherBlocks()
	{
		r(compressor);
		r(superCompressor);
		r(ahtv);
		r(diamondTNT);
		rChalk("white", whiteChalk);
		r(atomGatherer);
		r(autoAtomGatherer);
	}
	
	private static void r(Block b)
	{
		GameRegistry.register(b);
		GameRegistry.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
	}
	
	private static void rGemBlock(String gem, GemBlock gb)
	{
		GameRegistry.register(gb);
		GameRegistry.register(new ItemGemBlock(gem, gb));
	}
	
	private static void rOreBlock(String gem, OreBlock ob)
	{
		GameRegistry.register(ob);
		GameRegistry.register(new ItemOreBlock(gem, ob));
	}
	
	private static void rOreBlock(String gem, OreBlockFalling obf)
	{
		GameRegistry.register(obf);
		GameRegistry.register(new ItemOreBlock(gem, obf));
	}
	
	private static void rChalk(String color, Chalk c)
	{
		GameRegistry.register(c);
		GameRegistry.register(new ChalkItem(color, c));
	}
	
	private static void rCrystal(String type, Crystals c)
	{
		GameRegistry.register(c);
		GameRegistry.register(new ItemCrystal(type, c));
	}
}