package com.SirBlobman.gemmary.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.*;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Util {
	public static final String PREFIX = format("prefix.gemmary");
	
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
	
	public static String[] color(String... ss) {
		int length = (ss.length - 1);
		for(int i = length; i > -1; i--) {
			String c = color(ss[i]);
			ss[i] = c;
		}
		return ss;
	}
	
	public static String strip(String o) {
		if(o == null) return null;
		String s = o.replace('\u00a7', '&');
		return s;
	}
	
	public static String[] strip(String... ss) {
		int length = (ss.length - 1);
		for(int i = length; i > -1; i--) {
			String c = strip(ss[i]);
			ss[i] = c;
		}
		return ss;
	}
	
	public static String format(Object o, Object... os) {
		String s = str(o);
		String f = f(s, os);
		String c = color(f);
		return c;
	}
	
	private static String f(String s, Object... os) {
		try {
			String f = I18n.format(s, os);
			return f;
		} catch(Throwable ex) {
			String f = String.format(s, os);
			return f;
		}
	}
	
	public static String str(Object o) {
		if(o == null) return "";
		if((o instanceof Short) || (o instanceof Integer) || (o instanceof Long)) {
			Number n = (Number) o;
			long l = n.longValue();
			return Long.toString(l);
		} else if((o instanceof Float) || (o instanceof Double) || (o instanceof Number)) {
			Number n = (Number) o;
			double d = n.doubleValue();
			return Double.toString(d);
		} else if(o instanceof Boolean) {
			boolean b = (boolean) o;
			String s = b ? "yes" : "no";
			return s;
		} else if(o instanceof InetSocketAddress) {
			InetSocketAddress isa = (InetSocketAddress) o;
			String host = isa.getHostString();
			return host;
		} else if(o instanceof String) {
			String s = (String) o;
			return s;
		} else {
			String s = o.toString();
			return s;
		}
	}
	
	public static void print(Object... os) {
		for(Object o : os) {
			String s = str(o);
			String c = color(s);
			Logger l = LogManager.getLogger(PREFIX);
			l.info(c);
		}
	}
	
	public static void regEvents(Object... os) {
		EventBus EB = MinecraftForge.EVENT_BUS;
		for(Object o : os) {
			if(o != null) EB.register(o);
		}
	}
	
	@SafeVarargs
	public static <L> List<L> newList(L... ls) {
		List<L> list = new ArrayList<L>();
		for(L l : ls) list.add(l);
		return list;
	}
	
	@SafeVarargs
	public static <S> Set<S> newSet(S... ss) {
		Set<S> set = new HashSet<S>();
		for(S s : ss) set.add(s);
		return set;
	}
	
	public static <K, V> Map<K, V> newMap() {
		Map<K, V> map = new HashMap<K, V>();
		return map;
	}
	
	public static Biome[] getBiomes(String id) {
		final String check = id.toLowerCase();
		List<Biome> list = newList();
		Biome.REGISTRY.forEach(b -> {
			String name = b.getRegistryName().toString();
			String l = name.toLowerCase();
			if(l.contains(check)) list.add(b);
		});
		Biome[] bb = list.toArray(new Biome[0]);
		return bb;
	}

	public static void sendMessage(ICommandSender cs, String msg) {
		String c = PREFIX + color("&f " + msg);
		ITextComponent text = new TextComponentString(c);
		cs.sendMessage(text);
	}
}