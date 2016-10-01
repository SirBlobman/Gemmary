package com.SirBlobman.gemmary.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class CompressingCategory extends CompressorRecipeCategory
{
	private final IDrawable background;
	private final String name;
	
	public CompressingCategory(IGuiHelper igh)
	{
		super(igh);
		ResourceLocation rl = new ResourceLocation("gemmary", "textures/gui/compressor.png");
		background = igh.createDrawable(rl, 31, 9, 116, 69);
		name = Translator.translateToLocal("gui.gemmary-jei.compressing");
	}
	
	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawAnimations(Minecraft m)
	{
		flame.draw(m, 0, 51);
		arrow.draw(m, 18, 51);
	}
	
	@Override
	public String getTitle()
	{
		return name;
	}
	
	@Override
	public String getUid()
	{
		return GemmaryRecipeUIDS.COMPRESSING;
	}
	
	@Override
	public void setRecipe(IRecipeLayout irl, IRecipeWrapper irw)
	{
		IGuiItemStackGroup igisg = irl.getItemStacks();
		
		igisg.init(inputSlot, true, 0, 0);
		igisg.init(outputSlot, false, 98, 15);
		
		igisg.setFromRecipe(inputSlot, irw.getInputs());
		igisg.setFromRecipe(outputSlot, irw.getOutputs());
		
	}
}
