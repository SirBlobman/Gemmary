package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGem extends Block {
    private final double mohs;
    public BlockGem(String name, double mohs) {
        super(mohs > 3.5D ? Material.ROCK : Material.SAND);
        this.mohs = mohs;
        setUnlocalizedName(name + "_block");
        setRegistryName(name + "_block");
        setCreativeTab(GemmaryCreativeTabs.BLOCKS);
        setHardness((float) mohs);
        setHarvestLevel(mohs > 3.5D ? "pickaxe" : "shovel", 3);
    }
    
    public double getMohsScaleValue() {return mohs;}
}