package com.SirBlobman.gemmary.gui;

import com.SirBlobman.gemmary.gui.container.ContainerCompressor;
import com.SirBlobman.gemmary.gui.container.ContainerHydrothermal;
import com.SirBlobman.gemmary.tile.TileCompressor;
import com.SirBlobman.gemmary.tile.TileHydrothermal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int COMPRESSOR = 0;
	public static final int COMBINER = 1;
	public static final int RECIPE_BOOK = 2;
	public static final int HYDROTHERMAL = 3;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer ep, World w, int x, int y, int z)
	{
		BlockPos xyz = new BlockPos(x,y,z);
		TileEntity te = w.getTileEntity(xyz);
		
		if(ID == COMPRESSOR)
		{
			ContainerCompressor cc = new ContainerCompressor(ep.inventory, (TileCompressor) te);
			return cc;
		}
		if(ID == HYDROTHERMAL)
		{
			ContainerHydrothermal ch = new ContainerHydrothermal(ep.inventory, (TileHydrothermal) te);
			return ch;
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer ep, World w, int x, int y, int z)
	{
		BlockPos xyz = new BlockPos(x,y,z);
		TileEntity te = w.getTileEntity(xyz);
		
		if(te instanceof TileCompressor)
		{
			TileCompressor tc = (TileCompressor) te;
			return new GuiCompressor(ep.inventory, tc);
		}
		if(te instanceof TileHydrothermal)
		{
			TileHydrothermal th = (TileHydrothermal) te;
			return new GuiHydrothermal(ep.inventory, th);
		}
		if(ID == RECIPE_BOOK) return new GuiRecipeBook();
		
		return null;
	}
}