package com.SirBlobman.gemmary.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class CompressorRecipeCategory extends BlankRecipeCategory
{
	protected static final int inputSlot = 0;
	protected static final int fuelSlot = 1;
	protected static final int outputSlot = 2;
	
	protected final ResourceLocation background;
	protected final IDrawableAnimated flame;
	protected final IDrawableAnimated arrow;
	
	public CompressorRecipeCategory(IGuiHelper igh)
	{
		background = new ResourceLocation("gemmary", "textures/gui/compressor.png");
		
		IDrawableStatic flameD = igh.createDrawable(background, 176, 0, 18, 18);
		flame = igh.createAnimatedDrawable(flameD, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic arrowD = igh.createDrawable(background, 0, 207, 80, 18);
		arrow = igh.createAnimatedDrawable(arrowD, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
}