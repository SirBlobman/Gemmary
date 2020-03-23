package com.SirBlobman.gemmary.item;

import java.util.HashMap;
import java.util.Map;

import com.SirBlobman.gemmary.GemmaryMod;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemManager {
    private IForgeRegistry<Item> forgeRegistry;
    private final GemmaryMod gemmary;
    private final Map<String, Item> itemMap;
    public ItemManager(GemmaryMod gemmary) {
        this.gemmary = gemmary;
        this.itemMap = new HashMap<>();
    }
    
    public GemmaryMod getGemmary() {
        return this.gemmary;
    }
    
    public IForgeRegistry<Item> getItemRegistry() {
        return this.forgeRegistry;
    }
    
    public void setRegistry(IForgeRegistry<Item> forgeRegistry) {
        this.forgeRegistry = forgeRegistry;
    }
    
    public void registerItems() {
        registerItem(new ItemGem("amethyst", 9.0D));
    }
    
    private void registerItem(Item item) {
        ResourceLocation registryLocation = item.getRegistryName();
        if(registryLocation == null) throw new IllegalStateException("Missing registry name.");
        
        IForgeRegistry<Item> registry = getItemRegistry();
        if(registry == null) throw new IllegalStateException("Items cannot be registered at this time.");
        registry.register(item);
        
        String itemName = registryLocation.getPath();
        this.itemMap.put(itemName, item);
    }
    
    public Item getItem(String name) {
        return this.itemMap.getOrDefault(name, null);
    }
}