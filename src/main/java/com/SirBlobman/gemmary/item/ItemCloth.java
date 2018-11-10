package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCloth extends Item {
    public ItemCloth() {
        super();
        setRegistryName("cloth");
        setUnlocalizedName("cloth");
        setCreativeTab(GemmaryCreativeTabs.ITEMS);
        setMaxStackSize(1);
        setMaxDamage(ConfigGemmary.CLOTH_DURABILITY);
    }
    
    public boolean showDurabilityBar(ItemStack is) {
        int data = is.getItemDamage();
        return (data > 0);
    }
    
    public boolean hasContainerItem() {return true;}
    
    @Override
    public ItemStack getContainerItem(ItemStack is) {
        ItemStack copy = is.copy();
        if(copy.attemptDamageItem(1, itemRand, null)) return copy;
        else return ItemStack.EMPTY;
    }
}