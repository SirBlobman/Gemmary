package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.fluid.GFluids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;

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
		reg(GBlocks.diamondCrystals);
		
	//Tile Entity Blocks
		reg(GBlocks.compressor);
		reg(GBlocks.superCompressor);
		reg(GBlocks.ahtv);
		
	//Fluids
		for(IFluidBlock ifb : GFluids.FLUID_BLOCKS) regFluid(ifb);
	
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
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
	
	public void regFluid(IFluidBlock b)
	{
		final Item i = Item.getItemFromBlock((Block) b);
		assert i != null;
		
		ModelBakery.registerItemVariants(i);
		
		ModelResourceLocation mrl = new ModelResourceLocation(mod + ":fluid", b.getFluid().getName());
		ModelLoader.setCustomMeshDefinition(i, MeshDefinitionFix.create(stack -> mrl));
		ModelLoader.setCustomStateMapper((Block) b, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState ibs)
			{
				return mrl;
			}
		});
	}
}