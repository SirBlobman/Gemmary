package com.SirBlobman.gemmary.compat.jei;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class CompressingRecipe extends GemmaryRecipeWrapper
{
	private final List<List<ItemStack>> input;
	private final List<ItemStack> outputs;
	
	private final String expString;
	
	public CompressingRecipe(List<ItemStack> input, ItemStack output, float exp)
	{
		this.input = Collections.singletonList(input);
		outputs = Collections.singletonList(output);
		
		if(exp > 0.0)
		{
			expString = Translator.translateToLocalFormatted("gui.gemmary-jei.exp", exp);
		}
		else
		{
			expString = null;
		}
	}
	
	public List<List<ItemStack>> getInputs()
	{
		return input;
	}
	
	public List<ItemStack> getOutputs()
	{
		return outputs;
	}
	
	@Override
	public void drawInfo(Minecraft m, int width, int height, int x, int y)
	{
		if(expString != null)
		{
			FontRenderer fRO = m.fontRendererObj;
			int stringWidth = fRO.getStringWidth(expString);
			fRO.drawString(expString, width - stringWidth, 0, Color.gray.getRGB());
		}
	}
}