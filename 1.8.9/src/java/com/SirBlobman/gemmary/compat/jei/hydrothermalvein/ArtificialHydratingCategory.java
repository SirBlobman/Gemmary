package com.SirBlobman.gemmary.compat.jei.hydrothermalvein;

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

public class ArtificialHydratingCategory extends HydratorRecipeCategory
{
	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final String localizedName;
	
	public ArtificialHydratingCategory(IGuiHelper guiHelper)
	{
		super(guiHelper);
		ResourceLocation location = new ResourceLocation("minecraft", "textures/gui/artificial_hydrothermal_vein_gui.png");
		background = guiHelper.createDrawable(location, 26, 29, 116, 96);
		localizedName = Translator.translateToLocal("gui.jei.category.htv");
	}
	
	@Override
	@Nonnull
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawAnimations(Minecraft minecraft)
	{
		water.draw(minecraft, 44, 87);
		arrow.draw(minecraft, 61, 55);
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
		return GemmaryRecipeCategoryUid.HYDRATING;
	}
	
	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(inputSlot, true, 19, 19);
		guiItemStacks.init(outputSlot, false, 94, 54);
		
		guiItemStacks.setFromRecipe(inputSlot, recipeWrapper.getInputs());
		guiItemStacks.setFromRecipe(outputSlot, recipeWrapper.getOutputs());
	}

	@Override
	public void drawExtras(Minecraft arg0) 
	{
		
	}
}
