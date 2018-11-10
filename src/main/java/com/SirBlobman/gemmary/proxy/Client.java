package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.client.render.block.GemmaryBlockRenderer;
import com.SirBlobman.gemmary.client.render.item.GemmaryItemRenderer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Client extends Common {
	@Override
	public void pre(FMLPreInitializationEvent e) {
		super.pre(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void post(FMLPostInitializationEvent e) {
		super.post(e);
	}
	
	@Override
	public void registerItems(IForgeRegistry<Item> ifr) {
		super.registerItems(ifr);
		GemmaryItemRenderer.registerItems();
		GemmaryItemRenderer.registerItemBlocks();
	}
	
	@Override
	public void registerBlocks(IForgeRegistry<Block> ifr) {
		super.registerBlocks(ifr);
		MinecraftForge.EVENT_BUS.register(new GemmaryBlockRenderer());
	}
}