package com.SirBlobman.gemmary.render;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public final class RenderItems 
{
	public static void preInit()
	{
		ModelBakery.registerItemVariants(GItems.amethyst, new ResourceLocation("gemmary:amethyst"), new ResourceLocation("gemmary:amethyst_dusty"));
		ModelBakery.registerItemVariants(GItems.corundum, new ResourceLocation("gemmary:corundum"), new ResourceLocation("gemmary:corundum_dusty"));
		ModelBakery.registerItemVariants(GItems.ruby, new ResourceLocation("gemmary:ruby"), new ResourceLocation("gemmary:ruby_dusty"));
		ModelBakery.registerItemVariants(GItems.sapphire, new ResourceLocation("gemmary:sapphire"), new ResourceLocation("gemmary:sapphire_dusty"));
		ModelBakery.registerItemVariants(GItems.talc, new ResourceLocation("gemmary:talc"), new ResourceLocation("gemmary:talc_dusty"));
		ModelBakery.registerItemVariants(GItems.tanzanite, new ResourceLocation("gemmary:tanzanite"), new ResourceLocation("gemmary:tanzanite_dusty"));
		ModelBakery.registerItemVariants(GItems.topaz, new ResourceLocation("gemmary:topaz"), new ResourceLocation("gemmary:topaz_dusty"));
		ModelBakery.registerItemVariants(GItems.turquoise, new ResourceLocation("gemmary:turquoise"), new ResourceLocation("gemmary:turquoise_dusty"));
	}
	
	public static void rIR()
	{
	//Gems
		reg(GItems.amethyst);
		reg(GItems.corundum);
		reg(GItems.ruby);
		reg(GItems.sapphire);
		reg(GItems.talc);
		reg(GItems.tanzanite);
		reg(GItems.topaz);
		reg(GItems.turquoise);
		
	//Dusty Gems
		reg(GItems.amethyst, 1, "amethyst_dusty");
		reg(GItems.corundum, 1, "corundum_dusty");
		reg(GItems.ruby, 1, "ruby_dusty");
		reg(GItems.sapphire, 1, "sapphire_dusty");
		reg(GItems.talc, 1, "talc_dusty");
		reg(GItems.tanzanite, 1, "tanzanite_dusty");
		reg(GItems.topaz, 1, "topaz_dusty");
		reg(GItems.turquoise, 1, "turquoise_dusty");
		
	//Gem Parts
		reg(GItems.diamondPart);
		reg(GItems.emeraldPart);
		
	//Elements
		reg(GItems.aluminum);
		reg(GItems.beryllium);
		reg(GItems.carbon);
		reg(GItems.hydrogen);
		reg(GItems.oxygen);
		
	//Armor
		reg(GItems.amethystHelmet);
		reg(GItems.amethystChestplate);
		reg(GItems.amethystLeggings);
		reg(GItems.amethystBoots);
		
		reg(GItems.corundumHelmet);
		reg(GItems.corundumChestplate);
		reg(GItems.corundumLeggings);
		reg(GItems.corundumBoots);
		
		reg(GItems.rubyHelmet);
		reg(GItems.rubyChestplate);
		reg(GItems.rubyLeggings);
		reg(GItems.rubyBoots);

		reg(GItems.sapphireHelmet);
		reg(GItems.sapphireChestplate);
		reg(GItems.sapphireLeggings);
		reg(GItems.sapphireBoots);
		
		reg(GItems.talcHelmet);
		reg(GItems.talcChestplate);
		reg(GItems.talcLeggings);
		reg(GItems.talcBoots);

		reg(GItems.tanzaniteHelmet);
		reg(GItems.tanzaniteChestplate);
		reg(GItems.tanzaniteLeggings);
		reg(GItems.tanzaniteBoots);
		
		reg(GItems.topazHelmet);
		reg(GItems.topazChestplate);
		reg(GItems.topazLeggings);
		reg(GItems.topazBoots);

		reg(GItems.turquoiseHelmet);
		reg(GItems.turquoiseChestplate);
		reg(GItems.turquoiseLeggings);
		reg(GItems.turquoiseBoots);
		
	//Other
		reg(GItems.heatedWaterContainer);
		reg(GItems.cloth);
		reg(GItems.recipeBook);
	}
	
	public static String mod = Gemmary.MODID;
	
	//Default Model	
	public static void reg(Item i)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
	}
	
	//Metadata Model
	//i = item, m = meta, f = file
	public static void reg(Item i, int m, String f)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, m, new ModelResourceLocation(mod + ":" + f, "inventory"));
	}
}
