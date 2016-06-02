package com.SirBlobman.gemmary.blocks;

import com.SirBlobman.gemmary.ores.GemmaryOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GemBlock extends Block 
{

	public GemBlock(String unlocalizedName, float hardness) 
	{
		super(Material.iron);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryOres.BlocksOres);
		this.setHardness(hardness);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
        return this == RandomBlocks.amethystBlock || this == RandomBlocks.rubyBlock || this == RandomBlocks.sapphireBlock || this == RandomBlocks.talcBlock || this == RandomBlocks.tanzaniteBlock || this == RandomBlocks.topazBlock || this == RandomBlocks.turquoiseBlock;
    }

}
