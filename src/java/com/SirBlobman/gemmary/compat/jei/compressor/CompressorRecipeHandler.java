package com.SirBlobman.gemmary.compat.jei.compressor;

import javax.annotation.Nonnull;

import com.SirBlobman.gemmary.compat.jei.GemmaryRecipeCategoryUid;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CompressorRecipeHandler implements IRecipeHandler<CompressorRecipe>
{
	@Override
	@Nonnull
	public Class<CompressorRecipe> getRecipeClass()
	{
		return CompressorRecipe.class;
	}
	
	@Nonnull
	@Override
	public String getRecipeCategoryUid()
	{
		return GemmaryRecipeCategoryUid.COMPRESSING;
	}
	
	@Override
	@Nonnull
	public IRecipeWrapper getRecipeWrapper(@Nonnull CompressorRecipe recipe)
	{
		return recipe;
	}
	
	@Override
	public boolean isRecipeValid(@Nonnull CompressorRecipe recipe)
	{
		return recipe.getInputs().size() != 0 && recipe.getOutputs().size() > 0;
	}
}
