package com.SirBlobman.gemmary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.SirBlobman.gemmary.block.GemBlock;
import com.SirBlobman.gemmary.block.OreBlock;
import com.SirBlobman.gemmary.block.OreBlockFalling;
import com.SirBlobman.gemmary.item.Atom;
import com.SirBlobman.gemmary.item.Gem;
import com.SirBlobman.gemmary.item.armor.Armor;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class GUtil 
{
	public static final String gemmary = I18n.format("gemmary.prefix") + " ";
	public static final String csNotPlayer = gemmary + "You are not a Player";
	public static final String IA = gemmary + "\u00a74Invalid Arguments";
	public static final String NEA = gemmary + "\u00a74Not Enough Arguments!";
	
	
	public static void print(String msg) {FMLLog.info(gemmary + msg);}
	
	public static void msg(EntityPlayer p, String msg)
	{
		TextComponentString tcs = new TextComponentString(msg);
		p.addChatMessage(tcs);
	}
	
	private static Map<Item, Double> mohs = Maps.newHashMap();
	public static String getMohsOfItem(Item i)
	{
		String yes = I18n.format("command.gemmary.mohsScale") + ": ";
		String no = I18n.format("command.gemmary.error.noMohsSCale");
		
		if(mohs.get(i) == null) return no;
		return yes + "\u00a7b" + getMohs(i);
	}
	
	public static double getMohs(Item i)
	{
		if(mohs.containsKey(i)) return mohs.get(i);
		return 0.0D;
	}
	
	public static void setMohs(Item i, double value)
	{
		mohs.put(i, value);
	}
	
	public static List<Atom> atoms()
	{
		IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
		Iterator<Item> items = ITEMS.iterator();
		List<Atom> atoms = new ArrayList<Atom>();
		while(items.hasNext())
		{
			Item i = items.next();
			if(i instanceof Atom) atoms.add((Atom) i);
		}
		
		return atoms;
	}
	
	public static List<Armor> armor()
	{
		IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
		Iterator<Item> items = ITEMS.iterator();
		List<Armor> armor = new ArrayList<Armor>();
		while(items.hasNext())
		{
			Item i = items.next();
			if(i instanceof Armor) armor.add((Armor) i);
		}
		
		return armor;
	}
	
	public static List<ItemStack> getOreDictAtoms() {return OreDictionary.getOres("atom");}
	
	public static List<GemBlock> gemBlocks()
	{
		IForgeRegistry<Block> BLOCKS = ForgeRegistries.BLOCKS;
		Iterator<Block> blocks = BLOCKS.iterator();
		List<GemBlock> gems = new ArrayList<GemBlock>();
		while(blocks.hasNext())
		{
			Block b = blocks.next();
			if(b instanceof GemBlock) gems.add((GemBlock) b);
		}
		return gems;
	}
	
	public static List<Block> ores()
	{
		IForgeRegistry<Block> BLOCKS = ForgeRegistries.BLOCKS;
		Iterator<Block> blocks = BLOCKS.iterator();
		List<Block> ores = new ArrayList<Block>();
		while(blocks.hasNext())
		{
			Block b = blocks.next();
			boolean b1 = b instanceof OreBlock;
			boolean b2 = b instanceof OreBlockFalling;
			if(b1 || b2) ores.add(b);
		}
		return ores;
	}
	
	public static List<Gem> gems()
	{
		IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
		Iterator<Item> items = ITEMS.iterator();
		List<Gem> gems = new ArrayList<Gem>();
		while(items.hasNext())
		{
			Item i = items.next();
			if(i instanceof Gem) gems.add((Gem) i);
		}

		return gems;	
	}
	
	public static void regEvents(Object... listeners)
	{
		for(Object o : listeners)
		{
			if(o != null)
			{
				EventBus EB = MinecraftForge.EVENT_BUS;
				EB.register(o);
			}	
		}
	}
}