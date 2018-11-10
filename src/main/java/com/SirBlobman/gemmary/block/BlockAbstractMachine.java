package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.GemmaryCreativeTabs;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockAbstractMachine extends BlockFurnace {
    public BlockAbstractMachine(String name) {
        super(false);
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.ANVIL);
        setCreativeTab(GemmaryCreativeTabs.BLOCKS);
        translucent = true;
    }
    
    @Override
    public boolean isFullCube(IBlockState ibs) {return false;}
    
    @Override
    public boolean isOpaqueCube(IBlockState ibs) {return false;}
    
    @Override
    public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
    
    @Override
    public abstract TileEntity createNewTileEntity(World world, int meta);
    
    @Override
    public abstract boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float x, float y, float z);
    
    @Override
    public void breakBlock(World world, BlockPos bp, IBlockState ibs) {
        TileEntity te = world.getTileEntity(bp);
        if(te instanceof IInventory) {
            IInventory ii = (IInventory) te;
            InventoryHelper.dropInventoryItems(world, bp, ii);
            world.updateComparatorOutputLevel(bp, this);
        }
        super.breakBlock(world, bp, ibs);
    }
}