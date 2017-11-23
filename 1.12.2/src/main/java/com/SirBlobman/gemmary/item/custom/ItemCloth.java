package com.SirBlobman.gemmary.item.custom;

import com.SirBlobman.gemmary.config.GConfig;
import com.SirBlobman.gemmary.creative.GTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCloth extends Item {
    public ItemCloth() {
        super();
        setRegistryName("cloth");
        setUnlocalizedName("cloth");
        setCreativeTab(GTabs.ITEMS);
        setMaxStackSize(1);
        setMaxDamage(GConfig.CLOTH_DURABILITY);
    }
    
    public boolean showDurabilityBar(ItemStack stack) {return stack.getItemDamage() != 0;}
    public boolean hasContainerItem(ItemStack stack) {return true;}
    
    @Override
    public ItemStack getContainerItem(ItemStack is) {
        ItemStack is1 = is.copy();
        is1.attemptDamageItem(1, itemRand, null);
        return is1;
    }
}