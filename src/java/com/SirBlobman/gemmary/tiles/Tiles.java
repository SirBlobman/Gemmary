package com.SirBlobman.gemmary.tiles;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class Tiles 
{
	public static void createNewTiles()
	{
		GameRegistry.registerTileEntity(ACTE.class, "acte");
		GameRegistry.registerTileEntity(CCTE.class, "compressor");
		GameRegistry.registerTileEntity(AHTV_TE.class, "ahtv");
	}
}
