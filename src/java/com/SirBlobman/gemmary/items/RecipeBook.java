package com.SirBlobman.gemmary.items;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class RecipeBook extends Item
{
	public RecipeBook(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(GemmaryRandomItems.Other);
		this.setMaxStackSize(1);
	}
	@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (worldIn.isRemote) 
        {
            playerIn.openGui(Gemmary.instance, 1, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
            FMLLog.info(I18n.format("recipe.book.opened", new Object[0]));
        }
        return itemStackIn;
    }
}
