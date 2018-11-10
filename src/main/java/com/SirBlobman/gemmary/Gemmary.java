package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.constant.ModInfo;
import com.SirBlobman.gemmary.proxy.Common;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = ModInfo.MODID, name=ModInfo.NAME, version=ModInfo.VERSION, acceptedMinecraftVersions=ModInfo.MINECRAFT_VERSIONS, updateJSON=ModInfo.UPDATE_JSON, dependencies=ModInfo.DEPENDENCIES)
public class Gemmary {
	@Instance
	public static Gemmary INSTANCE = new Gemmary();
	
	@SidedProxy(clientSide=ModInfo.CLIENT_PROXY, serverSide=ModInfo.SERVER_PROXY)
	public static Common proxy;
	
	public static Logger LOGGER;
	
	@EventHandler
	public void pre(FMLPreInitializationEvent e) {
		proxy.pre(e);
		LOGGER = e.getModLog();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
	    proxy.init(e);
	    
		ConfigManager.sync(ModInfo.MODID, Type.INSTANCE);
		
		double explosionSize = ConfigGemmary.DIAMOND_TNT_EXPLOSION_SIZE;
		int clothDurability = ConfigGemmary.CLOTH_DURABILITY;
		boolean hdTextures = ConfigGemmary.HD_TEXTURES;
		LOGGER.info("[DEBUG] Config Values: {" + explosionSize + ", " + clothDurability + ", " + hdTextures + "}");
	}
	
	@EventHandler
	public void post(FMLPostInitializationEvent e) {
	    proxy.post(e);
	}
	
	@SubscribeEvent
	public void onConfigChange(OnConfigChangedEvent e) {
		String modid = e.getModID();
		if(modid.equals(ModInfo.MODID)) {
			ConfigManager.sync(ModInfo.MODID, Type.INSTANCE);
			
			double explosionSize = ConfigGemmary.DIAMOND_TNT_EXPLOSION_SIZE;
			int clothDurability = ConfigGemmary.CLOTH_DURABILITY;
			boolean hdTextures = ConfigGemmary.HD_TEXTURES;
			LOGGER.info("[DEBUG] New Config Values: {" + explosionSize + ", " + clothDurability + ", " + hdTextures + "}");
		}
	}
	
	@SubscribeEvent
	public void registerItems(Register<Item> e) {
		IForgeRegistry<Item> registry = e.getRegistry();
		proxy.registerItems(registry);
	}
	
	@SubscribeEvent
	public void registerBlocks(Register<Block> e) {
	    IForgeRegistry<Block> registry = e.getRegistry();
	    proxy.registerBlocks(registry);
	}
	
	@SubscribeEvent
	public void registerRecipes(Register<IRecipe> e) {
	    IForgeRegistry<IRecipe> registry = e.getRegistry();
	    proxy.registerRecipes(registry);
	}
}