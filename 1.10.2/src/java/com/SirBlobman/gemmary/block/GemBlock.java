package com.SirBlobman.gemmary.block;

import org.apache.commons.lang3.StringUtils;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GemBlock extends Block 
{
	private String gem;
	public GemBlock(String g, float hardness)
	{
		super(Material.IRON);
		this.gem = g;
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
	
	public String getOreDictGem()
	{
		return "gem" + StringUtils.capitalize(gem);
	}
}
