package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.block.GBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public final class RenderBlock
{
	public static void blocks()
	{
	/**	r(GBlocks.amethyst);
		r(GBlocks.corundum);
		r(GBlocks.ruby);
		r(GBlocks.sapphire);
		r(GBlocks.talc);
		r(GBlocks.tanzanite);
		r(GBlocks.topaz);
		r(GBlocks.turquoise);
		
		r(GBlocks.ameOre);
		r(GBlocks.corOre);
		r(GBlocks.rubOre);
		r(GBlocks.sapOre);
		r(GBlocks.talOre);
		r(GBlocks.tanOre);
		r(GBlocks.topOre);
		r(GBlocks.turOre);
		
		r(GBlocks.quartz);
		r(GBlocks.diamond);
		
		r(GBlocks.compressor);
		r(GBlocks.superCompressor);
		r(GBlocks.ahtv);
		r(GBlocks.diamond);
		r(GBlocks.whiteChalk);
		r(GBlocks.atomGatherer);
		r(GBlocks.autoAtomGatherer);**/
	}
	
	private static void r(Block b)
	{
		Item i = Item.getItemFromBlock(b);
		ResourceLocation rl = b.getRegistryName();
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
}
