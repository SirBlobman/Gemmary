package com.SirBlobman.gemmary.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GTiles
{
	public static void tiles()
	{
		r(TileCompressor.class, "compressor");
		r(TileHydrothermal.class, "hydrothermal");
	}
	
	private static void r(Class<? extends TileEntity> c, String name)
	{
		GameRegistry.registerTileEntity(c, name);
	}
}