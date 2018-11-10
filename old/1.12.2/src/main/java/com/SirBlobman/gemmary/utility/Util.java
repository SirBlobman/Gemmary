package com.SirBlobman.gemmary.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Util {
    public static String color(String o) {
        char[] cc = o.toCharArray();
        for(int i = 0; i < cc.length - 1; i++) {
            char c = cc[i];
            char d = cc[i + 1];
            String s = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
            boolean b1 = (c == '&');
            boolean b2 = (s.indexOf(d) > -1);
            if(b1 && b2) {
                cc[i] = '\u00a7';
                cc[i + 1] = Character.toLowerCase(d);
            }
        }

        String s = new String(cc);
        return s;
    }
    
    public static String[] color(String... oo) {
        String[] cc = new String[oo.length];
        for(int i = 0; i < oo.length; i++) {
            String s = oo[i];
            String c = color(s);
            cc[i] = c;
        } return cc;
    }

    public static void print(Object... oo) {
        for(Object o : oo) {
            String s = o.toString();
            String c = color(s);
            Logger log = LogManager.getLogger("Gemmary");
            log.info(c);
        }
    }

    public static void regEvents(Object... oo) {
        for(Object o : oo) {
            if(o != null) {
                EventBus eb = MinecraftForge.EVENT_BUS;
                eb.register(o);
            }
        }
    }
    
    public static boolean call(Event e) {
       EventBus eb = MinecraftForge.EVENT_BUS;
       return eb.post(e);
    }

    @SafeVarargs
    public static <L> List<L> newList(L... ll) {
        List<L> list = new ArrayList<L>();
        for(L l : ll) list.add(l);
        return list;
    }

    public static <L> List<L> newList(Collection<L> ll) {
        List<L> list = new ArrayList<L>();
        for(L l : ll) list.add(l);
        return list;
    }
    
    public static <K, V> Map<K, V> newMap() {
        Map<K, V> map = new HashMap<K, V>();
        return map;
    }
    
    public static int getRGB(int red, int green, int blue) {
        int r = (red << 16) & 0x00FF0000;
        int g = (green << 8) & 0x0000FF00;
        int b = (blue) & 0x000000FF;
        int color = (0xFF000000 | r | g | b);
        return color;
    }
    
    public static String cropDecimal(double d, int places) {
        String format = "%,." + places + "f";
        String dec = String.format(format, d);
        return dec;
    }
    
    public static void sendMessage(ICommandSender cs, String... ss) {
        String[] msg = color(ss);
        for(String s : msg) {
            TextComponentString txt = new TextComponentString(s);
            cs.sendMessage(txt);
        }
    }
}