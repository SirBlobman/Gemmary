package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.SirBlobman.gemmary.item.armor.Armor;
import com.SirBlobman.gemmary.item.armor.Boots;
import com.SirBlobman.gemmary.item.armor.Chestplate;
import com.SirBlobman.gemmary.item.armor.Helmet;
import com.SirBlobman.gemmary.item.armor.Leggings;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public final class GItems
{
/*
 * Armor Materials (name, durability, protection)
 * Durability = 3.3 * Mohs
 * Protection = Mohs
 */
	/**private static ArmorMaterial AMETHYST = armor("Amethyst", 30, new int[] {4,6,5,3});
	private static ArmorMaterial RUBY = armor("Ruby", 30, new int[] {4,6,5,3});
	private static ArmorMaterial SAPPHIRE = armor("Sapphire", 30, new int[] {4,6,5,3});
	private static ArmorMaterial TALC = armor("Talc", 4, new int[] {0,1,0,0});
	private static ArmorMaterial TANZANITE = armor("Tanzanite", 22, new int[] {5,7,6,4});
	private static ArmorMaterial TOPAZ = armor("Topaz", 26, new int[] {6,8,7,5});
	private static ArmorMaterial TURQUOISE = armor("Turquoise", 17, new int[] {4,6,5,2});**/
	
//Gems
	public static Gem amethyst = new Gem("amethyst", 7.0D);
	/**public static Gem corundum = new Gem("corundum", 9.0D);
	public static Gem ruby = new Gem("ruby", 9.0D);
	public static Gem sapphire = new Gem("sapphire", 9.0D);
	public static Gem talc = new Gem("talc", 1.0D);
	public static Gem tanzanite = new Gem("tanzanite", 6.5D);
	public static Gem topaz = new Gem("topaz", 8.0D);
	public static Gem turquoise = new Gem("turquoise", 5.0D);
	
//Gem Parts
	public static GemPart diamond = new GemPart("diamond");
	public static GemPart emerald = new GemPart("emerald");
	
//Atoms
	public static Atom aluminum = new Atom("aluminum");
	public static Atom beryllium = new Atom("beryllium");
	public static Atom carbon = new Atom("carbon");
	public static Atom chromium = new Atom("chromium");
	public static Atom hydrogen = new Atom("hydrogen");
	public static Atom iron = new Atom("iron");
	public static Atom oxygen = new Atom("oxygen");
	public static Atom titanium = new Atom("titanium");
	
//Alloys
	public static Alloy cCr = new Alloy(corundum, chromium, ruby);
	public static Alloy cFe = new Alloy(corundum, iron, sapphire);
	
//Armor
	public static Armor aHelmet = new Helmet("amethyst", AMETHYST, amethyst);
	public static Armor aChestplate = new Chestplate("amethyst", AMETHYST, amethyst);
	public static Armor aLeggings = new Leggings("amethyst", AMETHYST, amethyst);
	public static Armor aBoots = new Boots("amethyst", AMETHYST, amethyst);

	public static Armor rHelmet = new Helmet("ruby", RUBY, ruby);
	public static Armor rChestplate = new Chestplate("ruby", RUBY, ruby);
	public static Armor rLeggings = new Leggings("ruby", RUBY, ruby);
	public static Armor rBoots = new Boots("ruby", RUBY, ruby);
	
	public static Armor sHelmet = new Helmet("sapphire", SAPPHIRE, sapphire);
	public static Armor sChestplate = new Chestplate("sapphire", SAPPHIRE, sapphire);
	public static Armor sLeggings = new Leggings("sapphire", SAPPHIRE, sapphire);
	public static Armor sBoots = new Boots("sapphire", SAPPHIRE, sapphire);
	
	public static Armor talHelmet = new Helmet("talc", TALC, talc);
	public static Armor talChestplate = new Chestplate("talc", TALC, talc);
	public static Armor talLeggings = new Leggings("talc", TALC, talc);
	public static Armor talBoots = new Boots("talc", TALC, talc);
	
	public static Armor tanHelmet = new Helmet("tanzanite", TANZANITE, tanzanite);
	public static Armor tanChestplate = new Chestplate("tanzanite", TANZANITE, tanzanite);
	public static Armor tanLeggings = new Leggings("tanzanite", TANZANITE, tanzanite);
	public static Armor tanBoots = new Boots("tanzanite", TANZANITE, tanzanite);
	
	public static Armor topHelmet = new Helmet("topaz", TOPAZ, topaz);
	public static Armor topChestplate = new Chestplate("topaz", TOPAZ, topaz);
	public static Armor topLeggings = new Leggings("topaz", TOPAZ, topaz);
	public static Armor topBoots = new Boots("topaz", TOPAZ, topaz);
	
	public static Armor turHelmet = new Helmet("turquoise", TURQUOISE, turquoise);
	public static Armor turChestplate = new Chestplate("turquoise", TURQUOISE, turquoise);
	public static Armor turLeggings = new Leggings("turquoise", TURQUOISE, turquoise);
	public static Armor turBoots = new Boots("turquoise", TURQUOISE, turquoise);
	
//Other
	public static Item hWC = new Item().setRegistryName("hwc").setUnlocalizedName("hwc").setCreativeTab(GemmaryTabs.ITEMS);**/
	public static Cloth cloth = new Cloth();
	/**public static RecipeBook recipeBook = new RecipeBook();
	public static Fuel fuel = new Fuel();**/
	
	public static final void gems()
	{
		r(cloth);
		rD(amethyst);}
		/**rD(corundum);
		rD(ruby);
		rD(sapphire);
		rD(talc);
		rD(tanzanite);
		rD(topaz);
		rD(turquoise);
	}
	
	public static final void gemParts()
	{
		r(diamond);
		r(emerald);
	}
	
	public static final void atoms()
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
	
	public static final void alloys()
	{
		r(cCr);
		r(cFe);
	}
	
	public static final void items()
	{
		r(hWC);
		r(recipeBook);
		r(fuel);
	}
	
	public static final void armor()
	{
		rA(aHelmet);
		rA(aChestplate);
		rA(aLeggings);
		rA(aBoots);
		
		rA(rHelmet);
		rA(rChestplate);
		rA(rLeggings);
		rA(rBoots);
		
		rA(sHelmet);
		rA(sChestplate);
		rA(sLeggings);
		rA(sBoots);
		
		rA(talHelmet);
		rA(talChestplate);
		rA(talLeggings);
		rA(talBoots);
		
		rA(tanHelmet);
		rA(tanChestplate);
		rA(tanLeggings);
		rA(tanBoots);
		
		rA(topHelmet);
		rA(topChestplate);
		rA(topLeggings);
		rA(topBoots);
		
		rA(turHelmet);
		rA(turChestplate);
		rA(turLeggings);
		rA(turBoots);
	}
	
	private static ArmorMaterial armor(String name, int durability, int[] protection)
	{
		return EnumHelper.addArmorMaterial(name, "gemmary:" + name.toLowerCase(), durability, protection, 30, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
	}**/
	
	private static void r(Item i) {GameRegistry.register(i);}
	private static void rD(Gem g)
	{
		r(g);
		ItemStack result = new ItemStack(g, 1, 0);
		ItemStack dusty = new ItemStack(g, 1, 1);
		ItemStack wipe = new ItemStack(cloth, 1, OreDictionary.WILDCARD_VALUE);
		ShapelessOreRecipe sr = new ShapelessOreRecipe(result, dusty, wipe);
		GameRegistry.addRecipe(sr);
	}
	/**private static void rA(Armor a)
	{
		r(a);
		ItemStack i = a.repair;
		ItemStack armor = new ItemStack(a);
		Object[] helmet = new Object[] {"###", "# #", '#', i};
		Object[] chestplate = new Object[] {"# #", "###", "###", '#', i};
		Object[] leggings = new Object[] {"###", "# #", "# #", '#', i};
		Object[] boots = new Object[] {"# #", "# #", '#', i};
		switch(a.armorType)
		{
		case HEAD:
			GameRegistry.addShapedRecipe(armor, helmet);
			break;
		case CHEST:
			GameRegistry.addShapedRecipe(armor, chestplate);
			break;
		case LEGS:
			GameRegistry.addShapedRecipe(armor, leggings);
			break;
		case FEET:
			GameRegistry.addShapedRecipe(armor, boots);
			break;
		default:
			break;
		}
	}**/
}