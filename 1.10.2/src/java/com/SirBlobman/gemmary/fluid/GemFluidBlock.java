package com.SirBlobman.gemmary.fluid;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class GemFluidBlock extends BlockFluidClassic
{
	public GemFluidBlock(GemFluid gf)
	{
		super(gf, Material.LAVA);
		String name = "fluid." + StringUtils.capitalize(gf.getName());
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public GemFluid getGemFluid() 
	{
		Fluid f = getFluid();
		GemFluid gf = (GemFluid) f;
		return gf;
	}
	
	@Override
	public MapColor getMapColor(IBlockState ibs)
	{
		GemFluid gf = getGemFluid();
		MapColor mc = gf.getMapColor();
		return mc;
	}
}