package com.SirBlobman.gemmary.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.SirBlobman.gemmary.GUtil;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CommandGemmary extends CommandBase implements ICommand
{
	Object p = new Object[0];
	private final ArrayList aliases;
	
	protected String fullEntityName;
	protected Entity conjuredEntity;
	
	public CommandGemmary()
	{
		aliases = new ArrayList();
		aliases.add("gemmary");
		aliases.add("gem");
	}
	
	@Override
	public String getCommandName()
	{
		return "gemmary";
	}
	
	@Override
	public String getCommandUsage(ICommandSender ics)
	{
		return "§f/gemmary [mohsscale | suicide]";
	}
	
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"mohsScale", "suicide"}): Collections.<String>emptyList();
    }

	@Override
	public void execute(MinecraftServer srvr, ICommandSender s, String[] args) throws CommandException 
	{
		EntityPlayer player = (EntityPlayer)s.getCommandSenderEntity();
		if(player == null)
		{
			return;
		}
		if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("mohsscale"))
			{
				String air = new String(GUtil.translate("command.gemmary.error.holdingAir"));
				Item held_item = player.getHeldItemMainhand().getItem();
				

				if(held_item == null)
				{
					player.addChatComponentMessage(new TextComponentString(air));
				}
				else
				{
					player.addChatComponentMessage(new TextComponentString(GUtil.getMohsValueOfItem(held_item)));
				}
			}
			if(args[0].equalsIgnoreCase("suicide"))
			{
				player.setHealth(0.0F);
				player.addChatComponentMessage(new TextComponentString("§5[§3Gemmary§5]§r Oops! You killed your self!"));
			}
		}
		else
		{
			player.addChatComponentMessage(new TextComponentString("§5[§3Gemmary§5]§r Thanks for testing Gemmary"));
			player.addChatComponentMessage(new TextComponentString(getCommandUsage(s)));
		}
	}
}
