package com.SirBlobman.gemmary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.SirBlobman.gemmary.block.GemBlock;
import com.SirBlobman.gemmary.item.Element;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GUtil 
{
	/*
	 * Sends a message to the System output
	 * If you are using Forge this would be logged
	 */
	public static void print(String s)
	{
		System.out.print("[Gemmary] " + s);
	}
	
	/*
	 * Sends a chat message to a player
	 * The message can only be a String
	 */
	public static void chat(EntityPlayer p, String msg)
	{
		p.addChatMessage(new TextComponentString(msg));
	}
	
	public static String translate(String m)
	{
		return new String(I18n.format(m));
	}
	
	public static final Map<Item, Double> mohsScale = new HashMap<Item, Double>();
	
	public static String getMohsValueOfItem(Item i)
	{
		String yes = new String(translate("command.gemmary.MohsScale") + ": ");
		String no = new String(translate("command.gemmary.error.noMohsScale"));
		
		mohsScale.put(Items.DIAMOND, 10.0);
		mohsScale.put(Items.EMERALD, 8.0);
		mohsScale.put(GItems.diamondPart, 10.0);
		mohsScale.put(GItems.emeraldPart, 8.0);
		
		if(mohsScale.get(i) == null)
		{
			return no;
		}
		
		return yes + "§b" + mohsScale.get(i);
	}
	
	public static List<Item> getElements()
	{
		Iterator<Item> itemList = ForgeRegistries.ITEMS.iterator();
		List<Item> atoms = new ArrayList<Item>();
		while(itemList.hasNext())
		{
			Item i = itemList.next();
			if(i instanceof Element) atoms.add(i);
		}
		
		return atoms;
	}
	
	public static List<GemBlock> getGemBlocks()
	{
		Iterator<Block> blockList = ForgeRegistries.BLOCKS.iterator();
		List<GemBlock> gemBlocks = new ArrayList<GemBlock>();
		while(blockList.hasNext())
		{
			Block b = blockList.next();
			if(b instanceof GemBlock) gemBlocks.add((GemBlock) b);
		}
		
		return gemBlocks;
	}
}