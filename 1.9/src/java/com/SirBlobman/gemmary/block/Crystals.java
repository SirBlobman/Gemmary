package com.SirBlobman.gemmary.block;

import java.util.Random;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Crystals extends Block
{
	private Item drop;
	private int meta;
	private int least_dropped;
	private int most_dropped;
	public static final PropertyDirection Facing = PropertyDirection.create("facing");
	
	protected Crystals(String type, Item drop, int meta, int least, int most)
	{
		super(Material.rock);
		setDefaultState(blockState.getBaseState().withProperty(Facing, EnumFacing.UP));
		this.drop = drop;
		this.meta = meta;
		least_dropped = least;
		most_dropped = most;
		setHarvestLevel("pickaxe", 1);
		setHardness(10.0F);
		setResistance(15.0F);
		setUnlocalizedName(type + "_crystal");
		setRegistryName(type + "_crystal");
		setCreativeTab(GemmaryTabs.Blocks);
	}
	
	@Override
	public Item getItemDropped(IBlockState ibs, Random r, int fortune)
	{
		return drop;
	}
	
	@Override
	public int damageDropped(IBlockState ibs)
	{
		return meta;
	}
	
	@Override
	public int quantityDropped(IBlockState ibs, int fortune, Random r)
	{
		if(least_dropped >= most_dropped) return least_dropped;
		
		return least_dropped + r.nextInt(most_dropped - least_dropped + fortune + 1);
	}
	
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
	public IBlockState onBlockPlaced(World w, BlockPos pos, EnumFacing facing, float X, float Y, float Z, int meta, EntityLivingBase placer)
	{
		return getDefaultState().withProperty(Facing, facing);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing ef;
		switch(meta & 7)
		{
		case 0:
			ef = EnumFacing.DOWN;
			break;
		case 1:
			ef = EnumFacing.EAST;
			break;
		case 2:
			ef = EnumFacing.WEST;
			break;
		case 3:
			ef = EnumFacing.SOUTH;
			break;
		case 4:
			ef = EnumFacing.NORTH;
			break;
		case 5:
		default:
			ef = EnumFacing.UP;
		}
		
		return getDefaultState().withProperty(Facing, ef);
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		int i;
		
		switch(ibs.getValue(Facing))
		{
		case EAST:
            i = 1;
            break;
        case WEST:
            i = 2;
            break;
        case SOUTH:
            i = 3;
            break;
        case NORTH:
            i = 4;
            break;
        case UP:
        default:
            i = 5;
            break;
        case DOWN:
            i = 0;
		}
		
		return i;
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {Facing});
	}
}