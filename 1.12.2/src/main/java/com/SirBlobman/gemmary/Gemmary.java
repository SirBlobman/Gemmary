package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.proxy.Common;
import com.SirBlobman.gemmary.utility.Util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(
    modid = Gemmary.MODID,
    name = Gemmary.NAME,
    version = Gemmary.VERSION,
    acceptedMinecraftVersions = Gemmary.ACCEPTED_VERSIONS,
    updateJSON = Gemmary.UPDATE_JSON
)
public class Gemmary {
    public static final String MODID = "gemmary";
    public static final String NAME = "Gemmary: The Science of Gems";
    public static final String VERSION = "1.0.0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12, 1.12.2]";
    public static final String UPDATE_JSON = "";
    
    public static final String PACKAGE = "com.SirBlobman.gemmary";
    public static final String PROXY = PACKAGE + ".proxy";
    public static final String CLIENT = PROXY + ".Client";
    public static final String SERVER = PROXY + ".Server";
    
    @Instance
    public static Gemmary INSTANCE = new Gemmary();
    
    @SidedProxy(clientSide=CLIENT, serverSide=SERVER)
    public static Common proxy;
    
    public static File FOLDER;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e) {
        FOLDER = e.getModConfigurationDirectory();
        GConfig.load();
        Util.regEvents(this);
        proxy.pre(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    
    @EventHandler
    public void post(FMLPostInitializationEvent e) {
        proxy.post(e);
    }
    
    @SubscribeEvent
    public void registerItems(Register<Item> e) {
        IForgeRegistry<Item> ifr = e.getRegistry();
        proxy.items(ifr);
    }
    
    @SubscribeEvent
    public void registerBlocks(Register<Block> e) {
        IForgeRegistry<Block> ifr = e.getRegistry();
        proxy.blocks(ifr);
    }
}