package com.SirBlobman.gemmary.tile;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.recipe.HydroRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import scala.actors.threadpool.Arrays;

public class TileHydrothermal extends TileEntity implements IInventory,ITickable
{
	private final int WATER_SLOTS = 1;
	private final int INPUT_SLOTS = 1;
	private final int OUTPUT_SLOTS = 1;
	private final int TOTAL_SLOTS = 3;
	
	private final int FIRST_WATER = 0;
	private final int FIRST_INPUT = 1;
	private final int FIRST_OUTPUT = 2;
	
	private ItemStack[] stacks = new ItemStack[TOTAL_SLOTS];
	private int[] waterLeft = new int[WATER_SLOTS];
	private int[] waterInit = new int[WATER_SLOTS];
	
	private short progress;
	private final short progressNeeded = 2500; //2 Minutes = 2400 Ticks
	private int cached = -1;
	
	public double waterLeftFraction(int slot)
	{
		if(waterInit[slot] <= 0) return 0;
		double fraction = waterLeft[slot] / waterInit[slot];
		double math = MathHelper.clamp_double(fraction, 0.0D, 1.0D);
		return math;
	}
	
	public int waterLeftMilliBuckets(int slot)
	{
		if(waterLeft[slot] <= 0) return 0;
		return waterLeft[slot];
	}
	
	public int waterSlots()
	{
		int count = 0;
		for(int water : waterLeft) {if(water > 0) ++count;}
		return count;
	}
	
	public double progressFraction()
	{
		double fraction = progress / progressNeeded;
		double math = MathHelper.clamp_double(fraction, 0.0D, 1.0D);
		return math;
	}
	
	@Override
	public void update()
	{
		if(canHydrate())
		{
			int water = water();
			if(water > 0) progress += water;
			else progress -= 2;
			
			if(progress < 0) progress = 0;
			
			if(progress >= progressNeeded)
			{
				hydrate();
				progress = 0;
			}
		}
		else progress = 0;
		
		int water = waterSlots();
		if(cached != water)
		{
			cached = water;
			if(worldObj.isRemote)
			{
				final IBlockState ibs = getWorld().getBlockState(getPos());
				worldObj.notifyBlockUpdate(pos, ibs, ibs, 3);
			}
			worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
		}
	}
	
	private int water()
	{
		int count = 0;
		boolean changed = false;
		for(int i = 0; i < WATER_SLOTS; i++)
		{
			int slot = i + FIRST_WATER;
			if(waterLeft[i] > 0)
			{
				--waterLeft[i];
				++count;
			}
			if(waterLeft[i] == 0)
			{
				if(stacks[slot] != null && getHydrateTime(stacks[slot]) > 0)
				{
					waterLeft[i] = waterInit[i] = getHydrateTime(stacks[slot]);
					--stacks[slot].stackSize;
					++count;
					changed = true;
					if(stacks[slot].stackSize == 0)
					{
						stacks[slot] = stacks[slot].getItem().getContainerItem(stacks[slot]);
					}
				}
			}
		}
		
		if(changed) markDirty();
		return count;
	}
	
	private boolean canHydrate() {return hydrate(false);}
	private void hydrate() {hydrate(true);}
	private boolean hydrate(boolean perform)
	{
		Integer firstInput = null;
		Integer firstOutput = null;
		ItemStack result = null;
		
		for(int i = FIRST_INPUT; i < FIRST_INPUT + INPUT_SLOTS; i++)
		{
			if(stacks[i] != null)
			{
				result = getResult(stacks[i]);
				if(result != null)
				{
					for(int o = FIRST_OUTPUT; o < FIRST_OUTPUT + OUTPUT_SLOTS; o++)
					{
						ItemStack output = stacks[o];
						if(output == null)
						{
							firstInput = i;
							firstOutput = o;
							break;
						}
						
						boolean b1 = output.getItem() == result.getItem();
						boolean b2 = !output.getHasSubtypes();
						boolean b3 = output.getMetadata() == output.getMetadata();
						boolean b4 = b2 || b3;
						boolean b5 = ItemStack.areItemStackTagsEqual(output, result);
						boolean b6 = b1 && b4 & b5;
						if(b6)
						{
							int comb = stacks[o].stackSize + result.stackSize;
							if(comb <= getInventoryStackLimit() && comb <= stacks[o].getMaxStackSize())
							{
								firstInput = i;
								firstInput = o;
								break;
							}
						}
					}
					if(firstInput != null) break;
				}
			}
		}
		
		if(firstInput == null) return false;
		if(!perform) return true;
		
		stacks[firstInput].stackSize--;
		if(stacks[firstInput].stackSize <= 0) stacks[firstInput] = null;
		if(stacks[firstOutput] == null) stacks[firstOutput] = result.copy();
		else stacks[firstOutput].stackSize += result.stackSize;
		
		markDirty();
		return true;
	}
	
	public static ItemStack getResult(ItemStack is) 
	{
		HydroRecipes hr = HydroRecipes.instance();
		return hr.getResult(is);
	}

	public static short getHydrateTime(ItemStack is)
	{
		int time = getWater(is);
		int math = MathHelper.clamp_int(time, 0, Short.MAX_VALUE);
		return (short) math;
	}
	
	public static int getWater(ItemStack is)
	{
		if(is == null) return 0;
		else
		{
			Item i = is.getItem();
			if(i instanceof ItemBlock)
			{
				ItemBlock ib = (ItemBlock) i;
				Block b = ib.getBlock();
				if(b == Blocks.AIR) return 0;
				if(b == Blocks.ICE) return 500;
				if(b == Blocks.PACKED_ICE) return 250;
			}
			if(i == Items.WATER_BUCKET) return 1000;
			//if(i == GItems.hWC) return 2500;
		}
		return 0;
	}
	
