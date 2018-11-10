package com.SirBlobman.gemmary.utility;

import com.SirBlobman.gemmary.item.custom.ItemElement;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemUtil extends Util {
    public static List<ItemStack> getAtoms() {
        List<ItemStack> list = newList();
        IForgeRegistry<Item> allItems = ForgeRegistries.ITEMS;
        for(Item item : allItems) {
            if(item instanceof ItemElement) {
                ItemStack is = new ItemStack(item, 1);
                list.add(is);
            }
        } return list;
    }
}