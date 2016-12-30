package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.entity.GEntities;
import com.SirBlobman.gemmary.fluid.GFluids;
import com.SirBlobman.gemmary.forge.OreDict;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.item.Fuel;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.recipe.GRecipes;
import com.SirBlobman.gemmary.tile.GTiles;
import com.SirBlobman.gemmary.world.OreGen;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Common
{
	public void pre(FMLPreInitializationEvent e)
	{
		/**GItems.gemParts();
		GItems.atoms();
		GItems.alloys();
		GItems.armor();
		GItems.items();**/
		
		/**GBlocks.gemBlocks();
		GBlocks.ores();
		GBlocks.crystals();
		GBlocks.others();**/
		
		//GFluids.fluids();
		
		//GTiles.tiles();
	}
	
	public void init(FMLInitializationEvent e)
	{
		//OreDict.ores();

		GItems.gems();
		NetworkRegistry nr = NetworkRegistry.INSTANCE;
		nr.registerGuiHandler(Gemmary.instance, new GuiHandler());
		
		//GameRegistry.registerWorldGenerator(new OreGen(), 0);
		
		//GRecipes.vanilla();
		//GRecipes.compressor();
		//GRecipes.hydrating();
		
		GEntities.entities();
		
		GameRegistry.registerFuelHandler(new Fuel());
	}
}