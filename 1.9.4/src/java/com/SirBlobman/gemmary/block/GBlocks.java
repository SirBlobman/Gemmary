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
	public static Block amethyst = new GemBlock("amethyst_block", 3.5F);
	public static Block corundum = new GemBlock("corundum_block", 4.5F);
	public static Block ruby = new GemBlock("ruby_block", 4.5F);
	public static Block sapphire = new GemBlock("sapphire_block", 4.5F);
	public static Block talc = new GemBlock("talc_block", 0.5F);
	public static Block tanzanite = new GemBlock("tanzanite_block", 3.25F);
	public static Block topaz = new GemBlock("topaz_block", 4.0F);
	public static Block turquoise = new GemBlock("turquoise_block", 2.5F);
	
/*
 * Ore Blocks
 * Hardness = mohsScale / 2
 * Harvest Tool = "pickaxe" "axe" or "shovel"
 * Harvest Level: 0 = Wood, 1 = Stone, 2 = Iron, 3 = Diamond
 * Usage: new OreBlock("block_name", material, drop, drop_meta, least_dropped, most_dropped, harvestTool, harvestLevel, hardness);
 */
	public static Block amethyst_ore = new OreBlock("amethyst_ore", Material.rock, GItems.amethyst, 1, 3, 4, "pickaxe", 2, 3.5F);
	public static Block corundum_ore = new OreBlock("corundum_ore", Material.rock, GItems.corundum, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block ruby_ore = new OreBlock("ruby_ore", Material.rock, GItems.ruby, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block sapphire_ore = new OreBlock("sapphire_ore", Material.rock, GItems.sapphire, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static Block talc_ore = new OreBlock("talc_ore", Material.sand, GItems.talc, 1, 3, 4, "shovel", 0, 0.5F);
	public static Block tanzanite_ore = new OreBlock("tanzanite_ore", Material.rock, GItems.tanzanite, 1, 3, 4, "pickaxe", 2, 3.25F);
	public static Block topaz_ore = new OreBlock("topaz_ore", Material.rock, GItems.topaz, 1, 3, 4, "pickaxe", 2, 4.0F);
	public static Block turquoise_ore = new OreBlock("turquoise_ore", Material.rock, GItems.turquoise, 1, 3, 4, "pickaxe", 1, 2.5F);
	
/*
 * Crystals
 * These blocks can be broken by a level 1 pickaxe, and usually spawn in caves
 * Usage: new Crystals("type", drop, meta, least, most);
 */
	public static Block quartzCrystals = new Crystals("quartz", Items.quartz, 0, 1, 4);
	
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
		r(amethyst_ore);
		r(corundum_ore);
		r(ruby_ore);
		r(sapphire_ore);
		r(talc_ore);
		r(tanzanite_ore);
		r(topaz_ore);
		r(turquoise_ore);
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
