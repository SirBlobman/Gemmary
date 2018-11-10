package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

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
	public static ArmorMaterial Amethyst = EnumHelper.addArmorMaterial("Amethyst", "gemmary:amethyst", 30, new int[] {4, 6, 5, 3}, 30, null);
	public static ArmorMaterial Corundum = EnumHelper.addArmorMaterial("Corundum", "gemmary:corundum", 30, new int[] {4, 6, 5, 3}, 30, null);
	public static ArmorMaterial Ruby = EnumHelper.addArmorMaterial("Ruby", "gemmary:ruby", 30, new int[] {4, 6, 5, 3}, 30, null);
	public static ArmorMaterial Sapphire = EnumHelper.addArmorMaterial("Sapphire", "gemmary:sapphire", 30, new int[] {4, 6, 5, 3}, 30, null);
	public static ArmorMaterial Talc = EnumHelper.addArmorMaterial("Talc", "gemmary:talc", 4, new int[] {0, 1, 0, 0}, 30, null);
	public static ArmorMaterial Tanzanite = EnumHelper.addArmorMaterial("Tanzanite", "gemmary:tanzanite", 22, new int[] {5, 7, 6, 4}, 30, null);
	public static ArmorMaterial Topaz = EnumHelper.addArmorMaterial("Topaz", "gemmary:topaz", 26, new int[] {6, 8, 7, 5}, 30, null);
	public static ArmorMaterial Turquoise = EnumHelper.addArmorMaterial("Turquoise", "gemmary:turquoise", 17, new int[] {4, 6, 5, 2}, 30, null);
	
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
	public static Item hydrogen = new Element("hydrogen");
	public static Item oxygen = new Element("oxygen");
	
//Armor
	public static Item amethystHelmet = new Armor("amethyst", "helmet", Amethyst, 1, EntityEquipmentSlot.HEAD);
	public static Item amethystChestplate = new Armor("amethyst", "chestplate", Amethyst, 1, EntityEquipmentSlot.CHEST);
	public static Item amethystLeggings = new Armor("amethyst", "leggings", Amethyst, 2, EntityEquipmentSlot.LEGS);
	public static Item amethystBoots = new Armor("amethyst", "boots", Amethyst, 1, EntityEquipmentSlot.FEET);
	
	public static Item corundumHelmet = new Armor("corundum", "helmet", Corundum, 1, EntityEquipmentSlot.HEAD);
	public static Item corundumChestplate = new Armor("corundum", "chestplate", Corundum, 1, EntityEquipmentSlot.CHEST);
	public static Item corundumLeggings = new Armor("corundum", "leggings", Corundum, 2, EntityEquipmentSlot.LEGS);
	public static Item corundumBoots = new Armor("corundum", "boots", Corundum, 1, EntityEquipmentSlot.FEET);
	
	public static Item rubyHelmet = new Armor("ruby", "helmet", Ruby, 1, EntityEquipmentSlot.HEAD);
	public static Item rubyChestplate = new Armor("ruby", "chestplate", Ruby, 1, EntityEquipmentSlot.CHEST);
	public static Item rubyLeggings = new Armor("ruby", "leggings", Ruby, 2, EntityEquipmentSlot.LEGS);
	public static Item rubyBoots = new Armor("ruby", "boots", Ruby, 1, EntityEquipmentSlot.FEET);
	
	public static Item sapphireHelmet = new Armor("sapphire", "helmet", Sapphire, 1, EntityEquipmentSlot.HEAD);
	public static Item sapphireChestplate = new Armor("sapphire", "chestplate", Sapphire, 1, EntityEquipmentSlot.CHEST);
	public static Item sapphireLeggings = new Armor("sapphire", "leggings", Sapphire, 2, EntityEquipmentSlot.LEGS);
	public static Item sapphireBoots = new Armor("sapphire", "boots", Sapphire, 1, EntityEquipmentSlot.FEET);

	public static Item talcHelmet = new Armor("talc", "helmet", Talc, 1, EntityEquipmentSlot.HEAD);
	public static Item talcChestplate = new Armor("talc", "chestplate", Talc, 1, EntityEquipmentSlot.CHEST);
	public static Item talcLeggings = new Armor("talc", "leggings", Talc, 2, EntityEquipmentSlot.LEGS);
	public static Item talcBoots = new Armor("talc", "boots", Talc, 1, EntityEquipmentSlot.FEET);

	public static Item tanzaniteHelmet = new Armor("tanzanite", "helmet", Tanzanite, 1, EntityEquipmentSlot.HEAD);
	public static Item tanzaniteChestplate = new Armor("tanzanite", "chestplate", Tanzanite, 1, EntityEquipmentSlot.CHEST);
	public static Item tanzaniteLeggings = new Armor("tanzanite", "leggings", Tanzanite, 2, EntityEquipmentSlot.LEGS);
	public static Item tanzaniteBoots = new Armor("tanzanite", "boots", Tanzanite, 1, EntityEquipmentSlot.FEET);
	
	public static Item topazHelmet = new Armor("topaz", "helmet", Topaz, 1, EntityEquipmentSlot.HEAD);
	public static Item topazChestplate = new Armor("topaz", "chestplate", Topaz, 1, EntityEquipmentSlot.CHEST);
	public static Item topazLeggings = new Armor("topaz", "leggings", Topaz, 2, EntityEquipmentSlot.LEGS);
	public static Item topazBoots = new Armor("topaz", "boots", Topaz, 1, EntityEquipmentSlot.FEET);

	public static Item turquoiseHelmet = new Armor("turquoise", "helmet", Turquoise, 1, EntityEquipmentSlot.HEAD);
	public static Item turquoiseChestplate = new Armor("turquoise", "chestplate", Turquoise, 1, EntityEquipmentSlot.CHEST);
	public static Item turquoiseLeggings = new Armor("turquoise", "leggings", Turquoise, 2, EntityEquipmentSlot.LEGS);
	public static Item turquoiseBoots = new Armor("turquoise", "boots", Turquoise, 1, EntityEquipmentSlot.FEET);
	
//Other
	public static Item heatedWaterContainer = new Item().setUnlocalizedName("heated_water_container").setCreativeTab(GemmaryTabs.Items).setRegistryName("heated_water_container");
	public static Item cloth = new Cloth("cloth");
	public static Item recipeBook = new RecipeBook("recipe_book");
	
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
		r(hydrogen);
		r(oxygen);
	}
	
	public static final void createItems()
	{
		r(heatedWaterContainer);
		r(cloth);
		r(recipeBook);
	}
	
	public static final void createArmor()
	{
		r(amethystHelmet);
		r(amethystChestplate);
		r(amethystLeggings);
		r(amethystBoots);
		
		r(corundumHelmet);
		r(corundumChestplate);
		r(corundumLeggings);
		r(corundumBoots);
		
		r(rubyHelmet);
		r(rubyChestplate);
		r(rubyLeggings);
		r(rubyBoots);
		
		r(sapphireHelmet);
		r(sapphireChestplate);
		r(sapphireLeggings);
		r(sapphireBoots);

		r(talcHelmet);
		r(talcChestplate);
		r(talcLeggings);
		r(talcBoots);

		r(tanzaniteHelmet);
		r(tanzaniteChestplate);
		r(tanzaniteLeggings);
		r(tanzaniteBoots);
		
		r(topazHelmet);
		r(topazChestplate);
		r(topazLeggings);
		r(topazBoots);

		r(turquoiseHelmet);
		r(turquoiseChestplate);
		r(turquoiseLeggings);
		r(turquoiseBoots);
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
}
