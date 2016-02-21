package com.SirBlobman.gemmary.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompressorRecipes
{
    private static final CompressorRecipes smeltingBase = new CompressorRecipes();
    private static Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private static Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static CompressorRecipes instance()
    {
        return smeltingBase;
    }

    public CompressorRecipes()
    {
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     *  
     * @param input The block to be used as the input for the smelting recipe.
     * @param stack The output for CompressorRecipes recipe in the form of an ItemStack.
     * @param experience The amount of experience CompressorRecipes recipe will give the player.
     */
    public void addCompressingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        CompressorRecipes.addCompressing(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     *  
     * @param input The input Item to be used for CompressorRecipes recipe.
     * @param stack The output ItemStack for CompressorRecipes recipe.
     * @param experience The amount of experience CompressorRecipes recipe will give the player.
     */
    public static void addCompressing(Item input, ItemStack stack, float experience)
    {
        CompressorRecipes.addCompressingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     *  
     * @param input The input ItemStack for CompressorRecipes recipe.
     * @param stack The output ItemStack for CompressorRecipes recipe.
     * @param experience The amount of experience CompressorRecipes recipe will give the player.
     */
    public static void addCompressingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getCompressingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
        CompressorRecipes.smeltingList.put(input, stack);
        CompressorRecipes.experienceList.put(stack, Float.valueOf(experience));
    }

    /**
     * Returns the smelting result of an item.
     */
    public static ItemStack getCompressingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : CompressorRecipes.smeltingList.entrySet())
        {
            if (CompressorRecipes.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }

        return null;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private static boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return CompressorRecipes.smeltingList;
    }

    public float getSmeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : CompressorRecipes.experienceList.entrySet())
        {
            if (CompressorRecipes.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
}