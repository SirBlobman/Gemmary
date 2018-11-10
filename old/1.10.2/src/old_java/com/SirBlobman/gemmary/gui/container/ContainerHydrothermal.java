package com.SirBlobman.gemmary.gui.container;

import com.SirBlobman.gemmary.GUtil;
import com.SirBlobman.gemmary.tile.TileHydrothermal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHydrothermal extends Container
{
	private TileHydrothermal th;
	private int[] cached;
	
	private final int HOTBAR_SLOTS = 9;
	private final int PLAYER_SLOTS = 9 * 3;
	private final int VANILLA_SLOTS = HOTBAR_SLOTS + PLAYER_SLOTS;
	
	private final int WATER_SLOTS = 1;
	private final int INPUT_SLOTS = 1;
	private final int OUTPUT_SLOTS = 1;
	private final int HTV_SLOTS = WATER_SLOTS + INPUT_SLOTS + OUTPUT_SLOTS;
	
	private final int FIRST_VANILLA_I = 0;
	private final int FIRST_WATER_I = FIRST_VANILLA_I + VANILLA_SLOTS;
	private final int FIRST_INPUT_I = FIRST_WATER_I + WATER_SLOTS;
	
	private final int FIRST_WATER = 0;
	private final int FIRST_INPUT = FIRST_WATER + WATER_SLOTS;
	private final int FIRST_OUTPUT = FIRST_INPUT + INPUT_SLOTS;
	
	public ContainerHydrothermal(InventoryPlayer ip, TileHydrothermal th)
	{
		this.th = th;
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
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				int slot = HOTBAR_SLOTS + y * 9 + x;
				int xp = PLAYER_X + x + X_SPACING;
				int yp = PLAYER_Y + y + Y_SPACING;
				Slot SLOT = new Slot(ip, slot, xp, yp);
				addSlotToContainer(SLOT);
			}
		}
		
		final int WATER_X = 10;
		final int WATER_Y = 60;
		for(int x = 0; x < WATER_SLOTS; x++)
		{
			int slot = x + FIRST_WATER;
			SlotWater sw = new SlotWater(th, slot, WATER_X + X_SPACING * x, WATER_Y);
			addSlotToContainer(sw);
		}
		
		final int INPUT_X = 56;
		final int INPUT_Y = 34;
		for(int x = 0; x < INPUT_SLOTS; x++)
		{
			int slot = x + FIRST_INPUT;
			SlotInput si = new SlotInput(th, slot, INPUT_X + X_SPACING * x, INPUT_Y);
			addSlotToContainer(si);
		}
		
		final int OUTPUT_X = 56;
		final int OUTPUT_Y = 34;
		for(int y = 0; y < OUTPUT_SLOTS; y++)
		{
			int slot = y + FIRST_OUTPUT;
			SlotOutput so = new SlotOutput(th, slot, OUTPUT_X, OUTPUT_Y + Y_SPACING * y);
			addSlotToContainer(so);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer ep) {return th.isUseableByPlayer(ep);}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer ep, int source)
	{
		Slot slot = inventorySlots.get(source);
		if(slot == null || !slot.getHasStack()) return null;
		ItemStack is = slot.getStack();
		ItemStack copy = is.copy();
		
		boolean b1 = source >= FIRST_VANILLA_I;
		boolean b2 = source < FIRST_VANILLA_I + VANILLA_SLOTS;
		boolean b3 = source >= FIRST_WATER_I;
		boolean b4 = source < FIRST_WATER_I + HTV_SLOTS;
		if(b1 && b2)
		{
			if(TileHydrothermal.getResult(is) != null)
			{
				if(!mergeItemStack(is, FIRST_INPUT_I, FIRST_INPUT_I + INPUT_SLOTS, false)) return null;
			}
			else if(TileHydrothermal.getHydrateTime(is) > 0)
			{
				if(!mergeItemStack(is, FIRST_WATER_I, FIRST_WATER_I + WATER_SLOTS, true)) return null;
			}
			else return null;
		}
		else if(b3 && b4)
		{
			if(!mergeItemStack(is, FIRST_VANILLA_I, FIRST_VANILLA_I + VANILLA_SLOTS, false)) return null;
		}
		else
		{
			GUtil.print("Invalid Slot Index: " + source);
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
		boolean fieldChanged[] = new boolean[th.getFieldCount()];
		if(cached == null)
		{
			cached = new int[th.getFieldCount()];
			allChanged = true;
		}
		for(int i = 0; i < cached.length; ++i)
		{
			if(allChanged || cached[i] != th.getField(i))
			{
				cached[i] = th.getField(i);
				fieldChanged[i] = true;
			}
		}
		
		for(int i = 0; i < listeners.size(); ++i)
		{
			IContainerListener icl = listeners.get(i);
			for(int id = 0; id < th.getFieldCount(); ++id)
			{
				if(fieldChanged[id])
				{
					icl.sendProgressBarUpdate(this, id, cached[id]);
				}
			}
		}
	}
	
	@Override
	public void updateProgressBar(int id, int data) {th.setField(id, data);}
	
	public class SlotWater extends Slot
	{
		public SlotWater(IInventory ii, int i, int x, int y) {super(ii,i,x,y);}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileHydrothermal.isValidWater(is);}
	}
	
	public class SlotInput extends Slot
	{
		public SlotInput(IInventory ii, int i, int x, int y) {super(ii,i,x,y);}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileHydrothermal.isValidInput(is);}
	}
	
	public class SlotOutput extends Slot
	{
		public SlotOutput(IInventory ii, int i, int x, int y) {super(ii,i,x,y);}
		
		@Override
		public boolean isItemValid(ItemStack is) {return TileHydrothermal.isValidOutput(is);}	
	}
}