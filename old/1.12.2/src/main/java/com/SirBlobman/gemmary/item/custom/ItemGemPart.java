package com.SirBlobman.gemmary.item.custom;

import com.SirBlobman.gemmary.creative.GTabs;

import net.minecraft.item.Item;

public class ItemGemPart extends Item {
    public ItemGemPart(String name) {
        name = name + "_part";
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.ITEMS);
    }
}