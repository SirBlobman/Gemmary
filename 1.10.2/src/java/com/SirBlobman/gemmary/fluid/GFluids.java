package com.SirBlobman.gemmary.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GFluids 
{
	public static Fluid amethyst = createFluid("amethyst");
	public static Block amethystBlock = new BlockFluidClassic(amethyst, Material.LAVA).setRegistryName("fluidAmethyst").setUnlocalizedName("amethyst");
	
	public static void createGemFluids()
	{
		GameRegistry.register(amethystBlock);
	}
	
	private static Fluid createFluid(String name)
	{
		ResourceLocation still = new ResourceLocation("gemmary", "fluids/" + name + "Still");
		ResourceLocation flow = new ResourceLocation("gemmary", "fluids/" + name + "Flowing");
		Fluid f = new Fluid(name, still, flow);
		
		if(!FluidRegistry.registerFluid(f)) {throw new IllegalStateException(String.format("Unable to register fluid %s", f.getName()));}
		FluidRegistry.addBucketForFluid(f);
		return f;
	}
}