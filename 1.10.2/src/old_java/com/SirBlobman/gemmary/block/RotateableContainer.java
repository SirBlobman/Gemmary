package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class RotateableContainer extends BlockContainer
{
	private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);	
	public RotateableContainer(String name)
	{
		super(Material.ANVIL);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.BLOCKS);
	}
	
	@Override
	public void breakBlock(World w, BlockPos bp, IBlockState ibs)
	{
		TileEntity te = w.getTileEntity(bp);
		if(te instanceof IInventory) 
		{
			IInventory ii = (IInventory) te;
			InventoryHelper.dropInventoryItems(w, bp, ii);
		}
		super.breakBlock(w, bp, ibs);
	}
	
	@Override
	public void onBlockAdded(World w, BlockPos bp, IBlockState ibs) {setDefaultFacing(w, bp, ibs);}
	
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos bp, EnumFacing ef, float x, float y, float z, int i, EntityLivingBase elb)
	{
		IBlockState def = getDefaultState();
		EnumFacing fac = elb.getHorizontalFacing();
		EnumFacing opp = fac.getOpposite();
		IBlockState with = def.withProperty(FACING, opp);
		return with;
	}
	
	@SuppressWarnings("deprecation")
	private void setDefaultFacing(World w, BlockPos bp, IBlockState ibs)
	{
		if(!w.isRemote)
		{
			IBlockState ib = w.getBlockState(bp.north());
			IBlockState ib1 = w.getBlockState(bp.south());
			IBlockState ib2 = w.getBlockState(bp.west());
			IBlockState ib3 = w.getBlockState(bp.east());
			
			Block b = ib.getBlock();
			Block b1 = ib1.getBlock();
			Block b2 = ib2.getBlock();
			Block b3 = ib3.getBlock();
			EnumFacing ef = ibs.getValue(FACING);
			if(ef == EnumFacing.NORTH && b.isFullBlock(ibs) && !b1.isFullBlock(ibs)) ef = EnumFacing.SOUTH;
			else if(ef == EnumFacing.SOUTH && b1.isFullBlock(ibs) && !b.isFullBlock(ibs)) ef = EnumFacing.NORTH;
			else if(ef == EnumFacing.WEST && b2.isFullBlock(ibs) && !b3.isFullBlock(ibs)) ef = EnumFacing.EAST;
			else if(ef == EnumFacing.EAST && b3.isFullBlock(ibs) && b2.isFullBlock(ibs)) ef = EnumFacing.WEST;
			
			w.setBlockState(bp, ibs.withProperty(FACING, ef), 2);
		}
	}
	
	public void setState(boolean b, World w, BlockPos bp)
	{
		IBlockState ibs = w.getBlockState(bp);
		TileEntity te = w.getTileEntity(bp);
		w.setBlockState(bp, getDefaultState().withProperty(FACING, ibs.getValue(FACING)), 3);
		
		if(te != null)
		{
			te.validate();
			w.setTileEntity(bp, te);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing ef = EnumFacing.getFront(meta);
		if(ef.getAxis() == Axis.Y) ef = EnumFacing.NORTH;
		return getDefaultState().withProperty(FACING, ef);
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs) {return ibs.getValue(FACING).getIndex();}
	
	@Override
	public BlockStateContainer createBlockState() 
	{
		BlockStateContainer bsc = new BlockStateContainer(this, FACING);
		return bsc;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState ibs) {return EnumBlockRenderType.MODEL;}
	
	@Override
	public boolean isFullCube(IBlockState ibs) {return false;}
	@Override
	public boolean isOpaqueCube(IBlockState ibs) {return false;}
	@Override
	public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
}