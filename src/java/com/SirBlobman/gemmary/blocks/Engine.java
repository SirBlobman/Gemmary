package com.SirBlobman.gemmary.blocks;

import com.SirBlobman.gemmary.ores.GemmaryOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Engine extends Block 
{
	protected Engine(String unlocalizedName)
    {
        super(Material.circuits);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(GemmaryOres.BlocksOres);
        this.setStepSound(soundTypeMetal);
    }
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }
}
