package com.SirBlobman.gemmary.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class HydratingCategory extends HydratorRecipeCategory 
{
	private final IDrawable background;
	private final String name;
	
	public HydratingCategory(IGuiHelper igh)
	{
		super(igh);
		ResourceLocation rl = new ResourceLocation("gemmary", "textures/gui/ahtv.png");
		background = igh.createDrawable(rl, 9, 16, 128, 61);
		name = Translator.translateToLocal("gui.gemmary-jei.hydrating");
	}
	
	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawAnimations(Minecraft m)
	{
		water.draw(m, 5, 1);
		arrow.draw(m, 71, 19);
	}
	
	@Override
	public String getTitle()
	{
		return name;
	}
	
	@Override
	public String getUid()
	{
		return GemmaryRecipeUIDS.HYDRATING;
	}
	
	@Override
	public void setRecipe(IRecipeLayout irl, IRecipeWrapper irw)
	{
		IGuiItemStackGroup igisg = irl.getItemStacks();
		
		igisg.init(inputSlot, true, 46, 17);
		igisg.init(outputSlot, false, 106, 18);
		
		igisg.setFromRecipe(inputSlot, irw.getInputs());
		igisg.setFromRecipe(outputSlot, irw.getOutputs());
	}

	@Override
	public void drawExtras(Minecraft m) {}
}