package com.SirBlobman.gemmary.gui;

import com.SirBlobman.gemmary.container.ContainerCompressor;
import com.SirBlobman.gemmary.container.ContainerHydrothermal;
import com.SirBlobman.gemmary.tile.CompressorTE;
import com.SirBlobman.gemmary.tile.HydrothermalTE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int Compressor_GUI = 0;
	public static final int Combiner_GUI = 1;
	public static final int RecipeBook_GUI = 2;
	public static final int HydroThermalVent_GUI = 3;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer p, World w, int x, int y, int z)
	{
		if(ID == Compressor_GUI)
		{
			return new ContainerCompressor(p.inventory, (CompressorTE)w.getTileEntity(new BlockPos(x, y, z)));
		}
		if(ID == HydroThermalVent_GUI)
		{
			return new ContainerHydrothermal(p.inventory, (HydrothermalTE)w.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer p, World w, int x, int y, int z)
	{
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity te = w.getTileEntity(xyz);
		
		if(te instanceof CompressorTE)
		{
			CompressorTE tC = (CompressorTE)te;
			return new GuiCompressor(p.inventory, tC);
		}
		if(te instanceof HydrothermalTE)
		{
			HydrothermalTE tH = (HydrothermalTE)te;
			return new GuiHydrothermal(p.inventory, tH);
		}
		
		if(ID == RecipeBook_GUI)
		{
			return new GuiRecipeBook();
		}
		
		return null;
	}
}
