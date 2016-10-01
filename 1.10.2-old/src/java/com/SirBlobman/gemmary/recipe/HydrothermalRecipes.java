package com.SirBlobman.gemmary.recipe;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

public class HydrothermalRecipes 
{
	private static final HydrothermalRecipes hydratingBase = new HydrothermalRecipes();
	private static Map<ItemStack, ItemStack> hydratingList = Maps.<ItemStack, ItemStack>newHashMap();
	private static Map<ItemStack, Float> expList = Maps.<ItemStack, Float>newHashMap();
	
	public static HydrothermalRecipes instance() {return hydratingBase;}
	
	public HydrothermalRecipes() {}
	
	public static void addHydratingRecipe(ItemStack input, ItemStack output, float exp)
	{
		if(getHydratingResult(input) != null)
		{
			FMLLog.info("Ignored Hydrating Recipe with conflicting input: " + input + " = " + output);
			return;
		}
		
		hydratingList.put(input, output);
		expList.put(output, Float.valueOf(exp));
	}
	
	public static ItemStack getHydratingResult(ItemStack is)
	{
		for(Entry<ItemStack, ItemStack> e : hydratingList.entrySet())
		{
			if(compareItemStacks(is, (ItemStack)e.getKey()))
			{
				return (ItemStack)e.getValue();
			}
		}
		return null;
	}
	
	public Map<ItemStack, ItemStack> getHydratingList()
	{
		return HydrothermalRecipes.hydratingList;
	}
	
	private static boolean compareItemStacks(ItemStack s1, ItemStack s2)
	{
		return s2.getItem() == s1.getItem() && (s2.getMetadata() == 32767 || s2.getMetadata() == s1.getMetadata());
	}
	
	public float getSmeltingExp(ItemStack is)
	{
		float ret = is.getItem().getSmeltingExperience(is);
		if(ret != -1) return ret;
		
		for(Entry<ItemStack, Float> e : expList.entrySet())
		{
			if(compareItemStacks(is, (ItemStack)e.getKey()))
			{
				return ((Float)e.getValue()).floatValue();
			}
		}
		
		return 0.0F;
	}
}
