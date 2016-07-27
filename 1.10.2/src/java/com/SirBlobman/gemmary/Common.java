package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.entity.GEntities;
import com.SirBlobman.gemmary.fluid.GFluids;
import com.SirBlobman.gemmary.forge.GOreDict;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.recipe.GRecipes;
import com.SirBlobman.gemmary.tile.GTiles;
import com.SirBlobman.gemmary.world.OreGeneration;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Common 
{
	public void preInit(FMLPreInitializationEvent e)
	{
		GItems.createGems();
		GItems.createGemParts();
		GItems.createElements();
		GItems.createAlloys();
		GItems.createArmor();
		GItems.createItems();
		
		GBlocks.createGemBlocks();
		GBlocks.createOres();
		GBlocks.createCrystals();
		GBlocks.createOtherBlocks();
		
		GFluids.registerFluids();
		GFluids.registerFluidBuckets();
		
		GTiles.createTiles();
	}
	
	public void init(FMLInitializationEvent e)
	{
		GOreDict.createOreDictionaryEntries();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.instance, new GuiHandler());
		
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
		
		GRecipes.createCraftingRecipes();
		GRecipes.createCompressingRecipes();
		GRecipes.createHydratingRecipes();
		
		GEntities.createEntities();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
}
