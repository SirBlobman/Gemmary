package com.SirBlobman.gemmary.guicontainer;

import com.SirBlobman.gemmary.tiles.AHTV_TE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHTV extends Container
{
	private AHTV_TE tileHTV;
	
	private int [] cachedFields;
	
	private final int HotBarSlotCount = 9;
	private final int PlayerInventoryRowCount = 3;
	private final int PlayerInventoryColumnCount = 9;
	private final int PlayerInventorySlotCount = PlayerInventoryRowCount * PlayerInventoryColumnCount;
	private final int VanillaSlotCount = HotBarSlotCount + PlayerInventorySlotCount;
	
	public final int WaterSlotsCount = 1;
	public final int InputSlotsCount = 9;
	public final int OutputSlotsCount = 1;
	public final int HTVSlotsCount = WaterSlotsCount + InputSlotsCount + OutputSlotsCount;
	
	private final int VanillaFirstSlotIndex = 0;
	private final int FirstWaterSlotIndex = VanillaFirstSlotIndex + VanillaSlotCount;
	private final int FirstInputSlotIndex = FirstWaterSlotIndex + WaterSlotsCount;
	
	private final int FirstWaterSlotNumber = 0;
	private final int FirstInputSlotNumber = FirstWaterSlotNumber + WaterSlotsCount;
	private final int FirstOutputSlotNumber = FirstInputSlotNumber + InputSlotsCount;
	
	public ContainerHTV(InventoryPlayer invPlayer, AHTV_TE tileHTV)
	{
		this.tileHTV = tileHTV;
		
		final int SlotXSpacing = 18;
		final int SlotYSpacing = 18;
		final int HotbarXPos = 9;
		final int HotbarYPos = 231;
		for (int x = 0; x < HotBarSlotCount; x++)
		{
			int slotNumber = x;
			addSlotToContainer(new Slot(invPlayer, slotNumber, HotbarXPos + SlotXSpacing * x, HotbarYPos));
		}
		
		final int PlayerInventoryXPos = 9;
		final int PlayerInventoryYPos = 170;
		for (int y = 0; y < PlayerInventoryRowCount; y++)
		{
			for (int x = 0; x < PlayerInventoryColumnCount; x++)
			{
				int slotNumber = HotBarSlotCount + y * PlayerInventoryColumnCount + x;
				int xpos = PlayerInventoryXPos + x * SlotXSpacing;
				int ypos = PlayerInventoryYPos + y * SlotYSpacing;
				addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
			}
		}
		
		final int WaterSlotsXPos = 42;
		final int WaterSlotsYPos = 99;
		for(int x = 0; x < WaterSlotsCount; x++)
		{
			int slotNumber = x + FirstWaterSlotNumber;
			addSlotToContainer(new SlotWater(tileHTV, slotNumber, WaterSlotsXPos + SlotXSpacing * x, WaterSlotsYPos));
		}
		
		final int InputSlotsXPos = 27;
		final int InputSlotsYPos = 30;
		for (int y = 0; y < InputSlotsCount; y++)
		{
			int slotNumber = y + FirstInputSlotNumber;
			addSlotToContainer(new SlotHydratableInput(tileHTV, slotNumber, InputSlotsXPos, InputSlotsYPos + SlotYSpacing * y));
		}
		
		final int OutputSlotsXPos = 116;
		final int OutputSlotsYPos = 79;
		for (int y = 0; y < OutputSlotsCount; y++)
		{
			int slotNumber = y + FirstOutputSlotNumber;
			addSlotToContainer(new SlotOutput(tileHTV, slotNumber, OutputSlotsXPos, OutputSlotsYPos + SlotYSpacing * y));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileHTV.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex)
	{
		Slot sourceSlot = (Slot)inventorySlots.get(sourceSlotIndex);
		if (sourceSlot == null || !sourceSlot.getHasStack()) return null;
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();

		if (sourceSlotIndex >= VanillaFirstSlotIndex && sourceSlotIndex < VanillaFirstSlotIndex + VanillaSlotCount) 
		{
			if (AHTV_TE.getHydratingResultForItem(sourceStack) != null)
			{
				if (!mergeItemStack(sourceStack, FirstInputSlotIndex, FirstInputSlotIndex + InputSlotsCount, false))
				{
					return null;
				}
			}	
			else 
				if (AHTV_TE.getItemHydrateTime(sourceStack) > 0) 
				{
				if (!mergeItemStack(sourceStack, FirstWaterSlotIndex, FirstWaterSlotIndex + WaterSlotsCount, true)) 
				{
					return null;
				}
			}	
			else 
			{
				return null;
			}
		} 
		else if (sourceSlotIndex >= FirstWaterSlotIndex && sourceSlotIndex < FirstWaterSlotIndex + HTVSlotsCount) 
		{
			if (!mergeItemStack(sourceStack, VanillaFirstSlotIndex, VanillaFirstSlotIndex + VanillaSlotCount, false)) 
			{
				return null;
			}
		} 
		else 
		{
			System.err.print("Invalid slotIndex:" + sourceSlotIndex);
			return null;
		}

		if (sourceStack.stackSize == 0) {
			sourceSlot.putStack(null);
		} else {
			sourceSlot.onSlotChanged();
		}

		sourceSlot.onPickupFromSlot(player, sourceStack);
		return copyOfSourceStack;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		boolean allFieldsHaveChanged = false;
		boolean fieldHasChanged [] = new boolean[tileHTV.getFieldCount()];
		if (cachedFields == null)
		{
			cachedFields = new int[tileHTV.getFieldCount()];
			allFieldsHaveChanged = true;
		}
		for (int i = 0; i < cachedFields.length; ++i)
		{
			if (allFieldsHaveChanged || cachedFields[i] != tileHTV.getField(i))
			{
				cachedFields[i] = tileHTV.getField(i);
				fieldHasChanged[i] = true;
			}
		}
		
		for (int i = 0; i < this.crafters.size(); ++i) 
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			for (int fieldID = 0; fieldID < tileHTV.getFieldCount(); ++fieldID) 
			{
				if (fieldHasChanged[fieldID]) 
				{
					icrafting.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		tileHTV.setField(id, data);
	}
	
	public class SlotWater extends Slot
	{
		public SlotWater(IInventory inventoryIn, int index, int xPosition, int yPosition)
		{
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack)
		{
			return AHTV_TE.isItemValidForWaterSlot(stack);
		}
	}
	
	public class SlotHydratableInput extends Slot
	{
		public SlotHydratableInput(AHTV_TE tileHTV, int index, int xPosition, int yPosition)
		{
			super(tileHTV, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack)
		{
			return AHTV_TE.isItemValidForInputSlot(stack);
		}
	}
	
	public class SlotOutput extends Slot
	{
		public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) 
		{
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) 
		{
			return AHTV_TE.isItemValidForOutputSlot(stack);
		}
	}
}