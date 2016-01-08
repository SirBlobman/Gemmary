package com.SirBlobman.gemmary.compat.jei.compressor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.SirBlobman.gemmary.crafting.CarbonCompressorRecipes;

import mezz.jei.util.StackUtil;
import net.minecraft.item.ItemStack;

public class CompressorRecipeMaker 
{
	@Nonnull
	public static List<CompressorRecipe> getCompressorRecipes()
	{
		CarbonCompressorRecipes compressorRecipes = CarbonCompressorRecipes.instance();
		Map<ItemStack, ItemStack> compressingMap = getCompressingMap(compressorRecipes);
		
		List<CompressorRecipe> recipes = new ArrayList<>();
		
		for(Map.Entry<ItemStack, ItemStack> itemStackItemStackEntry : compressingMap.entrySet())
		{
			ItemStack input = itemStackItemStackEntry.getKey();
			ItemStack output = itemStackItemStackEntry.getValue();
			
			float experience = compressorRecipes.getSmeltingExperience(output);
			
			List<ItemStack> inputs = StackUtil.getSubtypes(input);
			CompressorRecipe recipe = new CompressorRecipe(inputs, output, experience);
			recipes.add(recipe);
		}
		
		return recipes;
	}
	
	private static Map<ItemStack, ItemStack> getCompressingMap(@Nonnull CarbonCompressorRecipes compressorRecipes)
	{
		return compressorRecipes.getSmeltingList();
	}
}
