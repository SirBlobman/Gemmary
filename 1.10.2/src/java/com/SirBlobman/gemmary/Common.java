package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.SirBlobman.gemmary.entity.GEntities;
import com.SirBlobman.gemmary.fluid.GFluids;
import com.SirBlobman.gemmary.fluid.SwimFog;
import com.SirBlobman.gemmary.forge.GOreDict;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.item.FuelHandler;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.recipe.GRecipes;
import com.SirBlobman.gemmary.tile.GTiles;
import com.SirBlobman.gemmary.world.OreGeneration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Common 
{
	public void preInit(FMLPreInitializationEvent e)
	{
	//Items
		GItems.createGems();
		GItems.createGemParts();
		GItems.createElements();
		GItems.createAlloys();
		GItems.createArmor();
		GItems.createItems();
		
	//Blocks
		GBlocks.createGemBlocks();
		GBlocks.createOres();
		GBlocks.createCrystals();
		GBlocks.createOtherBlocks();
		
	//Fluids
		GFluids.createFluids();
		
	//Tile Entities
		GTiles.createTiles();
		
	//Creative Tab Editing
		for(Item i : ForgeRegistries.ITEMS)
		{
			if(i instanceof ItemBucket || i instanceof UniversalBucket) i.setCreativeTab(GemmaryTabs.Buckets);
		}
		CreativeTabs.MISC.getIconItemStack().setItem(Items.MILK_BUCKET);
		
	//Events
		GUtil.regEvents(new SwimFog());
	}
	
	public void init(FMLInitializationEvent e)
	{
	//Ore Dictionary
		GOreDict.createOreDictionaryEntries();
		
	//GUIs
		NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.instance, new GuiHandler());
		
	//World Generation
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
		
	//Crafting Recipes
		GRecipes.createCraftingRecipes();
		GRecipes.createCompressingRecipes();
		GRecipes.createHydratingRecipes();
		
	//Entities
		GEntities.createEntities();
		
	//Fuel
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
	//Nothing here :P
	}
}
