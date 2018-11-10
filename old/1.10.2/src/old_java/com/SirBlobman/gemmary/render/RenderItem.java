package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.item.Atom;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.Gem;
import com.SirBlobman.gemmary.item.armor.Armor;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class RenderItem 
{
	public static void pre()
	{
		ri(GItems.amethyst, "amethyst_dusty");
	}
	
	public static void items()
	{
		r(GItems.amethyst);
		r(GItems.amethyst, 1, "amethyst_dusty");
	}
	
	private static void r(Item i)
	{
		ResourceLocation rl = i.getRegistryName();
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
	
	private static final String MOD = Gemmary.MODID;
	private static void r(Item i, int meta, String file)
	{
		String rl = MOD + ":" + file;
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
	}
	
	private static void ri(Item i, String name2)
	{
		ResourceLocation rl2 = new ResourceLocation(MOD, name2);
		ModelBakery.registerItemVariants(i, i.getRegistryName(), rl2);
	}
}