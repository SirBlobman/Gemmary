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
        // Gems
        registerItem(new ItemGem("amethyst", 7.0D));
        registerItem(new ItemGem("ruby", 9.0D));
        registerItem(new ItemGem("sapphire", 9.0D));
        registerItem(new ItemGem("talc", 1.0D));
        registerItem(new ItemGem("tanzanite", 6.75D));
        registerItem(new ItemGem("topaz", 8.0D));
        registerItem(new ItemGem("turquoise", 5.5D));
        
        // Gem Parts
        registerItem(new ItemGemPart("diamond"));
        registerItem(new ItemGemPart("emerald"));
        
        // Periodic Elements
        registerItem(new ItemElement("hydrogen", "H", 1, 1.008D));
        registerItem(new ItemElement("helium", "He", 4, 4.0026D));
        registerItem(new ItemElement("lithium", "Li", 3, 6.94D));
        registerItem(new ItemElement("beryllium", "Be", 4, 9.0122D));
        registerItem(new ItemElement("boron", "B", 5, 10.81D));
        registerItem(new ItemElement("carbon", "C", 6, 12.011D));
        registerItem(new ItemElement("nitrogen", "N", 7, 14.007D));
        registerItem(new ItemElement("oxygen", "O", 8, 15.999D));
        registerItem(new ItemElement("fluorine", "F", 9, 18.998D));
        registerItem(new ItemElement("neon", "Ne", 10, 20.180D));
        registerItem(new ItemElement("sodium", "Na", 11, 22.990D));
        registerItem(new ItemElement("magnesium", "Mg", 12, 23.305D));
        registerItem(new ItemElement("aluminium", "Al", 13, 26.982D));
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