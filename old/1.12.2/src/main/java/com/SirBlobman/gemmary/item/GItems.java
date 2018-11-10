package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.item.custom.*;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class GItems {
    public static final ArmorMaterial 
        ARMOR_AMETHYST  = EnumHelper.addArmorMaterial("AMETHYST", Gemmary.MODID + ":amethyst", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_RUBY      = EnumHelper.addArmorMaterial("RUBY", Gemmary.MODID + ":ruby", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_SAPPHIRE  = EnumHelper.addArmorMaterial("SAPPHIRE", Gemmary.MODID + ":sapphire", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_TALC      = EnumHelper.addArmorMaterial("TALC", Gemmary.MODID + ":talc", 4, new int[] {1, 1, 1, 1}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_TANZANITE = EnumHelper.addArmorMaterial("TANZANITE", Gemmary.MODID + ":tanzanite", 22, new int[] {5, 7, 6, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_TOPAZ     = EnumHelper.addArmorMaterial("TOPAZ", Gemmary.MODID + ":topaz", 26, new int[] {6, 8, 7, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0),
        ARMOR_TURQUOISE = EnumHelper.addArmorMaterial("TURQUOISE", Gemmary.MODID + ":turquoise", 17, new int[] {4, 6, 5, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
    
    public static final ItemGem 
        AMETHYST        = new ItemGem("amethyst", 7.0F),
        RUBY            = new ItemGem("ruby", 9.0F),
        SAPPHIRE        = new ItemGem("sapphire", 9.0F),
        TALC            = new ItemGem("talc", 1.0F),
        TANZANITE       = new ItemGem("tanzanite", 6.75F),
        TOPAZ           = new ItemGem("topaz", 8.0F),
        TURQUOISE       = new ItemGem("turquoise", 5.5F);
    
    public static final ItemGemPart
        DIAMOND_PART    = new ItemGemPart("diamond"),
        EMERALD_PART    = new ItemGemPart("emerald");
    
    public static final ItemElement
        ALUMINUM        = new ItemElement("aluminum", "Al", 13, 14),
        BERYLLIUM       = new ItemElement("beryllium", "Be", 4, 5),
        CARBON          = new ItemElement("carbon", "C", 6, 6),
        CHROMIUM        = new ItemElement("chromium", "Cr", 24, 28),
        OXYGEN          = new ItemElement("oxygen", "O", 8, 8);
    
    public static final ItemQuickArmor
        AMETHYST_HELMET      = new ItemQuickArmor(ARMOR_AMETHYST, EntityEquipmentSlot.HEAD),
        AMETHYST_CHESTPLATE  = new ItemQuickArmor(ARMOR_AMETHYST, EntityEquipmentSlot.CHEST),
        AMETHYST_LEGGINGS    = new ItemQuickArmor(ARMOR_AMETHYST, EntityEquipmentSlot.LEGS),
        AMETHYST_BOOTS       = new ItemQuickArmor(ARMOR_AMETHYST, EntityEquipmentSlot.FEET),
        
        RUBY_HELMET          = new ItemQuickArmor(ARMOR_RUBY, EntityEquipmentSlot.HEAD),
        RUBY_CHESTPLATE      = new ItemQuickArmor(ARMOR_RUBY, EntityEquipmentSlot.CHEST),
        RUBY_LEGGINGS        = new ItemQuickArmor(ARMOR_RUBY, EntityEquipmentSlot.LEGS),
        RUBY_BOOTS           = new ItemQuickArmor(ARMOR_RUBY, EntityEquipmentSlot.FEET),
                
        SAPPHIRE_HELMET      = new ItemQuickArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.HEAD),
        SAPPHIRE_CHESTPLATE  = new ItemQuickArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.CHEST),
        SAPPHIRE_LEGGINGS    = new ItemQuickArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.LEGS),
        SAPPHIRE_BOOTS       = new ItemQuickArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.FEET),
                        
        TALC_HELMET          = new ItemQuickArmor(ARMOR_TALC, EntityEquipmentSlot.HEAD),
        TALC_CHESTPLATE      = new ItemQuickArmor(ARMOR_TALC, EntityEquipmentSlot.CHEST),
        TALC_LEGGINGS        = new ItemQuickArmor(ARMOR_TALC, EntityEquipmentSlot.LEGS),
        TALC_BOOTS           = new ItemQuickArmor(ARMOR_TALC, EntityEquipmentSlot.FEET),
                
        TANZANITE_HELMET     = new ItemQuickArmor(ARMOR_TANZANITE, EntityEquipmentSlot.HEAD),
        TANZANITE_CHESTPLATE = new ItemQuickArmor(ARMOR_TANZANITE, EntityEquipmentSlot.CHEST),
        TANZANITE_LEGGINGS   = new ItemQuickArmor(ARMOR_TANZANITE, EntityEquipmentSlot.LEGS),
        TANZANITE_BOOTS      = new ItemQuickArmor(ARMOR_TANZANITE, EntityEquipmentSlot.FEET),
                
        TOPAZ_HELMET         = new ItemQuickArmor(ARMOR_TOPAZ, EntityEquipmentSlot.HEAD),
        TOPAZ_CHESTPLATE     = new ItemQuickArmor(ARMOR_TOPAZ, EntityEquipmentSlot.CHEST),
        TOPAZ_LEGGINGS       = new ItemQuickArmor(ARMOR_TOPAZ, EntityEquipmentSlot.LEGS),
        TOPAZ_BOOTS          = new ItemQuickArmor(ARMOR_TOPAZ, EntityEquipmentSlot.FEET),
                
        TURQUOISE_HELMET     = new ItemQuickArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.HEAD),
        TURQUOISE_CHESTPLATE = new ItemQuickArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.CHEST),
        TURQUOISE_LEGGINGS   = new ItemQuickArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.LEGS),
        TURQUOISE_BOOTS      = new ItemQuickArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.FEET);
    
    //Tools and Weapons
    public static final ItemCloth CLOTH = new ItemCloth();
    
    public static void registerItems(IForgeRegistry<Item> ifr) {
        //Gems and Parts
        ifr.registerAll(AMETHYST, RUBY, SAPPHIRE, TALC, TANZANITE, TOPAZ, TURQUOISE);
        ifr.registerAll(DIAMOND_PART, EMERALD_PART);
        
        //Elements and Molecules
        ifr.registerAll(ALUMINUM, BERYLLIUM, CARBON, CHROMIUM, OXYGEN);
        
        //Armor
        ifr.registerAll(
            AMETHYST_HELMET, AMETHYST_CHESTPLATE, AMETHYST_LEGGINGS, AMETHYST_BOOTS,
            RUBY_HELMET, RUBY_CHESTPLATE, RUBY_LEGGINGS, RUBY_BOOTS,
            SAPPHIRE_HELMET, SAPPHIRE_CHESTPLATE, SAPPHIRE_LEGGINGS, SAPPHIRE_BOOTS,
            TALC_HELMET, TALC_CHESTPLATE, TALC_LEGGINGS, TALC_BOOTS,
            TANZANITE_HELMET, TANZANITE_CHESTPLATE, TANZANITE_LEGGINGS, TANZANITE_BOOTS,
            TOPAZ_HELMET, TOPAZ_CHESTPLATE, TOPAZ_LEGGINGS, TOPAZ_BOOTS,
            TURQUOISE_HELMET, TURQUOISE_CHESTPLATE, TURQUOISE_LEGGINGS, TURQUOISE_BOOTS
        );
        
        //Tools and Weapons
        ifr.registerAll(CLOTH);
    }
}