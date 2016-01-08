package com.SirBlobman.gemmary;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Gemmary.MODID, name = Gemmary.MODNAME, version = Gemmary.VERSION)
public class Gemmary 
{
	public static final String MODID = "gemmary";
	public static final String MODNAME = "Gemmary";
	public static final String VERSION = "Beta 0.0.1.3";
	
    @SidedProxy(clientSide="com.SirBlobman.gemmary.GemmaryClient", serverSide="com.SirBlobman.gemmary.GemmaryServer")
    public static GemmaryCommon proxy;
	
	@Instance
	public static Gemmary instance = new Gemmary();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		proxy.preInit(e);
	}
	
	@EventHandler
    public void init(FMLInitializationEvent e) 
	{
		proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
    	proxy.postInit(e);
    }
}
