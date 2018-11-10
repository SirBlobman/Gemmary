package com.SirBlobman.gemmary.recipe;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

public class CompressorRecipes 
{
	private static final CompressorRecipes compressingBase = new CompressorRecipes();
	private static Map<ItemStack, ItemStack> compressingList = Maps.<ItemStack, ItemStack>newHashMap();
	private static Map<ItemStack, Float> expList = Maps.<ItemStack,Float>newHashMap();
	
	public static CompressorRecipes instance()
	{
		return compressingBase;
	}
	
	public CompressorRecipes() {}
	
	public static void addCompressingRecipe(ItemStack input, ItemStack is, float exp)
	{
		if(getCompressingResult(input) != null)
		{
			FMLLog.info("Ignored compressing recipe with conflicting input: " + input + " = " + is);
			return;
		}
		
		CompressorRecipes.compressingList.put(input, is);
		CompressorRecipes.expList.put(is, Float.valueOf(exp));
	}
	
	public void addCompressingRecipeForBlock(Block input, ItemStack is, float exp)
	{
		addCompressingRecipe(new ItemStack(Item.getItemFromBlock(input)), is, exp);
	}
	
	public static ItemStack getCompressingResult(ItemStack is)
	{
		for(Entry<ItemStack, ItemStack> e : CompressorRecipes.compressingList.entrySet())
		{
			if(CompressorRecipes.compareItemStacks(is, (ItemStack)e.getKey()))
			{
				return (ItemStack)e.getValue();
			}
		}
		
		return null;
	}
	
	private static boolean compareItemStacks(ItemStack is1, ItemStack is2)
	{
		return is2.getItem() == is1.getItem() && (is2.getMetadata() == 32767 || is2.getMetadata() == is1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getCompressingList()
	{
		return CompressorRecipes.compressingList;
	}
	
	public float getCompressingExp(ItemStack is)
	{
		float ret = is.getItem().getSmeltingExperience(is);
		if(ret != -1) return ret;
		
		for(Entry<ItemStack, Float> e : CompressorRecipes.expList.entrySet())
		{
			if(CompressorRecipes.compareItemStacks(is, (ItemStack)e.getKey()))
			{
				return ((Float)e.getValue()).floatValue();
			}
		}
		
		return 0.0F;
	}
}