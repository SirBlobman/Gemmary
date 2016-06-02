package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class GRecipes 
{
	public static void createCraftingRecipes()
	{
	//Shaped
		//Compressors
		aSdR(new ItemStack(GBlocks.superCompressor, 1), new Object[] {"CCC", "CSC", "CCC", 'C', GBlocks.compressor, 'S', Items.string});
		aSdR(new ItemStack(GBlocks.compressor, 1), new Object[] {"PPP","PRP","PPP", 'P', Blocks.piston, 'R', Items.redstone});
		//Hydrothermal Vein
		aSdR(new ItemStack(GBlocks.ahtv, 1), new Object[] {"CcL", " B ", "RRR", 'C', Blocks.crafting_table, 'c', Blocks.chest, 'L', Items.lava_bucket, 'B', Items.bucket, 'R', Blocks.redstone_block});
		//Diamond TNT
		aSdR(new ItemStack(GBlocks.diamondTNT, 1), new Object[] {"DDD", "DTD", "DDD", 'D', Blocks.diamond_block, 'T', Blocks.tnt});
		//Heated Water Container
		aSdR(new ItemStack(GItems.heatedWaterContainer, 1), new Object[] {" I ", "IWI", "ILI", 'I', Items.iron_ingot, 'W', Items.water_bucket, 'L', Items.lava_bucket});
		//Gem Parts
		aSdR(new ItemStack(Items.diamond, 1), new Object[] {"DDD", "DDD", "DDD", 'D', GItems.diamondPart});
		aSdR(new ItemStack(Items.emerald, 1), new Object[] {"EEE", "EEE", "EEE", 'E', GItems.emeraldPart});
		//Cloth
		aSdR(new ItemStack(GItems.cloth, 1), new Object[] {"WWW", "WWW", "WWW", 'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)});
		
	//Shapeless
		//Recipe Book
		aSlR(new ItemStack(GItems.recipeBook), new Object[] {new ItemStack(GItems.amethyst, 1)});
		//Chalk
		aSlR(new ItemStack(GBlocks.whiteChalk, 32), new Object[] {new ItemStack(GItems.talc), new ItemStack(GItems.talc), new ItemStack(GItems.talc)});
	}
	
	public static void createCompressingRecipes()
	{
		aCR(new ItemStack(Blocks.coal_block,1), new ItemStack(Items.diamond, 1), 100.0F);
		aCR(new ItemStack(GItems.carbon, 1), new ItemStack(GItems.diamondPart), 11.11F);
	}
	
	public static void createHydratingRecipes()
	{
		aHR(new ItemStack(GItems.beryllium, 1), new ItemStack(GItems.emeraldPart), 100.0F);
	}
	
	
	
	
	
	
	
	
	
	
	
	//Add a Shaped Recipe. This can be like the recipe for a chest, which can't be in random order
	private static void aSdR(ItemStack output, Object[] input)
	{
		GameRegistry.addShapedRecipe(output, input);
	}
	
	//Add a Shapeless Recipe. This can be like Colored Wool. It doesn't matter where the items are on the crafting table.
	private static void aSlR(ItemStack output, Object[] input)
	{
		GameRegistry.addShapelessRecipe(output, input);
	}
	
	
	//Add a Compressor Recipe. These go into the Gemmary Item Compressor
	private static void aCR(ItemStack input, ItemStack output, float exp)
	{
		CompressorRecipes.addCompressingRecipe(input, output, exp);
	}
	
	//Add a Hydrator Recipe. These go into the Gemmary Artificial Hydrothermal Vein
	private static void aHR(ItemStack input, ItemStack output, float exp)
	{
		HydrothermalRecipes.addHydratingRecipe(input, output, exp);
	}
}
