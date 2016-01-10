package com.SirBlobman.gemmary.compat.jei.hydrothermalvein;

import javax.annotation.Nonnull;

import com.SirBlobman.gemmary.compat.jei.GemmaryRecipeCategoryUid;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HydratorRecipeHandler implements IRecipeHandler<HydratorRecipe>
{
	@Override
	@Nonnull
	public Class<HydratorRecipe> getRecipeClass()
	{
		return HydratorRecipe.class;
	}
	
	@Nonnull
	@Override
	public String getRecipeCategoryUid()
	{
		return GemmaryRecipeCategoryUid.HYDRATING;
	}
	
	@Override
	@Nonnull
	public IRecipeWrapper getRecipeWrapper(@Nonnull HydratorRecipe recipe)
	{
		return recipe;
	}
	
	@Override
	public boolean isRecipeValid(@Nonnull HydratorRecipe recipe)
	{
		return recipe.getInputs().size() != 0 && recipe.getOutputs().size() > 0;
	}
}
