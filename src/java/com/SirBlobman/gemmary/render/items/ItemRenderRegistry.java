package com.SirBlobman.gemmary.render.items;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.items.GemmaryElements;
import com.SirBlobman.gemmary.items.GemmaryGemParts;
import com.SirBlobman.gemmary.items.GemmaryGems;
import com.SirBlobman.gemmary.items.GemmaryRandomItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class ItemRenderRegistry 
{
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
		//Elements
		reg(GemmaryElements.aluminum);
		reg(GemmaryElements.beryllium);
		reg(GemmaryElements.carbon);
		reg(GemmaryElements.chromium);
		reg(GemmaryElements.oxygen);
		//Gem Parts
		reg(GemmaryGemParts.DiamondPart);
		reg(GemmaryGemParts.EmeraldPart);
		//Other
		reg(GemmaryRandomItems.RecipeBook);
	}
	
	public static String modid = Gemmary.MODID;
	public static void reg(Item item) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
