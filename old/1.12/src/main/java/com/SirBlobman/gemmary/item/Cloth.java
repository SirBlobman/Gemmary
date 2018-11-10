package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.config.Config;
import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.SirBlobman.gemmary.utility.Util;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class Cloth extends ItemTool {
	private static final int MAX_DAMAGE = Config.CLOTH_DURABILITY;
	private static final ToolMaterial CLOTH = EnumHelper.addToolMaterial("cloth", 0, MAX_DAMAGE, 0.0F, 0.0F, 0);
	private static final Set<Block> EFFECTIVE_BLOCKS = Util.newSet();
	public Cloth() {
		super(0.0F, 0.0F, CLOTH, EFFECTIVE_BLOCKS);
		setUnlocalizedName("cloth");
		setRegistryName("cloth");
		setCreativeTab(GemmaryTabs.ITEMS);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack is) {return true;}
	
	@Override
	public ItemStack getContainerItem(ItemStack is) {
		ItemStack cis = is.copy();
		cis.attemptDamageItem(1, itemRand, null);
		return cis;
	}
}