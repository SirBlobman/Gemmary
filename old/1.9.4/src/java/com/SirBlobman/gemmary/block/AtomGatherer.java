package com.SirBlobman.gemmary.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.creative.tab.GemmaryTabs;
import com.SirBlobman.gemmary.item.GItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AtomGatherer extends Block 
{
	public static final PropertyDirection facing = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	boolean automatic = false;
	
	public AtomGatherer(boolean auto) 
	{
		super(Material.IRON);
		automatic = auto;
		if(automatic) {setRegistryName("auto_atom_gatherer"); setUnlocalizedName("auto_atom_gatherer"); setTickRandomly(true);}
		else {setUnlocalizedName("atom_gatherer"); setRegistryName("atom_gatherer");}
		setCreativeTab(GemmaryTabs.Blocks);
		setDefaultState(blockState.getBaseState().withProperty(facing, EnumFacing.NORTH));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.TRANSLUCENT;}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {facing});
	}
	
	@Override
	public IBlockState onBlockPlaced(World w, BlockPos pos, EnumFacing ef, float X, float Y, float Z, int meta, EntityLivingBase placer)
	{
		EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();

        try
        {
            return super.onBlockPlaced(w, pos, ef, X, Y, Z, meta, placer).withProperty(facing, enumfacing);
        }
        catch (IllegalArgumentException ex)
        {
            if (!w.isRemote)
            {
                GUtil.print(String.format("Invalid damage property for AtomGatherer at %s. Found %d, must be in [0, 1, 2]", new Object[] {pos, Integer.valueOf(meta >> 2)}));

                if (placer instanceof EntityPlayer)
                {
                    ((EntityPlayer)placer).addChatMessage(new TextComponentTranslation("Invalid damage property. Please pick in [0, 1, 2]", new Object[0]));
                }
            }

            return super.onBlockPlaced(w, pos, ef, X, Y, Z, 0, placer).withProperty(facing, enumfacing);
        }
	}
	
	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState ibs, EntityPlayer ep, EnumHand eh, @Nullable ItemStack held, EnumFacing ef, float X, float Y, float Z)
	{
		if(!automatic)
		{
			int items = 4;
			while(items > 0)
			{
				spawnItem(w, pos);
				items--;
			}	
		}
		return true;
	}
	
	@Override
	public void randomTick(World w, BlockPos pos, IBlockState ibs, Random r)
	{
		spawnItem(w, pos);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(facing, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(facing)).getHorizontalIndex();

        return i;
    }
    
    private void spawnItem(World w, BlockPos pos)
    {
    	if(!w.isRemote)
    	{
    		Random r = new Random();

    		List<Item> atoms = GItems.getAtoms();
    		EntityItem item = new EntityItem(w, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(atoms.get(r.nextInt(atoms.size()))));
    		w.spawnEntityInWorld(item);
    	}
    }
    
    @Override
    public boolean isFullCube(IBlockState ibs)
    {
    	return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState ibs)
    {
    	return false;
    }
}