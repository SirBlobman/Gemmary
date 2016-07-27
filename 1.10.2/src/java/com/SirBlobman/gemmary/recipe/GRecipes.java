package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class GRecipes 
{
	public static void createCraftingRecipes()
	{
	//Shaped
		//Compressors
		aSdR(new ItemStack(GBlocks.superCompressor, 1), new Object[] {"CCC", "CSC", "CCC", 'C', GBlocks.compressor, 'S', Items.STRING});
		aSdR(new ItemStack(GBlocks.compressor, 1), new Object[] {"PPP","PRP","PPP", 'P', Blocks.PISTON, 'R', Items.REDSTONE});
		//Hydrothermal Vein
		aSdR(new ItemStack(GBlocks.ahtv, 1), new Object[] {"CcL", " B ", "RRR", 'C', Blocks.CRAFTING_TABLE, 'c', Blocks.CHEST, 'L', Items.LAVA_BUCKET, 'B', Items.BUCKET, 'R', Blocks.REDSTONE_BLOCK});
		//Diamond TNT
		aSOR(new ItemStack(GBlocks.diamondTNT, 1), new Object[] {"DDD", "DTD", "DDD", 'D', "blockDiamond", 'T', "tnt"});
		//Heated Water Container
		aSdR(new ItemStack(GItems.heatedWaterContainer, 1), new Object[] {" I ", "IWI", "ILI", 'I', Items.IRON_INGOT, 'W', Items.WATER_BUCKET, 'L', Items.LAVA_BUCKET});
		//Gem Parts
		aSdR(new ItemStack(Items.DIAMOND, 1), new Object[] {"DDD", "DDD", "DDD", 'D', GItems.diamondPart});
		aSdR(new ItemStack(Items.EMERALD, 1), new Object[] {"EEE", "EEE", "EEE", 'E', GItems.emeraldPart});
		//Cloth
		aSdR(new ItemStack(GItems.cloth, 1), new Object[] {"WWW", "WWW", "WWW", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)});
		//Blocks
		aSOR(new ItemStack(GBlocks.amethyst), new Object[] {"ggg", "ggg", "ggg", 'g', "gemAmethyst"});
		aSOR(new ItemStack(GBlocks.corundum), new Object[] {"ggg", "ggg", "ggg", 'g', "gemCorundum"});
		aSOR(new ItemStack(GBlocks.ruby), new Object[] {"ggg", "ggg", "ggg", 'g', "gemRuby"});
		aSOR(new ItemStack(GBlocks.sapphire), new Object[] {"ggg", "ggg", "ggg", 'g', "gemSapphire"});
		aSOR(new ItemStack(GBlocks.talc), new Object[] {"ggg", "ggg", "ggg", 'g', "gemTalc"});
		aSOR(new ItemStack(GBlocks.tanzanite), new Object[] {"ggg", "ggg", "ggg", 'g', "gemTanzanite"});
		aSOR(new ItemStack(GBlocks.topaz), new Object[] {"ggg", "ggg", "ggg", 'g', "gemTopaz"});
		aSOR(new ItemStack(GBlocks.turquoise), new Object[] {"ggg", "ggg", "ggg", 'g', "gemTurquoise"});
		
	//Shapeless
		//Recipe Book
		aSlR(new ItemStack(GItems.recipeBook), new Object[] {new ItemStack(GItems.amethyst, 1)});
		//Chalk
		aSlOR(new ItemStack(GBlocks.whiteChalk, 32), new Object[] {"gemTalc", "gemTalc", "gemTalc"});
	}
	
	public static void createCompressingRecipes()
	{
		aCR(new ItemStack(Blocks.COAL_BLOCK), new ItemStack(Items.DIAMOND), 100.0F);
		aCR(new ItemStack(GItems.carbon), new ItemStack(GItems.diamondPart), 11.11F);
		aCR(new ItemStack(GItems.corundumChromium), new ItemStack(GItems.ruby), 100.0F);
		aCR(new ItemStack(GItems.corundumIron), new ItemStack(GItems.sapphire), 100.0F);
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
	
	//Add a shaped recipe with OreDictionary entries
	private static void aSOR(ItemStack output, Object[] input)
	{
		IRecipe recipe = new ShapedOreRecipe(output, input);
		GameRegistry.addRecipe(recipe);
	}
	
	private static void aSlOR(ItemStack output, Object[] input)
	{
		IRecipe recipe = new ShapelessOreRecipe(output, input);
		GameRegistry.addRecipe(recipe);
	}
}
