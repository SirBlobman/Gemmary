package com.SirBlobman.gemmary.tile;

import com.SirBlobman.gemmary.recipe.CompressorRecipes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import scala.actors.threadpool.Arrays;

public class CompressorTE extends TileEntity implements ITickable, IInventory
{
	public static final int FuelSlotsCount = 1;
	public static final int InputSlotsCount = 1;
	public static final int OutputSlotsCount = 1;
	public static final int TotalSlotsCount = FuelSlotsCount + InputSlotsCount + OutputSlotsCount;
	
	public static final int FirstFuelSlot = 0;
	public static final int FirstInputSlot = FirstFuelSlot + FuelSlotsCount;
	public static final int FirstOutputSlot = FirstInputSlot + InputSlotsCount;
	
	private ItemStack[] itemStacks = new ItemStack[TotalSlotsCount];
	
	private int [] compressTimeRemaining = new int[200];
	private int [] compressTimeInitValue = new int[FuelSlotsCount];
	
	private short compressTime;
	private static short CompressTimeForCompletion;
	
	private int cachedNumberOfBurningSlots = -1;
	
	public CompressorTE(short cTimeForCompletion)
	{
		CompressTimeForCompletion = cTimeForCompletion;
	}
	
	public double fractionOfFuelRemaining(int fuelslot)
	{
		if(compressTimeInitValue[fuelslot] <= 0) return 0;
		
		double fraction = compressTimeRemaining[fuelslot] / (double)compressTimeInitValue[fuelslot];
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	public int secondsOfFuelRemaining(int fuelslot)
	{
		return compressTimeRemaining[fuelslot] / 20;
	}
	
	public int numberOfBurningFuelSlots()
	{
		int burningCount = 1;
		for(int burnTime: compressTimeRemaining)
		{
			if(burnTime > 0) ++burningCount;
		}
		return burningCount;
	}
	
	public double fractionOfCompressTimeComplete()
	{
		double fraction = compressTime / (double)CompressTimeForCompletion;
		
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	@Override
	public String getName()
	{
		return "container.compressor";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
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
		ItemStack itemStackInSlot = getStackInSlot(index);
		if(itemStackInSlot == null) return null;
		
		ItemStack itemStackRemoved;
		if(itemStackInSlot.stackSize <= count)
		{
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(index, null);
		}
		else
		{
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if(itemStackInSlot.stackSize == 0)
			{
				setInventorySlotContents(index, null);
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack is)
	{
		itemStacks[index] = is;
		if (is != null && is.stackSize > getInventoryStackLimit()) 
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
		if(this.worldObj.getTileEntity(this.pos) != this) return false;
		
		final double XCentreOffset = 0.5;
		final double YCentreOffset = 0.5;
		final double ZCentreOffset = 0.5;
		final double MaximumDistanceSQ = 8.0 * 8.0;
		return p.getDistanceSq(pos.getX() + XCentreOffset, pos.getY() + YCentreOffset, pos.getZ() + ZCentreOffset) < MaximumDistanceSQ;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return false;
	}

	private static final byte CompressFieldID = 0;
	private static final byte FirstCompressTimeRemainingFieldID = 1;
	private static final byte FirstCompressTimeInitialFieldID = FirstCompressTimeRemainingFieldID + (byte)FuelSlotsCount;
	private static final byte NumberOfFields = FirstCompressTimeInitialFieldID + (byte)FuelSlotsCount;
	
	@Override
	public int getField(int id) 
	{
		if (id == CompressFieldID) return compressTime;
		if (id >= FirstCompressTimeRemainingFieldID && id < FirstCompressTimeRemainingFieldID + FuelSlotsCount) 
		{
			return compressTimeRemaining[id - FirstCompressTimeRemainingFieldID];
		}
		if (id >= FirstCompressTimeInitialFieldID && id < FirstCompressTimeInitialFieldID + FuelSlotsCount) 
		{
			return compressTimeInitValue[id - FirstCompressTimeInitialFieldID];
		}
		System.err.println("Invalid field ID in CompressorTE.getField:" + id);
		
		return 0;
	}

	@Override
	public void setField(int id, int value) 
	{
		if(id == CompressFieldID)
		{
			compressTime = (short)value;
		}
		else if(id >= FirstCompressTimeRemainingFieldID && id < FirstCompressTimeRemainingFieldID + FuelSlotsCount)
		{
			compressTimeRemaining[id - FirstCompressTimeRemainingFieldID] = value;
		}
		else if(id >= FirstCompressTimeInitialFieldID && id < FirstCompressTimeInitialFieldID + FuelSlotsCount)
		{
			compressTimeInitValue[id - FirstCompressTimeInitialFieldID] = value;
		}
		else
		{
			System.err.println("Invalid field ID in CompressorTE.setField:" + id);
		}
	}

	@Override
	public int getFieldCount()
	{
		return NumberOfFields;
	}

	@Override
	public void clear()
	{
		
	}

	@Override
	public void update()
	{
		if(canCompress())
		{
			int numberOfFuelBurning = burnFuel();
			
			if(numberOfFuelBurning > 0)
			{
				compressTime += numberOfFuelBurning;
			}
			else
			{
				compressTime -= 2;
			}
			
			if(compressTime < 0) compressTime = 0;
			
			if(compressTime >= CompressTimeForCompletion)
			{
				compressItem();
				compressTime = 0;
			}
		}
		else
		{
			compressTime = 0;
		}
		
		int numberBurning = numberOfBurningFuelSlots();
		if(cachedNumberOfBurningSlots != numberBurning)
		{
			cachedNumberOfBurningSlots = numberBurning;
			if(worldObj.isRemote)
			{
				final IBlockState s = getWorld().getBlockState(getPos());
				worldObj.notifyBlockUpdate(pos, s, s, 3);
			}
			worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
		}
	}
	
	private int burnFuel()
	{
		int burningCount = 0;
		boolean inventoryChanged = false;
		for(int i = 0; i < FuelSlotsCount; i++)
		{
			int fuelSlotNumber = i + FirstFuelSlot;
			if(compressTimeRemaining[i] > 0)
			{
				--compressTimeRemaining[i];
				++burningCount;
			}
			if(compressTimeRemaining[i] == 0)
			{
				if(itemStacks[fuelSlotNumber] != null && getItemBurnTime(itemStacks[fuelSlotNumber]) > 0)
				{
					compressTimeRemaining[i] = compressTimeInitValue[i] = getItemBurnTime(itemStacks[fuelSlotNumber]);
					--itemStacks[fuelSlotNumber].stackSize;
					++burningCount;
					inventoryChanged = true;
					if(itemStacks[fuelSlotNumber].stackSize == 0)
					{
						itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber].getItem().getContainerItem(itemStacks[fuelSlotNumber]);
					}
				}
			}
		}
		if(inventoryChanged) markDirty();
		return burningCount;
	}
	
	private boolean canCompress() {return compressItem(false);}
	
	private void compressItem() {compressItem(true);}
	
	private boolean compressItem(boolean performSmelt)
	{
		Integer firstSuitableInputSlot = null;
		Integer firstSuitableOutputSlot = null;
		ItemStack result = null;

		for (int inputSlot = FirstInputSlot; inputSlot < FirstInputSlot + InputSlotsCount; inputSlot++)	
		{
			if (itemStacks[inputSlot] != null) 
			{
				result = getCompressingResultForItem(itemStacks[inputSlot]);
				if (result != null) 
				{
					for (int outputSlot = FirstOutputSlot; outputSlot < FirstOutputSlot + OutputSlotsCount; outputSlot++) {
						ItemStack outputStack = itemStacks[outputSlot];
						if (outputStack == null) {
							firstSuitableInputSlot = inputSlot;
							firstSuitableOutputSlot = outputSlot;
							break;
						}

						if (outputStack.getItem() == result.getItem() && (!outputStack.getHasSubtypes() || outputStack.getMetadata() == outputStack.getMetadata())
								&& ItemStack.areItemStackTagsEqual(outputStack, result)) {
							int combinedSize = itemStacks[outputSlot].stackSize + result.stackSize;
							if (combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot].getMaxStackSize()) {
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
		if (!performSmelt) return true;

		itemStacks[firstSuitableInputSlot].stackSize--;
		if (itemStacks[firstSuitableInputSlot].stackSize <=0) itemStacks[firstSuitableInputSlot] = null;
		if (itemStacks[firstSuitableOutputSlot] == null) 
		{
			itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
		} 
		else 
		{
			itemStacks[firstSuitableOutputSlot].stackSize += result.stackSize;
		}
		markDirty();
		return true;
	}
	
	public static ItemStack getCompressingResultForItem(ItemStack is) 
	{
		CompressorRecipes.instance();
		
		return CompressorRecipes.getCompressingResult(is);
	}
	
	public static short getItemBurnTime(ItemStack is)
	{
		int burnTime = TileEntityFurnace.getItemBurnTime(is); //Use Vanilla Fuel Burn Values
		return (short)MathHelper.clamp_int(burnTime, 0, Short.MAX_VALUE);
	}
	
	static public boolean isItemValidForFuelSlot(ItemStack is)
	{
		return true;
	}
	
	static public boolean isItemValidForInputSlot(ItemStack is)
	{
		return true;
	}
	
	static public boolean isItemValidForOutputSlot(ItemStack is)
	{
		return false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parent)
	{
		super.writeToNBT(parent);
		
		NBTTagList dataForAllSlots = new NBTTagList();
		for(int i = 0; i < this.itemStacks.length; ++i)
		{
			if(this.itemStacks[i] != null)
			{
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		parent.setTag("Items", dataForAllSlots);
		
		parent.setShort("CompressTime", compressTime);
		parent.setTag("compressTimeRemaining", new NBTTagIntArray(compressTimeRemaining));
		parent.setTag("compressTimeInitial", new NBTTagIntArray(compressTimeInitValue));
		
		return parent;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		final byte NBTTypeCompound = 10;
		NBTTagList dataForAllSlots= tag.getTagList("Items", NBTTypeCompound);
		
		Arrays.fill(itemStacks, null);
		for(int i = 0; i < dataForAllSlots.tagCount(); ++i)
		{
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			byte slotNumber = dataForOneSlot.getByte("Slot");
			if(slotNumber >= 0 && slotNumber < this.itemStacks.length)
			{
				this.itemStacks[slotNumber] = ItemStack.loadItemStackFromNBT(dataForOneSlot);
			}
		}
		
		compressTime = tag.getShort("CompressTime");
		compressTimeRemaining = Arrays.copyOf(tag.getIntArray("compressTimeRemaining"), FuelSlotsCount);
		compressTimeInitValue = Arrays.copyOf(tag.getIntArray("compressTimeInitial"), FuelSlotsCount);
				cachedNumberOfBurningSlots = -1;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		final int META = 0;
		return new SPacketUpdateTileEntity(this.pos, META, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager n, SPacketUpdateTileEntity p)
	{
		readFromNBT(p.getNbtCompound());
	}
}
