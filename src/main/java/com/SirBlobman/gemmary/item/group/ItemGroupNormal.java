package com.SirBlobman.gemmary.item.group;

import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupNormal extends ItemGroup {
    private final Item item;
    public ItemGroupNormal(String label, Item item) {
        super(label);
        this.item = Objects.requireNonNull(item, "item must not be null!");
    }
    
    public ItemGroupNormal(String label, Block block) {
        this(label, block.asItem());
    }
    
    @Override
    @SuppressWarnings("NullableProblems")
    public ItemStack createIcon() {
        return new ItemStack(this.item, 1);
    }
}