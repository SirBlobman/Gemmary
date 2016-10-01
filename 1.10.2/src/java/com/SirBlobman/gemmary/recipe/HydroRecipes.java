package com.SirBlobman.gemmary.recipe;

import java.util.Map;
import java.util.Map.Entry;

import com.SirBlobman.gemmary.GUtil;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class HydroRecipes
{
	private static final HydroRecipes BASE = new HydroRecipes();
	private static Map<ItemStack, ItemStack> list = Maps.newHashMap();
	private static Map<ItemStack, Float> exp = Maps.newHashMap();
	
	public static HydroRecipes instance() {return BASE;}
	public HydroRecipes() {}
	
	public void addRecipe(ItemStack input, ItemStack output, float xp)
	{
		if(getResult(input) != null)
		{
			GUtil.print("Ignored Hydration Recipe:\n" + input + " = " + output);
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
	
	public Map<ItemStack, ItemStack> getRecipeList() {return list;}
	
	private boolean compare(ItemStack is1, ItemStack is2)
	{
		boolean b1 = is2.getItem() == is1.getItem();
		boolean b2 = is2.getMetadata() == OreDictionary.WILDCARD_VALUE;
		boolean b3 = is2.getMetadata() == is1.getMetadata();
		boolean b4 = b2 || b3;
		return b1 && b4;
	}
	
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