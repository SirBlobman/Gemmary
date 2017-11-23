package com.SirBlobman.gemmary.proxy.network;

import com.SirBlobman.gemmary.proxy.network.container.ContainerCompressor;
import com.SirBlobman.gemmary.proxy.network.container.ContainerHydrothermalVein;
import com.SirBlobman.gemmary.proxy.network.gui.GuiCompressor;
import com.SirBlobman.gemmary.proxy.network.gui.GuiHydrothermalVein;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GemmaryGuiHandler implements IGuiHandler {
    public static final int
        COMPRESSOR = 0,
        HYDROTHERMAL_VEIN = 1;
    
    @Override
    public Object getClientGuiElement(int id, EntityPlayer ep, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(bp);
        InventoryPlayer ip = ep.inventory;
        if(id == COMPRESSOR) {
            TileEntityCompressor tec = (TileEntityCompressor) tile;
            GuiCompressor gui = new GuiCompressor(ip, tec);
            return gui;
        } else if(id == HYDROTHERMAL_VEIN) {
            TileEntityHydrothermalVein thv = (TileEntityHydrothermalVein) tile;
            GuiHydrothermalVein gui = new GuiHydrothermalVein(ip, thv);
            return gui;
        } return null;
    }
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer ep, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(bp);
        InventoryPlayer ip = ep.inventory;
        if(id == COMPRESSOR) {
            TileEntityCompressor tc = (TileEntityCompressor) tile;
            ContainerCompressor cont = new ContainerCompressor(ip, tc);
            return cont;
        } else if(id == HYDROTHERMAL_VEIN) {
            TileEntityHydrothermalVein thv = (TileEntityHydrothermalVein) tile;
            ContainerHydrothermalVein cont = new ContainerHydrothermalVein(ip, thv);
            return cont;
        } return null;
    }
}