package com.SirBlobman.gemmary.recipe;

import java.util.Map;
import java.util.Map.Entry;

import com.SirBlobman.gemmary.GUtil;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompressorRecipes
{
	private static final CompressorRecipes base = new CompressorRecipes();
	private static Map<ItemStack, ItemStack> list = Maps.newHashMap();
	private static Map<ItemStack, Float> exp = Maps.newHashMap();
	
	public static CompressorRecipes instance() {return base;}
	
	public void addRecipe(ItemStack input, ItemStack output, float xp)
	{
		if(getResult(input) != null)
		{
			String msg = "Ignored compressing recipe:\nConflicting Input: " + input + " = " + output;
			GUtil.print(msg);
			return;
		}
		
		list.put(input, output);
		exp.put(output, xp);
	}
	
	public ItemStack getResult(ItemStack is)
	{
		for(Entry<ItemStack, ItemStack> e : list.entrySet())
		{
			if(compare(is, e.getKey())) return e.getValue();
		}
		return null;
	}
	
	private boolean compare(ItemStack is1, ItemStack is2)
	{
		boolean b1 = is2.getItem() == is1.getItem();
		boolean b2 = is2.getMetadata() == OreDictionary.WILDCARD_VALUE;
		boolean b3 = is2.getMetadata() == is1.getMetadata();
		return b1 && (b2 || b3);
	}
	
	public Map<ItemStack, ItemStack> getRecipeList() {return list;}
	
	public float getExp(ItemStack is)
	{
		float f = is.getItem().getSmeltingExperience(is);
		if(f != -1) return f;
		
		for(Entry<ItemStack, Float> e : exp.entrySet())
		{
			if(compare(is, e.getKey())) return e.getValue();
		}
		
		return 0.0F;
	}
} 