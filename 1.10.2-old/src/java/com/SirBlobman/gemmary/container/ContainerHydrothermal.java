package com.SirBlobman.gemmary.container;

import com.SirBlobman.gemmary.tile.HydrothermalTE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHydrothermal extends Container
{
	private HydrothermalTE tH;
	
	private int [] cachedFields;
	
	private final int HotbarSlotCount = 9;
	private final int PlayerInvRowCount = 3;
	private final int PlayerInvColumnCount = 9;
	private final int PlayerInvSlotCount = PlayerInvRowCount * PlayerInvColumnCount;
	private final int VanillaSlotCount = HotbarSlotCount + PlayerInvSlotCount;
	
	public final int WaterSlotsCount = 1;
	public final int InputSlotsCount = 1;
	public final int OutputSlotsCount = 1;
	public final int HTVSlotsCount = 3;
	
	private final int VanillaFirstSlotIndex = 0;
	private final int FirstWaterSlotIndex = VanillaFirstSlotIndex + VanillaSlotCount;
	private final int FirstInputSlotIndex = FirstWaterSlotIndex + WaterSlotsCount;
	
	private final int FirstWaterSlotNumber = 0;
	private final int FirstInputSlotNumber = FirstWaterSlotNumber + WaterSlotsCount;
	private final int FirstOutputSlotNumber = FirstInputSlotNumber + InputSlotsCount;
	
	public ContainerHydrothermal(InventoryPlayer ip, HydrothermalTE th)
	{
		tH = th;
		
		final int SlotXSpacing = 18;
		final int SlotYSpacing = 18;
		final int HotbarXPos = 8;
		final int HotbarYPos = 142;
		for(int x = 0; x < HotbarSlotCount; x++)
		{
			int slotNumber = x;
			addSlotToContainer(new Slot(ip, slotNumber, HotbarXPos + SlotXSpacing * x, HotbarYPos));
		}
		
		final int PlayerInvXPos = 8;
		final int PlayerInvYPos = 84;
		for(int y = 0; y < PlayerInvRowCount; y++)
		{
			for(int x = 0; x < PlayerInvColumnCount; x++)
			{
				int slotNumber = HotbarSlotCount + y * PlayerInvColumnCount + x;
				int xpos = PlayerInvXPos + x * SlotXSpacing;
				int ypos = PlayerInvYPos + y * SlotYSpacing;
				addSlotToContainer(new Slot(ip, slotNumber, xpos, ypos));
			}
		}
		
		final int WaterSlotsXPos = 10;
		final int WaterSlotsYPos = 60;
		for(int x = 0; x < WaterSlotsCount; x++)
		{
			int slotNumber = x + FirstWaterSlotNumber;
			addSlotToContainer(new SlotWater(tH, slotNumber, WaterSlotsXPos + SlotXSpacing * x, WaterSlotsYPos));
		}
		
		final int InputSlotsXPos = 56;
		final int InputSlotsYPos = 34;
		for(int x = 0; x < InputSlotsCount; x++)
		{
			int slotNumber = x + FirstInputSlotNumber;
			addSlotToContainer(new SlotHydratableInput(tH, slotNumber, InputSlotsXPos + SlotXSpacing * x, InputSlotsYPos));
		}
		
		final int OutputSlotsXPos = 116;
		final int OutputSlotsYPos = 35;
		for(int y = 0; y < OutputSlotsCount; y++)
		{
			int slotNumber = y + FirstOutputSlotNumber;
			addSlotToContainer(new SlotOuput(tH, slotNumber, OutputSlotsXPos, OutputSlotsYPos + SlotYSpacing * y));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p)
	{
		return tH.isUseableByPlayer(p);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int source)
	{
		Slot sSlot = (Slot)inventorySlots.get(source);
		if(sSlot == null || !sSlot.getHasStack()) return null;
		ItemStack sStack = sSlot.getStack();
		ItemStack copyStack = sStack.copy();
		
		if(source >= VanillaFirstSlotIndex && source < VanillaFirstSlotIndex + VanillaSlotCount)
		{
			if(HydrothermalTE.getHydratingResultForItem(sStack) != null)
			{
				if(!mergeItemStack(sStack, FirstInputSlotIndex, FirstInputSlotIndex + InputSlotsCount, false))
				{
					return null;
				}
			}
			else if(HydrothermalTE.getItemHydrateTime(sStack) > 0)
			{
				if(!mergeItemStack(sStack, FirstWaterSlotIndex, FirstWaterSlotIndex + WaterSlotsCount, true))
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		}
		else if (source >= FirstWaterSlotIndex && source < FirstWaterSlotIndex + HTVSlotsCount)
		{
			if(!mergeItemStack(sStack, VanillaFirstSlotIndex, VanillaFirstSlotIndex + VanillaSlotCount, false))
			{
				return null;
			}
		}
		else
		{
			System.err.print("Invalid slotIndex:" + source);
			return null;
		}
		
		if(sStack.stackSize == 0)
		{
			sSlot.putStack(null);
		}
		else
		{
			sSlot.onSlotChanged();
		}
		
		sSlot.onPickupFromSlot(p, sStack);
		return copyStack;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		boolean allFieldsHaveChanged = false;
		boolean fieldHasChanged [] = new boolean[tH.getFieldCount()];
		
		if(cachedFields == null)
		{
			cachedFields = new int[tH.getFieldCount()];
			allFieldsHaveChanged = true;
		}
		for(int i = 0; i < cachedFields.length; ++i)
		{
			if(allFieldsHaveChanged || cachedFields[i] != tH.getField(i))
			{
				cachedFields[i] = tH.getField(i);
				fieldHasChanged[i] = true;
			}
		}
		
		for(int i = 0; i < listeners.size(); ++i)
		{
			IContainerListener ic = (IContainerListener)listeners.get(i);
			for(int fieldID = 0; fieldID < tH.getFieldCount(); ++fieldID)
			{
				if(fieldHasChanged[fieldID])
				{
					ic.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		tH.setField(id, data);
	}
	
	public class SlotWater extends Slot
	{
		public SlotWater(IInventory ii, int i, int x, int y)
		{
			super(ii, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return HydrothermalTE.isItemValidForWaterSlot(is);
		}
	}
	
	public class SlotHydratableInput extends Slot
	{
		public SlotHydratableInput(HydrothermalTE th, int i, int x, int y)
		{
			super(th, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return HydrothermalTE.isItemValidForInputSlot(is);
		}
	}
	
	public class SlotOuput extends Slot
	{
		public SlotOuput(IInventory ii, int i, int x, int y)
		{
			super(ii, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return HydrothermalTE.isItemValidForOutputSlot(is);
		}
	}
}