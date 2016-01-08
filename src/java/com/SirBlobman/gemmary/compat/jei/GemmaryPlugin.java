package com.SirBlobman.gemmary.compat.jei;

import com.SirBlobman.gemmary.compat.jei.compressor.CompressorCompressingCategory;
import com.SirBlobman.gemmary.compat.jei.compressor.CompressorRecipeHandler;
import com.SirBlobman.gemmary.compat.jei.compressor.CompressorRecipeMaker;
import com.SirBlobman.gemmary.guicontainer.ContainerCompressor;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;

@JEIPlugin
public class GemmaryPlugin implements IModPlugin
{
	@SuppressWarnings("unused")
	private IItemRegistry itemRegistry;
	private IJeiHelpers jeiHelpers;
	
	@Override
	public boolean isModLoaded()
	{
		return true;
	}
	
	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers)
	{
		this.jeiHelpers = jeiHelpers;
	}
	
	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry)
	{
		this.itemRegistry = itemRegistry;
	}
	
	@Override
	public void register(IModRegistry registry)
	{
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories
		(
			new CompressorCompressingCategory(guiHelper)	
		);
		registry.addRecipeHandlers
		(
			new CompressorRecipeHandler()
		);
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		
		recipeTransferRegistry.addRecipeTransferHandler(ContainerCompressor.class, GemmaryRecipeCategoryUid.COMPRESSING, 0, 1, 1, 36);
		
		registry.addRecipes(CompressorRecipeMaker.getCompressorRecipes());
	}
	
	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry)
	{
		
	}
}
