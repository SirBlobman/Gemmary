package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.render.RenderBlocks;
import com.SirBlobman.gemmary.render.RenderItems;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientSide extends Common
{
	Object p = new Object[0];
	
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		
		RenderItems.preInit();
		
		GUtil.print(I18n.format("log.client.pre-init-done", p));
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		
		RenderItems.rIR();
		RenderBlocks.rBR();
		
		GUtil.print(I18n.format("log.client.init-done", p));
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
		
		GUtil.print(I18n.format("log.client.post-init-done", p));
	}
}
