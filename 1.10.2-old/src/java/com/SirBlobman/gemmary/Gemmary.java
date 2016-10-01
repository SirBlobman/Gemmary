package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.command.CommandGemmary;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Gemmary.MODID, name = Gemmary.NAME, version = Gemmary.VERSION, updateJSON = Gemmary.UPDATE_JSON, acceptedMinecraftVersions = Gemmary.ACCEPTED_VERSIONS, guiFactory = Gemmary.CONFIG_FACTORY)
public class Gemmary 
{
	public static final String MODID = "gemmary";
	public static final String NAME = "Gemmary";
	public static final String VERSION = "Beta 0.0.2.4";
	public static final String UPDATE_JSON = "https://raw.githubusercontent.com/SirBlobman/Gemmary/master/update.json";
	public static final String ACCEPTED_VERSIONS = "[1.9.4, 1.10.2]";
	public static final String CONFIG_FACTORY = "com.SirBlobman." + MODID + ".config.GConfigGui";
	public static final String CLIENT_PROXY = "com.SirBlobman." + MODID + ".ClientSide";
	public static final String SERVER_PROXY = "com.SirBlobman." + MODID + ".ServerSide";
	
	@SidedProxy(clientSide=CLIENT_PROXY, serverSide=SERVER_PROXY)
    public static Common proxy;
	
	static
	{
		FluidRegistry.enableUniversalBucket();
	}
	
	@Instance
	public static Gemmary instance = new Gemmary();
	
	public static Configuration config;
	public static int atomsToSpawn;
	public static float diamondTntExplosionSize;
	public static int clothDurability;
	public static boolean altTextures;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
	//Config
		config = new Configuration(e.getSuggestedConfigurationFile(), true);
		config.load();
	//Config Comments
		config.addCustomCategoryComment("Atom Gatherer", "This section is for the Atom Gatherer, which spawns atoms");
		config.addCustomCategoryComment("Diamond TNT", "This section is for the Diamond TNT, which has a huge explosion");
		config.addCustomCategoryComment("Items", "This section is for Items");
		config.addCustomCategoryComment("Textures", "This section is for textures");
	//Config Options
		atomsToSpawn = config.getInt("atoms to spawn", "Atom Gatherer", 4, 1, 20, "If the atom gatherer is causing server lag, lower this amount.");
		diamondTntExplosionSize = config.getFloat("explosion size", "Diamond TNT", 500.0F, 1.0F, Float.MAX_VALUE, "Diamond TNT Explosion size.");
		clothDurability = config.getInt("cloth durability", "Items", 10, 1, Integer.MAX_VALUE, "Durability of the cloth. \nThe cloth is used to dust items.");
		altTextures = config.getBoolean("16x textures", "Textures", false, "Change this option if you HATE 1024x textures or if your computer can't handle them. \nA lot of the 16x textures may be missing or outdated!");
		
		config.save();
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
