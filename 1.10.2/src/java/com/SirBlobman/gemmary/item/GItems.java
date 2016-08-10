package com.SirBlobman.gemmary.item;

import java.util.Arrays;
import java.util.List;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public final class GItems 
{
/*
 * Armor Materials (MaterialName, TextureName, Durability, DamageReduction, Enchantability)
 * Durability = 3.3 * Mohs Scale
 * Damage Reduction = Mohs Scale
 * 
 */
	public static ArmorMaterial Amethyst = EnumHelper.addArmorMaterial("Amethyst", "gemmary:amethyst", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Ruby = EnumHelper.addArmorMaterial("Ruby", "gemmary:ruby", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Sapphire = EnumHelper.addArmorMaterial("Sapphire", "gemmary:sapphire", 30, new int[] {4, 6, 5, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Talc = EnumHelper.addArmorMaterial("Talc", "gemmary:talc", 4, new int[] {0, 1, 0, 0}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Tanzanite = EnumHelper.addArmorMaterial("Tanzanite", "gemmary:tanzanite", 22, new int[] {5, 7, 6, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Topaz = EnumHelper.addArmorMaterial("Topaz", "gemmary:topaz", 26, new int[] {6, 8, 7, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	public static ArmorMaterial Turquoise = EnumHelper.addArmorMaterial("Turquoise", "gemmary:turquoise", 17, new int[] {4, 6, 5, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	
//Gems
	public static Item amethyst = new Gem("amethyst", 7.0);
	public static Item corundum = new Gem("corundum", 9.0);
	public static Item ruby = new Gem("ruby", 9.0);
	public static Item sapphire = new Gem("sapphire", 9.0);
	public static Item talc = new Gem("talc", 1.0);
	public static Item tanzanite = new Gem("tanzanite", 6.5);
	public static Item topaz = new Gem("topaz", 8.0);
	public static Item turquoise = new Gem("turquoise", 5.0);
	
//Gem Parts
	public static Item diamondPart = new GemPart("diamond_part");
	public static Item emeraldPart = new GemPart("emerald_part");
	
//Elements
	public static Item aluminum = new Element("aluminum");
	public static Item beryllium = new Element("beryllium");
	public static Item carbon = new Element("carbon");
	public static Item chromium = new Element("chromium");
	public static Item hydrogen = new Element("hydrogen");
	public static Item iron = new Element("iron");
	public static Item oxygen = new Element("oxygen");
	public static Item titanium = new Element("titanium");
	
//Alloys
	public static Item corundumChromium = new Alloy(corundum, chromium, ruby);
	public static Item corundumIron = new Alloy(corundum, iron, sapphire);
	
//Armor
	public static Armor amethystHelmet = new Armor("amethyst", "helmet", Amethyst, 1, EntityEquipmentSlot.HEAD, amethyst);
	public static Armor amethystChestplate = new Armor("amethyst", "chestplate", Amethyst, 1, EntityEquipmentSlot.CHEST, amethyst);
	public static Armor amethystLeggings = new Armor("amethyst", "leggings", Amethyst, 2, EntityEquipmentSlot.LEGS, amethyst);
	public static Armor amethystBoots = new Armor("amethyst", "boots", Amethyst, 1, EntityEquipmentSlot.FEET, amethyst);

	public static Armor rubyHelmet = new Armor("ruby", "helmet", Ruby, 1, EntityEquipmentSlot.HEAD, ruby);
	public static Armor rubyChestplate = new Armor("ruby", "chestplate", Ruby, 1, EntityEquipmentSlot.CHEST, ruby);
	public static Armor rubyLeggings = new Armor("ruby", "leggings", Ruby, 2, EntityEquipmentSlot.LEGS, ruby);
	public static Armor rubyBoots = new Armor("ruby", "boots", Ruby, 1, EntityEquipmentSlot.FEET, ruby);

	public static Armor sapphireHelmet = new Armor("sapphire", "helmet", Sapphire, 1, EntityEquipmentSlot.HEAD, sapphire);
	public static Armor sapphireChestplate = new Armor("sapphire", "chestplate", Sapphire, 1, EntityEquipmentSlot.CHEST, sapphire);
	public static Armor sapphireLeggings = new Armor("sapphire", "leggings", Sapphire, 2, EntityEquipmentSlot.LEGS, sapphire);
	public static Armor sapphireBoots = new Armor("sapphire", "boots", Sapphire, 1, EntityEquipmentSlot.FEET, sapphire);

	public static Armor talcHelmet = new Armor("talc", "helmet", Talc, 1, EntityEquipmentSlot.HEAD, talc);
	public static Armor talcChestplate = new Armor("talc", "chestplate", Talc, 1, EntityEquipmentSlot.CHEST, talc);
	public static Armor talcLeggings = new Armor("talc", "leggings", Talc, 2, EntityEquipmentSlot.LEGS, talc);
	public static Armor talcBoots = new Armor("talc", "boots", Talc, 1, EntityEquipmentSlot.FEET, talc);

	public static Armor tanzaniteHelmet = new Armor("tanzanite", "helmet", Tanzanite, 1, EntityEquipmentSlot.HEAD, tanzanite);
	public static Armor tanzaniteChestplate = new Armor("tanzanite", "chestplate", Tanzanite, 1, EntityEquipmentSlot.CHEST, tanzanite);
	public static Armor tanzaniteLeggings = new Armor("tanzanite", "leggings", Tanzanite, 2, EntityEquipmentSlot.LEGS, tanzanite);
	public static Armor tanzaniteBoots = new Armor("tanzanite", "boots", Tanzanite, 1, EntityEquipmentSlot.FEET, tanzanite);

	public static Armor topazHelmet = new Armor("topaz", "helmet", Topaz, 1, EntityEquipmentSlot.HEAD, topaz);
	public static Armor topazChestplate = new Armor("topaz", "chestplate", Topaz, 1, EntityEquipmentSlot.CHEST, topaz);
	public static Armor topazLeggings = new Armor("topaz", "leggings", Topaz, 2, EntityEquipmentSlot.LEGS, topaz);
	public static Armor topazBoots = new Armor("topaz", "boots", Topaz, 1, EntityEquipmentSlot.FEET, topaz);

	public static Armor turquoiseHelmet = new Armor("turquoise", "helmet", Turquoise, 1, EntityEquipmentSlot.HEAD, turquoise);
	public static Armor turquoiseChestplate = new Armor("turquoise", "chestplate", Turquoise, 1, EntityEquipmentSlot.CHEST, turquoise);
	public static Armor turquoiseLeggings = new Armor("turquoise", "leggings", Turquoise, 2, EntityEquipmentSlot.LEGS, turquoise);
	public static Armor turquoiseBoots = new Armor("turquoise", "boots", Turquoise, 1, EntityEquipmentSlot.FEET, turquoise);
	
//Other
	public static Item heatedWaterContainer = new Item().setUnlocalizedName("heated_water_container").setCreativeTab(GemmaryTabs.Items).setRegistryName("heated_water_container");
	public static Item cloth = new Cloth("cloth");
	public static Item recipeBook = new RecipeBook("recipe_book");
	public static Item creativeFuel = new CreativeFuel();
	
	public static final void createGems()
	{
		rDusty(amethyst);
		rDusty(corundum);
		rDusty(ruby);
		rDusty(sapphire);
		rDusty(talc);
		rDusty(tanzanite);
		rDusty(topaz);
		rDusty(turquoise);
	}
	
	public static final void createGemParts()
	{
		r(diamondPart);
		r(emeraldPart);
	}
	
	public static final void createElements()
	{
		r(aluminum);
		r(beryllium);
		r(carbon);
		r(chromium);
		r(hydrogen);
		r(iron);
		r(oxygen);
		r(titanium);
	}
	
	public static final void createAlloys()
	{
		r(corundumChromium);
		r(corundumIron);
	}
	
	public static final void createItems()
	{
		r(heatedWaterContainer);
		r(cloth);
		r(recipeBook);
		r(creativeFuel);
	}
	
	public static final void createArmor()
	{
		rArmor(amethystHelmet, amethyst);
		rArmor(amethystChestplate, amethyst);
		rArmor(amethystLeggings, amethyst);
		rArmor(amethystBoots, amethyst);
		
		rArmor(rubyHelmet, ruby);
		rArmor(rubyChestplate, ruby);
		rArmor(rubyLeggings, ruby);
		rArmor(rubyBoots, ruby);
		
		rArmor(sapphireHelmet, sapphire);
		rArmor(sapphireChestplate, sapphire);
		rArmor(sapphireLeggings, sapphire);
		rArmor(sapphireBoots, sapphire);

		rArmor(talcHelmet, talc);
		rArmor(talcChestplate, talc);
		rArmor(talcLeggings, talc);
		rArmor(talcBoots, talc);

		rArmor(tanzaniteHelmet, tanzanite);
		rArmor(tanzaniteChestplate, tanzanite);
		rArmor(tanzaniteLeggings, tanzanite);
		rArmor(tanzaniteBoots, tanzanite);
		
		rArmor(topazHelmet, topaz);
		rArmor(topazChestplate, topaz);
		rArmor(topazLeggings, topaz);
		rArmor(topazBoots, topaz);

		rArmor(turquoiseHelmet, turquoise);
		rArmor(turquoiseChestplate, turquoise);
		rArmor(turquoiseLeggings, turquoise);
		rArmor(turquoiseBoots, turquoise);
	}
	
	private static void r(Item i)
	{
		GameRegistry.register(i);
	}
	
	private static void rDusty(Item i)
	{
		r(i);
		GameRegistry.addShapelessRecipe(new ItemStack(i, 1, 0), new Object[] {new ItemStack(i, 1, 1), new ItemStack(GItems.cloth, 1, OreDictionary.WILDCARD_VALUE)});
	}
	
	private static void rArmor(Armor a, Item i)
	{
		r(a);
		if(a.armorType == EntityEquipmentSlot.HEAD) {GameRegistry.addShapedRecipe(new ItemStack(a), new Object[] {"###", "# #", '#', i});}
		if(a.armorType == EntityEquipmentSlot.CHEST) {GameRegistry.addShapedRecipe(new ItemStack(a), new Object[] {"# #", "###", "###", '#', i});}
		if(a.armorType == EntityEquipmentSlot.LEGS) {GameRegistry.addShapedRecipe(new ItemStack(a), new Object[] {"###", "# #", "# #", '#', i});}
		if(a.armorType == EntityEquipmentSlot.FEET) {GameRegistry.addShapedRecipe(new ItemStack(a), new Object[] {"# #", "# #", '#', i});}
	}
	
	public static List<Item> getAtoms()
	{
		return Arrays.asList(aluminum, beryllium, carbon, hydrogen, oxygen);
	}
}