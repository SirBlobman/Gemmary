package com.SirBlobman.gemmary;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerSide extends Common
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		
		GUtil.print(I18n.format("log.server.pre-init-done"));
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		
		GUtil.print(I18n.format("log.server.init-done"));
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
		
		GUtil.print(I18n.format("log.server.post-init-done"));
	}
}
