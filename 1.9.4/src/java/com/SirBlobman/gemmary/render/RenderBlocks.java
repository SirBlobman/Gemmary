package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

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
		reg(GBlocks.amethyst_ore);
		reg(GBlocks.corundum_ore);
		reg(GBlocks.ruby_ore);
		reg(GBlocks.sapphire_ore);
		reg(GBlocks.talc_ore);
		reg(GBlocks.tanzanite_ore);
		reg(GBlocks.topaz_ore);
		reg(GBlocks.turquoise_ore);
		
	//Crystals
		reg(GBlocks.quartzCrystals);
		
	//Tile Entity Blocks
		reg(GBlocks.compressor);
		reg(GBlocks.superCompressor);
		reg(GBlocks.ahtv);
	
	//Other Blocks
		reg(GBlocks.diamondTNT);
		reg(GBlocks.whiteChalk);
	}
	
	public static void reg(Block b)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(m_id + ":" + b.getUnlocalizedName().substring(5), "inventory"));
	}
}
