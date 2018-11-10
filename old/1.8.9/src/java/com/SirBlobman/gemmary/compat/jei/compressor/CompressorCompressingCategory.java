package com.SirBlobman.gemmary.compat.jei.compressor;

import javax.annotation.Nonnull;

import com.SirBlobman.gemmary.compat.jei.GemmaryRecipeCategoryUid;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class CompressorCompressingCategory extends CompressorRecipeCategory
{
	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final String localizedName;
	
	public CompressorCompressingCategory(IGuiHelper guiHelper)
	{
		super(guiHelper);
		ResourceLocation location = new ResourceLocation("minecraft", "textures/gui/compressor2.png");
		background = guiHelper.createDrawable(location, 24, 19, 131, 94);
		localizedName = Translator.translateToLocal("gui.jei.category.compressor");
	}
	
	@Override
	@Nonnull
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft)
	{
		
	}
	
	@Override
	public void drawAnimations(Minecraft minecraft)
	{
		pressure.draw(minecraft, 32, 66);
		arrow.draw(minecraft, 29, 5);
	}
	
	@Nonnull
	@Override
	public String getTitle()
	{
		return localizedName;
	}
	
	@Nonnull
	@Override
	public String getUid()
	{
		return GemmaryRecipeCategoryUid.COMPRESSING;
	}
	
	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(inputSlot, true, 1, 4);
		guiItemStacks.init(outputSlot, false, 109, 6);
		
		guiItemStacks.setFromRecipe(inputSlot, recipeWrapper.getInputs());
		guiItemStacks.setFromRecipe(outputSlot, recipeWrapper.getOutputs());
	}
}