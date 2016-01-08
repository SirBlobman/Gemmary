package com.SirBlobman.gemmary.crafting;

import com.SirBlobman.gemmary.blocks.RandomBlocks;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.SirBlobman.gemmary.items.GemmaryGems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Crafting 
{
	public static void startCraftingRecipes()
	{
	//Shaped
		//Machines
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.carbonCompressor), new Object[] {" E ", "PFP", "RDR", 'E', RandomBlocks.SmallEngine, 'P', Blocks.piston, 'F', Blocks.furnace, 'R', Blocks.redstone_torch, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.carbonCompressor), new Object[] {" E ", "PFP", "RDR", 'E', RandomBlocks.SmallEngine, 'P', Blocks.sticky_piston, 'F', Blocks.furnace, 'R', Blocks.redstone_torch, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.SmallEngine), new Object[] {"RPR", "P P", "RPR", 'P', Blocks.piston, 'R', Items.repeater});
		//Gems
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object [] {"DDD", "DDD", "DDD", 'D', GemmaryGemParts.DiamondPart});
		GameRegistry.addRecipe(new ItemStack(Items.emerald), new Object [] {"EEE", "EEE", "EEE", 'E', GemmaryGemParts.EmeraldPart});
		//Block Recipes
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.amethystBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.rubyBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.sapphireBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.talcBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.tanzaniteBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.topazBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.turquoiseBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.turquoise});
	//Shapeless
		//Chalk = 3 Talc
		GameRegistry.addShapelessRecipe(new ItemStack(RandomBlocks.chalk, 32, 0 ), new Object[] {GemmaryGems.talc, GemmaryGems.talc, GemmaryGems.talc});
		//Diamond TnT = Diamond Block + TnT
		GameRegistry.addShapelessRecipe(new ItemStack(RandomBlocks.diamondTnT), new Object[] {Blocks.diamond_block, Blocks.tnt});
	//Furnace
	}
}