package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;
import com.google.common.collect.Lists;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElement extends Item {
    private final String atomicSymbol;
    private final int atomicNumber, neutrons;
    public ItemElement(String name, String symbol, int atomicNumber, int neutrons) {
        super();
        this.atomicSymbol = symbol;
        this.atomicNumber = atomicNumber;
        this.neutrons = neutrons;
        
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GemmaryCreativeTabs.ITEMS);
    }
    
    public String getAtomicSymbol() {return atomicSymbol;}
    public int getAtomicNumber() {return atomicNumber;}
    public int getProtons() {return getAtomicNumber();}
    public int getNeutrons() {return neutrons;}
    public int getElectrons() {return getAtomicNumber();}
    
    public double getAtomicMass() {
        double neutrons = getNeutrons();
        double protons = getProtons();
        double mass = (neutrons + protons);
        return mass;
    }
    
    @Override
    public void addInformation(ItemStack is, World world, List<String> tooltip, ITooltipFlag flags) {
        if(flags.isAdvanced()) {
            String symbol = getAtomicSymbol();
            String number = Integer.toString(getAtomicNumber());
            String mass = Double.toString(getAtomicMass());
            
            String format1 = I18n.format("lore.gemmary.element.atom.symbol", symbol);
            String format2 = I18n.format("lore.gemmary.element.atom.number", number);
            String format3 = I18n.format("lore.gemmary.element.atom.mass", mass);
            List<String> newLore = Lists.newArrayList(format1, format2, format3);
            tooltip.addAll(newLore);       
        }
    }
}