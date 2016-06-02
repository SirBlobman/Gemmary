package com.SirBlobman.gemmary.compat.jei.hydrothermalvein;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.SirBlobman.gemmary.compat.jei.GemmaryRecipeWrapper;

import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class HydratorRecipe extends GemmaryRecipeWrapper
{
	@Nonnull
	private final List<List<ItemStack>> input;
	@Nonnull
	private final List<ItemStack> outputs;
	
	@Nullable
	private final String experienceString;
	
	public HydratorRecipe(@Nonnull List<ItemStack> input, @Nonnull ItemStack output, float experience)
	{
		this.input = Collections.singletonList(input);
		this.outputs = Collections.singletonList(output);
		
		if (experience > 0.0)
		{
			experienceString = Translator.translateToLocalFormatted("gui.jei.category.compressor.experience", experience);
		}
		else
		{
			experienceString = null;
		}
	}
	
	@Nonnull
	public List<List<ItemStack>> getInputs()
	{
		return input;
	}
	
	@Nonnull
	public List<ItemStack> getOutputs()
	{
		return outputs;
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight)
	{
		if(experienceString != null)
		{
			FontRenderer fontRendererObj = minecraft.fontRendererObj;
			fontRendererObj.drawString(experienceString,  85 - fontRendererObj.getStringWidth(experienceString) / 2, 1, Color.gray.getRGB());
		}
	}
}
