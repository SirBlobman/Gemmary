package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.render.RenderBlock;
import com.SirBlobman.gemmary.render.RenderItem;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Client extends Common
{
	@Override
	public void pre(FMLPreInitializationEvent e)
	{
		RenderItem.pre();
		super.pre(e);
	}
	
	@Override
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		RenderItem.items();
		RenderBlock.blocks();
	}
}