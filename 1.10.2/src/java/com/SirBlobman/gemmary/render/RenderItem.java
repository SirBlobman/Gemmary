package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.item.GItems;

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
		ri(GItems.corundum, "corundum_dusty");
		/*ri(GItems.ruby, "ruby_dusty");
		ri(GItems.sapphire, "sapphire_dusty");
		ri(GItems.talc, "talc_dusty");
		ri(GItems.tanzanite, "tanzanite_dusty");
		ri(GItems.topaz, "topaz_dusty");
		ri(GItems.turquoise, "turquoise_dusty");*/
	}
	
	public static void items() 
	{
		r(GItems.amethyst);
		r(GItems.corundum);
		/*r(GItems.ruby);
		r(GItems.sapphire);
		r(GItems.talc);
		r(GItems.tanzanite);
		r(GItems.topaz);
		r(GItems.turquoise);*/
		
		r(GItems.amethyst, 1, "amethyst_dusty");
		r(GItems.corundum, 1, "corundum_dusty");
		/*r(GItems.ruby, 1, "ruby_dusty");
		r(GItems.sapphire, 1, "sapphire_dusty");
		r(GItems.talc, 1, "talc_dusty");
		r(GItems.tanzanite, 1, "tanzanite_dusty");
		r(GItems.topaz, 1, "topaz_dusty");
		r(GItems.turquoise, 1, "turquoise_dusty");*/
	}
	
	private static final String MOD = Gemmary.MODID;
	
	private static void r(Item i)
	{
		ResourceLocation rl = i.getRegistryName();
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
	}
	private static void r(Item i, int meta, String file)
	{
		ResourceLocation rl = new ResourceLocation(MOD, file);
		ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
		ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
	}
	private static void ri(Item i, String name)
	{
		ResourceLocation rl = new ResourceLocation(MOD, name);
		ModelBakery.registerItemVariants(i, i.getRegistryName(), rl);
	}
}