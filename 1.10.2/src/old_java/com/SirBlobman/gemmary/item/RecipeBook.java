package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.SirBlobman.gemmary.gui.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class RecipeBook extends Item
{
	public RecipeBook()
	{
		super();
		setUnlocalizedName("recipe_book");
		setRegistryName("recipe_book");
		setCreativeTab(GemmaryTabs.ITEMS);
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack is, World w, EntityPlayer ep, EnumHand eh)
	{
		if(w.isRemote)
		{
			int x = (int) ep.posX;
			int y = (int) ep.posY;
			int z = (int) ep.posZ;
			
			ep.openGui(Gemmary.instance, GuiHandler.RECIPE_BOOK, w, x, y, z);
		}
		
		ActionResult<ItemStack> ar = new ActionResult<ItemStack>(EnumActionResult.PASS, is);
		return ar;
	}
}