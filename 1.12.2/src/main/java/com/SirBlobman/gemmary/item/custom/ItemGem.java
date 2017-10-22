package com.SirBlobman.gemmary.item.custom;

import com.SirBlobman.gemmary.creative.GTabs;

import net.minecraft.item.Item;

public class ItemGem extends Item {
    private final float mohs;
    public ItemGem(String name, float mohs) {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.ITEMS);
        this.mohs = mohs;
    }
    
    public float getMohsValue() {return mohs;}
}