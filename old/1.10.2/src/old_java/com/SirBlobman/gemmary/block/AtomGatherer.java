package com.SirBlobman.gemmary.block;

import java.util.List;
import java.util.Random;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.creative.GemmaryTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class AtomGatherer extends Block
{
	private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private boolean auto;
	
	public AtomGatherer(boolean auto)
	{
		super(Material.IRON);
		this.auto = auto;
		String name;
		if(auto) 
		{
			name = "auto_atom_gatherer";
			setTickRandomly(true);
		}
		else name = "atom_gatherer";
		setUnlocalizedName(name);
		setRegistryName(name);	
		setCreativeTab(GemmaryTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
	
	@Override
	public BlockStateContainer createBlockState() 
	{
		BlockStateContainer bsc = new BlockStateContainer(this, FACING);
		return bsc;
	}
	
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos bp, EnumFacing ef, float x, float y, float z, int i, EntityLivingBase elb)
	{
		EnumFacing f = elb.getHorizontalFacing().rotateY();
		try{return super.onBlockPlaced(w, bp, ef, x, y, z, i, elb).withProperty(FACING, f);}
		catch(IllegalArgumentException ex)
		{
			if(!w.isRemote)
			{
				String msg = "Invalid damage property for AtomGatherer at %s\nFound %d, must be in [0,1,2]";
				String msg2 = String.format(msg, bp, i >> 2);
				GUtil.print(msg2);
				
				if(elb instanceof EntityPlayer)
				{
					EntityPlayer ep = (EntityPlayer) elb;
					ep.addChatMessage(new TextComponentString("Invalid damage property"));
				}
			}
			
			return super.onBlockPlaced(w, bp, ef, x, y, z, 0, elb).withProperty(FACING, f);
		}
	}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, ItemStack held, EnumFacing ef, float x, float y, float z)
	{
		if(!auto) spawn(w, bp);
		return true;
	}
	
	@Override
	public void randomTick(World w, BlockPos bp, IBlockState ibs, Random r)
	{
		if(auto)
		{
			int items = Gemmary.atoms_to_spawn;
			while(items > 0)
			{
				spawn(w, bp);
				items--;
			}
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	@Override
	public int getMetaFromState(IBlockState ibs)
	{
		return ibs.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public boolean isFullCube(IBlockState ibs) {return false;}
	@Override
	public boolean isOpaqueCube(IBlockState ibs) {return false;}
	
	private void spawn(World w, BlockPos bp)
	{
		if(!w.isRemote)
		{
			Random r = new Random();
			List<ItemStack> atoms = GUtil.getOreDictAtoms();
			ItemStack is = atoms.get(r.nextInt(atoms.size()));
			EntityItem ei = new EntityItem(w, bp.getX(), bp.getY() + 0.5D, bp.getZ());
			ei.setEntityItemStack(is);
			w.spawnEntityInWorld(ei);
		}
	}
}