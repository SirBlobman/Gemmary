package com.SirBlobman.gemmary.block;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;
import com.SirBlobman.gemmary.entity.DiamondTNTPrimed;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DiamondTNT extends Block
{
	private static final PropertyBool EXPLODE = PropertyBool.create("explode");
	
	public DiamondTNT()
	{
		super(Material.TNT);
		setCreativeTab(GemmaryTabs.BLOCKS);
		String name = "diamond_tnt";
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
	}
	
	@Override
	public void onBlockAdded(World w, BlockPos bp, IBlockState ibs)
	{
		super.onBlockAdded(w, bp, ibs);
		if(w.isBlockPowered(bp))
		{
			IBlockState i = ibs.withProperty(EXPLODE, true);
			onBlockDestroyedByPlayer(w, bp, i);
			w.setBlockToAir(bp);
		}
	}
	
	@Override
	public void neighborChanged(IBlockState ibs, World w, BlockPos bp, Block b)
	{
		if(w.isBlockPowered(bp))
		{
			IBlockState i = ibs.withProperty(EXPLODE, true);
			onBlockDestroyedByPlayer(w, bp, i);
			w.setBlockToAir(bp);
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World w, BlockPos bp, Explosion e)
	{
		if(!w.isRemote)
		{
			DiamondTNTPrimed dtntp = new DiamondTNTPrimed(w, bp.getX(), bp.getY(), bp.getZ(), e.getExplosivePlacedBy());
			dtntp.setFuse(w.rand.nextInt(dtntp.getFuse() / 4) + dtntp.getFuse() / 8);
			w.spawnEntityInWorld(dtntp);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World w, BlockPos bp, IBlockState ibs) {explode(w, bp, ibs, null);}
	
	public void explode(World w, BlockPos bp, IBlockState ibs, EntityLivingBase elb)
	{
		if(!w.isRemote)
		{
			if(ibs.getValue(EXPLODE))
			{
				DiamondTNTPrimed dtnt = new DiamondTNTPrimed(w, bp.getX(), bp.getY(), bp.getZ(), elb);
				w.spawnEntityInWorld(dtnt);
				w.playSound(null, dtnt.posX, dtnt.posY, dtnt.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 100.0F, -1 * Gemmary.diamond_tnt_explosion_size);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, ItemStack held, EnumFacing ef, float x, float y, float z)
	{
		if(held != null)
		{
			Item i = held.getItem();
			if(i == Items.FLINT_AND_STEEL || i == Items.FIRE_CHARGE)
			{
				explode(w, bp, ibs.withProperty(EXPLODE, true), ep);
				w.setBlockToAir(bp);
				if(i == Items.FLINT_AND_STEEL) held.damageItem(1, ep);
				else if(!ep.capabilities.isCreativeMode) --held.stackSize;
				
				return true;
			}
		}
		
		return super.onBlockActivated(w, bp, ibs, ep, eh, held, ef, x, y, z);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, BlockPos bp, IBlockState ibs, Entity e)
	{
		if(!w.isRemote)
		{
			if(e instanceof EntityArrow)
			{
				EntityArrow ea = (EntityArrow) e;
				if(ea.isBurning())
				{
					Entity shot = ea.shootingEntity;
					boolean b1 = shot instanceof EntityLivingBase;
					EntityLivingBase shooter = b1 ? (EntityLivingBase) shot : null;
					explode(w, bp, w.getBlockState(bp).withProperty(EXPLODE, true), shooter);
					w.setBlockToAir(bp);
				}
			}
		}
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion e) {return false;}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState def = getDefaultState();
		IBlockState with = def.withProperty(EXPLODE, (meta & 1) > 0);
		return with;
	}
	
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		boolean b1 = ibs.getValue(EXPLODE);
		return b1 ? 1 : 0;
	}
	
	public BlockStateContainer createBlockState()
	{
		BlockStateContainer bsc = new BlockStateContainer(this, EXPLODE);
		return bsc;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState ibs) {return false;}
	@Override
	public boolean isFullCube(IBlockState ibs) {return false;}
}