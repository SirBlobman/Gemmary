package com.SirBlobman.gemmary;

import com.SirBlobman.gemmary.render.blocks.RenderBlocks;
import com.SirBlobman.gemmary.render.items.ItemRenderRegistry;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GemmaryClient extends GemmaryCommon
{
	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		super.preInit(e);
		ItemRenderRegistry.preInit();
		FMLLog.info(I18n.format("mod.preinitclient.done", new Object[0]));
	}

	@Override
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		ItemRenderRegistry.registerItemRenderer();
		RenderBlocks.registerBlockRenderer();
		FMLLog.info(I18n.format("mod.initclient.done", new Object[0]));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) 
	{
		super.postInit(e);
		FMLLog.info(I18n.format("mod.postinitclient.done", new Object[0]));
	}
}
