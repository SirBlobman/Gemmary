package com.SirBlobman.gemmary.tiles;

import java.util.Arrays;

import com.SirBlobman.gemmary.crafting.HydroThermalRecipes;

import net.minecraft.block.Block;
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
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;

public class AHTV_TE extends TileEntity implements ITickable, IInventory
{
	public static final int WaterSlotsCount = 1;
	public static final int InputSlotsCount = 9;
	public static final int OutputSlotsCount = 1;
	public static final int TotalSlotsCount = 11;
	
	public static final int FirstWaterSlot = 0;
	public static final int FirstInputSlot = 1;
	public static final int FirstOutputSlot = 10;
	
	private ItemStack[] itemStacks = new ItemStack[TotalSlotsCount];
	
	private int [] waterRemaining = new int[WaterSlotsCount];
	private int [] waterInitialValue = new int[WaterSlotsCount];
	
	private short progress;
	private static final short ProgressForCompletion = 2400; //2 Minutes = 2400 ticks
	
	private int cachedNumberOfWaterSlots = -1;
	
	public double fractionOfWaterRemaining(int waterSlot)
	{
		if (waterInitialValue[waterSlot] <= 0 ) return 0;
		double fraction = waterRemaining[waterSlot] / (double)waterInitialValue[waterSlot];
		return MathHelper.clamp_double(fraction,  0.0,  1.0);
	}
	
	public int millibucketsOfWaterRemaining(int waterSlot)
	{
		if (waterRemaining[waterSlot] <= 0 ) return 0;
		return waterRemaining[waterSlot] / 20; // 20 ticks per second
	}
	
