package com.SirBlobman.gemmary;

import java.io.File;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = Gemmary.MODID,
	name = Gemmary.NAME,
	version = Gemmary.VERSION,
	acceptedMinecraftVersions = Gemmary.VERSIONS,
	updateJSON = Gemmary.UPDATE
)
public class Gemmary {
	public static final String MODID = "gemmary";
	public static final String NAME = "Gemmary: The Science of Gems";
	public static final String VERSION = "0.0.2.2";
	public static final String VERSIONS = "[1.12]";
	public static final String UPDATE = "https://raw.githubusercontent.com/SirBlobman/Gemmary/master/update.json";
	public static final String PACKAGE = "com.SirBlobman.gemmary";
	public static final String PROXY = PACKAGE + ".proxy.";
	public static final String CLIENT = PROXY + "Client";
	public static final String SERVER = PROXY + "Server";
	
	@Instance(MODID)
	public static Gemmary INSTANCE = new Gemmary();
	
	//@SidedProxy(clientSide=CLIENT, serverSide=SERVER)
	//public static Common proxy;
	public static File FOLDER;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e) {
		FOLDER = e.getModConfigurationDirectory();
	}
}