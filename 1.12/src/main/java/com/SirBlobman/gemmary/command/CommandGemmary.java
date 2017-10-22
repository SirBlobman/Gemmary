package com.SirBlobman.gemmary.command;

import com.SirBlobman.gemmary.utility.ItemUtil;
import com.SirBlobman.gemmary.utility.Util;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandGemmary extends CommandBase implements ICommand {
	//private final List<String> aliases;
	//private String entityName;
	//private Entity entity;
	
	public CommandGemmary() {/*aliases = Util.newList("gemmary", "gem");*/}
	public String getName() {return "gemmary";}
	public String getUsage(ICommandSender cs) {return Util.color("&f/gemmary <mohs/suicide/oredictionary/class>");}
	public List<String> getTabCompletions(MinecraftServer ms, ICommandSender cs, String[] args, BlockPos bp) {
		List<String> list = Util.newList("mohs", "suicide", "oredictionary", "class");
		List<String> empty = Util.newList();
		boolean l = (args.length == 1);
		if(l) {
			List<String> match = getListOfStringsMatchingLastWord(args, list);
			return match;
		} else return empty;
	}
	
	@Override
	public void execute(MinecraftServer ms, ICommandSender cs, String[] args) {
		if(cs instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) cs;
			if(args.length > 0) {
				String sub = args[0].toLowerCase();
				if(sub.equals("mohs")) {
					ItemStack is = ep.getHeldItemMainhand();
					if(!ItemUtil.air(is)) {
						
					} else air(ep);
				} else if(sub.equals("suicide")) {
					ep.setHealth(0.0F);
					Util.sendMessage(ep, "Why would you do that?");
				} else if(sub.equals("oredictionary")) {
					
				} else if(sub.equals("class")) {
					
				}
			} else {
				String error = "Invalid Usage! Try this instead:\n" + getUsage(ep);
				Util.sendMessage(ep, error);
				return;
			}
		} else {
			String error = "You are not an EntityPlayer!";
			Util.sendMessage(cs, error);
			return;
		}
	}
	
	private void air(EntityPlayer ep) {
		String msg = "Air doesn't have any data!";
		Util.sendMessage(ep, msg);
	}
}