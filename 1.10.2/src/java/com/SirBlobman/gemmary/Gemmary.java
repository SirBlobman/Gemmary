package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.command.CommandGemmary;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Gemmary.MODID, name = "Gemmary", version = "Release 0.0.2.0", updateJSON = "https://raw.githubusercontent.com/SirBlobman/Gemmary/master/update.json", acceptedMinecraftVersions = "[1.9.4, 1.10, 1.10.2]")
public class Gemmary 
{
	public static final String MODID = "gemmary";
	
	@SidedProxy(clientSide="com.SirBlobman.gemmary.ClientSide", serverSide="com.SirBlobman.gemmary.ServerSide")
    public static Common proxy;
	
	static
	{
		FluidRegistry.enableUniversalBucket();
	}
	
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
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new CommandGemmary());
	}
}
