package com.SirBlobman.gemmary.crafting;

import com.SirBlobman.gemmary.blocks.RandomBlocks;
import com.SirBlobman.gemmary.items.GemmaryArmor;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.SirBlobman.gemmary.items.GemmaryGems;
import com.SirBlobman.gemmary.items.GemmaryRandomItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Crafting 
{
	public static void startCraftingRecipes()
	{
	//Shaped
		//Machines
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.carbonCompressor), new Object[] {" E ", "PFP", "RDR", 'E', RandomBlocks.SmallEngine, 'P', Blocks.piston, 'F', Blocks.furnace, 'R', Blocks.redstone_torch, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.carbonCompressor), new Object[] {" E ", "PFP", "RDR", 'E', RandomBlocks.SmallEngine, 'P', Blocks.sticky_piston, 'F', Blocks.furnace, 'R', Blocks.redstone_torch, 'D', Items.diamond});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.SmallEngine), new Object[] {"RPR", "P P", "RPR", 'P', Blocks.piston, 'R', Items.repeater});
		//Gems
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object [] {"DDD", "DDD", "DDD", 'D', GemmaryGemParts.DiamondPart});
		GameRegistry.addRecipe(new ItemStack(Items.emerald), new Object [] {"EEE", "EEE", "EEE", 'E', GemmaryGemParts.EmeraldPart});
		//Block Recipes
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.amethystBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.rubyBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.sapphireBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.talcBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.tanzaniteBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.topazBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(RandomBlocks.turquoiseBlock), new Object[] {"AAA", "AAA", "AAA", 'A', GemmaryGems.turquoise});
		//Armor
		//Helmets
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.amethystHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.corundumHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.corundum});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.rubyHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.sapphireHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.talcHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.tanzaniteHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.topazHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.turquoiseHelmet), new Object[] {"AAA", "A A", 'A', GemmaryGems.turquoise});
		//Chestplates
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.amethystChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.corundumChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.corundum});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.rubyChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.sapphireChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.talcChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.tanzaniteChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.topazChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.turquoiseChestplate), new Object[] {"A A", "AAA", "AAA", 'A', GemmaryGems.turquoise});
		//Leggings
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.amethystLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.corundumLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.corundum});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.rubyLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.sapphireLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.talcLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.tanzaniteLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.topazLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.turquoiseLeggings), new Object[] {"AAA", "A A", "A A", 'A', GemmaryGems.turquoise});
		//Boots
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.amethystBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.amethyst});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.corundumBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.corundum});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.rubyBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.ruby});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.sapphireBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.sapphire});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.talcBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.talc});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.tanzaniteBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.tanzanite});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.topazBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.topaz});
		GameRegistry.addRecipe(new ItemStack(GemmaryArmor.turquoiseBoots), new Object[] {"A A", "A A", 'A', GemmaryGems.turquoise});
		//Other
		GameRegistry.addRecipe(new ItemStack(GemmaryRandomItems.Cloth), new Object[] {"W W", " W ", "W W", 'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(GemmaryRandomItems.HeatedWaterContainer), new Object[] {" B", " W", 'W', Items.water_bucket, 'B', Items.blaze_powder});
	//Shapeless
		//Chalk = 3 Talc
		GameRegistry.addShapelessRecipe(new ItemStack(RandomBlocks.chalk, 32, 0), new Object[] {GemmaryGems.talc, GemmaryGems.talc, GemmaryGems.talc});
		//Diamond TnT = Diamond Block + TnT
		GameRegistry.addShapelessRecipe(new ItemStack(RandomBlocks.diamondTnT), new Object[] {Blocks.diamond_block, Blocks.tnt});
		//Gem Polishing
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.amethyst, 1, 0), new Object[] {new ItemStack(GemmaryGems.amethyst, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.corundum, 1, 0), new Object[] {new ItemStack(GemmaryGems.corundum, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.ruby, 1, 0), new Object[] {new ItemStack(GemmaryGems.ruby, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.sapphire, 1, 0), new Object[] {new ItemStack(GemmaryGems.sapphire, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.talc, 1, 0), new Object[] {new ItemStack(GemmaryGems.talc, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.tanzanite, 1, 0), new Object[] {new ItemStack(GemmaryGems.tanzanite, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.topaz, 1, 0), new Object[] {new ItemStack(GemmaryGems.topaz, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryGems.turquoise, 1, 0), new Object[] {new ItemStack(GemmaryGems.turquoise, 1, 1), new ItemStack(GemmaryRandomItems.Cloth, 1, OreDictionary.WILDCARD_VALUE)});
		//Recipe Book
		GameRegistry.addShapelessRecipe(new ItemStack(GemmaryRandomItems.RecipeBook), new Object[] {new ItemStack(GemmaryGems.amethyst, 1, 1)});
	//Furnace
	}
}