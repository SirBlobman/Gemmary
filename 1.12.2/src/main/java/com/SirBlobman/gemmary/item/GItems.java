package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.item.custom.ItemElement;
import com.SirBlobman.gemmary.item.custom.ItemGem;
import com.SirBlobman.gemmary.item.custom.ItemGemPart;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class GItems {
    public static final ItemGem 
        AMETHYST        = new ItemGem("amethyst", 7.0F),
        RUBY            = new ItemGem("ruby", 9.0F),
        SAPPHIRE        = new ItemGem("sapphire", 9.0F),
        TALC            = new ItemGem("talc", 1.0F),
        TANZANITE       = new ItemGem("tanzanite", 6.75F),
        TOPAZ           = new ItemGem("topaz", 8.0F),
        TURQUOISE       = new ItemGem("turquoise", 5.5F);
    
    public static final ItemGemPart
        DIAMOND_PART    = new ItemGemPart("diamond"),
        EMERALD_PART    = new ItemGemPart("emerald");
    
    public static final ItemElement
        ALUMINUM        = new ItemElement("aluminum", "Al", 13, 14),
        BERYLLIUM       = new ItemElement("beryllium", "Be", 4, 5),
        CARBON          = new ItemElement("carbon", "C", 6, 6),
        CHROMIUM        = new ItemElement("chromium", "Cr", 24, 28),
        OXYGEN          = new ItemElement("oxygen", "O", 8, 8);
    
    public static void registerItems(IForgeRegistry<Item> ifr) {
        //Gems and Parts
        ifr.registerAll(AMETHYST, RUBY, SAPPHIRE, TALC, TANZANITE, TOPAZ, TURQUOISE);
        ifr.registerAll(DIAMOND_PART, EMERALD_PART);
        
        //Elements and Molecules
        ifr.registerAll(ALUMINUM, BERYLLIUM, CARBON, CHROMIUM, OXYGEN);
    }
}