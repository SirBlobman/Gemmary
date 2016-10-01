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
		for(Gem g : GUtil.gems())
		{
			ResourceLocation rl1 = g.getRegistryName();
			String name2 = g.getUnlocalizedName().substring(5) + "_dusty";
			ri(g, rl1, name2);
		}
	}
	
	public static void items()
	{
		for(Gem g : GUtil.gems())
		{
			r(g);
			String name = g.getUnlocalizedName().substring(5) + "_dusty";
			r(g, 1, name);
		}
		
		r(GItems.diamond);
		r(GItems.emerald);
		
		for(Atom a : GUtil.atoms()) r(a);
		r(GItems.cCr);
		r(GItems.cFe);
		
		for(Armor a : GUtil.armor()) r(a);
		
		r(GItems.hWC);
		r(GItems.cloth);
		r(GItems.recipeBook);
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
	
	private static void ri(Item i, ResourceLocation rl1, String name2)
	{
		ResourceLocation rl2 = new ResourceLocation(name2);
		ModelBakery.registerItemVariants(i, rl1, rl2);
	}
}