package com.SirBlobman.gemmary.network;

import com.SirBlobman.gemmary.gui.GuiACTE;
import com.SirBlobman.gemmary.gui.GuiCompressor;
import com.SirBlobman.gemmary.gui.GuiHTV;
import com.SirBlobman.gemmary.gui.GuiRecipeBook;
import com.SirBlobman.gemmary.guicontainer.ContainerACTE;
import com.SirBlobman.gemmary.guicontainer.ContainerCompressor;
import com.SirBlobman.gemmary.guicontainer.ContainerHTV;
import com.SirBlobman.gemmary.tiles.ACTE;
import com.SirBlobman.gemmary.tiles.AHTV_TE;
import com.SirBlobman.gemmary.tiles.CCTE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int MOD_ACTE_GUI = 0;
	public static final int MOD_RECIPE_BOOK = 1;
	public static final int MOD_CCTE_GUI = 2;
	public static final int MOD_AHTV_GUI = 3;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == MOD_CCTE_GUI)
			return new ContainerCompressor(player.inventory, (CCTE) world.getTileEntity(new BlockPos(x, y,z)));
		if(ID == MOD_ACTE_GUI)
			return new ContainerACTE(player.inventory, (ACTE) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == MOD_AHTV_GUI)
			return new ContainerHTV(player.inventory, (AHTV_TE) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof CCTE) 
		{
			CCTE tileCompressor = (CCTE) tileEntity;
			return new GuiCompressor(player.inventory, tileCompressor);
		}
		if (tileEntity instanceof AHTV_TE)
		{
			AHTV_TE tileHTV = (AHTV_TE) tileEntity;
			return new GuiHTV(player.inventory, tileHTV);
		}
		if (ID == MOD_ACTE_GUI)
	        return new GuiACTE(new ContainerACTE(player.inventory, (ACTE) world.getTileEntity(new BlockPos(x, y, z))));
		if (ID == MOD_RECIPE_BOOK)
	        return new GuiRecipeBook();
	    return null;
	}
}
