package com.SirBlobman.gemmary.fluid;

import net.minecraft.block.material.MapColor;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GFluids
{
	public static GemFluid amethyst = new GemFluid("amethyst", MapColor.PURPLE);
	public static void fluids()
	{
		fluid(amethyst);
	}
	
	private static void fluid(GemFluid gf)
	{
		FluidRegistry.registerFluid(gf);
		FluidRegistry.addBucketForFluid(gf);
		GemFluidBlock gfb = new GemFluidBlock(gf);
		GameRegistry.register(gfb);
	}
}