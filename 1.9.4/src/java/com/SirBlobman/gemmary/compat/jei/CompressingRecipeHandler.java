package com.SirBlobman.gemmary.compat.jei;

import jline.internal.Log;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;

public class CompressingRecipeHandler implements IRecipeHandler<CompressingRecipe> 
{
	@Override
	public String getRecipeCategoryUid(CompressingRecipe recipe)
	{
		return GemmaryRecipeUIDS.COMPRESSING;
	}

	@Override
	public String getRecipeCategoryUid() 
	{
		return GemmaryRecipeUIDS.COMPRESSING;
	}

	@Override
	public Class<CompressingRecipe> getRecipeClass()
	{
		return CompressingRecipe.class;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(CompressingRecipe cr)
	{
		return cr;
	}

	@Override
	public boolean isRecipeValid(CompressingRecipe crs)
	{
		if(crs.getInputs().isEmpty())
		{
			String info = ErrorUtil.getInfoFromBrokenRecipe(crs, this);
			Log.error("Recipe has no inputs. {}", info);
		}
		if(crs.getOutputs().isEmpty())
		{
			String info = ErrorUtil.getInfoFromBrokenRecipe(crs, this);
			Log.error("Recipe has no outputs. {}", info);
		}
		
		return true;
	}
}