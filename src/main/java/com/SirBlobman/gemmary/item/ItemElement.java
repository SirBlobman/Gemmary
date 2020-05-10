package com.SirBlobman.gemmary.item;

import java.util.Collections;
import java.util.List;

import com.SirBlobman.gemmary.item.group.ItemGroupManager;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemElement extends Item {
    private final String elementSymbol;
    private final int atomicNumber;
    private final double atomicWeight;
    public ItemElement(String elementName, String elementSymbol, int atomicNumber, double atomicWeight) {
        super(new Properties().group(ItemGroupManager.GEMMARY_ATOMS));
        setRegistryName("gemmary", elementName + "_atom");
        
        this.elementSymbol = elementSymbol;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
    }
    
    public String getElementSymbol() {
        return this.elementSymbol;
    }
    
    public int getAtomicNumber() {
        return this.atomicNumber;
    }
    
    public double getAtomicWeight() {
        return this.atomicWeight;
    }
    
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> loreList, ITooltipFlag flags) {
        String elementSymbol = getElementSymbol();
        int atomicNumber = getAtomicNumber();
        double atomicWeight = getAtomicWeight();
        
        TranslationTextComponent symbolComponent = new TranslationTextComponent("gemmary.lore.elementSymbol", elementSymbol);
        TranslationTextComponent numberComponent = new TranslationTextComponent("gemmary.lore.atomicNumber", atomicNumber);
        TranslationTextComponent weightComponent = new TranslationTextComponent("gemmary.lore.atomicWeight", atomicWeight);
        Collections.addAll(loreList, symbolComponent, numberComponent, weightComponent);
    }
}