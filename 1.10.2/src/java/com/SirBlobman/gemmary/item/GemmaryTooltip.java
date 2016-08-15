package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.GUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GemmaryTooltip 
{
	@SubscribeEvent
	public void tooltip(ItemTooltipEvent e)
	{
		List<String> toolTip = e.getToolTip();
		ItemStack is = e.getItemStack();
		Item i = is.getItem();
		if(is == null || i == null) return;
		if(GuiScreen.isShiftKeyDown())
		{
			toolTip.add("§9Gemmary:");
			if(GUtil.getMohsScaleNumber(i) > 0.0D) toolTip.add("  " + GUtil.getMohsValueOfItem(i));
			toolTip.add("  ID (unused): " + Item.getIdFromItem(i));
			toolTip.add("  Unlocalized Name: " + i.getUnlocalizedName());
			toolTip.add("  Registry Name: " + i.getRegistryName());
			toolTip.add("  Class: " + i.getClass().getName());
			toolTip.add("  Mod: " + i.getRegistryName().getResourceDomain());
			toolTip.add("  Durability: " + is.getItemDamage() + "/" + is.getMaxDamage());
			if(GUtil.getOreDictionaryEntries(i).size() != 0) 
			{
				toolTip.add("  Ore Dictionary:");
				for(String s : GUtil.getOreDictionaryEntries(i)) toolTip.add("    - " + s);
			}
		}
		else
		{
			toolTip.add("§7§oPress SHIFT to see more information...");
		}
	}
}
