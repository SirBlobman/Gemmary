package com.SirBlobman.gemmary.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class HydratorRecipeCategory implements IRecipeCategory
{
	protected static final int inputSlot = 0;
	protected static final int waterSlot = 1;
	protected static final int outputSlot = 2;
	
	protected final ResourceLocation background;
	protected final IDrawableAnimated water;
	protected final IDrawableAnimated arrow;
	
	public HydratorRecipeCategory(IGuiHelper igh)
	{
		background = new ResourceLocation("gemmary", "textures/gui/ahtv.png");
		
		IDrawableStatic waterD = igh.createDrawable(background, 248, 0, 8, 41);
		water = igh.createAnimatedDrawable(waterD, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic arrowD = igh.createDrawable(background, 176, 14, 24, 16);
		arrow = igh.createAnimatedDrawable(arrowD, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
}
