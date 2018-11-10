package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.item.GemmaryItems;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompressorRecipes {
    private static CompressorRecipes INSTANCE;
    private final Map<ItemStack, ItemStack> recipeMap = new HashMap<>();
    private CompressorRecipes() {
        ItemStack coalBlockItem = new ItemStack(Blocks.COAL_BLOCK, 1, 0);
        ItemStack diamondItem = new ItemStack(Items.DIAMOND, 1, 0);
        addRecipe(coalBlockItem, diamondItem);
        
        ItemStack carbonItem = new ItemStack(GemmaryItems.CARBON, 1, 0);
        ItemStack diamondPartItem = new ItemStack(GemmaryItems.DIAMOND_PART, 1, 0);
        addRecipe(carbonItem, diamondPartItem);
    }
    
    private boolean areSimilar(ItemStack stack1, ItemStack stack2) {
        boolean areItemsEqual = (stack1.getItem() == stack2.getItem());
        boolean isWildCard = (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE);
        boolean areMetasEqual = (stack1.getMetadata() == stack2.getMetadata());
        return (areItemsEqual && (isWildCard || areMetasEqual));
    }
    
    public static CompressorRecipes getInstance() {
        if(INSTANCE == null) INSTANCE = new CompressorRecipes();
        
        return INSTANCE;
    }
    
    public Map<ItemStack, ItemStack> getRecipeList() {return new HashMap<>(this.recipeMap);}
    
    public void addRecipe(ItemStack input, ItemStack output) {
        ItemStack resultItem = getResult(input);
        if(resultItem != ItemStack.EMPTY) {
            String errorMessage = String.format("Ignored compressor recipe with conflicting input %s = %s", input, output);
            Gemmary.LOGGER.error(errorMessage);
            return;
        }
        
        this.recipeMap.put(input.copy(), output.copy());
    }
    
    public ItemStack getResult(ItemStack input) {
        for(Entry<ItemStack, ItemStack> entry : getRecipeList().entrySet()) {
            ItemStack entryInput = entry.getKey();
            if(areSimilar(input, entryInput)) {
                ItemStack resultItem = entry.getValue();
                return resultItem;
            }
        }
        
        return ItemStack.EMPTY;
    }
}