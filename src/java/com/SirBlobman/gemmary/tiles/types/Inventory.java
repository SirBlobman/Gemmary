package com.SirBlobman.gemmary.tiles.types;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class Inventory extends TileEntity implements IInventory
{
	private ItemStack[] inv;
	protected String invTitle;
	protected boolean hasCName;
	protected int stackSizeLimit;
	
	public Inventory(String name, int size)
	{
		this(name, size, 64);
	}
	
	public Inventory(String name, int size, int maxStackSize)
	{
		this.inv = new ItemStack[size];
		this.stackSizeLimit = maxStackSize;
		this.invTitle = name;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if(slot < 0 || slot >= inv.length)
		{
			return null;
		}
		
		return inv[slot];
	}
	
	public boolean isStackInSlot(int slot)
	{
		return getStackInSlot(slot) != null;
	}
	
	public void resize(int size)
	{
		inv = Arrays.copyOf(inv, size);
	}
	
	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return stackSizeLimit;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		if(slot < 0 || slot >= inv.length)
		{
			return;
		}
		
		inv[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int quantity)
	{
		ItemStack stack = getStackInSlot(slot);
		
		if(stack == null)
		{
			return null;
		}
		
		if(stack.stackSize <= quantity)
		{
			setInventorySlotContents(slot, null);
			this.markDirty();
			return stack;
		}
		
		stack = stack.splitStack(quantity);
		
		if(getStackInSlot(slot).stackSize == 0)
		{
			setInventorySlotContents(slot, null);
		}
		
		this.markDirty();
		return stack;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		if(slot < getSizeInventory())
		{
			if(inv[slot] == null || stack.stackSize + inv[slot].stackSize <= getInventoryStackLimit())
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void clear()
	{
		for(int i = 0; i < inv.length; i++)
		{
			inv[i] = null;
		}
	}
	
	@Override
	public String getName()
	{
		return this.invTitle;
	}
	
	@Override
	public boolean hasCustomName()
	{
		return this.hasCName;
	}
	
	public void setCustomName(String cName)
	{
		this.hasCName = true;
		this.invTitle = cName;
	}
	
	@Override
	public IChatComponent getDisplayName()
	{
		if(hasCustomName())
		{
			return new ChatComponentText(getName());
		}
		
		return new ChatComponentTranslation(getName());
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer p)
	{
		if(worldObj.getTileEntity(pos) != this || worldObj.getBlockState(pos).getBlock() == Blocks.air)
		{
			return false;
		}
		
		return p.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64D;
	}
	
	@Override
	public void openInventory(EntityPlayer p)
	{
	}
	
	@Override
	public void closeInventory(EntityPlayer p)
	{
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tags)
	{
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
	}
	
	public void readInventoryFromNBT(NBTTagCompound tags)
	{
		super.readFromNBT(tags);

	    this.resize(tags.getInteger("InventorySize"));

	    readInventoryFromNBT(this, tags);

	    if(tags.hasKey("CustomName", 8)) 
	    {
	      this.invTitle = tags.getString("CustomName");
	    }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tags)
	{
		super.writeToNBT(tags);
		
		tags.setInteger("InventorySize", inv.length);
		
		writeInventoryToNBT(this, tags);
		
		if(this.hasCustomName()){
			tags.setString("CustomName", this.invTitle);
		}
	}
	
	public static void writeInventoryToNBT(IInventory inv, NBTTagCompound tag)
	{
		NBTTagList nbttl = new NBTTagList();
		
		for(int i = 0; i < inv.getSizeInventory(); i++)
		{
			if(inv.getStackInSlot(i) != null)
			{
				NBTTagCompound itemTag = new NBTTagCompound();
		        itemTag.setByte("Slot", (byte) i);
		        inv.getStackInSlot(i).writeToNBT(itemTag);
		        nbttl.appendTag(itemTag);
			}
		}
		
		tag.setTag("Items", nbttl);
	}
	
	public static void readInventoryFromNBT(IInventory inv, NBTTagCompound tag) 
	{
	    NBTTagList nbttaglist = tag.getTagList("Items", 10);

	    for(int i = 0; i < nbttaglist.tagCount(); ++i) 
	    {
	      NBTTagCompound itemTag = nbttaglist.getCompoundTagAt(i);
	      int slot = itemTag.getByte("Slot") & 255;

	      if(slot >= 0 && slot < inv.getSizeInventory()) 
	      {
	        inv.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(itemTag));
	      }
	    }
	  }
	
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}
	
	@Override
	public int getField(int id)
	{
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
	}
	
	@Override
	public int getFieldCount()
	{
		return 0;
	}
}