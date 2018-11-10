package com.SirBlobman.gemmary.render.blocks;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.blocks.RandomBlocks;
import com.SirBlobman.gemmary.ores.GemmaryOres;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class RenderBlocks 
{
		public static void registerBlockRenderer()
	{
		reg(GemmaryOres.amethystOre);
		reg(GemmaryOres.quartzCrystal);
		reg(GemmaryOres.rubyOre);
		reg(GemmaryOres.sapphireOre);
		reg(GemmaryOres.talcOre);
		reg(GemmaryOres.tanzaniteOre);
		reg(GemmaryOres.topazOre);
		reg(GemmaryOres.turquoiseOre);
		reg(RandomBlocks.chalk);
		reg(RandomBlocks.diamondTnT);
		reg(RandomBlocks.atomCombiner);
		reg(RandomBlocks.amethystBlock);
		reg(RandomBlocks.corundumBlock);
		reg(RandomBlocks.rubyBlock);
		reg(RandomBlocks.sapphireBlock);
		reg(RandomBlocks.talcBlock);
		reg(RandomBlocks.tanzaniteBlock);
		reg(RandomBlocks.topazBlock);
		reg(RandomBlocks.turquoiseBlock);
		reg(RandomBlocks.carbonCompressor);
		reg(RandomBlocks.SmallEngine);
		reg(RandomBlocks.AHTV);
		
	}
	public static String modid = Gemmary.MODID;
	
	public static void reg(Block block) 
	{
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
