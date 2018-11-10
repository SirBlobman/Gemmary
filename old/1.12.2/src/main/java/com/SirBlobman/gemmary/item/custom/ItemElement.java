package com.SirBlobman.gemmary.item.custom;

import com.SirBlobman.gemmary.creative.GTabs;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElement extends Item {
    private final String symbol;
    private final int atomicNumber, neutrons;
    public ItemElement(String name, String symbol, int atomicNumber, int neutrons) {
        super();
        name = name + "_element";
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.ITEMS);
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.neutrons = neutrons;
    }
    
    public String getSymbol() {return symbol;}
    public int getAtomicNumber() {return atomicNumber;}
    public int getProtons() {return getAtomicNumber();}
    public int getNeutrons() {return neutrons;}
    public int getElectrons() {return getAtomicNumber();}
    
    public double getAtomicMass() {
        double neu = getNeutrons();
        double pro = getProtons();
        return (neu + pro);
    }
    
    @Override
    public void addInformation(ItemStack is, World world, List<String> lore, ITooltipFlag flag) {
        String symbol = I18n.format("lore.element.atomic_symbol", getSymbol());
        String number = I18n.format("lore.element.atomic_number", getAtomicNumber());
        String mass = I18n.format("lore.element.atomic_mass", getAtomicMass());
        lore.add(symbol); lore.add(number); lore.add(mass);
    }
}