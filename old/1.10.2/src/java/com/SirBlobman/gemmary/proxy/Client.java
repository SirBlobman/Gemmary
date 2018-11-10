package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.render.RenderItem;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Client extends Common
{
	@Override
	public void pre(FMLPreInitializationEvent e)
	{
		super.pre(e);
		RenderItem.pre();
		RenderItem.items();
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
	}
}