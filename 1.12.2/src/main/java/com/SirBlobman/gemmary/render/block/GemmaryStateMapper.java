package com.SirBlobman.gemmary.render.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.config.GConfig;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;

public class GemmaryStateMapper extends StateMapperBase {
    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState ibs) {
        Block block = ibs.getBlock();
        ResourceLocation orl = Block.REGISTRY.getNameForObject(block);
        String path = orl.getResourcePath();
        if(GConfig.FANCY_TEXTURES) path = "fancy/" + path;
        
        ResourceLocation nrl = new ResourceLocation(Gemmary.MODID, path);
        ImmutableMap<IProperty<?>, Comparable<?>> properties = ibs.getProperties();
        String propertyString = getPropertyString(properties);
        ModelResourceLocation mrl = new ModelResourceLocation(nrl, propertyString);
        return mrl;
    }
}