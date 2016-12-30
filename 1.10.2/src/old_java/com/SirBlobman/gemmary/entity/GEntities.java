package com.SirBlobman.gemmary.entity;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class GEntities
{
	private static int id = 0;
	public static void entities() {r(DiamondTNTPrimed.class, "diamondTNT", 0);}
	
	private static void r(Class<? extends Entity> c, String name, int range)
	{
		EntityRegistry.registerModEntity
		(
			c,
			name,
			id++,
			Gemmary.instance,
			range,
			64,
			true
		);
	}
}