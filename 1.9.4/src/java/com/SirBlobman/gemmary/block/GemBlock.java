package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GemBlock extends Block 
{
	public GemBlock(String gem, float hardness)
	{
		super(Material.IRON);
		setUnlocalizedName(gem + "_block");
		setRegistryName(gem + "_block");
		setCreativeTab(GemmaryTabs.Blocks);
		setHardness(hardness);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) 
	{
		return true;
	}
}
