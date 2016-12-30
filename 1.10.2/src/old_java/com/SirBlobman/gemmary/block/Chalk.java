package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Chalk extends Block
{
	private final AxisAlignedBB BB = new AxisAlignedBB(0.1875, 0.0, 0.1875, 0.8125, 0.0625, 0.8125);
	
	Chalk(String color)
	{
		super(Material.CIRCUITS);
		setHarvestLevel("pickaxe", 1);
		setHardness(10.0F);
		setResistance(15.0F);
		String name = color + "_chalk";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.ITEMS);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
	@Override
	public boolean isOpaqueCube(IBlockState ibs) {return false;}
	@Override
	public boolean isFullCube(IBlockState ibs) {return false;}
	@Override
	public boolean canPlaceBlockAt(World w, BlockPos bp) {return !w.isAirBlock(bp.down());}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState ibs, IBlockAccess iba, BlockPos bp, EnumFacing ef)
	{
		boolean b1 = ef == EnumFacing.UP;
		boolean b2 = true;
		return b1 ? b2 : super.shouldSideBeRendered(ibs, iba, bp, ef);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState ibs, IBlockAccess iba, BlockPos bp) {return BB;}
}