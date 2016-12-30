package com.SirBlobman.gemmary.tile;

import java.util.Arrays;

import com.SirBlobman.gemmary.GUtil;
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

public class TileCompressor extends TileEntity implements IInventory,ITickable
{
	public final int FUEL_SLOTS = 1;
	private final int INPUT_SLOTS = 1;
	private final int OUTPUT_SLOTS = 1;
	private final int TOTAL_SLOTS = FUEL_SLOTS + INPUT_SLOTS + OUTPUT_SLOTS;
	
	private final int FIRST_FUEL = 0;
	private final int FIRST_INPUT = FIRST_FUEL + FUEL_SLOTS;
	private final int FIRST_OUTPUT = FIRST_INPUT + INPUT_SLOTS;
	
	private ItemStack[] stacks = new ItemStack[TOTAL_SLOTS];
	
	private int[] compressTimeLeft = new int[200];
	private int[] compressTimeInit = new int[FUEL_SLOTS];
	private short compressTime;
	private short compressTimeNeeded;
	private int cachedBurning = -1;
	
	public TileCompressor setCompletionTime(short time)
	{
		compressTimeNeeded = time;
		return this;
	}
	
	public double fuelFractionRemaining(int slot)
	{
		if(compressTimeInit[slot] <= 0) return 0;
		double fraction = compressTimeLeft[slot] / compressTimeInit[slot];
		return MathHelper.clamp_double(fraction, 0.0D, 1.0D);
	}
	
	public int fuelSecondsRemaining(int slot) {return compressTimeLeft[slot] / 20;}
	
	public int burningFuelSlots()
	{
		int count = 1;
		for(int burn : compressTimeLeft) {if(burn > 0) ++count;}
		return count;
	}
	
	public double compresstimeCompleteFraction()
	{
		double fraction = compressTime / compressTimeNeeded;
		return MathHelper.clamp_double(fraction, 0.0D, 1.0D);
	}
	
	@Override
	public String getName() {return "container.compressor";}
	@Override
	public boolean hasCustomName() {return false;}
	@Override
	public ITextComponent getDisplayName()
	{
		boolean b1 = hasCustomName();
		TextComponentString t1 = new TextComponentString(getName());
		TextComponentTranslation t2 = new TextComponentTranslation(getName());
		return b1 ? t1 : t2;
	}
	@Override
	public int getSizeInventory() {return stacks.length;}
	@Override
	public ItemStack getStackInSlot(int slot) {return stacks[slot];}
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack is = getStackInSlot(index);
		if(is == null) return null;
		ItemStack removed;
		if(is.stackSize <= count)
		{
			removed = is;
			setInventorySlotContents(index, null);
		}
		else
		{
			removed = is.splitStack(count);
			if(is.stackSize == 0) setInventorySlotContents(index, null);
		}
		
