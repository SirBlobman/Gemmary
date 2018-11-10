package com.SirBlobman.gemmary.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Crystals extends OreBlock
{
	private static final PropertyDirection FACING = PropertyDirection.create("facing");
	public Crystals(Block type, Item drop, int meta, int min, int max)
	{
		super(drop, Material.ROCK, meta, min, max, type.getUnlocalizedName().substring(5) + "_crystal", 1, 10.0F);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
	@Override
	public boolean isOpaqueCube(IBlockState ibs) {return false;}
	@Override
	public boolean isFullCube(IBlockState ibs) {return false;}
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos pos, EnumFacing facing, float X, float Y, float Z, int meta, EntityLivingBase placer)
	{
		return getDefaultState().withProperty(FACING, facing);
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
		
		return getDefaultState().withProperty(FACING, ef);
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		int i;
		
		switch(ibs.getValue(FACING))
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
		return new BlockStateContainer(this, FACING);
	}
}