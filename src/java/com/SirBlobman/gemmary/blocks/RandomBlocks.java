package com.SirBlobman.gemmary.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RandomBlocks 
{
	//Random
	public static Block atomCombiner;
	public static Block carbonCompressor;
	public static Block chalk;
	public static Block diamondTnT;
	public static Block SmallEngine;
	public static Block AHTV; //AHTV = Artificial HydroThermal Vein
	//Gem Blocks
	public static Block amethystBlock;
	public static Block corundumBlock;
	public static Block rubyBlock;
	public static Block sapphireBlock;
	public static Block talcBlock;
	public static Block tanzaniteBlock;
	public static Block topazBlock;
	public static Block turquoiseBlock;
	
	public static final void createRandomBlocks()
	{
		//Random
		GameRegistry.registerBlock(atomCombiner = new AtomCombiner(), "atom_combiner");
		GameRegistry.registerBlock(carbonCompressor = new CarbonCompressor(), "carbon_compressor");
		GameRegistry.registerBlock(chalk = new Chalk("chalk", Material.circuits), "chalk");
		GameRegistry.registerBlock(diamondTnT = new DiamondTnT("diamond_tnt"), "diamond_tnt");
		GameRegistry.registerBlock(SmallEngine = new Engine("small_engine"), "small_engine");
		GameRegistry.registerBlock(AHTV = new HydrothermalVein("artificial_hydrothermal_vein"), "artificial_hydrothermal_vein");
		//Gem Blocks: Hardness is the Mohs Scale divided by 2. Example: Diamond is 10 so Diamond Block hardness is 5.0F
		GameRegistry.registerBlock(amethystBlock = new GemBlock("amethyst_block", 4.5F) , "amethyst_block");
		GameRegistry.registerBlock(corundumBlock = new GemBlock("corundum_block", 4.5F), "corundum_block");
		GameRegistry.registerBlock(rubyBlock = new GemBlock("ruby_block", 4.5F) , "ruby_block");
		GameRegistry.registerBlock(sapphireBlock = new GemBlock("sapphire_block", 4.5F) , "sapphire_block");
		GameRegistry.registerBlock(talcBlock = new GemBlock("talc_block", 0.5F) , "talc_block");
		GameRegistry.registerBlock(tanzaniteBlock = new GemBlock("tanzanite_block", 3.25F) , "tanzanite_block");
		GameRegistry.registerBlock(topazBlock = new GemBlock("topaz_block", 4.0F) , "topaz_block");
		GameRegistry.registerBlock(turquoiseBlock = new GemBlock("turquoise_block", 2.5F) , "turquoise_block");
		
	}
}
