package com.SirBlobman.gemmary.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Atom extends Item 
{
	public Atom(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryElements.Elements);
		this.setMaxStackSize(1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack par1, EntityPlayer par2, @SuppressWarnings("rawtypes") List par3, boolean par4)
	{
		par3.add(I18n.format("lore.atoms" , new Object[0]));
	}
}
