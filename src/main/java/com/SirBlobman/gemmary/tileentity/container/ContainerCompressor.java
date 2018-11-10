package com.SirBlobman.gemmary.tileentity.container;

import com.SirBlobman.gemmary.recipe.CompressorRecipes;
import com.SirBlobman.gemmary.tileentity.TileEntityCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCompressor extends Container {
    private final TileEntityCompressor tileEntity;
    private int[] cachedFields;
    
    private static final int
        X_SPACING = 18,
        Y_SPACING = 18,
        
        HOTBAR_X = 8,
        HOTBAR_Y = 142,
        
        PLAYER_X = 8,
        PLAYER_Y = 84,
        
        FUEL_X = 32,
        FUEL_Y = 41,
        
        INPUT_X = 32,
        INPUT_Y = 19,
        
        OUTPUT_X = 126,
        OUTPUT_Y = 31
    ;
    
    public ContainerCompressor(InventoryPlayer inv, TileEntityCompressor tileEntity) {
        this.tileEntity = tileEntity;
        
        addSlotToContainer(new Slot(this.tileEntity, 0, INPUT_X, INPUT_Y));
        addSlotToContainer(new SlotFuel(this.tileEntity, 1, FUEL_X, FUEL_Y));
        addSlotToContainer(new SlotOutput(this.tileEntity, 2, OUTPUT_X, OUTPUT_Y));
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                int slotID = (9 + y * 9 + x);
                int slotX = (PLAYER_X + x * X_SPACING);
                int slotY = (PLAYER_Y + y * Y_SPACING);
                
                Slot slot = new Slot(inv, slotID, slotX, slotY);
                addSlotToContainer(slot);
            }
        }
        
        for(int x = 0; x < 9; ++x) {
            int slotX = (HOTBAR_X + X_SPACING * x);
            Slot slot = new Slot(inv, x, slotX, HOTBAR_Y);
            addSlotToContainer(slot);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileEntity.isUsableByPlayer(player);
    }
    
    @Override
    public void updateProgressBar(int id, int value) {
        this.tileEntity.setField(id, value);
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        boolean allChanged = false;
        boolean[] fieldChanged = new boolean[this.tileEntity.getFieldCount()];
        
        if(this.cachedFields == null) {
            this.cachedFields = new int[this.tileEntity.getFieldCount()];
            allChanged = true;
        }
        
        int cachedFieldsLength = this.cachedFields.length;
        for(int i = 0; i < cachedFieldsLength; ++i) {
            if(allChanged || this.cachedFields[i] != this.tileEntity.getField(i)) {
                this.cachedFields[i] = this.tileEntity.getField(i);
                fieldChanged[i] = true;
            }
        }
        
        int listenersSize = this.listeners.size();
        for(int i = 0; i < listenersSize; ++i) {
            IContainerListener listener = this.listeners.get(i);
            for(int field = 0; field < this.tileEntity.getFieldCount(); ++field) {
                if(fieldChanged[field]) listener.sendWindowProperty(this, field, this.cachedFields[field]);
            }
        }
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack item = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack slotItem = slot.getStack();
            item = slotItem.copy();
            
            if(index == 2) {
                if(!mergeItemStack(slotItem, 3, 39, true)) return ItemStack.EMPTY;
                slot.onSlotChange(slotItem, item);
            } else if(index != 1 && index != 0) {
                if(!CompressorRecipes.getInstance().getResult(slotItem).isEmpty()) {
                    if(!mergeItemStack(slotItem, 0, 1, false)) return ItemStack.EMPTY;
                } else if(TileEntityCompressor.isFuel(slotItem)) {
                    if(!mergeItemStack(slotItem, 1, 2, false)) return ItemStack.EMPTY;
                } else if(index >= 3 && index < 30) {
                    if(!mergeItemStack(slotItem, 30, 39, false)) return ItemStack.EMPTY;
                } else if(index >= 30 && index < 39 && !mergeItemStack(slotItem, 3, 30, false)) return ItemStack.EMPTY;
            } else if(!mergeItemStack(slotItem, 3, 39, false)) return ItemStack.EMPTY;
            
            if(slotItem.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();
            
            if(slotItem.getCount() == item.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, slotItem);
        }
        
        return item;
    }
    
    private static class SlotFuel extends Slot {
        public SlotFuel(IInventory inv, int index, int x, int y) {super(inv, index, x, y);}
        
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntityCompressor.isFuel(stack);
        }
    }
    
    private static class SlotOutput extends Slot {
        public SlotOutput(IInventory inv, int index, int x, int y) {super(inv, index, x, y);}
        
        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }
    }
}