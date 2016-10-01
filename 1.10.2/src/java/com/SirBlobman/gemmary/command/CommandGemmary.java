package com.SirBlobman.gemmary.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.SirBlobman.gemmary.GUtil;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.oredict.OreDictionary;

public class CommandGemmary extends CommandBase implements ICommand
{
	private final List<String> alias;
	String name;
	Entity ent;
	
	public CommandGemmary()
	{
		alias = new ArrayList<String>();
		alias.add("gemmary");
		alias.add("gem");
	}
	
	@Override
	public String getCommandName() {return "gemmary";}
	
	@Override
	public String getCommandUsage(ICommandSender cs)
	{
		String usage = "\u00a7f/gemmart [mohsScale | suicide | oreDictionary | getClass]";
		return usage;
	}
	
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer ms, ICommandSender ics, String[] args, BlockPos pos)
	{
		boolean b1 = args.length == 1;
		List<String> l1 = getListOfStringsMatchingLastWord(args, new String[] {"mohsScale", "suicide", "OreDictionary", "getClass"});
		List<String> l2 = Collections.emptyList();
		return b1 ? l1 : l2;
	}
	
	@Override
	public void execute(MinecraftServer ms, ICommandSender cs, String[] args)
	{
		Entity e = cs.getCommandSenderEntity();
		if(e != null && cs.getCommandSenderEntity() instanceof EntityPlayer)
		{
			EntityPlayer ep = (EntityPlayer) e;
			String sub = args[0].toLowerCase();
			if(args.length > 0)
			{
				String air = I18n.format("command.gemmary.error.holdingAir");
				if(sub.equals("mohsscale"))
				{
					ItemStack held = ep.getHeldItemMainhand();
					Item heldi = held.getItem();
					if(heldi == null) 
					{
						ep.addChatComponentMessage(new TextComponentString(air));
						return;
					}
					else
					{
						TextComponentString msg = new TextComponentString(GUtil.getMohsOfItem(heldi));
						ep.addChatComponentMessage(msg);
						return;
					}
				}
				if(sub.equals("oredictionary"))
				{
					ItemStack held = ep.getHeldItemMainhand();
					if(held == null)
					{
						ep.addChatComponentMessage(new TextComponentString(air));
						return;
					}
					else
					{
						int[] ids = OreDictionary.getOreIDs(held);
						ep.addChatComponentMessage(new TextComponentString("OreDictionary Names:"));
						for(int id : ids)
						{
							String name = OreDictionary.getOreName(id);
							ep.addChatComponentMessage(new TextComponentString(" - " + name));
						}
						return;
					}
				}
				if(sub.equals("suicide"))
				{
					ep.setHealth(0.0F);
					ep.addChatComponentMessage(new TextComponentString(GUtil.gemmary + "Oops! You killed your self!"));
					return;
				}
				if(sub.equals("getclass"))
				{
					ItemStack held = ep.getHeldItemMainhand();
					if(held == null)
					{
						ep.addChatComponentMessage(new TextComponentString(air));
						return;
					}
					else
					{
						Item i = held.getItem();
						Class<? extends Item> c = i.getClass();
						ep.addChatComponentMessage(new TextComponentString("Item Class: " + c.getName()));
					}
				}
				ep.addChatComponentMessage(new TextComponentString(GUtil.IA));
				return;
			}
			ep.addChatComponentMessage(new TextComponentString(GUtil.NEA));
			return;
		}
		cs.addChatMessage(new TextComponentString(GUtil.csNotPlayer));
		return;
	}
}