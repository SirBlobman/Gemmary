package com.SirBlobman.gemmary.gui.container;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.tile.TileCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCompressor extends Container
{
	private TileCompressor tc;
	private int[] cachedFields;
	
	private final int HOTBAR_SLOTS = 9;
	private final int PLAYER_ROWS = 3;
	private final int PLAYER_COLUMNS = 9;
	private final int PLAYER_SLOTS = PLAYER_COLUMNS * PLAYER_ROWS;
	private final int VANILLA_SLOTS = PLAYER_SLOTS + HOTBAR_SLOTS;
	
	private final int FUEL_SLOTS = 1;
	private final int INPUT_SLOTS = 1;
	private final int OUTPUT_SLOTS = 1;
	private final int COMPRESSOR_SLOTS = FUEL_SLOTS + INPUT_SLOTS + OUTPUT_SLOTS;
	
	private final int VANILLA_FIRST_I = 0;
	private final int FUEL_FIRST_I = VANILLA_FIRST_I + VANILLA_SLOTS;
	private final int INPUT_FIRST_I = FUEL_FIRST_I + FUEL_SLOTS;
	
	private final int FUEL_FIRST = 0;
	private final int INPUT_FIRST = FUEL_FIRST + FUEL_SLOTS;
	private final int OUTPUT_FIRST = INPUT_FIRST + INPUT_SLOTS;
	
	public ContainerCompressor(InventoryPlayer ip, TileCompressor tc)
	{
		this.tc = tc;
		
		final int X_SPACING = 18;
		final int Y_SPACING = 18;
		final int HOTBAR_X = 8;
		final int HOTBAR_Y = 142;	
		for(int x = 0; x < HOTBAR_SLOTS; x++)
		{
			int slot = x;
			Slot SLOT = new Slot(ip, slot, HOTBAR_X + X_SPACING * x, HOTBAR_Y);
			addSlotToContainer(SLOT);
		}
		
		final int PLAYER_X = 8;
		final int PLAYER_Y = 84;
		for(int y = 0; y < PLAYER_ROWS; y++)
		{
			for(int x = 0; x < PLAYER_COLUMNS; x++)
			{
				int slot = HOTBAR_SLOTS + y * PLAYER_COLUMNS + x;
				int xp = PLAYER_X + x * X_SPACING;
				int yp = PLAYER_Y + y * Y_SPACING;
				Slot SLOT = new Slot(ip, slot, xp, yp);
				addSlotToContainer(SLOT);
			}
		}
		
		final int FUEL_X = 32;
		final int FUEL_Y = 43;
		for(int x = 0; x < FUEL_SLOTS; x++)
		{
			int slot = x + FUEL_FIRST;
			SlotFuel SF = new SlotFuel(tc, slot, FUEL_X + X_SPACING * x, FUEL_Y);
			addSlotToContainer(SF);
		}
		
		final int INPUT_X = 32;
		final int INPUT_Y = 10;
		for(int y = 0; y < INPUT_SLOTS; y++)
		{
			int slot = y + INPUT_FIRST;
			SlotInput SI = new SlotInput(tc, slot, INPUT_X, INPUT_Y + Y_SPACING * y);
			addSlotToContainer(SI);
		}
		
		final int OUTPUT_X = 130;
		final int OUTPUT_Y = 25;
		for(int y = 0; y < OUTPUT_SLOTS; y++)
		{
			int slot = y + OUTPUT_FIRST;
			SlotOutput SO = new SlotOutput(tc, slot, OUTPUT_X, OUTPUT_Y + Y_SPACING * y);
			addSlotToContainer(SO);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer ep) {return tc.isUseableByPlayer(ep);}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer ep, int index)
	{
		Slot slot = inventorySlots.get(index);
		if(slot == null || !slot.getHasStack()) return null;
		
		ItemStack is = slot.getStack();
		ItemStack copy = is.copy();
		
		boolean b1 = index >= VANILLA_FIRST_I;
		boolean b2 = index < VANILLA_FIRST_I + VANILLA_SLOTS;
		boolean b3 = index > FUEL_FIRST_I;
		boolean b4 = index < FUEL_FIRST_I + COMPRESSOR_SLOTS;
		if(b1 && b2)
		{
			if(TileCompressor.getResult(is) != null)
			{
				int i1 = INPUT_FIRST_I;
				int i2 = INPUT_FIRST_I + INPUT_SLOTS;
				if(!mergeItemStack(is, i1, i2, false)) return null;
			}
			else if(TileCompressor.getBurnTime(is) > 0)
			{
				int i3 = FUEL_FIRST_I;
				int i4 = FUEL_FIRST_I + FUEL_SLOTS;
				if(!mergeItemStack(is, i3, i4, true)) return null;
			}
			else return null;
		}
		else if(b3 && b4)
		{
			int i5 = VANILLA_FIRST_I;
			int i6 = VANILLA_FIRST_I + VANILLA_SLOTS;
			if(!mergeItemStack(is, i5, i6, false)) return null;
		}
		else
		{
			GUtil.print("Invalid Slot Index: " + index);
			return null;
		}
		
		if(is.stackSize == 0) slot.putStack(null);
		else slot.onSlotChanged();
		
		slot.onPickupFromSlot(ep, is);
		return copy;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		boolean allChanged = false;
		boolean fieldChanged[] = new boolean[tc.getFieldCount()];
		
		if(cachedFields == null)
		{
			cachedFields = new int[tc.getFieldCount()];
			allChanged = true;
		}
		
		for(int i = 0; i < cachedFields.length; ++i)
		{
			if(allChanged || cachedFields[i] != tc.getField(i))
			{
				cachedFields[i] = tc.getField(i);
				fieldChanged[i] = true;
			}
		}
		
		for(int i = 0; i < listeners.size(); ++i)
		{
			IContainerListener icl = listeners.get(i);
			for(int field = 0; field < tc.getFieldCount(); ++field)
			{
				if(fieldChanged[field])
				{
					icl.sendProgressBarUpdate(this, field, cachedFields[field]);
				}
			}
		}
	}
	
	@Override
	public void updateProgressBar(int id, int data) {tc.setField(id, data);}
	
	public class SlotFuel extends Slot
	{
		public SlotFuel(IInventory ii, int i, int x, int y)
		{
			super(ii, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileCompressor.isValidFuel(is);}
	}
	
	public class SlotInput extends Slot
	{
		public SlotInput(TileCompressor tc, int i, int x, int y)
		{
			super(tc, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileCompressor.isValidInput(is);}
	}
	
	public class SlotOutput extends Slot
	{
		public SlotOutput(IInventory ii, int i, int x, int y)
		{
			super(ii, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileCompressor.isValudOutput(is);}
	}
}