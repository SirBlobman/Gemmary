package com.SirBlobman.gemmary.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class Cloth extends ItemTool
{
	public static ToolMaterial CLOTH = EnumHelper.addToolMaterial("CLOTH", 0, 10, 0.0F, 0.0F, 0);
	@SuppressWarnings("rawtypes")
	private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.air});
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cloth(String name, float attackDamage, ToolMaterial material, Set effectiveBlocks)
	{
		super(attackDamage, material, effectiveBlocks);
		this.setUnlocalizedName(name);
		this.setCreativeTab(GemmaryRandomItems.Other);
	}
	
	@SuppressWarnings("rawtypes")
	public Cloth(String name, ToolMaterial material, Set effectiveBlocks) 
	{
		this(name, 0.0f, material, (Set) effectiveBlocks);
	}

	public Cloth(String name) 
	{
		this(name, CLOTH, EFFECTIVE_ON);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public ItemStack getContainerItem(ItemStack stack)
	{
		stack.attemptDamageItem(1, this.itemRand);
		return stack;
	}
}
