package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.tile.CompressorTE;

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

@SuppressWarnings({"deprecation"})
public class Compressor extends BlockContainer
{
	String type;
	public static final PropertyDirection Facing = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public Compressor(String type)
	{
		super(Material.ROCK);
		setCreativeTab(GemmaryTabs.Blocks);
		setUnlocalizedName(type + "_compressor");
		setRegistryName(type + "_compressor");
		setSoundType(SoundType.ANVIL);
		translucent = true;
		this.type = type;
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		if(type == "fast")
		{
			return new CompressorTE().setCompletionTime(100);
		}
		return new CompressorTE().setCompletionTime(3000);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote) return true;
		playerIn.openGui(Gemmary.instance, GuiHandler.Compressor_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof IInventory)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tE);
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockAdded(World w, BlockPos p, IBlockState ibs)
	{
		this.setDefaultFacing(w, p, ibs);
	}
	
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos pos, EnumFacing ef, float X, float Y, float Z, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(Facing, placer.getHorizontalFacing().getOpposite());
	}
	
	private void setDefaultFacing(World w, BlockPos p, IBlockState s)
	{
		if(!w.isRemote)
		{
			Block block = w.getBlockState(p.north()).getBlock();
            Block block1 = w.getBlockState(p.south()).getBlock();
            Block block2 = w.getBlockState(p.west()).getBlock();
            Block block3 = w.getBlockState(p.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)s.getValue(Facing);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock(s) && !block1.isFullBlock(s))
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock(s) && !block.isFullBlock(s))
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock(s) && !block3.isFullBlock(s))
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock(s) && !block2.isFullBlock(s))
            {
                enumfacing = EnumFacing.WEST;
            }

            w.setBlockState(p, s.withProperty(Facing, enumfacing), 2);
		}
	}
	
	public static void setState(boolean active, World w, BlockPos pos)
	{
		IBlockState ibs = w.getBlockState(pos);
		TileEntity tE = w.getTileEntity(pos);
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
		
		if(tE != null)
		{
			tE.validate();
			w.setTileEntity(pos, tE);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing ef = EnumFacing.getFront(meta);
		
		if(ef.getAxis() == EnumFacing.Axis.Y)
		{
			ef = EnumFacing.NORTH;
		}
		
		return this.getDefaultState().withProperty(Facing, ef);
	}
	
	@Override
	public int getMetaFromState(IBlockState s)
	{
		return ((EnumFacing)s.getValue(Facing)).getIndex();
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {Facing});
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
}