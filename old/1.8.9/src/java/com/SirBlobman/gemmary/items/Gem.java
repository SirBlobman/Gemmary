package com.SirBlobman.gemmary.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Gem extends Item
{
	public double MohsValue;
	public Gem(String unlocalizedName, double MohsDouble)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryGems.Gems);
		this.setHasSubtypes(true);
		this.MohsValue = MohsDouble;
	}
	
	public boolean isBeaconPayment(ItemStack stack)
	{
	    return this == GemmaryGems.amethyst || this == GemmaryGems.corundum || this == GemmaryGems.ruby || this == GemmaryGems.sapphire || this == GemmaryGems.talc || this == GemmaryGems.tanzanite || this == GemmaryGems.topaz || this == GemmaryGems.turquoise;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "_" + (stack.getItemDamage() == 0 ? "normal" : "dusty");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List sub)
	{
		sub.add(new ItemStack(item, 1, 0));
		sub.add(new ItemStack(item, 1, 1));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2, @SuppressWarnings("rawtypes") List lore, boolean par4)
	{
		double Mohs = this.MohsValue;
		if (stack.getItemDamage() == 1)
			lore.add(I18n.format("lore.dusty_gems", new Object[0]));
		lore.add(I18n.format("lore.mohs_scale", new Object[0]) + ":" + " " + Mohs);
	}
	
	public double getMohsValue()
	{
		return this.MohsValue;
	}
}
