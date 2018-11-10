package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.item.GItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Common 
{
	public void pre(FMLPreInitializationEvent e)
	{
		
	}
	
	public void init(FMLInitializationEvent e)
	{
		GItems.gems();
	}
}