	public int numberOfWaterSlots()
	{
		int waterCount = 0;
		for (int water : waterRemaining)
		{
			if (water > 0) ++waterCount;
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
			
			if (numberOfWater > 0)
			{
				progress += numberOfWater;
			}
			else
			{
				progress -= 2;
			}
			
			if (progress < 0) progress = 0;
			
			if (progress >= ProgressForCompletion)
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
		if (cachedNumberOfWaterSlots != numberWater)
		{
			cachedNumberOfWaterSlots = numberWater;
			if (worldObj.isRemote)
			{
				worldObj.markBlockForUpdate(pos);
			}
			worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
		}
	}
	
	private int water()
	{
		int waterCount = 0;
		boolean inventoryChanged = false;
		for (int i = 0; i < WaterSlotsCount; i++)
		{
			int waterSlotNumber = i + FirstWaterSlot;
			if (waterRemaining[i] > 0)
			{
				--waterRemaining[i];
				++waterCount;
			}
			if (waterRemaining[i] == 0)
			{
				if (itemStacks[waterSlotNumber] != null && getItemHydrateTime(itemStacks[waterSlotNumber]) > 0)
				{
					waterRemaining[i] = waterInitialValue[i] = getItemHydrateTime(itemStacks[waterSlotNumber]);
					--itemStacks[waterSlotNumber].stackSize;
					++waterCount;
					inventoryChanged = true;
					if (itemStacks[waterSlotNumber].stackSize == 0) {
						itemStacks[waterSlotNumber] = itemStacks[waterSlotNumber].getItem().getContainerItem(itemStacks[waterSlotNumber]);
					}
				}
			}
		}
		if (inventoryChanged) markDirty();
		return waterCount;
	}
	private boolean canHydrate() {return hydrateItem(false);}
	
	private void hydrateItem() {hydrateItem(true);}
	
	private boolean hydrateItem(boolean performHydrate)
	{
		Integer firstSuitableInputSlot = null;
		Integer firstSuitableOutputSlot = null;
		ItemStack result = null;
		
		for (int inputSlot = FirstInputSlot; inputSlot < FirstInputSlot + InputSlotsCount; inputSlot++)
		{
			if(itemStacks[inputSlot] != null)
			{
				result = getHydratingResultForItem(itemStacks[inputSlot]);
	  			if (result != null) 
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
		if (!performHydrate) return true;
		
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
	
	public static ItemStack getHydratingResultForItem(ItemStack stack) {return HydroThermalRecipes.instance().getHydratingResult(stack); }
	
	public static short getItemHydrateTime(ItemStack stack)
	{
		int hydratetime = AHTV_TE.getItemWaterValue(stack);
		return (short)MathHelper.clamp_int(hydratetime,  0,  Short.MAX_VALUE);
	}
	
	public static int getItemWaterValue(ItemStack stack)
	{
		if (stack == null)
		{
			return 0;
		}
		else
		{
			Item item = stack.getItem();
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.ice)
                {
                	return 800;
                }
                if (block == Blocks.packed_ice)
                {
                	return 400;
                }
            }
            if (item == Items.water_bucket) return 800;
            return 0;
		}
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
	public ItemStack decrStackSize(int slotIndex, int count) 
	{
		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
		if (itemStackInSlot == null) return null;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.stackSize <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(slotIndex, null);
		} 
		else 
		{
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.stackSize == 0) 
			{
				setInventorySlotContents(slotIndex, null);
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) 
	{
		itemStacks[slotIndex] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) 
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		if (this.worldObj.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}

	static public boolean isItemValidForWaterSlot(ItemStack itemStack)
	{
		return true;
	}

	static public boolean isItemValidForInputSlot(ItemStack itemStack)
	{
		return true;
	}

	static public boolean isItemValidForOutputSlot(ItemStack itemStack)
	{
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound parentNBTTagCompound)
	{
		super.writeToNBT(parentNBTTagCompound);
		
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
		parentNBTTagCompound.setTag("Items", dataForAllSlots);

		// Save everything else
		parentNBTTagCompound.setShort("Progress", progress);
	  parentNBTTagCompound.setTag("waterRemaining", new NBTTagIntArray(waterRemaining));
		parentNBTTagCompound.setTag("waterInitial", new NBTTagIntArray(waterInitialValue));
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		final byte NBT_TYPE_COMPOUND = 11;
		NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

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
		progress = nbtTagCompound.getShort("Progress");
		waterRemaining = Arrays.copyOf(nbtTagCompound.getIntArray("waterRemaining"), WaterSlotsCount);
		waterInitialValue = Arrays.copyOf(nbtTagCompound.getIntArray("waterInitial"), WaterSlotsCount);
		cachedNumberOfWaterSlots = -1;
	}

	// When the world loads from disk, the server needs to send the TileEntity information to the client
	//  it uses getDescriptionPacket() and onDataPacket() to do this
	@SuppressWarnings("rawtypes")
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		final int METADATA = 0;
		return new S35PacketUpdateTileEntity(this.pos, METADATA, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) 
	{
		readFromNBT(pkt.getNbtCompound());
	}

	//------------------------

	// set all slots to empty
	@Override
	public void clear() 
	{
		Arrays.fill(itemStacks, null);
	}

	// will add a key for this container to the lang file so we can name it in the GUI
	@Override
	public String getName() {
		return I18n.format("container.ahtv.name", new Object[0]);
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// standard code to look up what the human-readable name is
	@Override
	public IChatComponent getDisplayName() {
		return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
	}

	private static final byte COOK_FIELD_ID = 0;
	private static final byte FIRST_BURN_TIME_REMAINING_FIELD_ID = 1;
	private static final byte FIRST_BURN_TIME_INITIAL_FIELD_ID = FIRST_BURN_TIME_REMAINING_FIELD_ID + (byte)WaterSlotsCount;
	private static final byte NUMBER_OF_FIELDS = FIRST_BURN_TIME_INITIAL_FIELD_ID + (byte)WaterSlotsCount;

	@Override
	public int getField(int id) 
	{
		if (id == COOK_FIELD_ID) return progress;
		if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + WaterSlotsCount) 
		{
			return waterRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID];
		}
		if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID && id < FIRST_BURN_TIME_INITIAL_FIELD_ID + WaterSlotsCount) 
		{
			return waterInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID];
		}
		System.err.println("Invalid field ID in TileInventorySmelting.getField:" + id);
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		if (id == COOK_FIELD_ID) {
			progress = (short)value;
		} else if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + WaterSlotsCount) {
			waterRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID] = value;
		} else if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID && id < FIRST_BURN_TIME_INITIAL_FIELD_ID + WaterSlotsCount) {
			waterInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID] = value;
		} else {
			System.err.println("Invalid field ID in TileInventorySmelting.setField:" + id);
		}
	}

	@Override
	public int getFieldCount() 
	{
		return NUMBER_OF_FIELDS;
	}
	
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
		return false;
	}

	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) setInventorySlotContents(slotIndex, null);
		return itemStack;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return null;
	}
}
