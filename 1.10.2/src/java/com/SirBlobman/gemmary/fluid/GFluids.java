package com.SirBlobman.gemmary.fluid;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GFluids 
{
	public static final Fluid AMETHYST;
	
	
	public static final Set<Fluid> FLUIDS = new HashSet<Fluid>();
	public static final Set<IFluidBlock> FLUID_BLOCKS = new HashSet<IFluidBlock>();
	
	static
	{
		AMETHYST = createFluid("amethyst", 
				fluid -> fluid.setDensity(1600).setViscosity(12000), 
				fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.ADOBE)));
	}
	
	public static void registerFluids() {}
	public static void registerFluidBuckets()
	{
		for(final Fluid f : FLUIDS)
		{
			createBucket(f);
		}
	}
	
	private static <T extends Block & IFluidBlock> Fluid createFluid(String name, Consumer<Fluid> fPA, Function<Fluid, T> bF)
	{
		final String prefix = Gemmary.MODID + ":fluids/";
		final ResourceLocation still = new ResourceLocation(prefix + name + "_still");
		final ResourceLocation flow = new ResourceLocation(prefix + name + "_flow");
		
		Fluid f = new Fluid(name, still, flow);
		final boolean uof = FluidRegistry.registerFluid(f);
		
		if(uof)
		{
			fPA.accept(f);
			createFluidBlock(bF.apply(f));
		}
		else
		{
			f = FluidRegistry.getFluid(name);
		}
		
		FLUIDS.add(f);
		return f;
	}
	
	private static <T extends Block & IFluidBlock> T createFluidBlock(T b)
	{
		b.setRegistryName("fluid." + b.getFluid().getName());
		b.setUnlocalizedName("fluid." + b.getFluid().getName());
		
		GameRegistry.register(b);
		GameRegistry.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
		
		FLUID_BLOCKS.add(b);
		return b;
	}
	
	private static void createBucket(Fluid f)
	{
		FluidRegistry.addBucketForFluid(f);
	}
}