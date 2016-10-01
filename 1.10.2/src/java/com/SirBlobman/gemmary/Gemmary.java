package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.command.CommandGemmary;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod
(
	modid = Gemmary.MODID, 
	name = Gemmary.NAME, 
	version = Gemmary.VERSION, 
	acceptedMinecraftVersions = Gemmary.MCVERSIONS, 
	updateJSON = Gemmary.UPDATE,
	guiFactory = Gemmary.CONFIG
)
public class Gemmary
{
	public static final String MODID = "gemmary";
	public static final String NAME = "Gemmary";
	public static final String DESC = "The Science of Gems";
	public static final String VERSION = "Alpha 0.0.3.0";
	public static final String UPDATE = "https://raw.githubusercontent.com/SirBlobman/Gemmary/master/update.json";
	public static final String MCVERSIONS = "[1.9.4, 1.10.2]";
	public static final String PACKAGE = "com.SirBlobman." + MODID;
	public static final String CLIENT = PACKAGE + ".Client";
	public static final String SERVER = PACKAGE + ".Server";
	public static final String CONFIG = PACKAGE + ".config.ConfigGui";
	
	@SidedProxy(clientSide=CLIENT, serverSide=SERVER)
	public static Common proxy;
	
	@Instance
	public static Gemmary instance = new Gemmary();
	
	static{FluidRegistry.enableUniversalBucket();}
	
	//Config Options
	public static Configuration config;
	public static int atoms_to_spawn;
	public static float diamond_tnt_explosion_size;
	public static int cloth_durability;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e)
	{
		config = new Configuration(e.getSuggestedConfigurationFile(), true);
		config.load();
		
		cat("Atom Gatherer", "The Atom Gatherer spawns atoms");
		cat("Diamond TNT", "Diamond TNT explodes");
		cat("Items", "Config Section for in-game items");
		
		atoms_to_spawn = config.getInt("atoms to spawn", "Atom Gatherer", 4, 1, 20, "Amount of atoms to spawn.\nIf the server is lagging, lower this amount");
		diamond_tnt_explosion_size = config.getFloat("explosion size", "Diamond TNT", 500, 1, Float.MAX_VALUE, "Explosion Size for Dimaond TNT");
		cloth_durability = config.getInt("cloth durability", "Items", 10, 1, Integer.MAX_VALUE, "Durability for the cloth\nThe cloth is used to polish gems");
		
		config.save();
		
		proxy.pre(e);
	}
	@EventHandler
	public void init(FMLInitializationEvent e) {proxy.init(e);}
	
	@EventHandler
	public void server(FMLServerStartingEvent e) {e.registerServerCommand(new CommandGemmary());}
	
	private void cat(String section, String description){config.addCustomCategoryComment(section, description);}
}