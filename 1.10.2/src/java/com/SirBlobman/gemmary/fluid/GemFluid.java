package com.SirBlobman.gemmary.fluid;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.block.material.MapColor;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class GemFluid extends Fluid
{
	private MapColor color;
	public GemFluid(String name, MapColor mc)
	{
		super
		(
			name,
			new ResourceLocation(Gemmary.MODID, "fluids/" + name + "_still"),
			new ResourceLocation(Gemmary.MODID, "fluids/" + name + "_flow")
		);
		setDensity(1600);
		setViscosity(12000);
		setTemperature(1974);
		setFillSound(SoundEvents.ITEM_BUCKET_FILL_LAVA);
		setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
		this.color = mc;
	}
	
	public MapColor getMapColor() {return color;}
}