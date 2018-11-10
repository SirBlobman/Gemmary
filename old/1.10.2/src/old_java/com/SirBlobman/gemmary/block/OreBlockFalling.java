package com.SirBlobman.gemmary.block;

import java.util.Random;

import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class OreBlockFalling extends BlockFalling
{
	private Item drop;
	private int meta,min,max;
	
	public OreBlockFalling(Item gem, int meta, int min, int max, int level, float mohs)
	{
		super();
		this.drop = gem;
		this.meta = meta;
		this.min = min;
		this.max = max;
		setHarvestLevel("shovel", level);
		setHardness(mohs);
		setResistance(15.0F);
		setSoundType(SoundType.SAND);
		String name = gem.getUnlocalizedName().substring(5) + "_ore";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.BLOCKS);
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