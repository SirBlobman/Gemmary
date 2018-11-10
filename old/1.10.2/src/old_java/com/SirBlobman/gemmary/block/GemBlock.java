package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GemBlock extends Block
{
	private Item gem;
	public GemBlock(Item gem, float mohs)
	{
		super(Material.IRON);
		this.gem = gem;
		String name = gem.getUnlocalizedName().substring(5);
		setUnlocalizedName(name + "_block");
		setRegistryName(name + "_block");
		setCreativeTab(GemmaryTabs.BLOCKS);
		setHardness(mohs);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess iba, BlockPos bp, BlockPos beacon) {return true;}
	
	public Item getGem() {return gem;}
}