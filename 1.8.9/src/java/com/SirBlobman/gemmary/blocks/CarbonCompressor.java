package com.SirBlobman.gemmary.blocks;

import com.SirBlobman.gemmary.Gemmary;
import com.SirBlobman.gemmary.items.GemmaryRandomItems;
import com.SirBlobman.gemmary.network.GuiHandler;
import com.SirBlobman.gemmary.tiles.CCTE;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * BlockInventoryAdvanced is an advanced furnace with 5 input, 4 output and 4 fuel slots that smelts at twice the speed
 * of a regular furnace. The block itself doesn't do much more then any regular block except create a tile entity when
 * placed, open a gui when right clicked and drop tne inventory's contents when harvested. Everything else is handled
 * by the tile entity.
 *
 * The block model will change appearance depending on how many fuel slots are burning.
 * The amount of "block light" produced by the furnace will also depending on how many fuel slots are burning.
 */
public class CarbonCompressor extends BlockContainer
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public CarbonCompressor()
	{
		super(Material.rock);
		this.setCreativeTab(GemmaryRandomItems.OtherBlocks);
		this.setUnlocalizedName("carbon_compressor");
		this.setStepSound(soundTypeAnvil);
	}

	// Called when the block is placed or loaded client side to get the tile entity for the block
	// Should return a new instance of the tile entity for the block
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CCTE();
	}

	// Called when the block is right clicked
	// In this block it is used to open the blocks gui when right clicked by a player
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Uses the gui handler registered to your mod to open the gui for the given gui id
		// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;

		playerIn.openGui(Gemmary.instance, GuiHandler.MOD_CCTE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	// This is where you can do something when the block is broken. In this case drop the inventory's contents
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileEntity);
		}

//		if (inventory != null){
//			// For each slot in the inventory
//			for (int i = 0; i < inventory.getSizeInventory(); i++){
//				// If the slot is not empty
//				if (inventory.getStackInSlot(i) != null)
//				{
//					// Create a new entity item with the item stack in the slot
//					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));
//
//					// Apply some random motion to the item
//					float multiplier = 0.1f;
//					float motionX = worldIn.rand.nextFloat() - 0.5f;
//					float motionY = worldIn.rand.nextFloat() - 0.5f;
//					float motionZ = worldIn.rand.nextFloat() - 0.5f;
//
//					item.motionX = motionX * multiplier;
//					item.motionY = motionY * multiplier;
//					item.motionZ = motionZ * multiplier;
//
//					// Spawn the item in the world
//					worldIn.spawnEntityInWorld(item);
//				}
//			}
//
//			// Clear the inventory so nothing else (such as another mod) can do anything with the items
//			inventory.clear();
//		}

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}

	//------------------------------------------------------------
	//  The code below isn't necessary for illustrating the Inventory Furnace concepts, it's just used for rendering.
	//  For more background information see MBE03

	// we will give our Block a property which tracks the number of burning sides, 0 - 4.
	// This will affect the appearance of the block model, but does not need to be stored in metadata (it's stored in
	//  the tileEntity) so we only need to implement getActualState.  getStateFromMeta, getMetaFromState aren't required
	//   but we give defaults anyway because the base class getMetaFromState gives an error if we don't

	// update the block state depending on the number of slots which contain burning fuel
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (active)
        {
            worldIn.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }

	// the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	// used by the renderer to control lighting and visibility of other blocks.
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	// used by the renderer to control lighting and visibility of other blocks, also by
	// (eg) wall or fence to control whether the fence joins itself to this block
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isFullCube() {
		return false;
	}

	// render using a BakedModel
	// not strictly required because the default (super method) is 3.
	@Override
	public int getRenderType() {
		return 3;
	}
}