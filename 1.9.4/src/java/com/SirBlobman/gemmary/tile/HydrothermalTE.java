package com.SirBlobman.gemmary.tile;

import java.util.Arrays;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.recipe.HydrothermalRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

public class HydrothermalTE extends TileEntity implements ITickable, IInventory
{
	public static final int WaterSlotsCount = 1;
	public static final int InputSlotsCount = 1;
	public static final int OutputSlotsCount = 1;
	public static final int TotalSlotsCount = 3;
	
	public static final int FirstWaterSlot = 0;
	public static final int FirstInputSlot = 1;
	public static final int FirstOutputSlot = 2;
	
	private ItemStack[] itemStacks = new ItemStack[TotalSlotsCount];
	
	private int [] waterRemaining = new int[WaterSlotsCount];
	private int [] waterInitValue = new int[WaterSlotsCount];
	
	private short progress;
	private static final short ProgressForCompletion = 2500; //2 Minutes = 2400 Ticks
	
	private int cachedNumberOfWaterSlots = -1;
	
	public double fractionOfWaterRemaining(int waterSlot)
	{
		if(waterInitValue[waterSlot] <= 0) return 0;
		
		double fraction = waterRemaining[waterSlot] / (double)waterInitValue[waterSlot];
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	public int millibucketsOfWaterRemaining(int waterSlot)
	{
		if(waterRemaining[waterSlot] <= 0) return 0;
		
		return waterRemaining[waterSlot];
	}
	
	public int numberOfWaterSlots()
	{
		int waterCount = 0;
		for(int water: waterRemaining)
		{
			if(water > 0) ++waterCount;
		}
		
		return waterCount;
	}
	
	public double fractionOfProgressComplete()
	{
		double fraction = progress / (double)ProgressForCompletion;
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	@Override
	public void update()
	{
		if(canHydrate())
		{
			int numberOfWater = water();
			
			if(numberOfWater > 0)
			{
				progress += numberOfWater;
			}
			else
			{
				progress -= 2;
			}
			
			if(progress < 0) progress = 0;
			
			if(progress >= ProgressForCompletion)
			{
				hydrateItem();
				progress = 0;
			}
		}
		else
		{
			progress = 0;
		}
		
		int numberWater = numberOfWaterSlots();
		if(cachedNumberOfWaterSlots != numberWater)
		{
			cachedNumberOfWaterSlots = numberWater;
			
			if(worldObj.isRemote)
			{
				final IBlockState s = getWorld().getBlockState(getPos());
				worldObj.notifyBlockUpdate(pos, s, s, 3);
			}
			worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
		}
	}
	
	private int water()
	{
		int waterCount = 0;
		boolean inventoryChanged = false;
		for(int i = 0; i < WaterSlotsCount; i++)
		{
			int waterSlotNumber = i + FirstWaterSlot;
			if(waterRemaining[i] > 0)
			{
				--waterRemaining[i];
				++waterCount;
			}
			if(waterRemaining[i] == 0)
			{
				if(itemStacks[waterSlotNumber] != null && getItemHydrateTime(itemStacks[waterSlotNumber]) > 0)
				{
					waterRemaining[i] = waterInitValue[i] = getItemHydrateTime(itemStacks[waterSlotNumber]);
					--itemStacks[waterSlotNumber].stackSize;
					++waterCount;
					inventoryChanged = true;
					if(itemStacks[waterSlotNumber].stackSize == 0)
					{
						itemStacks[waterSlotNumber] = itemStacks[waterSlotNumber].getItem().getContainerItem(itemStacks[waterSlotNumber]);
					}
				}
			}
		}
		if(inventoryChanged) markDirty();
		return waterCount;
	}
	
	private boolean canHydrate() {return hydrateItem(false);}
	
	private void hydrateItem() {hydrateItem(true);}
	
	private boolean hydrateItem(boolean perform)
	{
		Integer firstSuitableInputSlot = null;
		Integer firstSuitableOutputSlot = null;
		ItemStack result = null;

		for(int inputSlot = FirstInputSlot; inputSlot < FirstInputSlot + InputSlotsCount; inputSlot++)
		{
			if(itemStacks[inputSlot] != null)
			{
				result = getHydratingResultForItem(itemStacks[inputSlot]);
				if(result != null)
				{
					for (int outputSlot = FirstOutputSlot; outputSlot < FirstOutputSlot + OutputSlotsCount; outputSlot++) 
					{
						ItemStack outputStack = itemStacks[outputSlot];
						if (outputStack == null) 
						{
							firstSuitableInputSlot = inputSlot;
							firstSuitableOutputSlot = outputSlot;
							break;
						}
						if (outputStack.getItem() == result.getItem() && (!outputStack.getHasSubtypes() || outputStack.getMetadata() == outputStack.getMetadata())
								&& ItemStack.areItemStackTagsEqual(outputStack, result)) 
						{
							int combinedSize = itemStacks[outputSlot].stackSize + result.stackSize;
							if (combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot].getMaxStackSize()) 
							{
								firstSuitableInputSlot = inputSlot;
								firstSuitableOutputSlot = outputSlot;
								break;
							}
						}
					}
					if (firstSuitableInputSlot != null) break;
				}
			}
		}
		
		if (firstSuitableInputSlot == null) return false;
		if (!perform) return true;
		
		itemStacks[firstSuitableInputSlot].stackSize--;
		if (itemStacks[firstSuitableInputSlot].stackSize <=0) itemStacks[firstSuitableInputSlot] = null;
		if (itemStacks[firstSuitableOutputSlot] == null) 
		{
			itemStacks[firstSuitableOutputSlot] = result.copy();
		} 
		else 
		{
			itemStacks[firstSuitableOutputSlot].stackSize += result.stackSize;
		}
		markDirty();
		return true;
	}
	
	public static ItemStack getHydratingResultForItem(ItemStack is)
	{
		return HydrothermalRecipes.getHydratingResult(is);
	}
	
	public static short getItemHydrateTime(ItemStack is)
	{
		int hydrateTime = HydrothermalTE.getItemWaterValue(is);
		
		return (short)MathHelper.clamp_int(hydrateTime, 0, Short.MAX_VALUE);
	}
	
	public static int getItemWaterValue(ItemStack is)
	{
		if(is == null)
		{
			return 0;
		}
		else
		{
			Item i = is.getItem();
			if(i instanceof ItemBlock && Block.getBlockFromItem(i) != Blocks.AIR)
			{
				Block b = Block.getBlockFromItem(i);
				
				if(b == Blocks.ICE) return 500;
				if(b == Blocks.PACKED_ICE) return 250;
			}
			if(i == Items.WATER_BUCKET) return 1000;
			if(i == GItems.heatedWaterContainer) return 2500;
		}
		
		return 0;
	}
	
	@Override
	public int getSizeInventory()
	{
		return itemStacks.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i)
	{
		return itemStacks[i];
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack iSInSlot = getStackInSlot(index);
		if(iSInSlot == null) return null;
		
		ItemStack iSRemoved;
		if(iSInSlot.stackSize <= count)
		{
			iSRemoved = iSInSlot;
			setInventorySlotContents(index, null);
		}
		else
		{
			iSRemoved = iSInSlot.splitStack(count);
			if(iSInSlot.stackSize == 0)
			{
				setInventorySlotContents(index, null);
			}
		}
		
		markDirty();
		return iSRemoved;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		itemStacks[slot] = is;
		if(is != null && is.stackSize > getInventoryStackLimit())
		{
			is.stackSize = getInventoryStackLimit();
		}
		
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer p)
	{
		if(worldObj.getTileEntity(pos) != this) return false;
		
		final double XCenterOffset = 0.5;
		final double YCenterOffset = 0.5;
		final double ZCenterOffset = 0.5;
		final double MaximumDistanceSQ = 8 * 8;
		
		return p.getDistanceSq(pos.getX() + XCenterOffset, pos.getY() + YCenterOffset, pos.getZ() + ZCenterOffset) < MaximumDistanceSQ;
	}
	
	public static boolean isItemValidForWaterSlot(ItemStack is)
	{
		return true;
	}
	
	public static boolean isItemValidForInputSlot(ItemStack is)
	{
		return true;
	}
	
	public static boolean isItemValidForOutputSlot(ItemStack is)
	{
		return false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parent)
	{
		super.writeToNBT(parent);

		NBTTagList dataForAllSlots = new NBTTagList();
		for (int i = 0; i < this.itemStacks.length; ++i) 
		{
			if (this.itemStacks[i] != null) 
			{
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		parent.setTag("Items", dataForAllSlots);

		parent.setShort("Progress", progress);
		parent.setTag("waterRemaining", new NBTTagIntArray(waterRemaining));
		parent.setTag("waterInitial", new NBTTagIntArray(waterInitValue));
		
		return parent;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		final byte NBT_TYPE_COMPOUND = 11;
		NBTTagList dataForAllSlots = tag.getTagList("Items", NBT_TYPE_COMPOUND);

		Arrays.fill(itemStacks, null);
		for (int i = 0; i < dataForAllSlots.tagCount(); ++i) 
		{
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			byte slotNumber = dataForOneSlot.getByte("Slot");
			if (slotNumber >= 0 && slotNumber < this.itemStacks.length) 
			{
				this.itemStacks[slotNumber] = ItemStack.loadItemStackFromNBT(dataForOneSlot);
			}
		}

		// Load everything else.  Trim the arrays (or pad with 0) to make sure they have the correct number of elements
		progress = tag.getShort("Progress");
		waterRemaining = Arrays.copyOf(tag.getIntArray("waterRemaining"), WaterSlotsCount);
		waterInitValue = Arrays.copyOf(tag.getIntArray("waterInitial"), WaterSlotsCount);
		cachedNumberOfWaterSlots = -1;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity p)
	{
		readFromNBT(p.getNbtCompound());
	}
	
	@Override
	public void clear()
	{
		Arrays.fill(itemStacks, null);
	}
	
	@Override
	public String getName()
	{
		return GUtil.translate("container.ahtv.name");
	}
	
	@Override
	public boolean hasCustomName()
	{
		return false;
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
	}
	
	
	private static final byte HydrateFieldID = 0;
	private static final byte FirstHydrateTimeRemainingFieldID = 1;
	private static final byte FirstHydrateTimeInitialFieldID = FirstHydrateTimeRemainingFieldID + (byte)WaterSlotsCount;
	private static final byte NumberOfFields = FirstHydrateTimeInitialFieldID + (byte)WaterSlotsCount;
	
	@Override
	public int getField(int id)
	{
		if(id == HydrateFieldID) return progress;
		if(id >= FirstHydrateTimeRemainingFieldID && id < FirstHydrateTimeRemainingFieldID + WaterSlotsCount)
		{
			return waterRemaining[id - FirstHydrateTimeRemainingFieldID];
		}
		if (id >= FirstHydrateTimeInitialFieldID && id < FirstHydrateTimeInitialFieldID + WaterSlotsCount) 
		{
			return waterInitValue[id - FirstHydrateTimeInitialFieldID];
		}
		System.err.println("Invalid field ID in HydrothermalTE.getField:" + id);
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
		if (id == HydrateFieldID) 
		{
			progress = (short)value;
		} else if (id >= FirstHydrateTimeRemainingFieldID && id < FirstHydrateTimeRemainingFieldID + WaterSlotsCount) 
		{
			waterRemaining[id - FirstHydrateTimeRemainingFieldID] = value;
		} else if (id >= FirstHydrateTimeInitialFieldID && id < FirstHydrateTimeInitialFieldID + WaterSlotsCount) 
		{
			waterInitValue[id - FirstHydrateTimeInitialFieldID] = value;
		} else 
		{
			System.err.println("Invalid field ID in TileInventorySmelting.setField:" + id);
		}
	}
	
	@Override
	public int getFieldCount()
	{
		return NumberOfFields;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return false;
	}
	
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack iS = getStackInSlot(slot);
		if(iS != null) setInventorySlotContents(slot, null);
		return iS;
	}
	
	@Override
	public void openInventory(EntityPlayer p) {}
	
	@Override
	public void closeInventory(EntityPlayer p) {}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {return null;}
}