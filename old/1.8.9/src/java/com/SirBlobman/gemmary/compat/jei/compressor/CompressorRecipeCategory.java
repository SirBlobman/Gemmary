package com.SirBlobman.gemmary.compat.jei.compressor;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class CompressorRecipeCategory implements IRecipeCategory
{
	protected static final int inputSlot = 0;
	protected static final int fuelSlot = 1;
	protected static final int outputSlot = 2;
	
	protected final ResourceLocation backgroundLocation;
	@Nonnull
	protected final IDrawableAnimated pressure;
	@Nonnull
	protected final IDrawableAnimated arrow;
	
	public CompressorRecipeCategory(IGuiHelper guiHelper)
	{
		backgroundLocation = new ResourceLocation("minecraft", "textures/gui/compressor2.png");
		
		IDrawableStatic pressureDrawable = guiHelper.createDrawable(backgroundLocation, 176, 0, 14, 14);
		pressure = guiHelper.createAnimatedDrawable(pressureDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(backgroundLocation, 0,  207, 80, 17);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable,  200,  IDrawableAnimated.StartDirection.LEFT, false);
	}
}
