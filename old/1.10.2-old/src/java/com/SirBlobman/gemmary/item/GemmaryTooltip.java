package com.SirBlobman.gemmary.item;

import java.util.List;

import com.SirBlobman.gemmary.GUtil;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
			toolTip.add("§9§lGemmary:");
			if(GUtil.getMohsScaleNumber(i) > 0.0D) toolTip.add("  " + GUtil.getMohsValueOfItem(i));
			toolTip.add("  §lID (unused): §r" + Item.getIdFromItem(i));
			toolTip.add("  §lMeta: §r" + is.getMetadata());
			toolTip.add("  §lUnlocalized Name: §r" + i.getUnlocalizedName());
			toolTip.add("  §lRegistry Name: §r" + i.getRegistryName());
			if(i instanceof ItemBlock)
			{
				ItemBlock ib = (ItemBlock) i;
				Block b = ib.getBlock();
				toolTip.add("  §lBlock Class: §r" + b.getClass().getName());
			}
			toolTip.add("  §lClass: §r" + i.getClass().getName());
			toolTip.add("  §lMod: §r" + i.getRegistryName().getResourceDomain());
			int meta = is.getItemDamage();
			int max = is.getMaxDamage();
			int damage = max - meta; 
			if(max != 0) toolTip.add("  §lDurability: §r" + damage + "/" + max);
			if(GUtil.getOreDictionaryEntries(i).size() != 0) 
			{
				toolTip.add("  §lOre Dictionary:");
				for(String s : GUtil.getOreDictionaryEntries(i)) toolTip.add("    - " + s);
			}
			
		}
		else
		{
			toolTip.add("§7§oPress SHIFT to see more information...");
		}
		if(GuiScreen.isAltKeyDown()) 
		{
			toolTip.add(is.serializeNBT().toString());
		}
		else toolTip.add("Press ALT to see NBT Data...");
	}
}