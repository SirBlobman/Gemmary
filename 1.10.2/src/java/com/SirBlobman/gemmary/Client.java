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
		super.pre(e);
		RenderItem.pre();
	}
	
	@Override
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		RenderItem.items();
		RenderBlock.blocks();
	}
}