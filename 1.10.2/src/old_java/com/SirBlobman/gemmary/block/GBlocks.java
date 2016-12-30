package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.block.MultiSpeedContainer.Speed;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public final class GBlocks
{
	/**public static GemBlock amethyst = new GemBlock(GItems.amethyst, 3.5F);
	public static GemBlock corundum = new GemBlock(GItems.corundum, 4.5F);
	public static GemBlock ruby = new GemBlock(GItems.ruby, 4.5F);
	public static GemBlock sapphire = new GemBlock(GItems.sapphire, 4.5F);
	public static GemBlock talc = new GemBlock(GItems.talc, 0.5F);
	public static GemBlock tanzanite = new GemBlock(GItems.tanzanite, 3.25F);
	public static GemBlock topaz = new GemBlock(GItems.topaz, 4.0F);
	public static GemBlock turquoise = new GemBlock(GItems.turquoise, 2.5F);

	public static OreBlock ameOre = new OreBlock(GItems.amethyst, Material.ROCK, 1, 3, 4, "pickaxe", 2, 3.5F);
	public static OreBlock corOre = new OreBlock(GItems.corundum, Material.ROCK, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlock rubOre = new OreBlock(GItems.ruby, Material.ROCK, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlock sapOre = new OreBlock(GItems.sapphire, Material.ROCK, 1, 3, 4, "pickaxe", 2, 4.5F);
	public static OreBlockFalling talOre = new OreBlockFalling(GItems.talc, 1, 3, 4, 0, 0.5F);
	public static OreBlock tanOre = new OreBlock(GItems.tanzanite, Material.ROCK, 1, 3, 4, "pickaxe", 2, 3.25F);
	public static OreBlock topOre = new OreBlock(GItems.topaz, Material.ROCK, 1, 3, 4, "pickaxe", 2, 4.0F);
	public static OreBlock turOre = new OreBlock(GItems.turquoise, Material.ROCK, 1, 3, 4, "pickaxe", 1, 2.5F);
	
	public static Crystals quartz = new Crystals(Blocks.QUARTZ_BLOCK, Items.QUARTZ, 0, 1, 4);
	public static Crystals diamond = new Crystals(Blocks.DIAMOND_BLOCK, Items.DIAMOND, 0, 1, 4);
	
	public static Compressor compressor = new Compressor(Speed.NORMAL);
	public static Compressor superCompressor = new Compressor(Speed.ULTRA);
	public static HydrothermalVein ahtv = new HydrothermalVein();
	public static DiamondTNT diamondTNT = new DiamondTNT();
	public static Chalk whiteChalk = new Chalk("white");
	public static AtomGatherer atomGatherer = new AtomGatherer(false);
	public static AtomGatherer autoAtomGatherer = new AtomGatherer(true);
	
	public static void gemBlocks()
	{
		rGB(amethyst);
		rGB(corundum);
		rGB(ruby);
		rGB(sapphire);
		rGB(talc);
		rGB(tanzanite);
		rGB(topaz);
		rGB(turquoise);
	}
	
	public static void ores()
	{
		r(ameOre);
		r(corOre);
		r(rubOre);
		r(sapOre);
		r(talOre);
		r(tanOre);
		r(topOre);
		r(turOre);
	}
	
	public static void crystals()
	{
		r(quartz);
		r(diamond);
	}
	
	public static void others()
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
		ResourceLocation name = b.getRegistryName();
		ItemBlock ib = new ItemBlock(b);
		ib.setRegistryName(name);
		GameRegistry.register(ib);
	}
	
	private static void rGB(GemBlock gb)
	{
		Item gem = gb.getGem();
		r(gb);
		Object[] recipe = new Object[] {"GGG", "GGG", "GGG", 'G', gem};
		ShapedOreRecipe sr = new ShapedOreRecipe(gb, recipe);
		GameRegistry.addRecipe(sr);
	}**/
}