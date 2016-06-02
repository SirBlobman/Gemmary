package com.SirBlobman.gemmary.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class QuartzCrystals extends Block 
{
	private Item drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	protected QuartzCrystals(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity) 
	{
		super(mat);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		this.drop = Items.quartz;
		this.meta = 0;
		this.least_quantity = 1;
		this.most_quantity = 5;
		this.setHarvestLevel("pickaxe", 1);
		this.setHardness(10.0f);
		this.setResistance(15.0f);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryOres.BlocksOres);
	}

	protected QuartzCrystals(String unlocalizedName, Material mat, Item drop, int least_quantity, int most_quantity) {
		this(unlocalizedName, mat, drop, 0, least_quantity, most_quantity);
	}

	protected QuartzCrystals(String unlocalizedName, Material mat, Item drop) {
		this(unlocalizedName, mat, drop, 1, 1);
	}

	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
		return this.drop;
	}

	@Override
	public int damageDropped(IBlockState blockstate) {
		return this.meta;
	}

	@Override
	public int quantityDropped(IBlockState blockstate, int fortune, Random random) {
		if (this.least_quantity >= this.most_quantity)
			return this.least_quantity;
		return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;

        switch (meta & 7)
        {
            case 0:
                enumfacing = EnumFacing.DOWN;
                break;
            case 1:
                enumfacing = EnumFacing.EAST;
                break;
            case 2:
                enumfacing = EnumFacing.WEST;
                break;
            case 3:
                enumfacing = EnumFacing.SOUTH;
                break;
            case 4:
                enumfacing = EnumFacing.NORTH;
                break;
            case 5:
            default:
                enumfacing = EnumFacing.UP;
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    public int getMetaFromState(IBlockState state)
    {
        int i;

        switch ((EnumFacing)state.getValue(FACING))
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
    
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
}
