package com.SirBlobman.gemmary.block;

import java.util.Random;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class OreBlock extends Block
{
	private Item drop;
	private int meta;
	private int least;
	private int most;
	
	protected OreBlock(String gem, Material m, Item drop, int meta, int least, int most, String harvestTool, int harvestLvl, float hardness)
	{
		super(m);
		this.drop = drop;
		this.meta = meta;
		this.least = least;
		this.most = most;
		setHarvestLevel(harvestTool, harvestLvl);
		setHardness(hardness);
		setResistance(15.0F);
		setUnlocalizedName(gem + "_ore");
		setRegistryName(gem + "_ore");
		setCreativeTab(GemmaryTabs.Blocks);
	}
	
	@Override
	public Item getItemDropped(IBlockState ibs, Random r, int fortune)
	{
		return drop;
	}
	
	@Override
	public int quantityDropped(IBlockState ibs, int fortune, Random r)
	{
		if(least >= most)
		{
			return least;
		}
		
		return least + r.nextInt(most - least + fortune + 1);
	}
	
	public static final PropertyBool altTexture = PropertyBool.create("16x");
	
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(altTexture, Gemmary.altTextures);
	}
	
	public int getMetaFromState(IBlockState ibs)
	{
		if(ibs.getValue(altTexture)) return 1;
		else return 0;
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {altTexture});
	}
}