package com.SirBlobman.gemmary.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.SirBlobman.gemmary.recipe.CompressorRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class CompressingRecipeMaker 
{
	public static List<CompressingRecipe> getCompressorRecipes(IJeiHelpers ijh)
	{
		IStackHelper ish = ijh.getStackHelper();
		CompressorRecipes cRecipes = CompressorRecipes.instance();
		Map<ItemStack, ItemStack> compressingMap = cRecipes.getCompressingList();
		
		List<CompressingRecipe> recipes = new ArrayList<>();
		
		for(Map.Entry<ItemStack, ItemStack> isise : compressingMap.entrySet())
		{
			ItemStack input = isise.getKey();
			ItemStack output = isise.getValue();
			
			float exp = cRecipes.getCompressingExp(output);
			
			List<ItemStack> inputs = ish.getSubtypes(input);
			CompressingRecipe recipe = new CompressingRecipe(inputs, output, exp);
			recipes.add(recipe);
		}
		
		return recipes;
	}
}
