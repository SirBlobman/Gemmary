package com.SirBlobman.gemmary.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class HydratingRecipeHandler implements IRecipeHandler<HydratorRecipe> 
{
	@Override
	public Class<HydratorRecipe> getRecipeClass()
	{
		return HydratorRecipe.class;
	}
	
	@Override
	public String getRecipeCategoryUid()
	{
		return GemmaryRecipeUIDS.HYDRATING;
	}
	
	@Override
	public IRecipeWrapper getRecipeWrapper(HydratorRecipe hr)
	{
		return hr;
	}
	
	@Override
	public boolean isRecipeValid(HydratorRecipe hr)
	{
		return hr.getInputs().size() != 0 && hr.getOutputs().size() > 0;
	}
}