package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Chalk extends Block
{
	protected static final AxisAlignedBB[] ChalkAABB = new AxisAlignedBB[] {new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D)};
	
	protected Chalk(String color)
	{
		super(Material.CIRCUITS);
		setHarvestLevel("pickaxe", 1);
		setHardness(10.0F);
		setResistance(15.0F);
		setUnlocalizedName(color + "_chalk");
		setRegistryName(color + "_chalk");
		setCreativeTab(GemmaryTabs.Items);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState ibs)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState ibs)
	{
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(World w, BlockPos pos)
	{
		return super.canPlaceBlockAt(w, pos) && this.canBlockStay(w, pos);
	}
	
	private boolean canBlockStay(World w, BlockPos pos)
	{
		return !w.isAirBlock(pos.down());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState ibs, IBlockAccess w, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.UP ? true : super.shouldSideBeRendered(ibs, w, pos, side);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState ibs, IBlockAccess source, BlockPos pos)
    {
        return ChalkAABB[0];
    }
}