package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.proxy.Common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod
(
	name = Gemmary.NAME,
	modid = Gemmary.MODID,
	version = Gemmary.VERSION,
	acceptedMinecraftVersions = Gemmary.MCVERSION,
	updateJSON = Gemmary.UPDATE
	//guiFactory = Gemmary.CONFIG
)
public class Gemmary
{
	public static final String NAME = "Gemmary";
	public static final String MODID = "gemmary";
	public static final String VERSION = "Beta 0.0.3.0";
	public static final String DESC = "The Science of Gems";
	public static final String UPDATE = "https://raw.githubusercontent.com/SirBlobman/Gemmary/master/update.json";
	public static final String MCVERSION = "[1.10.2]";
	public static final String PACKAGE = "com.SirBlobman." + MODID + ".";
	public static final String PROXY = PACKAGE + "proxy.";
	public static final String CLIENT = PROXY + "Client";
	public static final String SERVER = PROXY + "Server";
	public static final String CONFIG = PACKAGE + "config.ConfigGui";
	
	@Instance
	public static Gemmary instance = new Gemmary();
	
	@SidedProxy(clientSide=CLIENT, serverSide=SERVER)
	public static Common proxy;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e)
	{
		GConfig.config = GConfig.load(e);
		proxy.pre(e);
		System.out.println(GConfig.config.getConfigFile().getPath());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {proxy.init(e);}
	
	@EventHandler
	public void server(FMLServerStartingEvent e) {}
}