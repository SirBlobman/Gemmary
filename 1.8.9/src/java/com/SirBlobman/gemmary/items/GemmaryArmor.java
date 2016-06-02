package com.SirBlobman.gemmary.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemmaryArmor 
{
//Creative Tab
	public static final CreativeTabs Armor = new CreativeTabs("Armor")
	{
		@Override
		public Item getTabIconItem() 
		{
			return GemmaryArmor.amethystChestplate;
		}		
	};
	
//Armor Materials (MaterialName, TextureName, Durability, DamageReductions, Enchantability)
	//Durability = 3.3 * Mohs Scale Value (rounded to nearest whole number)
	//Damage Reduction = Mohs Scale Value rounded to nearest .5 or .0
	public static ArmorMaterial AMETHYST = EnumHelper.addArmorMaterial("AMETHYST", "gemmary:amethyst_armor", 30, new int[] {4, 6, 5, 3}, 30);
	public static ArmorMaterial CORUNDUM = EnumHelper.addArmorMaterial("CORUNDUM", "gemmary:corundum_armor", 30, new int[] {4, 6, 5, 3}, 30);
	public static ArmorMaterial RUBY = EnumHelper.addArmorMaterial("RUBY", "gemmary:ruby_armor", 30, new int[] {4, 6, 5, 3}, 30);
	public static ArmorMaterial SAPPHIRE = EnumHelper.addArmorMaterial("SAPPHIRE", "gemmary:sapphire_armor", 30, new int[] {4, 6, 5, 3}, 30);
	public static ArmorMaterial TALC = EnumHelper.addArmorMaterial("TALC", "gemmary:talc_armor", 4, new int[] {0, 1, 0, 0}, 30);
	public static ArmorMaterial TANZANITE = EnumHelper.addArmorMaterial("TANZANITE", "gemmary:tanzanite_armor", 22, new int[] {5, 7, 6, 4}, 30);
	public static ArmorMaterial TOPAZ = EnumHelper.addArmorMaterial("TOPAZ", "gemmary:topaz_armor", 26, new int[] {6, 8, 7, 5}, 30);
	public static ArmorMaterial TURQUOISE = EnumHelper.addArmorMaterial("TURQUOISE", "gemmary:turquoise_armor", 17, new int[] {4, 6, 5, 2}, 30);
//Armor Objects
	//Amethyst
	public static Item amethystHelmet;
	public static Item amethystChestplate;
	public static Item amethystLeggings;
	public static Item amethystBoots;
	//Corundum
	public static Item corundumHelmet;
	public static Item corundumChestplate;
	public static Item corundumLeggings;
	public static Item corundumBoots;
	//Ruby
	public static Item rubyHelmet;
	public static Item rubyChestplate;
	public static Item rubyLeggings;
	public static Item rubyBoots;
	//Sapphire
	public static Item sapphireHelmet;
	public static Item sapphireChestplate;
	public static Item sapphireLeggings;
	public static Item sapphireBoots;
	//Talc
	public static Item talcHelmet;
	public static Item talcChestplate;
	public static Item talcLeggings;
	public static Item talcBoots;
	//Tanzanite
	public static Item tanzaniteHelmet;
	public static Item tanzaniteChestplate;
	public static Item tanzaniteLeggings;
	public static Item tanzaniteBoots;
	//Topaz
	public static Item topazHelmet;
	public static Item topazChestplate;
	public static Item topazLeggings;
	public static Item topazBoots;
	//Turquoise
	public static Item turquoiseHelmet;
	public static Item turquoiseChestplate;
	public static Item turquoiseLeggings;
	public static Item turquoiseBoots;
	
//Armor Creator
	public static void createArmor()
	{
	//Amethyst
		GameRegistry.registerItem(amethystHelmet = new Armor("amethyst_helmet", AMETHYST, 1, 0), "amethyst_helmet");
		GameRegistry.registerItem(amethystChestplate = new Armor("amethyst_chestplate", AMETHYST, 1, 1), "amethyst_chestplate");
		GameRegistry.registerItem(amethystLeggings = new Armor("amethyst_leggings", AMETHYST, 2, 2), "amethyst_leggings");
		GameRegistry.registerItem(amethystBoots = new Armor("amethyst_boots", AMETHYST, 1, 3), "amethyst_boots");
	//Corundum
		GameRegistry.registerItem(corundumHelmet = new Armor("corundum_helmet", CORUNDUM, 1, 0), "corundum_helmet");
		GameRegistry.registerItem(corundumChestplate = new Armor("corundum_chestplate", CORUNDUM, 1, 1), "corundum_chestplate");
		GameRegistry.registerItem(corundumLeggings = new Armor("corundum_leggings", CORUNDUM, 2, 2), "corundum_leggings");
		GameRegistry.registerItem(corundumBoots = new Armor("corundum_boots", CORUNDUM, 1, 3), "corundum_boots");
	//Ruby
		GameRegistry.registerItem(rubyHelmet = new Armor("ruby_helmet", RUBY, 1, 0), "ruby_helmet");
		GameRegistry.registerItem(rubyChestplate = new Armor("ruby_chestplate", RUBY, 1, 1), "ruby_chestplate");
		GameRegistry.registerItem(rubyLeggings = new Armor("ruby_leggings", RUBY, 2, 2), "ruby_leggings");
		GameRegistry.registerItem(rubyBoots = new Armor("ruby_boots", RUBY, 1, 3), "ruby_boots");
	//Sapphire
		GameRegistry.registerItem(sapphireHelmet = new Armor("sapphire_helmet", SAPPHIRE, 1, 0), "sapphire_helmet");
		GameRegistry.registerItem(sapphireChestplate = new Armor("sapphire_chestplate", SAPPHIRE, 1, 1), "sapphire_chestplate");
		GameRegistry.registerItem(sapphireLeggings = new Armor("sapphire_leggings", SAPPHIRE, 2, 2), "sapphire_leggings");
		GameRegistry.registerItem(sapphireBoots = new Armor("sapphire_boots", SAPPHIRE, 1, 3), "sapphire_boots");
	//Talc
		GameRegistry.registerItem(talcHelmet = new Armor("talc_helmet", TALC, 1, 0), "talc_helmet");
		GameRegistry.registerItem(talcChestplate = new Armor("talc_chestplate", TALC, 1, 1), "talc_chestplate");
		GameRegistry.registerItem(talcLeggings = new Armor("talc_leggings", TALC, 2, 2), "talc_leggings");
		GameRegistry.registerItem(talcBoots = new Armor("talc_boots", TALC, 1, 3), "talc_boots");
	//Tanzanite
		GameRegistry.registerItem(tanzaniteHelmet = new Armor("tanzanite_helmet", TANZANITE, 1, 0), "tanzanite_helmet");
		GameRegistry.registerItem(tanzaniteChestplate = new Armor("tanzanite_chestplate", TANZANITE, 1, 1), "tanzanite_chestplate");
		GameRegistry.registerItem(tanzaniteLeggings = new Armor("tanzanite_leggings", TANZANITE, 2, 2), "tanzanite_leggings");
		GameRegistry.registerItem(tanzaniteBoots = new Armor("tanzanite_boots", TANZANITE, 1, 3), "tanzanite_boots");
	//Topaz
		GameRegistry.registerItem(topazHelmet = new Armor("topaz_helmet", TOPAZ, 1, 0), "topaz_helmet");
		GameRegistry.registerItem(topazChestplate = new Armor("topaz_chestplate", TOPAZ, 1, 1), "topaz_chestplate");
		GameRegistry.registerItem(topazLeggings = new Armor("topaz_leggings", TOPAZ, 2, 2), "topaz_leggings");
		GameRegistry.registerItem(topazBoots = new Armor("topaz_boots", TOPAZ, 1, 3), "topaz_boots");
	//Turquoise
		GameRegistry.registerItem(turquoiseHelmet = new Armor("turquoise_helmet", TURQUOISE, 1, 0), "turquoise_helmet");
		GameRegistry.registerItem(turquoiseChestplate = new Armor("turquoise_chestplate", TURQUOISE, 1, 1), "turquoise_chestplate");
		GameRegistry.registerItem(turquoiseLeggings = new Armor("turquoise_leggings", TURQUOISE, 2, 2), "turquoise_leggings");
		GameRegistry.registerItem(turquoiseBoots = new Armor("turquoise_boots", TURQUOISE, 1, 3), "turquoise_boots");
	}
}
