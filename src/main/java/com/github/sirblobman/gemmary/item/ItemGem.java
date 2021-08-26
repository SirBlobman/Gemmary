package com.github.sirblobman.gemmary.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import com.github.sirblobman.gemmary.item.group.ItemGroupManager;

public final class ItemGem extends Item {
    private final double mohsValue;
    
    public ItemGem(String name, double mohsValue) {
        super(new Properties().group(ItemGroupManager.GEMMARY_ITEMS));
        
        setRegistryName("gemmary", name);
        this.mohsValue = mohsValue;
    }
    
    public double getMohsValue() {
        return this.mohsValue;
    }
    
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> loreList, ITooltipFlag flags) {
        double mohsValue = getMohsValue();
        TranslationTextComponent textComponent = new TranslationTextComponent("gemmary.lore.mohsValue", mohsValue);
        loreList.add(textComponent);
    }
}
