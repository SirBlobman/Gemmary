package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.proxy.GemmaryGuiHandler;
import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;

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

public class BlockHydrothermalVein extends BlockAbstractMachine {
    public BlockHydrothermalVein() {super("hydrothermal_vein");}
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {return GemmaryBlocks.ITEM_HYDROTHERMAL_VEIN;}
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {return new ItemStack(GemmaryBlocks.HYDROTHERMAL_VEIN);}
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityHydrothermalVein();
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        if(world.isRemote) return true;
        
        ep.openGui(Gemmary.INSTANCE, GemmaryGuiHandler.HYDROTHERMAL_VEIN, world, bp.getX(), bp.getY(), bp.getZ());
        return true;
    }
}