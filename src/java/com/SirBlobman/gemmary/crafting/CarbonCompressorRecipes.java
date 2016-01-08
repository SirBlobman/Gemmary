package com.SirBlobman.gemmary.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.SirBlobman.gemmary.items.GemmaryElements;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CarbonCompressorRecipes
{
    private static final CarbonCompressorRecipes smeltingBase = new CarbonCompressorRecipes();
    private Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static CarbonCompressorRecipes instance()
    {
        return smeltingBase;
    }

    public CarbonCompressorRecipes()
    {
        this.addCompressingRecipe(new ItemStack(Blocks.coal_block, 1), new ItemStack(Items.diamond), 100.0F);
        this.addCompressingRecipe(new ItemStack(GemmaryElements.carbon, 1), new ItemStack(GemmaryGemParts.DiamondPart), 100.0F);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     *  
     * @param input The block to be used as the input for the smelting recipe.
     * @param stack The output for this recipe in the form of an ItemStack.
     * @param experience The amount of experience this recipe will give the player.
     */
    public void addCompressingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addCompressing(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     *  
     * @param input The input Item to be used for this recipe.
     * @param stack The output ItemStack for this recipe.
     * @param experience The amount of experience this recipe will give the player.
     */
    public void addCompressing(Item input, ItemStack stack, float experience)
    {
        this.addCompressingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     *  
     * @param input The input ItemStack for this recipe.
     * @param stack The output ItemStack for this recipe.
     * @param experience The amount of experience this recipe will give the player.
     */
    public void addCompressingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getCompressingResult(input) != null) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getCompressingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }

        return null;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
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