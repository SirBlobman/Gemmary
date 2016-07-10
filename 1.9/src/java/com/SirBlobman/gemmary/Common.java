package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.forge.GDictionary;
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
		GItems.createItems();
		GItems.createArmor();
		
		GBlocks.createGemBlocks();
		GBlocks.createOres();
		GBlocks.createCrystals();
		GBlocks.createOtherBlocks();
		
		GTiles.createTiles();
	}
	
	public void init(FMLInitializationEvent e)
	{
		GDictionary.defineItems();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.instance, new GuiHandler());
		
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
		
		GRecipes.createCraftingRecipes();
		GRecipes.createCompressingRecipes();
		GRecipes.createHydratingRecipes();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
}
