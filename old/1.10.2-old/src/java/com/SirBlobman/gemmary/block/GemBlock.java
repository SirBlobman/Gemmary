package com.SirBlobman.gemmary.block;

import org.apache.commons.lang3.StringUtils;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GemBlock extends Block 
{
	public static final PropertyBool altTexture = PropertyBool.create("16x");
	private String gem;
	public GemBlock(String g, float hardness)
	{
		super(Material.IRON);
		this.gem = g;
		setUnlocalizedName(gem + "_block");
		setRegistryName(gem + "_block");
		setCreativeTab(GemmaryTabs.Blocks);
		setHardness(hardness);
		setDefaultState(blockState.getBaseState().withProperty(altTexture, Gemmary.altTextures));
	}
	
	public String getOreDictGem()
	{
		return "gem" + StringUtils.capitalize(gem);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) 
	{
		return true;
	}
	
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