package com.SirBlobman.gemmary.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CommandGemmary extends CommandBase implements ICommand
{
	@SuppressWarnings("rawtypes")
	private final List aliases;
	
	protected String fullEntityName;
	protected Entity conjuredEntity;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CommandGemmary()
	{
		aliases = new ArrayList();
		aliases.add("gemmary");
		aliases.add("gemm");
	}
	
	@Override
	public String getCommandName()
	{
		return "gemmary";
	}
	
	@Override
	public String getCommandUsage(ICommandSender v1)
	{
		return "/gemmary <mohsScale|suicide>";
	}
	
	@Override
	public void processCommand(ICommandSender s, String[] string)
	{
		World w = s.getEntityWorld();
		
		if(w.isRemote)
		{
			System.out.println("[Gemmary] Command '/gemmary' has been processed Client-Side");
		}
		else
		{
			System.out.println("[Gemmary] Command '/gemmary' has been processed Server-Side");
			if(string.length == 0)
			{
				s.addChatMessage(new ChatComponentText(""));
				return;
			}
			
			if("mohsScale".equalsIgnoreCase(string[0]))
			{
				s.addChatMessage(new ChatComponentText("This command doesn't work yet!"));
			}
			
			if("suicide".equalsIgnoreCase(string[0]))
			{
				s.getCommandSenderEntity().setDead();
				s.addChatMessage(new ChatComponentText("You just died! HAHAHAHAHAHAHAHAHAHAHA"));
			}
			else
			{
				s.addChatMessage(new ChatComponentText("Invalid Argument. Valid arguments are mohsScale and Suicide"));
			}
		}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender v1)
	{
		return true;
	}

	@Override
	public int compareTo(ICommand arg0) 
	{
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCommandAliases() 
	{
		return this.aliases;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) 
	{
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"mohsScale", "suicide"}): null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) 
	{
		return false;
	}
}