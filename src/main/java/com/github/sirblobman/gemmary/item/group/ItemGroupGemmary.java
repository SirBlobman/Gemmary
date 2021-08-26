package com.github.sirblobman.gemmary.item.group;

import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import com.github.sirblobman.gemmary.GemmaryMod;
import com.github.sirblobman.gemmary.item.ItemManager;

public final class ItemGroupGemmary extends ItemGroup {
    private final String gemmaryItemId;
    
    public ItemGroupGemmary(String label, String gemmaryItemId) {
        super(label);
        this.gemmaryItemId = Objects.requireNonNull(gemmaryItemId, "gemmaryItemId must not be null!");
    }
    
    @Override
    @SuppressWarnings("NullableProblems")
    public ItemStack createIcon() {
        GemmaryMod gemmaryMod = GemmaryMod.getInstance();
        ItemManager itemManager = gemmaryMod.getItemManager();
        
        Item item = itemManager.getItem(this.gemmaryItemId);
        return new ItemStack(item, 1);
    }
}
