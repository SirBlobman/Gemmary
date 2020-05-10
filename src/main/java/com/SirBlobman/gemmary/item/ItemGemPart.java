package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.item.group.ItemGroupManager;

import net.minecraft.item.Item;

public class ItemGemPart extends Item {
    public ItemGemPart(String gemName) {
        super(new Properties().group(ItemGroupManager.GEMMARY_ITEMS));
        setRegistryName("gemmary", gemName + "_part");
    }
}