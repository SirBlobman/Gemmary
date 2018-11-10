package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.utility.Util;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompressorRecipes {
    private static final CompressorRecipes INSTANCE = new CompressorRecipes();
    private final Map<ItemStack, ItemStack> compressingList = Util.newMap();
    public static CompressorRecipes instance() {return INSTANCE;}
    public Map<ItemStack, ItemStack> getCompressingList() {return compressingList;}
    
    private CompressorRecipes() {
        addCompressingRecipe(Blocks.COAL_BLOCK, new ItemStack(Items.DIAMOND));
        addCompressingRecipe(GItems.CARBON, new ItemStack(GItems.DIAMOND_PART));
    }
    
    public void addCompressingRecipe(Block input, ItemStack output) {
        Item item = Item.getItemFromBlock(input);
        addCompressingRecipe(item, output);
    }
    
    public void addCompressingRecipe(Item input, ItemStack output) {
        ItemStack is = new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE);
        addCompressingRecipe(is, output);
    }
    
    public void addCompressingRecipe(ItemStack input, ItemStack output) {
        if(getResult(input) != ItemStack.EMPTY) {Util.print(String.format("Ignored compressing recipe with conflicting input %s = %s", input, output)); return;}
        compressingList.put(input, output);
    }
    
    public ItemStack getResult(ItemStack is) {
        for(Entry<ItemStack, ItemStack> en : compressingList.entrySet()) {
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