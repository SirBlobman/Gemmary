package com.SirBlobman.gemmary.block;

import java.util.Random;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

@SuppressWarnings("unused")
public class OreBlockFalling extends BlockFalling
{
	private Item drop;
	private int meta;
	private int least;
	private int most;
	
	protected OreBlockFalling(String gem, Item drop, int meta, int least, int most, String harvestTool, int harvestLvl, float hardness)
	{
		super();
		this.drop = drop;
		this.meta = meta;
		this.least = least;
		this.most = most;
		setHarvestLevel(harvestTool, harvestLvl);
		setHardness(hardness);
		setResistance(15.0F);
		setSoundType(SoundType.SAND);
		setUnlocalizedName(gem + "_ore");
		setRegistryName(gem + "_ore");
		setCreativeTab(GemmaryTabs.Blocks);
	}
	
	@Override
	public Item getItemDropped(IBlockState ibs, Random r, int fortune) {return drop;}
	
	@Override
	public int quantityDropped(IBlockState ibs, int fortune, Random r)
	{
		if(least >= most) {return least;}
		
		return least + r.nextInt(most - least + fortune + 1);
	}
}