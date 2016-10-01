package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Class for registering recipes
 * <br/># = Air
 * @author SirBlobman
 */
public class GRecipes
{
	private static final ItemStack allWool = new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE);
	
	public static void vanilla()
	{
		Object[] compressor = new Object[] {"PPP", "PRP", "PPP", 'P', Blocks.PISTON, 'R', Items.REDSTONE};
		Object[] superCompressor = new Object[] {"CCC", "CSC", "CCC", 'C', GBlocks.compressor, 'S', Items.STRING};
		Object[] ahtv = new Object[] {"CcL", "#B#", "RRR", 'B', Items.BUCKET, 'C', Blocks.CRAFTING_TABLE, 'c', Blocks.CHEST, 'L', Items.LAVA_BUCKET, 'R', Blocks.REDSTONE_BLOCK};
		Object[] diamondTNT = new Object[] {"DDD", "DTD", "DDD", 'D', "blockDiamond", 'T', "tnt"};
		Object[] hwc = new Object[] {"#I#", "IWI", "ILI", 'I', "ingotIron", 'L', Items.LAVA_BUCKET, 'W', Items.WATER_BUCKET};
		Object[] diamondPart = new Object[] {"DDD", "DDD", "DDD", 'D', "nuggetDiamond"};
		Object[] emeraldPart = new Object[] {"EEE", "EEE", "EEE", 'E', "nuggetEmerald"};
		Object[] cloth = new Object[] {"WWW", "WWW", "WWW", allWool};
		Object[] recipeBook = new Object[] {GItems.amethyst};
		Object[] chalk = new Object[] {"gemTalc", "gemTalc", "gemTalc"};
		
		shaped(new ItemStack(GBlocks.compressor), compressor);
		shaped(new ItemStack(GBlocks.superCompressor), superCompressor);
		shaped(new ItemStack(GBlocks.ahtv), ahtv);
		shaped(new ItemStack(GBlocks.diamondTNT), diamondTNT);
		shaped(new ItemStack(GItems.hWC), hwc);
		shaped(new ItemStack(GItems.diamond), diamondPart);
		shaped(new ItemStack(GItems.emerald), emeraldPart);
		shaped(new ItemStack(GItems.cloth), cloth);
		shapeless(new ItemStack(GItems.recipeBook), recipeBook);
		shapeless(new ItemStack(GBlocks.whiteChalk), chalk);
	}
	
	public static void compressor()
	{
		compressor(new ItemStack(Blocks.COAL_BLOCK), new ItemStack(Items.DIAMOND), 100.0F);
		compressor(new ItemStack(GItems.carbon), new ItemStack(GItems.diamond), 11.11F);
		compressor(new ItemStack(GItems.cCr), new ItemStack(GItems.ruby), 100.0F);
		compressor(new ItemStack(GItems.cFe), new ItemStack(GItems.sapphire), 100.0F);
	}
	
	public static void hydrating()
	{
		hydro(new ItemStack(GItems.beryllium), new ItemStack(GItems.emerald), 100.0F);
	}
	
	private static void shapeless(ItemStack output, Object[] recipe)
	{
		try
		{
			ShapelessOreRecipe sor = new ShapelessOreRecipe(output, recipe);
			GameRegistry.addRecipe(sor);
		} catch(Exception ex)
		{
			GUtil.print("Error creating Shapeless Recipe: ");
			GUtil.print(recipe + " =\n" + output);
			GUtil.print("Reason: \n" + ex.getMessage());
		}
	}
	
	private static void shaped(ItemStack output, Object[] recipe)
	{
		try
		{
			ShapedOreRecipe sor = new ShapedOreRecipe(output, recipe);
			GameRegistry.addRecipe(sor);
		} catch(Exception ex)
		{
			GUtil.print("Error creating Recipe: ");
			GUtil.print(recipe + " =\n" + output);
			GUtil.print("Reason: \n" + ex.getMessage());
		}
	}
	
	private static void compressor(ItemStack input, ItemStack output, float xp)
	{
		CompressorRecipes cr = CompressorRecipes.instance();
		cr.addRecipe(input, output, xp);
	}
	
	private static void hydro(ItemStack input, ItemStack output, float xp)
	{
		HydroRecipes hr = HydroRecipes.instance();
		hr.addRecipe(input, output, xp);
	}
}