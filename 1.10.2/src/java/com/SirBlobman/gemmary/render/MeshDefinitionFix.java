package com.SirBlobman.gemmary.render;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

interface MeshDefinitionFix extends ItemMeshDefinition
{
	ModelResourceLocation getLocation(ItemStack is);
	
	static ItemMeshDefinition create(MeshDefinitionFix lambda)
	{
		return lambda;
	}
	
	default ModelResourceLocation getModelLocation(ItemStack is)
	{
		return getLocation(is);
	}
}