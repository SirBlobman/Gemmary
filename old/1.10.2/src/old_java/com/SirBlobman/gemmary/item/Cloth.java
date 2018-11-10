package com.SirBlobman.gemmary.item;

import java.util.Set;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class Cloth extends ItemTool
{
	private static final int maxDamage = Gemmary.cloth_durability;
	private static final ToolMaterial CLOTH = EnumHelper.addToolMaterial("cloth", 0, maxDamage, 0.0F, 0.0F, 0);
	private static final Set<Block> EFF = Sets.newHashSet();
	
	public Cloth()
	{
		super(0.0F, 0.0F, CLOTH, EFF);
		setUnlocalizedName("cloth");
		setRegistryName("cloth");
		setCreativeTab(GemmaryTabs.ITEMS);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack is) {return true;}
	
	@Override
	public ItemStack getContainerItem(ItemStack is)
	{
		ItemStack copy = is.copy();
		copy.attemptDamageItem(1, Cloth.itemRand);
		return copy;
	}
}