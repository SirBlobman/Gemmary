package com.SirBlobman.gemmary.block.custom;

import com.SirBlobman.gemmary.creative.GTabs;
import com.SirBlobman.gemmary.utility.ItemUtil;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAtomGatherer extends BlockHorizontal {
    public final boolean automatic;
    public BlockAtomGatherer(boolean automatic) {
        super(Material.ANVIL);
        this.automatic = automatic;
        if(automatic) {
            setRegistryName("auto_atom_gatherer");
            setTickRandomly(true);
        } else setRegistryName("atom_gatherer");
        setUnlocalizedName("atom_gatherer");
        setCreativeTab(GTabs.BLOCKS);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
    
    public BlockStateContainer createBlockState() {return new BlockStateContainer(this, FACING);}
    public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
    public boolean isFullCube(IBlockState ibs) {return false;}
    public boolean isOpaqueCube(IBlockState ibs) {return false;}
    public int getMetaFromState(IBlockState ibs) {return ibs.getValue(FACING).getHorizontalIndex();}
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing ef = EnumFacing.getHorizontal(meta);
        return getDefaultState().withProperty(FACING, ef);
    } 
    
    @Override
    public IBlockState withRotation(IBlockState ibs, Rotation rot) {
        EnumFacing ef = ibs.getValue(FACING);
        EnumFacing efn = rot.rotate(ef);
        return ibs.withProperty(FACING, efn);
    }
    
    @Override
    public IBlockState withMirror(IBlockState ibs, Mirror mirror) {
        EnumFacing ef = ibs.getValue(FACING);
        Rotation rot = mirror.toRotation(ef);
        return ibs.withRotation(rot);
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos bp, EnumFacing ef, float x, float y, float z, int meta, EntityLivingBase placer) {
        IBlockState ibs = getDefaultState();
        EnumFacing pef = placer.getHorizontalFacing();
        EnumFacing oef = pef.getOpposite();
        return ibs.withProperty(FACING, oef);
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        if(!automatic && !world.isRemote) {
            List<ItemStack> atoms = ItemUtil.getAtoms();
            int random = new Random().nextInt(atoms.size());
            ItemStack atom = atoms.get(random);
            EntityItem ei = new EntityItem(world, bp.getX() + 0.5D, bp.getY() + 1.5D, bp.getZ() + 0.5D, atom);
            world.spawnEntity(ei);
        } return true;
    }
    
    @Override
    public void randomTick(World world, BlockPos bp, IBlockState ibs, Random rand) {
        if(!world.isRemote) {
            List<ItemStack> atoms = ItemUtil.getAtoms();
            int random = rand.nextInt(atoms.size());
            ItemStack atom = atoms.get(random);
            EntityItem ei = new EntityItem(world, bp.getX() + 0.5D, bp.getY() + 1.5D, bp.getZ() + 0.5D, atom);
            world.spawnEntity(ei);
        }
    }
    
    @Override
    public void addInformation(ItemStack is, World world, List<String> lore, ITooltipFlag flag) {
        if(automatic) {
            lore.add("Automatic");
            lore.add("  This version of the Atom Gatherer will spawn atoms randomly");        
        } else {
            lore.add("Manual");
            lore.add("  This version of the Atom Gatherer requires you to right click it");
        }
    }
}