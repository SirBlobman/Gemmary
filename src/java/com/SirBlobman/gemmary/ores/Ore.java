package com.SirBlobman.gemmary.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class Ore extends Block 
{

	private Item drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;

	protected Ore(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity, String harvestLevel, int harvestLevel2) 
	{
		super(mat);
		this.drop = drop;
		this.meta = meta;
		this.least_quantity = least_quantity;
		this.most_quantity = most_quantity;
		this.setHarvestLevel(harvestLevel, harvestLevel2);
		this.setHardness(10.0f);
		this.setResistance(15.0f);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryOres.BlocksOres);
	}

	protected Ore(String unlocalizedName, Material mat, Item drop, int least_quantity, int most_quantity, String harvestLevel, int harvestLevel2) 
	{
		this(unlocalizedName, mat, drop, 0, least_quantity, most_quantity, harvestLevel, harvestLevel2);
	}

	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) 
	{
		return this.drop;
	}

	@Override
	public int damageDropped(IBlockState blockstate) 
	{
		return this.meta;
	}

	@Override
	public int quantityDropped(IBlockState blockstate, int fortune, Random random) 
	{
		if (this.least_quantity >= this.most_quantity)
			return this.least_quantity;
		return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
}