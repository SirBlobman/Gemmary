package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.gui.GuiHandler;
import com.SirBlobman.gemmary.tile.TileHydrothermal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HydrothermalVein extends RotateableContainer
{
	public HydrothermalVein() {super("ahtv");}

	@Override
	public TileEntity createNewTileEntity(World w, int i) {return new TileHydrothermal();}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, ItemStack held, EnumFacing ef, float x, float y, float z)
	{
		if(w.isRemote) return true;
		ep.openGui(Gemmary.instance, GuiHandler.HYDROTHERMAL, w, bp.getX(), bp.getY(), bp.getZ());
		return true;
	}
}