	@Override
	public int getSizeInventory() {return stacks.length;}
	@Override
	public ItemStack getStackInSlot(int slot) {return stacks[slot];}
	
	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		ItemStack is = getStackInSlot(slot);
		if(is == null) return null;
		 
		ItemStack rem;
		if(is.stackSize <= count)
		{
			rem = is;
			setInventorySlotContents(slot, null);
		}
		else
		{
			rem = is.splitStack(count);
			if(is.stackSize == 0) setInventorySlotContents(slot, null);
		}
		
		markDirty();
		return rem;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		stacks[slot] = is;
		if(is != null && is.stackSize > getInventoryStackLimit()) is.stackSize = getInventoryStackLimit();
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {return 64;}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer ep)
	{
		if(worldObj.getTileEntity(pos) != this) return false;
		final double X_OFF = 0.5;
		final double Y_OFF = 0.5;
		final double Z_OFF = 0.5;
		final double MAX_SQ = 64;
		double x = pos.getX() + X_OFF;
		double y = pos.getY() + Y_OFF;
		double z = pos.getZ() + Z_OFF;
		double sq = ep.getDistanceSq(x,y,z);
		return sq < MAX_SQ;
	}
	
	public static boolean isValidWater(ItemStack is) {return true;}
	public static boolean isValidInput(ItemStack is) {return true;}
	public static boolean isValidOutput(ItemStack is) {return false;}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound old)
	{
		super.writeToNBT(old);
		NBTTagList data = new NBTTagList();
		for(int i = 0; i < stacks.length; ++i)
		{
			if(stacks[i] != null)
			{
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				stacks[i].writeToNBT(slot);
				data.appendTag(slot);
			}
		}
		
		old.setTag("Items", data);
		old.setShort("Progress", progress);
		old.setTag("Water Left", new NBTTagIntArray(waterLeft));
		old.setTag("Water Init", new NBTTagIntArray(waterInit));
		return old;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound old)
	{
		super.readFromNBT(old);
		final byte TYPE = 11;
		NBTTagList data = old.getTagList("Items", TYPE);
		
		Arrays.fill(stacks, null);
		for(int i = 0; i < data.tagCount(); ++i)
		{
			NBTTagCompound slot = data.getCompoundTagAt(i);
			byte number = slot.getByte("Slot");
			if(number >= 0 && number < stacks.length)
			{
				stacks[number] = ItemStack.loadItemStackFromNBT(slot);
			}
		}
		
		progress = old.getShort("Progress");
		waterLeft = Arrays.copyOf(old.getIntArray("Water Left"), WATER_SLOTS);
		waterInit = Arrays.copyOf(old.getIntArray("Water Init"), WATER_SLOTS);
		cached = -1;
	}
	
	@Override
	public void onDataPacket(NetworkManager nm, SPacketUpdateTileEntity sput) {readFromNBT(sput.getNbtCompound());}
	@Override
	public void clear() {Arrays.fill(stacks, null);}
	@Override
	public String getName() {return I18n.format("container.htv.name");}
	@Override
	public boolean hasCustomName() {return false;}
	@Override
	public ITextComponent getDisplayName() 
	{
		TextComponentString t1 = new TextComponentString(getName());
		TextComponentTranslation t2 = new TextComponentTranslation(getName());
		return hasCustomName() ? t1 : t2;
	}
	
	private final byte HYDRATE_ID = 0;
	private final byte FIRST_HYDRATE_TIME_ID = 1;
	private final byte FIRST_HYDRATE_TIME_INIT_ID = FIRST_HYDRATE_TIME_ID + WATER_SLOTS;
	private final byte FIELDS = FIRST_HYDRATE_TIME_INIT_ID + WATER_SLOTS;
	
	@Override
	public int getField(int id)
	{
		if(id == HYDRATE_ID) return progress;
		if(id >= FIRST_HYDRATE_TIME_ID && id < FIRST_HYDRATE_TIME_ID + WATER_SLOTS) return waterLeft[id - FIRST_HYDRATE_TIME_ID];
		if(id >= FIRST_HYDRATE_TIME_INIT_ID && id < FIRST_HYDRATE_TIME_INIT_ID + WATER_SLOTS) return waterInit[id - FIRST_HYDRATE_TIME_INIT_ID];
		
		GUtil.print("Invalid Field ID:\nTileHydrothermal.getField(" + id + ")");
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
		if(id == HYDRATE_ID) progress = (short) value;
		else if(id >= FIRST_HYDRATE_TIME_ID && id < FIRST_HYDRATE_TIME_ID + WATER_SLOTS) waterLeft[id - FIRST_HYDRATE_TIME_ID] = value;
		else if(id >= FIRST_HYDRATE_TIME_INIT_ID && id < FIRST_HYDRATE_TIME_INIT_ID + WATER_SLOTS) waterInit[id - FIRST_HYDRATE_TIME_INIT_ID] = value;
		else GUtil.print("Invalid Field ID:\nTileHydrothermal.setField(" + id + ")");
	}
	
	@Override
	public int getFieldCount() {return FIELDS;}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {return false;}
	
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack is = getStackInSlot(slot);
		if(is != null) setInventorySlotContents(slot, null);
		return is;
	}
	
	@Override
	public void openInventory(EntityPlayer ep) {}
	@Override
	public void closeInventory(EntityPlayer ep) {}
	@Override
	public ItemStack removeStackFromSlot(int id) {return null;}
}