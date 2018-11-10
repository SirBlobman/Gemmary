package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemGemArmor extends ItemArmor {
    public ItemGemArmor(ArmorMaterial mat, EntityEquipmentSlot slot) {
        super(mat, (slot == EntityEquipmentSlot.LEGS ? 2 : 1), slot);
        String name = getName(mat, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GemmaryCreativeTabs.ITEMS);
    }
    
    public static String getName(ArmorMaterial mat, EntityEquipmentSlot slot) {
        String name = mat.name();
        String lower = name.toLowerCase();
        switch (slot) {
            case HEAD:
                return (lower + "_helmet");
            case CHEST:
                return (lower + "_chestplate");
            case LEGS:
                return (lower + "_leggings");
            case FEET:
                return (lower + "_boots");
            default:
                return (lower + "_armor");
        }
    }
}