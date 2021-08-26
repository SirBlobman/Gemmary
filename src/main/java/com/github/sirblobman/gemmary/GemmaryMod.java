package com.github.sirblobman.gemmary;

import net.minecraft.item.Item;

import com.github.sirblobman.gemmary.item.ItemManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("gemmary")
@EventBusSubscriber(modid="gemmary", bus=Bus.MOD)
public final class GemmaryMod {
    private static GemmaryMod instance;
    private final ItemManager itemManager;
    
    public GemmaryMod() {
        instance = this;
        this.itemManager = new ItemManager(this);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public ItemManager getItemManager() {
        return this.itemManager;
    }
    
    public static GemmaryMod getInstance() {
        return instance;
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        IForgeRegistry<Item> registry = e.getRegistry();
        ItemManager manager = instance.getItemManager();
        
        manager.setRegistry(registry);
        manager.registerItems();
    }
}
