package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.constant.ModInfo;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class GemmaryItems {
	// Gems
	public static final ItemGem
		GEM_AMETHYST  = new ItemGem("amethyst", 7.0D),
		GEM_RUBY 	  = new ItemGem("ruby", 9.0D),
		GEM_SAPPHIRE  = new ItemGem("sapphire", 9.0D),
		GEM_TALC 	  = new ItemGem("talc", 1.0F),
		GEM_TANZANITE = new ItemGem("tanzanite", 6.75D),
		GEM_TOPAZ 	  = new ItemGem("topaz", 8.0D),
		GEM_TURQUOISE = new ItemGem("turquoise", 5.5D)
	;
	
	// Gem Parts
	public static final ItemGemPart
		DIAMOND_PART = new ItemGemPart("diamond"),
		EMERALD_PART = new ItemGemPart("emerald")
	;
	
	// Periodic Elements
	public static final ItemElement
	    ALUMINUM   = new ItemElement("aluminum", "Al", 13, 14),
	    BERYLLIUM  = new ItemElement("beryllium", "Be", 4, 5),
	    CARBON     = new ItemElement("carbon", "C", 6, 6),
	    CHROMIUM   = new ItemElement("chromium", "Cr", 24, 28),
	    OXYGEN     = new ItemElement("oxygen", "O", 8, 8)
	;
	
	public static final ItemCloth CLOTH = new ItemCloth();
	
	// Armor
	public static final ArmorMaterial
		ARMOR_AMETHYST  = createArmorMaterial("amethyst", 30, new int[] {4, 6, 5, 3}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
		ARMOR_RUBY 		= createArmorMaterial("ruby", 30, new int[] {4, 6, 5, 3}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
		ARMOR_SAPPHIRE	= createArmorMaterial("sapphire", 30, new int[] {4, 6, 5, 3}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
		ARMOR_TALC 		= createArmorMaterial("talc", 4, new int[] {1, 1, 1, 1}, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER),
		ARMOR_TANZANITE = createArmorMaterial("tanzanite", 22, new int[] {5, 7, 6, 4}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
		ARMOR_TOPAZ		= createArmorMaterial("topaz", 26, new int[] {6, 8, 7, 5}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND),
		ARMOR_TURQUOISE = createArmorMaterial("turquoise", 17, new int[] {4, 6, 5, 2}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND)
	;
	
	public static final ItemGemArmor
        AMETHYST_HELMET      = new ItemGemArmor(ARMOR_AMETHYST, EntityEquipmentSlot.HEAD),
        AMETHYST_CHESTPLATE  = new ItemGemArmor(ARMOR_AMETHYST, EntityEquipmentSlot.CHEST),
        AMETHYST_LEGGINGS    = new ItemGemArmor(ARMOR_AMETHYST, EntityEquipmentSlot.LEGS),
        AMETHYST_BOOTS       = new ItemGemArmor(ARMOR_AMETHYST, EntityEquipmentSlot.FEET),
    
        RUBY_HELMET          = new ItemGemArmor(ARMOR_RUBY, EntityEquipmentSlot.HEAD),
        RUBY_CHESTPLATE      = new ItemGemArmor(ARMOR_RUBY, EntityEquipmentSlot.CHEST),
        RUBY_LEGGINGS        = new ItemGemArmor(ARMOR_RUBY, EntityEquipmentSlot.LEGS),
        RUBY_BOOTS           = new ItemGemArmor(ARMOR_RUBY, EntityEquipmentSlot.FEET),
            
        SAPPHIRE_HELMET      = new ItemGemArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.HEAD),
        SAPPHIRE_CHESTPLATE  = new ItemGemArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.CHEST),
        SAPPHIRE_LEGGINGS    = new ItemGemArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.LEGS),
        SAPPHIRE_BOOTS       = new ItemGemArmor(ARMOR_SAPPHIRE, EntityEquipmentSlot.FEET),
                    
        TALC_HELMET          = new ItemGemArmor(ARMOR_TALC, EntityEquipmentSlot.HEAD),
        TALC_CHESTPLATE      = new ItemGemArmor(ARMOR_TALC, EntityEquipmentSlot.CHEST),
        TALC_LEGGINGS        = new ItemGemArmor(ARMOR_TALC, EntityEquipmentSlot.LEGS),
        TALC_BOOTS           = new ItemGemArmor(ARMOR_TALC, EntityEquipmentSlot.FEET),
            
        TANZANITE_HELMET     = new ItemGemArmor(ARMOR_TANZANITE, EntityEquipmentSlot.HEAD),
        TANZANITE_CHESTPLATE = new ItemGemArmor(ARMOR_TANZANITE, EntityEquipmentSlot.CHEST),
        TANZANITE_LEGGINGS   = new ItemGemArmor(ARMOR_TANZANITE, EntityEquipmentSlot.LEGS),
        TANZANITE_BOOTS      = new ItemGemArmor(ARMOR_TANZANITE, EntityEquipmentSlot.FEET),
            
        TOPAZ_HELMET         = new ItemGemArmor(ARMOR_TOPAZ, EntityEquipmentSlot.HEAD),
        TOPAZ_CHESTPLATE     = new ItemGemArmor(ARMOR_TOPAZ, EntityEquipmentSlot.CHEST),
        TOPAZ_LEGGINGS       = new ItemGemArmor(ARMOR_TOPAZ, EntityEquipmentSlot.LEGS),
        TOPAZ_BOOTS          = new ItemGemArmor(ARMOR_TOPAZ, EntityEquipmentSlot.FEET),
            
        TURQUOISE_HELMET     = new ItemGemArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.HEAD),
        TURQUOISE_CHESTPLATE = new ItemGemArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.CHEST),
        TURQUOISE_LEGGINGS   = new ItemGemArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.LEGS),
        TURQUOISE_BOOTS      = new ItemGemArmor(ARMOR_TURQUOISE, EntityEquipmentSlot.FEET)
	;
	
	public static final void registerAllItems(IForgeRegistry<Item> reg) {
		reg.registerAll(GEM_AMETHYST, GEM_RUBY, GEM_SAPPHIRE, GEM_TALC, GEM_TANZANITE, GEM_TOPAZ, GEM_TURQUOISE);
		reg.registerAll(DIAMOND_PART, EMERALD_PART);
		
		reg.registerAll(ALUMINUM, BERYLLIUM, CARBON, CHROMIUM, OXYGEN);
		
		reg.registerAll(CLOTH);
		
		reg.registerAll(
	        AMETHYST_HELMET, AMETHYST_CHESTPLATE, AMETHYST_LEGGINGS, AMETHYST_BOOTS,
	        RUBY_HELMET, RUBY_CHESTPLATE, RUBY_LEGGINGS, RUBY_BOOTS,
	        SAPPHIRE_HELMET, SAPPHIRE_CHESTPLATE, SAPPHIRE_LEGGINGS, SAPPHIRE_BOOTS,
	        TALC_HELMET, TALC_CHESTPLATE, TALC_LEGGINGS, TALC_BOOTS,
	        TANZANITE_HELMET, TANZANITE_CHESTPLATE, TANZANITE_LEGGINGS, TANZANITE_BOOTS,
	        TOPAZ_HELMET, TOPAZ_CHESTPLATE, TOPAZ_LEGGINGS, TOPAZ_BOOTS,
	        TURQUOISE_HELMET, TURQUOISE_CHESTPLATE, TURQUOISE_LEGGINGS, TURQUOISE_BOOTS
		);
	}
	
	private static final ArmorMaterial createArmorMaterial(String type, int durability, int[] protectionValues, SoundEvent sound) {
		String enumName = type.toUpperCase();
		String idName = ModInfo.MODID + ":" + type;
		return EnumHelper.addArmorMaterial(enumName, idName, durability, protectionValues, 30, sound, 0.0F);
	}
}