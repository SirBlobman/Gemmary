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

public final class ItemRenderRegistry 
{
	@SuppressWarnings("deprecation")
	public static void preInit() 
	{
	    ModelBakery.addVariantName(GemmaryGems.amethyst, "gemmary:amethyst", "gemmary:amethyst_dusty");
	    ModelBakery.addVariantName(GemmaryGems.corundum, "gemmary:corundum", "gemmary:corundum_dusty");
	    ModelBakery.addVariantName(GemmaryGems.ruby, "gemmary:ruby", "gemmary:ruby_dusty");
	    ModelBakery.addVariantName(GemmaryGems.sapphire, "gemmary:sapphire", "gemmary:sapphire_dusty");
	    ModelBakery.addVariantName(GemmaryGems.talc, "gemmary:talc", "gemmary:talc_dusty");
	    ModelBakery.addVariantName(GemmaryGems.tanzanite, "gemmary:tanzanite", "gemmary:tanzanite_dusty");
	    ModelBakery.addVariantName(GemmaryGems.topaz, "gemmary:topaz", "gemmary:topaz_dusty");
	    ModelBakery.addVariantName(GemmaryGems.turquoise, "gemmary:turquoise", "gemmary:turquoise_dusty");
	}
	
	public static void registerItemRenderer()
	{
		//Gems
		reg(GemmaryGems.amethyst);
		reg(GemmaryGems.corundum);
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
