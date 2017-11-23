package com.SirBlobman.gemmary.item.custom;

import com.SirBlobman.gemmary.creative.GTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemQuickArmor extends ItemArmor {
    public ItemQuickArmor(ArmorMaterial mat, EntityEquipmentSlot slot) {
        super(mat, slot == EntityEquipmentSlot.LEGS ? 2 : 1, slot);
        String name = getName(mat, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.ITEMS);
    }
    
    public static String getName(ArmorMaterial mat, EntityEquipmentSlot slot) {
        String name = mat.getName().substring(8).toLowerCase();
        switch(slot) {
            case HEAD: return name + "_helmet";
            case CHEST: return name + "_chestplate";
            case LEGS: return name + "_leggings";
            case FEET: return name + "_boots";
            default: return name + "_armor";
        }
    }
}