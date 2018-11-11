package com.SirBlobman.gemmary.client.render.block;

import com.SirBlobman.gemmary.config.ConfigGemmary;
import com.SirBlobman.gemmary.constant.ModInfo;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;

public class GemmaryHDMapper extends StateMapperBase {
    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Block block = state.getBlock();
        ResourceLocation brl = block.getRegistryName();
        String bpath = (ConfigGemmary.HD_TEXTURES ? "hd/" : "sd/") + brl.getResourcePath();
        
        ImmutableMap<IProperty<?>, Comparable<?>> properties = state.getProperties();
        String propertyString = getPropertyString(properties);

        ResourceLocation nrl = new ResourceLocation(ModInfo.MODID, bpath);
        ModelResourceLocation mrl = new ModelResourceLocation(nrl, propertyString);
        return mrl;
    }
}