package com.SirBlobman.gemmary.command;

import com.SirBlobman.gemmary.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.oredict.OreDictionary;

public class CommandGemmary extends CommandBase {
    public String getName() {return "gemmary";}
    public String getUsage(ICommandSender cs) {return "/gemmary <ore/class>";}

    @Override
    public void execute(MinecraftServer ms, ICommandSender cs, String[] args) {
        if(args.length > 0) {
            Entity en = cs.getCommandSenderEntity();
            if(en != null && en instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) en;
                ItemStack held = elb.getHeldItemMainhand();
                if(held != null && !held.isEmpty()) {
                    String sub = args[0].toLowerCase();
                    if(sub.equals("ore")) {
                        int[] ids = OreDictionary.getOreIDs(held);
                        if(ids.length > 0) {
                            String msg1 = I18n.format("command.gemmary.ore_dictionary_ids");
                            Util.sendMessage(elb, msg1);
                            String[] stringIDs = new String[ids.length];
                            for(int i = 0; i < ids.length; i++) {
                                int id = ids[i];
                                String stringID = OreDictionary.getOreName(id);
                                stringIDs[i] = "\u00a77 - " + stringID;
                            }
                            Util.sendMessage(elb, stringIDs);
                        } else {
                            String error = I18n.format("command.gemmary.error.no_ore_dictionary_ids");
                            Util.sendMessage(elb, error);
                        }
                    } else if(sub.equals("class")) {
                        Item item = held.getItem();
                        Class<? extends Item> clazz = item.getClass();
                        String name = clazz.getName();
                        String msg = I18n.format("command.gemmary.item_class", name);
                        Util.sendMessage(elb, msg);
                        
                        if(item instanceof ItemBlock) {
                            ItemBlock ib = (ItemBlock) item;
                            Block block = ib.getBlock();
                            Class<? extends Block> clazz1 = block.getClass();
                            String name1 = clazz1.getName();
                            String msg1 = I18n.format("command.gemmary.block_class", name1);
                            Util.sendMessage(elb, msg1);
                        }
                    }
                } else {
                    String error = I18n.format("command.gemmary.error.holding_nothing");
                    Util.sendMessage(elb, error);
                }
            } else {
                String error = I18n.format("command.gemmary.error.not_living");
                Util.sendMessage(cs, error);
            }
        } else {
            String error = I18n.format("command.gemmary.error.not_enough_arguments");
            Util.sendMessage(cs, error);
        }
    }
}