package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.fluid.GFluids;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;

public final class RenderBlocks 
{
	static String m_id = Gemmary.MODID;
	public static void rBR()
	{
	//Gem Blocks
		reg(GBlocks.amethyst);
		reg(GBlocks.corundum);
		reg(GBlocks.ruby);
		reg(GBlocks.sapphire);
		reg(GBlocks.talc);
		reg(GBlocks.tanzanite);
		reg(GBlocks.topaz);
		reg(GBlocks.turquoise);
		
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
		
	//Tile Entity Blocks
		reg(GBlocks.compressor);
		reg(GBlocks.superCompressor);
		reg(GBlocks.ahtv);
		
	//Fluids
		ModelLoader.setCustomStateMapper(GFluids.amethystBlock, (new StateMap.Builder()).ignore(BlockFluidBase.LEVEL).build());
		reg(GFluids.amethystBlock);
	
	//Other Blocks
		reg(GBlocks.diamondTNT);
		reg(GBlocks.whiteChalk);
		reg(GBlocks.atomGatherer);
		reg(GBlocks.autoAtomGatherer);
	}
	
	public static void reg(Block b)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(m_id + ":" + b.getUnlocalizedName().substring(5), "inventory"));
	}
}
