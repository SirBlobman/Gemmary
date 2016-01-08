package com.SirBlobman.gemmary;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GemmaryServer extends GemmaryCommon
{
	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		super.preInit(e);
		FMLLog.info(I18n.format("mod.preinitserver.done", new Object[0]));
	}

	@Override
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		FMLLog.info(I18n.format("mod.initserver.done", new Object[0]));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) 
	{
		super.postInit(e);
		FMLLog.info(I18n.format("mod.postinitserver.done", new Object[0]));
	}
}
