package com.SirBlobman.gemmary.compat.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class GemmaryJEI extends BlankModPlugin
{
	@Override
	public void register(@Nonnull IModRegistry imr)
	{
		IJeiHelpers ijh = imr.getJeiHelpers();
		
		IGuiHelper igh = ijh.getGuiHelper();
		
		imr.addRecipeCategories
		(
			new CompressingCategory(igh),
			new HydratingCategory(igh)
		);
		
		imr.addRecipeHandlers
		(
			new CompressingRecipeHandler(),
			new HydratingRecipeHandler()
		);
		
		imr.addRecipes(CompressingRecipeMaker.getCompressorRecipes(ijh));
		imr.addRecipes(HydratingRecipeMaker.getHydratorRecipes(ijh));
	}
}