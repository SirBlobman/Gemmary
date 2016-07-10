package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.tile.HydrothermalTE;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings({"unused", "deprecation"})
public class HydrothermalVein extends BlockContainer
{
	private static boolean keepInventory;
	public static final PropertyDirection Facing = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	protected HydrothermalVein()
	{
		super(Material.CIRCUITS);
		setUnlocalizedName("artificial_hydrothermal_vein");
		setRegistryName("artificial_hydrothermal_vein");
		setCreativeTab(GemmaryTabs.Blocks);
		setSoundType(SoundType.METAL);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isFullCube(IBlockState ibs)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState ibs)
	{
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState ibs)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	private void setDefaultFacing(World w, BlockPos pos, IBlockState ibs)
	{
		if(!w.isRemote)
		{
			Block b = w.getBlockState(pos.north()).getBlock();
			Block b1 = w.getBlockState(pos.south()).getBlock();
			Block b2 = w.getBlockState(pos.east()).getBlock();
			Block b3 = w.getBlockState(pos.west()).getBlock();
			EnumFacing ef = (EnumFacing)ibs.getValue(Facing);
			
			if(ef == EnumFacing.NORTH && b.isFullBlock(ibs) && !b1.isFullBlock(ibs))
			{
				ef = EnumFacing.SOUTH;
			}
			else if(ef == EnumFacing.SOUTH && b1.isFullBlock(ibs) && !b.isFullBlock(ibs))
			{
				ef = EnumFacing.NORTH;
			}
			else if (ef == EnumFacing.WEST && b2.isFullBlock(ibs) && !b3.isFullBlock(ibs))
            {
                ef = EnumFacing.EAST;
            }
            else if (ef == EnumFacing.EAST && b3.isFullBlock(ibs) && !b2.isFullBlock(ibs))
            {
                ef = EnumFacing.WEST;
            }
			
			w.setBlockState(pos, ibs.withProperty(Facing, ef), 2);
		}
	}
	
	public static void setState(boolean active, World w, BlockPos pos)
	{
		IBlockState ibs = w.getBlockState(pos);
		TileEntity te = w.getTileEntity(pos);
		keepInventory = true;
		
		Block aturner = Blocks.LIT_FURNACE;
		Block turner = Blocks.FURNACE;
		
		if(active)
		{
			w.setBlockState(pos, aturner.getDefaultState().withProperty(Facing, ibs.getValue(Facing)), 3);
		}
		else
		{
			w.setBlockState(pos, turner.getDefaultState().withProperty(Facing, ibs.getValue(Facing)), 3);
		}
		
		keepInventory = false;
		
		if(te != null)
		{
			te.validate();
			w.setTileEntity(pos, te);
		}
	}
	
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos pos, EnumFacing ef, float X, float Y, float Z, int meta, EntityLivingBase placer)
	{
		return getDefaultState().withProperty(Facing, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing ef = EnumFacing.getFront(meta);
		
		if(ef.getAxis() == EnumFacing.Axis.Y)
		{
			ef = EnumFacing.NORTH;
		}
		
		return getDefaultState().withProperty(Facing, ef);
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		return ((EnumFacing)ibs.getValue(Facing)).getIndex();
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {Facing});
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new HydrothermalTE();
	}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState ibs, EntityPlayer p, EnumHand hand, ItemStack heldItem, EnumFacing side, float X, float Y, float Z)
	{
		if(w.isRemote) return true;
		
		p.openGui(Gemmary.instance, GuiHandler.HydroThermalVent_GUI, w, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World w, BlockPos pos, IBlockState ibs)
	{
		TileEntity tE = w.getTileEntity(pos);
		if(tE instanceof IInventory)
		{
			InventoryHelper.dropInventoryItems(w, pos, (IInventory)tE);
		}
		
		super.breakBlock(w, pos, ibs);
	}
	
	@Override
	public void onBlockAdded(World w, BlockPos pos, IBlockState ibs)
	{
		this.setDefaultFacing(w, pos, ibs);
	}
}
