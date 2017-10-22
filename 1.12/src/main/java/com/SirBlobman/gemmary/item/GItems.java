package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class GItems {
	public static final ArmorMaterial ARMOR_AMETHYST = newArmor("amethyst", 30, new int[] {4, 6, 5, 3});
	public static final ArmorMaterial ARMOR_RUBY = newArmor("ruby", 30, new int[] {4, 6, 5, 3});
	public static final ArmorMaterial ARMOR_SAPPHIRE = newArmor("sapphire", 30, new int[] {4, 6, 5, 3});
	public static final ArmorMaterial ARMOR_TALC = newArmor("talc", 4, new int[] {0, 1, 0, 0});
	public static final ArmorMaterial ARMOR_TANZANITE = newArmor("tanzanite", 22, new int[] {5, 7, 6, 4});
	public static final ArmorMaterial ARMOR_TOPAZ = newArmor("topaz", 26, new int[] {5, 7, 6, 4});
	public static final ArmorMaterial ARMOR_TURQUOISE = newArmor("turquoise", 17, new int[] {4, 6, 5, 2});
	
	public static final Gem AMETHYST = new Gem("amethyst", 7.0D);
	public static final Gem CORUNDUM = new Gem("corundum", 9.0D);
	public static final Gem RUBY = new Gem("ruby", 9.0D);
	public static final Gem SAPPHIRE = new Gem("talc", 1.0D);
	public static final Gem TANZANITE = new Gem("tanzanite", 6.5D);
	public static final Gem TOPAZ = new Gem("topaz", 8.0D);
	public static final Gem TURQUOISE = new Gem("turquoise", 5.0D);
	
	public static final GemPart PART_DIAMOND = new GemPart("diamond");
	public static final GemPart PART_EMERALD = new GemPart("emerald");
	
	public static final Item HOT_WATER_CONTAINER = customItem("hot_water_container");
	public static final Cloth CLOTH = new Cloth();
	
	public static void items(IForgeRegistry<Item> ifr) {
		ifr.registerAll(AMETHYST, CORUNDUM, RUBY, SAPPHIRE, TANZANITE, TOPAZ, TURQUOISE);
		ifr.registerAll(PART_DIAMOND, PART_EMERALD);
	}
	
	private static final ArmorMaterial newArmor(String name, int durability, int[] protection) {
		String l = name.toLowerCase();
		String id = Gemmary.MODID + ":" + l;
		int enchantability = 30;
		SoundEvent sound = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		int toughness = 0;
		final ArmorMaterial am = EnumHelper.addArmorMaterial(name, id, durability, protection, enchantability, sound, toughness);
		return am;
	}
	
	private static final Item customItem(String name) {
		Item i = new Item();
		i = i.setUnlocalizedName(name);
		i = i.setRegistryName(name);
		i = i.setCreativeTab(GemmaryTabs.ITEMS);
		return i;
	}
}