package com.SirBlobman.gemmary.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.SirBlobman.gemmary.recipe.HydrothermalRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class HydratingRecipeMaker 
{
	public static List<HydratorRecipe> getHydratorRecipes(IJeiHelpers ijh)
	{
		IStackHelper ish = ijh.getStackHelper();
		
		HydrothermalRecipes hR = HydrothermalRecipes.instance();
		Map<ItemStack, ItemStack> hMap = hR.getHydratingList();
		
		List<HydratorRecipe> recipes = new ArrayList<>();
		
		for(Map.Entry<ItemStack, ItemStack> isise : hMap.entrySet())
		{
			ItemStack input = isise.getKey();
			ItemStack output = isise.getValue();
			
			float exp = hR.getSmeltingExp(output);
			
			List<ItemStack> inputs = ish.getSubtypes(input);
			HydratorRecipe recipe = new HydratorRecipe(inputs, output, exp);
			recipes.add(recipe);
		}
		
		return recipes;
	}
}