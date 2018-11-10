package com.SirBlobman.gemmary.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.SirBlobman.gemmary.items.GemmaryElements;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

public class HydroThermalRecipes 
{
	private static final HydroThermalRecipes hydratingBase = new HydroThermalRecipes();
	private Map<ItemStack, ItemStack> hydratingList = Maps.<ItemStack, ItemStack>newHashMap();
	private Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static HydroThermalRecipes instance()
	{
		return hydratingBase;
	}
	
	public HydroThermalRecipes()
	{
		this.addHydratingRecipe(new ItemStack(GemmaryElements.beryllium, 1), new ItemStack(GemmaryGemParts.EmeraldPart), 100.0F);
	}
	
	public void addHydratingRecipe(ItemStack input, ItemStack stack, float experience)
	{
		if (getHydratingResult(input) != null)
		{
			FMLLog.info("Ignored Hydrating Recipe with conflicting input: " + input + " = " + stack);
			return;
		}
		this.hydratingList.put(input, stack);
		this.experienceList.put(stack, Float.valueOf(experience));
	}
	
	public ItemStack getHydratingResult(ItemStack stack)
	{
		for (Entry<ItemStack, ItemStack> entry : this.hydratingList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }
        return null;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getHydratingList()
    {
        return this.hydratingList;
    }

    public float getSmeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
}