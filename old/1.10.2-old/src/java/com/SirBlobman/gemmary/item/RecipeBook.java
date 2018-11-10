package com.SirBlobman.gemmary.item;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.SirBlobman.gemmary.gui.GuiHandler;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RecipeBook extends Item
{
	public RecipeBook(String name)
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(GemmaryTabs.Items);
		setMaxStackSize(1);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack is, World w, EntityPlayer p, EnumHand h)
	{
		if(w.isRemote)
		{
			int x = (int) p.posX;
			int y = (int) p.posY;
			int z = (int) p.posZ;
			Object[] playerName = new Object[] {p.getName()};
			
			p.openGui(Gemmary.instance, GuiHandler.RecipeBook_GUI, w, x, y, z);
			GUtil.print(I18n.format("recipe_book.openByPlayer", playerName));
		}
		
		return new ActionResult(EnumActionResult.PASS, is);
	}
}
