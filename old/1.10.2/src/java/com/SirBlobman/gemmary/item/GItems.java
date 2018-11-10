package com.SirBlobman.gemmary.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GItems
{
	public static Gem amethyst = new Gem("amethyst", 7.0D);
	public static Gem corundum = new Gem("corundum", 9.0D);
	public static Gem ruby = new Gem("ruby", 9.0D);
	public static Gem sapphire = new Gem("sapphire", 9.0D);
	public static Gem talc = new Gem("talc", 1.0D);
	public static Gem topaz = new Gem("topaz", 8.0D);
	public static Gem tanzanite = new Gem("tanzanite", 6.75D);
	public static Gem turquoise = new Gem("turquoise", 5.5D);
	
	public static final void gems()
	{
		r(amethyst);
		r(corundum);
		/*r(ruby);
		r(sapphire);
		r(talc);
		r(topaz);
		r(tanzanite);
		r(turquoise);*/
	}
	
	private static void r(Item i) {GameRegistry.register(i);}
}