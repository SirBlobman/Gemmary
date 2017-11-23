package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.utility.Util;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class HydrothermalVeinRecipes {
    private static final HydrothermalVeinRecipes INSTANCE = new HydrothermalVeinRecipes();
    private final Map<ItemStack, ItemStack> hydratingList = Util.newMap();
    public static HydrothermalVeinRecipes instance() {return INSTANCE;}
    public Map<ItemStack, ItemStack> getHydratingList() {return hydratingList;}
    
    private HydrothermalVeinRecipes() {
        addHydrationRecipe(GItems.BERYLLIUM, new ItemStack(GItems.EMERALD_PART));
    }
    
    public void addHydrationRecipe(Block input, ItemStack output) {
        Item item = Item.getItemFromBlock(input);
        addHydrationRecipe(item, output);
    }
    
    public void addHydrationRecipe(Item input, ItemStack output) {
        ItemStack is = new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE);
        addHydrationRecipe(is, output);
    }
    
    public void addHydrationRecipe(ItemStack input, ItemStack output) {
        if(getResult(input) != ItemStack.EMPTY) {Util.print(String.format("Ignored hydrating recipe with conflicting input %s = %s", input, output)); return;}
        hydratingList.put(input, output);
    }
    
    public ItemStack getResult(ItemStack is) {
        for(Entry<ItemStack, ItemStack> en : hydratingList.entrySet()) {
            if(compareItemStacks(is, en.getKey())) return en.getValue();
        } return ItemStack.EMPTY;
    }
    
    private boolean compareItemStacks(ItemStack is1, ItemStack is2) {
        boolean b1 = (is2.getItem() == is1.getItem());
        boolean b2 = (is2.getMetadata() == OreDictionary.WILDCARD_VALUE);
        boolean b3 = (is2.getMetadata() == is1.getMetadata());
        return (b1 && (b2 || b3));
    }
}