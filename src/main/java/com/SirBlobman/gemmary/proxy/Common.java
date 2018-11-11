package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GemmaryBlocks;
import com.SirBlobman.gemmary.constant.ModInfo;
import com.SirBlobman.gemmary.forge.GemmaryOreDictionary;
import com.SirBlobman.gemmary.item.GemmaryItems;
import com.SirBlobman.gemmary.recipe.GemmaryRecipes;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
	public void pre(FMLPreInitializationEvent e) {
		GameRegistry.registerTileEntity(TileEntityCompressor.class, new ResourceLocation(ModInfo.MODID, "compressor"));
		GameRegistry.registerTileEntity(TileEntityHydrothermalVein.class, new ResourceLocation(ModInfo.MODID, "hydrothermal_vein"));
	}
	
	public void init(FMLInitializationEvent e) {
	    NetworkRegistry.INSTANCE.registerGuiHandler(Gemmary.INSTANCE, new GemmaryGuiHandler());
	}
	
	public void post(FMLPostInitializationEvent e) {
		
	}
	
	public void registerItems(IForgeRegistry<Item> ifr) {
		GemmaryItems.registerAllItems(ifr);
		GemmaryBlocks.registerAllItems(ifr);
	}
	
	public void registerBlocks(IForgeRegistry<Block> ifr) {
		GemmaryBlocks.registerAllBlocks(ifr);
	}
	
	public void registerRecipes(IForgeRegistry<IRecipe> ifr) {
        GemmaryOreDictionary.registerAllItems();
        GemmaryOreDictionary.registerAllBlocks();
	    GemmaryRecipes.registerAllRecipes(ifr);
	}
}