package com.SirBlobman.gemmary.ores;

import com.SirBlobman.gemmary.items.GemmaryGems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GemmaryOres 
{
	//Creative Tab
	public static final CreativeTabs BlocksOres = new CreativeTabs("Blocks_Ores")
	{
		@Override
		public Item getTabIconItem() 
		{
			return Item.getItemFromBlock(GemmaryOres.amethystOre);
		}		
	};
	
	//Ore Block Objects (ABC Order)
	public static Block amethystOre;
	public static Block quartzCrystal;
	public static Block rubyOre;
	public static Block sapphireOre;
	public static Block talcOre;
	public static Block tanzaniteOre;
	public static Block topazOre;
	public static Block turquoiseOre;
	
	
	public static void createOres()
	{
		GameRegistry.registerBlock(amethystOre = new Ore("amethyst_ore", Material.rock, GemmaryGems.amethyst, 1, 2, 4, "pickaxe", 2), "amethyst_ore");
		GameRegistry.registerBlock(quartzCrystal = new QuartzCrystals("quartz_crystals", Material.rock, Items.quartz, 2, 4), "quartz_crystals");
		GameRegistry.registerBlock(rubyOre = new Ore("ruby_ore", Material.rock, GemmaryGems.ruby, 1, 2, 4, "pickaxe", 3), "ruby_ore");
		GameRegistry.registerBlock(sapphireOre = new Ore("sapphire_ore", Material.rock, GemmaryGems.sapphire, 1, 2, 4, "pickaxe", 3), "sapphire_ore");
		GameRegistry.registerBlock(talcOre = new Ore("talc_ore", Material.rock, GemmaryGems.talc, 1, 2, 4, "pickaxe", 0), "talc_ore");
		GameRegistry.registerBlock(tanzaniteOre = new Ore("tanzanite_ore", Material.rock, GemmaryGems.tanzanite, 1, 2, 4, "pickaxe", 2), "tanzanite_ore");
		GameRegistry.registerBlock(topazOre = new Ore("topaz_ore", Material.rock, GemmaryGems.topaz, 1, 2, 4, "pickaxe", 2), "topaz_ore");
		GameRegistry.registerBlock(turquoiseOre = new Ore("turquoise_ore", Material.rock, GemmaryGems.turquoise, 1, 2, 4, "pickaxe", 1), "turquoise_ore");
	}
}
