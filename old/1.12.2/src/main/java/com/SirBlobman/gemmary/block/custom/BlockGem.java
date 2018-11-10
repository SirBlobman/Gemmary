package com.SirBlobman.gemmary.block.custom;

import com.SirBlobman.gemmary.creative.GTabs;
import com.SirBlobman.gemmary.item.custom.ItemGem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGem extends Block {
    private final ItemGem gem;
    public BlockGem(ItemGem gem, int harvestLevel) {
        super(Material.IRON);
        this.gem = gem;
        String name = (gem.getRegistryName().getResourcePath() + "_block");
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(GTabs.BLOCKS);
        setHarvestLevel("pickaxe", harvestLevel);
        setHardness(gem.getMohsValue());
        setResistance(gem.getMohsValue());
    }
    
    public ItemGem getGem() {return gem;}
    public float getMohsValue() {return getGem().getMohsValue();}
}