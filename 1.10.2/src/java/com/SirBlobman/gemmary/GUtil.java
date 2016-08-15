package com.SirBlobman.gemmary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.SirBlobman.gemmary.block.GemBlock;
import com.SirBlobman.gemmary.item.Element;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.item.Gem;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class GUtil 
{
	/**
	 * Sends a message to {@link FML#getLogger}
	 * If you are using Forge this would be logged
	 * @param msg Message to print
	 */
	public static void print(String msg)
	{
		FMLLog.info("[Gemmary] " + msg);
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
	
	private static Map<Item, Double> mohsScale = new HashMap<Item, Double>();
	
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
	
	public static Double getMohsScaleNumber(Item i)
	{
		getMohsValueOfItem(i);
		if(mohsScale.containsKey(i)) return mohsScale.get(i);
		return 0.0D;
	}
	
	public static void setMohsScaleOfItem(Item i, double mohs)
	{
		if(i == null) return;
		mohsScale.put(i, mohs);
	}
	
	public static List<Element> getElements()
	{
		Iterator<Item> itemList = ForgeRegistries.ITEMS.iterator();
		List<Element> atoms = new ArrayList<Element>();
		while(itemList.hasNext())
		{
			Item i = itemList.next();
			if(i instanceof Element) atoms.add((Element) i);
		}
		
		return atoms;
	}
	
	public static List<ItemStack> getOreDictionaryAtoms()
	{
		return OreDictionary.getOres("atom");
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
	
	public static List<Gem> getGems()
	{
		Iterator<Item> itemList = ForgeRegistries.ITEMS.iterator();
		List<Gem> gems = new ArrayList<Gem>();
		while(itemList.hasNext())
		{
			Item i = itemList.next();
			if(i instanceof Gem) gems.add((Gem) i);
		}
		
		return gems;
	}
	
	public static void regEvents(Object eventClass)
	{
		MinecraftForge.EVENT_BUS.register(eventClass);
	}
	
	public static List<String> getOreDictionaryEntries(Item i)
	{
		int[] entries = OreDictionary.getOreIDs(new ItemStack(i));
		List<String> oreNames = new ArrayList<String>();
		for(int id : entries)
		{
			String oreName = OreDictionary.getOreName(id);
			oreNames.add(oreName);
		}
		Collections.sort(oreNames);
		return oreNames;
	}
}