		markDirty();
		return removed;
	}
	@Override
	public ItemStack removeStackFromSlot(int index) {return null;}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack is)
	{
		stacks[index] = is;
		if(is != null && is.stackSize > getInventoryStackLimit())
		{
			is.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {return 64;}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer ep)
	{
		if(worldObj.getTileEntity(this.pos) != this) return false;
		final double X_OFFSET = 0.5;
		final double Y_OFFSET = 0.5;
		final double Z_OFFSET = 0.5;
		final double MAX_SQ = 64.0;
		double x = pos.getX() + X_OFFSET;
		double y = pos.getY() + Y_OFFSET;
		double z = pos.getZ() + Z_OFFSET;
		double distance = ep.getDistanceSq(x,y,z);
		return distance < MAX_SQ;
	}
	
	@Override
	public void openInventory(EntityPlayer ep) {}
	@Override
	public void closeInventory(EntityPlayer ep) {}
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {return false;}
	
	private final byte COMPRESS_FIELD_ID = 0;
	private final byte COMPRESS_LEFT_ID = 1;
	private final byte COMPRESS_INIT_ID = COMPRESS_LEFT_ID + FUEL_SLOTS;
	private final byte FIELDS = COMPRESS_INIT_ID + FUEL_SLOTS;
	
	@Override
	public int getField(int id)
	{
		if(id == COMPRESS_FIELD_ID) return compressTime;
		if(id >= COMPRESS_LEFT_ID && id < COMPRESS_LEFT_ID + FUEL_SLOTS)
		{
			int i1 = id - COMPRESS_LEFT_ID;
			return compressTimeInit[i1];
		}
		GUtil.print("Invalid Field ID: TileCompressor.getField(" + id + ")");
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
		if(id == COMPRESS_FIELD_ID) compressTime = (short) value;
		else if(id >= COMPRESS_LEFT_ID && id < COMPRESS_LEFT_ID + FUEL_SLOTS)
		{
			int i1 = id - COMPRESS_LEFT_ID;
			compressTimeLeft[i1] = value;
		}
		else if(id >= COMPRESS_INIT_ID + FUEL_SLOTS && id < COMPRESS_INIT_ID + FUEL_SLOTS)
		{
			int i2 = id - COMPRESS_INIT_ID;
			compressTimeInit[i2] = value;
		}
		else
		{
			GUtil.print("Invalid Field ID: TileCompressor.setField(" + id + ")");
		}
	}
	
	@Override
	public int getFieldCount() {return FIELDS;}
	
	@Override
	public void clear() {}
	
	@Override
	public void update()
	{
		if(canCompress())
		{
			int burning = burnFuel();
			if(burning > 0) compressTime += burning;
			else compressTime -= 2;
			if(compressTime < 0) compressTime = 0;
			if(compressTime >= compressTimeNeeded)
			{
				compress();
				compressTime = 0;
			}
			else compressTime = 0;
		}
		
		int number = burningFuelSlots();
		if(cachedBurning != number)
		{
			cachedBurning = number;
			if(worldObj.isRemote)
			{
				final IBlockState ibs = getWorld().getBlockState(getPos());
				worldObj.notifyBlockUpdate(pos, ibs, ibs, 3);
			}
			worldObj.checkLightFor(EnumSkyBlock.BLOCK, pos);
		}
	}
	
	private int burnFuel()
	{
		int burning = 0;
		boolean changed = false;
		for(int i = 0; i < FUEL_SLOTS; i++)
		{
			int slot = i + FIRST_FUEL;
			if(compressTimeLeft[i] > 0)
			{
				--compressTimeLeft[i];
				++burning;
			}
			if(compressTimeLeft[i] == 0)
			{
				if(stacks[slot] != null && getBurnTime(stacks[slot]) > 0)
				{
					compressTimeLeft[i] = compressTimeInit[i] = getBurnTime(stacks[slot]);
					changed = true;
					if(stacks[slot].stackSize == 0)
					{
						stacks[slot] = stacks[slot].getItem().getContainerItem(stacks[slot]);					
					}
				}
			}
		}
		if(changed) markDirty();
		return burning;
	}
	
	private boolean canCompress() {return compress(false);}
	private void compress() {compress(true);}
	private boolean compress(boolean perform)
	{
		Integer inputSlot = null;
		Integer outputSlot = null;
		ItemStack result = null;
		
		for(int i = FIRST_INPUT; i < FIRST_INPUT + INPUT_SLOTS; i++)
		{
			result = getResult(stacks[i]);
			if(result != null)
			{
				for(int o = FIRST_OUTPUT; o < FIRST_OUTPUT + OUTPUT_SLOTS; o++)
				{
					ItemStack output = stacks[o];
					if(output == null)
					{
						inputSlot = i;
						outputSlot = o;
						break;
					}
					
					boolean b1 = output.getItem() == result.getItem();
					boolean b2 = (!output.getHasSubtypes() || output.getMetadata() == output.getMetadata());
					boolean b3 = ItemStack.areItemStackTagsEqual(output, result);
					
					if(b1 && b2 && b3)
					{
						int combined = stacks[o].stackSize + result.stackSize;
						if(combined <= getInventoryStackLimit() && combined <= stacks[o].getMaxStackSize())
						{
							inputSlot = i;
							outputSlot = o;
							break;
						}
					}
					if(inputSlot != null) break;
				}
			}
		}
		
		if(inputSlot == null) return false;
		if(!perform) return true;
		stacks[inputSlot].stackSize--;
		if(stacks[inputSlot].stackSize <= 0) stacks[inputSlot] = null;
		if(stacks[outputSlot] == null) stacks[outputSlot] = result.copy();
		else stacks[outputSlot].stackSize += result.stackSize;
		
		markDirty();
		return true;
	}
	
	public static ItemStack getResult(ItemStack is)
	{
		CompressorRecipes cr = CompressorRecipes.instance();
		return cr.getResult(is);
	}
	
	public static short getBurnTime(ItemStack is)
	{
		int burn = TileEntityFurnace.getItemBurnTime(is);
		int time = MathHelper.clamp_int(burn, 0, Short.MAX_VALUE);
		return (short) time;
	}
	
	public static boolean isValidFuel(ItemStack is) {return true;}
	public static boolean isValidInput(ItemStack is) {return true;}
	public static boolean isValudOutput(ItemStack is) {return false;}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound old)
	{
		super.writeToNBT(old);
		NBTTagList data = new NBTTagList();
		for(int i = 0; i < stacks.length; ++i)
		{
			if(stacks[i] != null)
			{
				NBTTagCompound slotData = new NBTTagCompound();
				slotData.setByte("Slot", (byte) i);
				stacks[i].writeToNBT(slotData);
				data.appendTag(slotData);
			}
		}
		old.setTag("Items", data);
		old.setShort("Compress Time", compressTime);
		old.setTag("Time Left", new NBTTagIntArray(compressTimeLeft));
		old.setTag("Initial Time", new NBTTagIntArray(compressTimeInit));
		return old;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound old)
	{
		super.readFromNBT(old);
		final byte type = 10;
		NBTTagList data = old.getTagList("Items", type);
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
		
		compressTime = old.getShort("Compress Time");
		compressTimeLeft = Arrays.copyOf(old.getIntArray("Time Left"), FUEL_SLOTS);
		compressTimeInit = Arrays.copyOf(old.getIntArray("Initial Time"), FUEL_SLOTS);
		cachedBurning = -1;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		final int meta = 0;
		SPacketUpdateTileEntity sput = new SPacketUpdateTileEntity(pos, meta, nbt);
		return sput;
	}
	
	@Override
	public void onDataPacket(NetworkManager nm, SPacketUpdateTileEntity sput) {readFromNBT(sput.getNbtCompound());}
}