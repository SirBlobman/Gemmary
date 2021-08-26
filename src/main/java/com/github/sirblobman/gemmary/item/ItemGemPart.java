package com.github.sirblobman.gemmary.item;

import net.minecraft.item.Item;

import com.github.sirblobman.gemmary.item.group.ItemGroupManager;

public final class ItemGemPart extends Item {
    public ItemGemPart(String gemName) {
        super(new Properties().group(ItemGroupManager.GEMMARY_ITEMS));
        setRegistryName("gemmary", gemName + "_part");
    }
}
