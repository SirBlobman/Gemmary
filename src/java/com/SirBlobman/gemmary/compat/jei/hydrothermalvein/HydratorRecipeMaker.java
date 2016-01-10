package com.SirBlobman.gemmary.compat.jei.hydrothermalvein;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.SirBlobman.gemmary.crafting.HydroThermalRecipes;

import mezz.jei.util.StackUtil;
import net.minecraft.item.ItemStack;

public class HydratorRecipeMaker 
{
	@Nonnull
	public static List<HydratorRecipe> getHydratorRecipes()
	{
		HydroThermalRecipes hydratingRecipes = HydroThermalRecipes.instance();
		Map<ItemStack, ItemStack> hydratingMap = getHydratingMap(hydratingRecipes);
		
		List<HydratorRecipe> recipes = new ArrayList<>();
		
		for(Map.Entry<ItemStack, ItemStack> itemStackItemStackEntry : hydratingMap.entrySet())
		{
			ItemStack input = itemStackItemStackEntry.getKey();
			ItemStack output = itemStackItemStackEntry.getValue();
			
			float experience = hydratingRecipes.getSmeltingExperience(output);
			
			List<ItemStack> inputs = StackUtil.getSubtypes(input);
			HydratorRecipe recipe = new HydratorRecipe(inputs, output, experience);
			recipes.add(recipe);
		}
		
		return recipes;
	}
	
	private static Map<ItemStack, ItemStack> getHydratingMap(@Nonnull HydroThermalRecipes hydratingRecipes)
	{
		return hydratingRecipes.getHydratingList();
	}
}