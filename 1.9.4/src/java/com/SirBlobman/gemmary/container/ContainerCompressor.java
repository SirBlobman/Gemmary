package com.SirBlobman.gemmary.container;

import com.SirBlobman.gemmary.tile.CompressorTE;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCompressor extends Container
{
	private CompressorTE tC;
	
	private int [] cachedFields;
	
	private final int HotbarSlotCount = 9;
	private final int PlayerInvRowCount = 3;
	private final int PlayerInvColumnCount = 9;
	private final int PlayerInvSlotCount = PlayerInvColumnCount * PlayerInvRowCount;
	private final int VanillaSlotCount = HotbarSlotCount + PlayerInvSlotCount;
	
	public final int FuelSlotsCount = 1;
	public final int InputSlotsCount = 1;
	public final int OutputSlotsCount = 1;
	public final int CompressorSlotsCount = FuelSlotsCount + InputSlotsCount + OutputSlotsCount;
	
	private final int VanillaFirstSlotIndex = 0;
	private final int FirstFuelSlotIndex = VanillaFirstSlotIndex + VanillaSlotCount;
	private final int FirstInputSlotIndex = FirstFuelSlotIndex + FuelSlotsCount;
	
	private final int FirstFuelSlotNumber = 0;
	private final int FirstInputSlotNumber = FirstFuelSlotNumber + FuelSlotsCount;
	private final int FirstOutputSlotNumber = FirstInputSlotNumber + InputSlotsCount;
	
	public ContainerCompressor(InventoryPlayer iP, CompressorTE tC)
	{
		this.tC = tC;
		
		final int SlotXSpacing = 18;
		final int SlotYSpacing = 18;
		final int HotbarXPos = 8;
		final int HotbarYPos = 142;
		
		for(int x = 0; x < HotbarSlotCount; x++)
		{
			int slotNumber = x;
			addSlotToContainer(new Slot(iP, slotNumber, HotbarXPos + SlotXSpacing * x, HotbarYPos));
		}
		
		final int PlayerInventoryXPos = 8;
		final int PlayerInventoryYPos = 84;
		
		for(int y = 0; y < PlayerInvRowCount; y++)
		{
			for(int x = 0; x < PlayerInvColumnCount; x++)
			{
				int slotNumber = HotbarSlotCount + y * PlayerInvColumnCount + x;
				int xpos = PlayerInventoryXPos + x * SlotXSpacing;
				int ypos = PlayerInventoryYPos + y * SlotYSpacing;
				addSlotToContainer(new Slot(iP, slotNumber, xpos, ypos));
			}
		}
		
		final int FuelSlotsXPos = 32;
		final int FuelSlotsYPos = 43;
		
		for(int x = 0; x< FuelSlotsCount; x++)
		{
			int slotNumber = x + FirstFuelSlotNumber;
			addSlotToContainer(new SlotFuel(tC, slotNumber, FuelSlotsXPos + SlotXSpacing * x, FuelSlotsYPos));
		}
		
		final int InputSlotsXPos = 32;
		final int InputSlotsYPos = 10;
		
		for(int y = 0; y < InputSlotsCount; y++)
		{
			int slotNumber = y+ FirstInputSlotNumber;
			addSlotToContainer(new SlotCompressableInput(tC, slotNumber, InputSlotsXPos, InputSlotsYPos + SlotYSpacing * y));
		}
		
		final int OutputSlotsXPos = 130;
		final int OutputSlotsYPos = 25;
		
		for(int y = 0; y < OutputSlotsCount; y++)
		{
			int slotNumber = y+ FirstOutputSlotNumber;
			addSlotToContainer(new SlotOutput(tC, slotNumber, OutputSlotsXPos, OutputSlotsYPos + SlotYSpacing * y));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p)
	{
		return tC.isUseableByPlayer(p);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int sSlotIndex)
	{
		Slot sSlot = (Slot)inventorySlots.get(sSlotIndex);
		if(sSlot == null || !sSlot.getHasStack()) return null;

		ItemStack sStack = sSlot.getStack();
		ItemStack copyOfsStack = sStack.copy();

		if(sSlotIndex >= VanillaFirstSlotIndex & sSlotIndex < VanillaFirstSlotIndex + VanillaSlotCount)
		{
			if(CompressorTE.getCompressingResultForItem(sStack) != null)
			{
				if(!mergeItemStack(sStack, FirstInputSlotIndex, FirstInputSlotIndex + InputSlotsCount, false))
				{
					return null;
				}
			}
			else if(CompressorTE.getItemBurnTime(sStack) > 0)
			{
				if(!mergeItemStack(sStack, FirstFuelSlotIndex, FirstFuelSlotIndex + FuelSlotsCount, true))
				{
					return null;
				}
			}
			else
			{
				return null;
			}

		}
		else if(sSlotIndex > FirstFuelSlotIndex && sSlotIndex < FirstFuelSlotIndex + CompressorSlotsCount)
		{
			if(!mergeItemStack(sStack, VanillaFirstSlotIndex, VanillaFirstSlotIndex + VanillaSlotCount, false))
			{
				return null;
			}
		}
		else
		{
			System.err.print("Invalid slotIndex:" + sSlotIndex);
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
		return copyOfsStack;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		boolean allFieldsHaveChanged = false;
		boolean fieldHasChanged [] = new boolean[tC.getFieldCount()];
		if(cachedFields == null)
		{
			cachedFields = new int[tC.getFieldCount()];
			allFieldsHaveChanged = true;
		}
		for(int i = 0; i < cachedFields.length; ++i)
		{
			if(allFieldsHaveChanged || cachedFields[i] != tC.getField(i))
			{
				cachedFields[i] = tC.getField(i);
				fieldHasChanged[i] = true;
			}
		}
		
		for(int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting iC = (ICrafting)this.crafters.get(i);
			for(int fieldID = 0; fieldID < tC.getFieldCount(); ++fieldID)
			{
				if(fieldHasChanged[fieldID])
				{
					iC.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		tC.setField(id, data);
	}
	
	public class SlotFuel extends Slot
	{
		public SlotFuel(IInventory iI, int i, int x, int y)
		{
			super(iI, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return CompressorTE.isItemValidForFuelSlot(is);
		}
	}
	
	public class SlotCompressableInput extends Slot
	{
		public SlotCompressableInput(CompressorTE tC, int i, int x, int y)
		{
			super(tC, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return CompressorTE.isItemValidForInputSlot(is);
		}
	}
	
	public class SlotOutput extends Slot
	{
		public SlotOutput(IInventory iI, int i, int x, int y)
		{
			super(iI, i, x, y);
		}
		
		@Override
		public boolean isItemValid(ItemStack is)
		{
			return CompressorTE.isItemValidForOutputSlot(is);
		}
	}
}