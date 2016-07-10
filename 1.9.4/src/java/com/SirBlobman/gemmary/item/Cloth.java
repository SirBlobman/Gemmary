package com.SirBlobman.gemmary.item;

import java.util.Set;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Cloth extends ItemTool
{
	public static ToolMaterial CLOTH = EnumHelper.addToolMaterial("cloth", 0, 10, 0.0F, 0.0F, 0);
	private static final Set Effective_On = Sets.newHashSet(new Block[] {Blocks.AIR});
	
	public Cloth(String name)
	{
		super(0.0F, 0.0F, CLOTH, Effective_On);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.Items);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack is)
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack is)
	{
		ItemStack is2 = is.copy();
		is2.attemptDamageItem(1, Cloth.itemRand);
		return is2;
	}
}
