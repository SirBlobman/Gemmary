package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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
	public static Block amethyst = new GemBlock("amethyst", 3.5F);
	public static Block corundum = new GemBlock("corundum", 4.5F);
	public static Block ruby = new GemBlock("ruby", 4.5F);
	public static Block sapphire = new GemBlock("sapphire", 4.5F);
	public static Block talc = new GemBlock("talc", 0.5F);
	public static Block tanzanite = new GemBlock("tanzanite", 3.25F);
	public static Block topaz = new GemBlock("topaz", 4.0F);
	public static Block turquoise = new GemBlock("turquoise", 2.5F);
	
/*
 * Ore Blocks
 * Hardness = mohsScale / 2
 * Harvest Tool = "pickaxe" "axe" or "shovel"
 * Harvest Level: 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond
 * Usage: new OreBlock("block_name", material, drop, drop_meta, least_dropped, most_dropped, harvestTool, harvestLevel, hardness);
 */
	public static Block amethystOre = new OreBlock("amethyst", Material.ROCK, GItems.amethyst, 1, 3, 4, "pickaxe", 2, 3.5F);
	public static Block corundumOre = new OreBlock("corundum", Material.ROCK, GItems.corundum, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block rubyOre = new OreBlock("ruby", Material.ROCK, GItems.ruby, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block sapphireOre = new OreBlock("sapphire", Material.ROCK, GItems.sapphire, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block talcOre = new OreBlockFalling("talc", GItems.talc, 1, 3, 4, "shovel", 0, 0.5F);
	public static Block tanzaniteOre = new OreBlock("tanzanite", Material.ROCK, GItems.tanzanite, 1, 3, 4, "pickaxe", 2, 3.25F);
	public static Block topazOre = new OreBlock("topaz", Material.ROCK, GItems.topaz, 1, 3, 4, "pickaxe", 2, 4.0F);
	public static Block turquoiseOre = new OreBlock("turquoise", Material.ROCK, GItems.turquoise, 1, 3, 4, "pickaxe", 1, 2.5F);
	
/*
 * Crystals
 * These blocks can be broken by a level 1 pickaxe, and usually spawn in caves
 * Usage: new Crystals("type", drop, meta, least, most);
 */
	public static Block quartzCrystals = new Crystals("quartz", Items.QUARTZ, 0, 1, 4);
	
/*
 * Other Blocks
 * TileEntities, Randomness or Containers
 * These won't usually have a hardness
 */
	public static Block compressor = new Compressor("normal");
	public static Block superCompressor = new Compressor("fast");
	public static Block ahtv = new HydrothermalVein();
	public static Block diamondTNT = new DiamondTNT();
	public static Block whiteChalk = new Chalk("white");
	public static Block atomGatherer = new AtomGatherer(false);
	public static Block autoAtomGatherer = new AtomGatherer(true);
	
	public static final void createGemBlocks()
	{
		rGemBlock(amethyst, GItems.amethyst);
		rGemBlock(corundum, GItems.corundum);
		rGemBlock(ruby, GItems.ruby);
		rGemBlock(sapphire, GItems.sapphire);
		rGemBlock(talc, GItems.talc);
		rGemBlock(tanzanite, GItems.tanzanite);
		rGemBlock(topaz, GItems.topaz);
		rGemBlock(turquoise, GItems.turquoise);
	}
	
	public static final void createOres()
	{
		r(amethystOre);
		r(corundumOre);
		r(rubyOre);
		r(sapphireOre);
		r(talcOre);
		r(tanzaniteOre);
		r(topazOre);
		r(turquoiseOre);
	}
	
	public static final void createCrystals()
	{
		r(quartzCrystals);
	}
	
	public static final void createOtherBlocks()
	{
		r(compressor);
		r(superCompressor);
		r(ahtv);
		r(diamondTNT);
		r(whiteChalk);
		r(atomGatherer);
		r(autoAtomGatherer);
	}
	
	private static void r(Block b)
	{
		GameRegistry.register(b);
		GameRegistry.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
	}
	
	private static void rGemBlock(Block b, Item source)
	{
		r(b);
		GameRegistry.addRecipe(new ItemStack(b), new Object[] {"###", "###", "###", '#', source});
	}
}
