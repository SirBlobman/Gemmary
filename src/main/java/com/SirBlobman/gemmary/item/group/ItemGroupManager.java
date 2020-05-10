package com.SirBlobman.gemmary.item.group;

import com.SirBlobman.gemmary.GemmaryMod;
import com.SirBlobman.gemmary.item.ItemManager;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class ItemGroupManager {
    public static final ItemGroup
    
    GEMMARY_ITEMS = new ItemGroup("gemmaryItems") {
        @Override
        public ItemStack createIcon() {
            GemmaryMod gemmaryMod = GemmaryMod.getInstance();
            ItemManager itemManager = gemmaryMod.getItemManager();
    
            Item item = itemManager.getItem("amethyst");
            return new ItemStack(item, 1);
        }
    },
    
    GEMMARY_ATOMS = new ItemGroup("gemmaryAtoms") {
        @Override
        public ItemStack createIcon() {
            GemmaryMod gemmaryMod = GemmaryMod.getInstance();
            ItemManager itemManager = gemmaryMod.getItemManager();
    
            Item item = itemManager.getItem("hydrogen_atom");
            return new ItemStack(item, 1);
        }
    }
    ;
}