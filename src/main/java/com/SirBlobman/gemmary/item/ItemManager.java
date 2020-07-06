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
        registerItem(new ItemElement("silicon", "Si", 14, 28.085D));
        registerItem(new ItemElement("phosphorus", "P", 15, 30.974D));
        registerItem(new ItemElement("sulfur", "s", 16, 32.06D));
        registerItem(new ItemElement("chlorine", "Cl", 17, 35.45D));
        registerItem(new ItemElement("argon", "Ar", 18, 39.948D));
        
        registerItem(new ItemElement("potassium", "K", 19, 39.098D));
        registerItem(new ItemElement("calcium", "Ca", 20, 40.078D));
        registerItem(new ItemElement("scandium", "Sc", 21, 44.956D));
        registerItem(new ItemElement("titanium", "Ti", 22, 47.867D));
        registerItem(new ItemElement("vanadium", "V", 23, 50.942D));
        registerItem(new ItemElement("chromium", "Cr", 24, 51.996D));
        registerItem(new ItemElement("manganese", "Mn", 25, 54.938D));
        registerItem(new ItemElement("iron", "Fe", 26, 55.845D));
        registerItem(new ItemElement("cobalt", "Co", 27, 58.933D));
        registerItem(new ItemElement("nickel", "Ni", 28, 58.693D));
        registerItem(new ItemElement("copper", "Cu", 29, 63.546D));
        registerItem(new ItemElement("zinc", "Zn", 30, 65.38D));
        registerItem(new ItemElement("gallium", "Ga", 31, 69.723D));
        registerItem(new ItemElement("germanium", "Ge", 32, 72.630D));
        registerItem(new ItemElement("arsenic", "As", 33, 74.922D));
        registerItem(new ItemElement("selenium", "Se", 34, 78.971D));
        registerItem(new ItemElement("bromine", "Br", 35, 79.904D));
        registerItem(new ItemElement("krypton", "Kr", 36, 83.798D));
        
        registerItem(new ItemElement("rubidium", "Rb", 37, 85.468D));
        registerItem(new ItemElement("strontium", "Sr", 38, 87.62D));
        registerItem(new ItemElement("yttrium", "Y", 39, 88.906D));
        registerItem(new ItemElement("zirconium", "Zr", 40, 91.224D));
        registerItem(new ItemElement("niobium", "Nb", 41, 92.906D));
        registerItem(new ItemElement("molybdenum", "Mo", 42, 95.95D));
        registerItem(new ItemElement("technetium", "Tc", 43, 98.0D));
        registerItem(new ItemElement("ruthenium", "Ru", 44, 101.07D));
        registerItem(new ItemElement("rhodium", "Rh", 45, 102.91D));
        registerItem(new ItemElement("palladium", "Pd", 46, 106.42D));
        registerItem(new ItemElement("silver", "Ag", 47, 107.87D));
        registerItem(new ItemElement("cadmium", "Cd", 48, 112.41D));
        registerItem(new ItemElement("indium", "In", 49, 114.82D));
        registerItem(new ItemElement("tin", "Sn", 50, 118.71D));
        registerItem(new ItemElement("antimony", "Sb", 51, 121.76D));
        registerItem(new ItemElement("tellurium", "Te", 52, 127.60D));
        registerItem(new ItemElement("iodine", "I", 53, 126.90D));
        registerItem(new ItemElement("xenon", "Xe", 54, 131.29D));
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