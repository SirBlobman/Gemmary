package com.SirBlobman.gemmary.proxy;

import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;
import com.SirBlobman.gemmary.tileentity.container.ContainerCompressor;
import com.SirBlobman.gemmary.tileentity.gui.GuiCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GemmaryGuiHandler implements IGuiHandler {
    public static final int
        COMPRESSOR = 0
    ;
    
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(blockPos);
        InventoryPlayer inv = player.inventory;
        
        if(id == COMPRESSOR) {
            TileEntityCompressor tileCompressor = (TileEntityCompressor) tileEntity;
            return new GuiCompressor(inv, tileCompressor);
        }
        
        return null;
    }
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(blockPos);
        InventoryPlayer inv = player.inventory;
        
        if(id == COMPRESSOR) {
            TileEntityCompressor tileCompressor = (TileEntityCompressor) tileEntity;
            return new ContainerCompressor(inv, tileCompressor);
        }
        
        return null;
    }
}