package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DiamondTNT extends Block
{
	public static final PropertyBool Explode = PropertyBool.create("explode");
	
	public DiamondTNT()
	{
		super(Material.tnt);
		setDefaultState(blockState.getBaseState().withProperty(Explode, false));
		setCreativeTab(GemmaryTabs.Blocks);
		setUnlocalizedName("diamond_tnt");
		setRegistryName("diamond_tnt");
		setStepSound(SoundType.PLANT);
	}
	
	@Override
	public void onBlockAdded(World w, BlockPos pos, IBlockState ibs)
	{
		super.onBlockAdded(w, pos, ibs);
		if(w.isBlockPowered(pos))
		{
			onBlockDestroyedByPlayer(w, pos, ibs.withProperty(Explode, true));
			w.setBlockToAir(pos);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World w, BlockPos pos, IBlockState ibs, Block neighbor)
	{
		if(w.isBlockPowered(pos))
		{
			onBlockDestroyedByPlayer(w, pos, ibs.withProperty(Explode, true));
			w.setBlockToAir(pos);
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World w, BlockPos pos, Explosion e)
	{
		if(w.isRemote)
		{
			EntityTNTPrimed tnt = new EntityTNTPrimed(w, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, e.getExplosivePlacedBy());
			tnt.setFuse(w.rand.nextInt(tnt.getFuse() / 4) + tnt.getFuse() / 8);
			w.spawnEntityInWorld(tnt);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World w, BlockPos pos, IBlockState ibs)
	{
		explode(w, pos, ibs, null);
	}
	
	public void explode(World w, BlockPos pos, IBlockState ibs, EntityLivingBase igniter)
	{
		if(!w.isRemote)
		{
			if(ibs.getValue(Explode).booleanValue())
			{
				w.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 50.0F, false, true);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState ibs, EntityPlayer p, EnumHand hand, ItemStack heldItem, EnumFacing side, float X, float Y, float Z)
	{
		if(heldItem != null)
		{
			Item i = heldItem.getItem();
			
			if(i == Items.flint_and_steel || i == Items.fire_charge)
			{
				explode(w, pos, ibs.withProperty(Explode, true), p);
				w.setBlockToAir(pos);
				
				if(i == Items.flint_and_steel)
				{
					heldItem.damageItem(1, p);
				}
				else if(!p.capabilities.isCreativeMode)
				{
					--heldItem.stackSize;
				}
				
				return true;
			}
		}
		
		return super.onBlockActivated(w, pos, ibs, p, hand, heldItem, side, X, Y, Z);
	}
	
	@Override
	 public void onEntityCollidedWithBlock(World w, BlockPos pos, Entity e)
	 {
		if(!w.isRemote && e instanceof EntityArrow)
		{
			EntityArrow arrow = (EntityArrow)e;
			
			if(arrow.isBurning())
			{
				explode(w, pos, w.getBlockState(pos).withProperty(Explode, true), arrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)arrow.shootingEntity : null);
				w.setBlockToAir(pos);
			}
		}
	 }
	
	@Override
	public boolean canDropFromExplosion(Explosion e)
	{
		return false;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(Explode, Boolean.valueOf((meta & 1) > 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		return ibs.getValue(Explode).booleanValue() ? 1 : 0;
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {Explode});
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState ibs)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState ibs)
	{
		return false;
	}
}