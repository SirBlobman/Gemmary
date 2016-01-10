package com.SirBlobman.gemmary.compat.jei.hydrothermalvein;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class HydratorRecipeCategory implements IRecipeCategory
{
	protected static final int inputSlot = 0;
	protected static final int fuelSlot = 1;
	protected final int outputSlot = 2;
	
	protected final ResourceLocation backgroundLocation;
	@Nonnull
	protected final IDrawableAnimated water;
	@Nonnull
	protected final IDrawableAnimated arrow;
	
	public HydratorRecipeCategory(IGuiHelper guiHelper)
	{
		backgroundLocation = new ResourceLocation("minecraft", "textures/gui/artificial_hydrothermal_vein_gui.png");
		
		IDrawableStatic waterDrawable = guiHelper.createDrawable(backgroundLocation, 185, 0, 70, 7);
		water = guiHelper.createAnimatedDrawable(waterDrawable, 300, IDrawableAnimated.StartDirection.LEFT, true);
		
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(backgroundLocation, 234, 13, 20, 14);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable,  200,  IDrawableAnimated.StartDirection.LEFT, false);
	}
}
