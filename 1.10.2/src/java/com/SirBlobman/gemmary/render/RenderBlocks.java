package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public final class RenderBlocks 
{
	public static final RenderBlocks INSTANCE = new RenderBlocks();
	private RenderBlocks() {}
	
	static String mod = Gemmary.MODID;
	public void rBR()
	{
	//Gem Blocks
		reg(GBlocks.amethyst);
		reg(GBlocks.corundum);
		reg(GBlocks.ruby);
		reg(GBlocks.sapphire);
		reg(GBlocks.talc);
		reg(GBlocks.tanzanite);
		reg(GBlocks.topaz);
		
	//Ore Blocks
		reg(GBlocks.amethystOre);
		reg(GBlocks.corundumOre);
		reg(GBlocks.rubyOre);
		reg(GBlocks.sapphireOre);
		reg(GBlocks.talcOre);
		reg(GBlocks.tanzaniteOre);
		reg(GBlocks.topazOre);
		reg(GBlocks.turquoiseOre);
		
	//Crystals
		reg(GBlocks.quartzCrystals);
		reg(GBlocks.diamondCrystals);
		
	//Tile Entity Blocks
		reg(GBlocks.compressor);
		reg(GBlocks.superCompressor);
		reg(GBlocks.ahtv);
	
	//Other Blocks
		reg(GBlocks.diamondTNT);
		reg(GBlocks.whiteChalk);
		reg(GBlocks.atomGatherer);
		reg(GBlocks.autoAtomGatherer);
	}
	
	public static void reg(Block b)
	{
		Item i = Item.getItemFromBlock(b);
		ModelResourceLocation mrl = new ModelResourceLocation(b.getRegistryName(), "inventory");
		if(Gemmary.altTextures)
		{
			String[] name = b.getRegistryName().toString().split(":");
			String block = name[1];
			mrl = new ModelResourceLocation(Gemmary.MODID + ":16x/" + block, "inventory");
		}
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
}