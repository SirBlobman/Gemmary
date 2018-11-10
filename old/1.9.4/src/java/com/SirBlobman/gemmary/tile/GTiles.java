package com.SirBlobman.gemmary.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GTiles 
{
	public static void createTiles()
	{
		GameRegistry.registerTileEntity(CompressorTE.class, "compressor");
		GameRegistry.registerTileEntity(HydrothermalTE.class, "hydrothermal_vein");
	}
}
