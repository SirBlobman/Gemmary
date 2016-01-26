package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.blocks.RandomBlocks;
import com.SirBlobman.gemmary.crafting.Crafting;
import com.SirBlobman.gemmary.items.GemmaryArmor;
import com.SirBlobman.gemmary.items.GemmaryElements;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.SirBlobman.gemmary.items.GemmaryGems;
import com.SirBlobman.gemmary.items.GemmaryRandomItems;
import com.SirBlobman.gemmary.network.GuiHandler;
import com.SirBlobman.gemmary.ores.GemmaryOres;
import com.SirBlobman.gemmary.tiles.Tiles;
import com.SirBlobman.gemmary.world.OreGeneration;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemmaryCommon 
{
	public void preInit(FMLPreInitializationEvent e) 
	{
		GemmaryGems.createGems();
		GemmaryElements.createChemicalElements();
		GemmaryGemParts.createGemParts();
		GemmaryArmor.createArmor();
		GemmaryRandomItems.createRandomItems();
		GemmaryOres.createOres();
		RandomBlocks.createRandomBlocks();
		Tiles.createNewTiles();
    }

    public void init(FMLInitializationEvent e) 
    {
    	GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
    	NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.instance, new GuiHandler());
    	Crafting.startCraftingRecipes();
    }

    public void postInit(FMLPostInitializationEvent e) 
    {
    	
    }
}
