package com.SirBlobman.gemmary;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class GUtil
{
	public static final String gemmary = I18n.format("gemmary.prefix") + " ";
	public static final String notPlayer = gemmary + "You are not a Player";
	public static final String IA = gemmary + "\u00a74Invalid Arguments";
	public static final String NEA = gemmary + "\u00a74Not Enough Arguments";
	
	public static void print(String msg)
	{
		String m = gemmary + msg;
		FMLLog.info(m);
	}
	
	public static void msg(EntityPlayer ep, String msg)
	{
		TextComponentString txt = new TextComponentString(msg);
		ep.addChatComponentMessage(txt);
	}
	
	private static Map<Item, Double> mohs = Maps.newHashMap();
	public static String getMohsString(Item i)
	{
		String yes = I18n.format("command.gemmary.mohsScale") + ": ";
		String no = I18n.format("command.gemmary.error.noMohsScale");
		double m = getMohs(i);
		if(m == Double.NaN || m == 0.0D) return no;
		return yes + "\00a7b" + m;
	}
	
	public static double getMohs(Item i)
	{
		if(mohs.containsKey(i)) return mohs.get(i);
		return 0.0D;
	}
	
	public static void setMohs(Item i, double m) {mohs.put(i, m);}
	
	public static void regEvents(Object... list)
	{
		for(Object o : list)
		{
			if(o != null)
			{
				EventBus EB = MinecraftForge.EVENT_BUS;
				EB.register(o);
			}
		}
	}
}