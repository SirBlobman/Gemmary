package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.proxy.GemmaryGuiHandler;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCompressor extends BlockAbstractMachine {
    public BlockCompressor() {super("compressor");}
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {return GemmaryBlocks.ITEM_COMPRESSOR;}
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {return new ItemStack(GemmaryBlocks.COMPRESSOR);}
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCompressor();
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        if(world.isRemote) return true;
        else {
            ep.openGui(Gemmary.INSTANCE, GemmaryGuiHandler.COMPRESSOR, world, bp.getX(), bp.getY(), bp.getZ());
            return true;
        }
    }
}