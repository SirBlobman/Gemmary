package com.SirBlobman.gemmary.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class OreBlock extends Block
{
	private Item drop;
	private int meta;
	private int min;
	private int max;
	
	public OreBlock(Item gem, Material m, int meta, int min, int max, String tool, int level, float mohs)
	{
		super(m);
		this.drop = gem;
		this.meta = meta;
		this.min = min;
		this.max = max;
		setHarvestLevel(tool, level);
		setHardness(mohs);
		setResistance(15.0F);
		String name = gem.getUnlocalizedName().substring(5);
		setUnlocalizedName(name + "_ore");
		setRegistryName(name + "_ore");
	}
	
	@Override
	public Item getItemDropped(IBlockState ibs, Random r, int i) {return drop;}
	@Override
	public int damageDropped(IBlockState ibs) {return meta;}
	
	@Override
	public int quantityDropped(IBlockState ibs, int i, Random r)
	{
		if(min >= max) return min;
		return min + r.nextInt(max - min + i + 1);
	}
}