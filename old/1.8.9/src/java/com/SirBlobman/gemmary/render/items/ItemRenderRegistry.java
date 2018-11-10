package com.SirBlobman.gemmary.render.items;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.items.GemmaryArmor;
import com.SirBlobman.gemmary.items.GemmaryElements;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.SirBlobman.gemmary.items.GemmaryGems;
import com.SirBlobman.gemmary.items.GemmaryRandomItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public final class ItemRenderRegistry 
{
	public static void preInit() 
	{
	    ModelBakery.registerItemVariants(GemmaryGems.amethyst, new ResourceLocation("gemmary:amethyst"), new ResourceLocation("gemmary:amethyst_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.corundum, new ResourceLocation("gemmary:corundum"), new ResourceLocation("gemmary:corundum_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.real_diamond, new ResourceLocation("gemmary:diamond"), new ResourceLocation("gemmary:diamond_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.ruby, new ResourceLocation("gemmary:ruby"), new ResourceLocation("gemmary:ruby_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.sapphire, new ResourceLocation("gemmary:sapphire"), new ResourceLocation("gemmary:sapphire_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.talc, new ResourceLocation("gemmary:talc"), new ResourceLocation("gemmary:talc_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.tanzanite, new ResourceLocation("gemmary:tanzanite"), new ResourceLocation("gemmary:tanzanite_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.topaz, new ResourceLocation("gemmary:topaz"), new ResourceLocation("gemmary:topaz_dusty"));
	    ModelBakery.registerItemVariants(GemmaryGems.turquoise, new ResourceLocation("gemmary:turquoise"), new ResourceLocation("gemmary:turquoise_dusty"));
	}
	
	public static void registerItemRenderer()
	{
		//Gems
		reg(GemmaryGems.amethyst);
		reg(GemmaryGems.corundum);
		reg(GemmaryGems.real_diamond);
		reg(GemmaryGems.random);
		reg(GemmaryGems.ruby);
		reg(GemmaryGems.sapphire);
		reg(GemmaryGems.talc);
		reg(GemmaryGems.tanzanite);
		reg(GemmaryGems.topaz);
		reg(GemmaryGems.turquoise);
		//Dusty Gems
		regm(GemmaryGems.amethyst, 1, "amethyst_dusty");
		regm(GemmaryGems.corundum, 1, "corundum_dusty");
		regm(GemmaryGems.real_diamond, 1, "diamond_dusty");
		regm(GemmaryGems.ruby, 1, "ruby_dusty");
		regm(GemmaryGems.sapphire, 1, "sapphire_dusty");
		regm(GemmaryGems.talc, 1, "talc_dusty");
		regm(GemmaryGems.tanzanite, 1, "tanzanite_dusty");
		regm(GemmaryGems.topaz, 1, "topaz_dusty");
		regm(GemmaryGems.turquoise, 1, "turquoise_dusty");
		//Elements
		reg(GemmaryElements.aluminum);
		reg(GemmaryElements.beryllium);
		reg(GemmaryElements.carbon);
		reg(GemmaryElements.chromium);
		reg(GemmaryElements.oxygen);
		//Gem Parts
		reg(GemmaryGemParts.DiamondPart);
		reg(GemmaryGemParts.EmeraldPart);
		//Armor
		reg(GemmaryArmor.amethystHelmet);
		reg(GemmaryArmor.amethystChestplate);
		reg(GemmaryArmor.amethystLeggings);
		reg(GemmaryArmor.amethystBoots);
		reg(GemmaryArmor.corundumHelmet);
		reg(GemmaryArmor.corundumChestplate);
		reg(GemmaryArmor.corundumLeggings);
		reg(GemmaryArmor.corundumBoots);
		reg(GemmaryArmor.rubyHelmet);
		reg(GemmaryArmor.rubyChestplate);
		reg(GemmaryArmor.rubyLeggings);
		reg(GemmaryArmor.rubyBoots);
		reg(GemmaryArmor.sapphireHelmet);
		reg(GemmaryArmor.sapphireChestplate);
		reg(GemmaryArmor.sapphireLeggings);
		reg(GemmaryArmor.sapphireBoots);
		reg(GemmaryArmor.talcHelmet);
		reg(GemmaryArmor.talcChestplate);
		reg(GemmaryArmor.talcLeggings);
		reg(GemmaryArmor.talcBoots);
		reg(GemmaryArmor.tanzaniteHelmet);
		reg(GemmaryArmor.tanzaniteChestplate);
		reg(GemmaryArmor.tanzaniteLeggings);
		reg(GemmaryArmor.tanzaniteBoots);
		reg(GemmaryArmor.topazHelmet);
		reg(GemmaryArmor.topazChestplate);
		reg(GemmaryArmor.topazLeggings);
		reg(GemmaryArmor.topazBoots);
		reg(GemmaryArmor.turquoiseHelmet);
		reg(GemmaryArmor.turquoiseChestplate);
		reg(GemmaryArmor.turquoiseLeggings);
		reg(GemmaryArmor.turquoiseBoots);
		//Other
		reg(GemmaryRandomItems.Cloth);
		reg(GemmaryRandomItems.HeatedWaterContainer);
		reg(GemmaryRandomItems.RecipeBook);
	}
	
	public static String modid = Gemmary.MODID;
	
	//Register Model (Default)
	public static void reg(Item item) 
	{
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	//Register Model (Metadata)
	public static void regm(Item item, int meta, String file)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(item, meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
	}
}
