package com.SirBlobman.gemmary.entity;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class GEntities 
{
	private static int entityID = 0;
	
	public static final void createEntities()
	{
		registerEntity(DiamondTNTPrimed.class, "diamondTNT", 0);
	}
	
	private static void registerEntity(Class<? extends Entity> entity, String name, int trackRange)
	{
		EntityRegistry.registerModEntity(entity, name, entityID++, Gemmary.instance, trackRange, 64, true);
	}
}
