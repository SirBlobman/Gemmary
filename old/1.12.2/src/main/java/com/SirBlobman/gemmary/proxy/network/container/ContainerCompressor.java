package com.SirBlobman.gemmary.proxy.network.container;

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
    private final TileEntityCompressor tileCompressor;
    private int[] cachedFields;
    
    private static final int
        X_SPACING = 18,  Y_SPACING = 18,
        HOTBAR_X  = 8,   HOTBAR_Y  = 142,
        PLAYER_X  = 8,   PLAYER_Y  = 84,
        FUEL_X    = 32,  FUEL_Y    = 41,
        INPUT_X   = 32,  INPUT_Y   = 19,
        OUTPUT_X  = 126, OUTPUT_Y  = 31;
    
    public ContainerCompressor(InventoryPlayer ip, TileEntityCompressor tileCompressor) {
        this.tileCompressor = tileCompressor;
        addSlotToContainer(new Slot(tileCompressor, 0, INPUT_X, INPUT_Y));
        addSlotToContainer(new SlotFuel(tileCompressor, 1, FUEL_X, FUEL_Y));
        addSlotToContainer(new SlotOutput(tileCompressor, 2, OUTPUT_X, OUTPUT_Y));
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                int id = 9 + y * 9 + x;
                int xp = PLAYER_X + x * X_SPACING;
                int yp = PLAYER_Y + y * Y_SPACING;
                Slot slot = new Slot(ip, id, xp, yp);
                addSlotToContainer(slot);
            }
        }
        
        for(int x = 0; x < 9; ++x) {
            Slot slot = new Slot(ip, x, HOTBAR_X + X_SPACING * x, HOTBAR_Y);
            addSlotToContainer(slot);
        }
    }
    
    public boolean canInteractWith(EntityPlayer ep) {return tileCompressor.isUsableByPlayer(ep);}
    public void updateProgressBar(int id, int data) {tileCompressor.setField(id, data);}
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        boolean allChanged = false;
        boolean[] fieldChanged = new boolean[tileCompressor.getFieldCount()];
        
        if(cachedFields == null) {
            cachedFields = new int[tileCompressor.getFieldCount()];
            allChanged = true;
        }
        
        for(int i = 0; i < cachedFields.length; ++i) {
            if(allChanged || cachedFields[i] != tileCompressor.getField(i)) { 
                cachedFields[i] = tileCompressor.getField(i);
                fieldChanged[i] = true;
            }
        }
        
        for(int i = 0; i < listeners.size(); ++i) {
            IContainerListener icl = listeners.get(i);
            for(int field = 0; field < tileCompressor.getFieldCount(); ++field) {
                if(fieldChanged[field]) icl.sendWindowProperty(this, field, cachedFields[field]);
            }
        }
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer ep, int index) {
        ItemStack is = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack is1 = slot.getStack();
            is = is1.copy();
            if(index == 2) {
                if(!mergeItemStack(is1, 3, 39, true)) return ItemStack.EMPTY;
                slot.onSlotChange(is1, is);
            } else if(index != 1 && index != 0) {
                if(!CompressorRecipes.instance().getResult(is1).isEmpty()) {
                    if(!mergeItemStack(is1, 0, 1, false)) return ItemStack.EMPTY;
                } else if(TileEntityCompressor.isItemFuel(is1)) {
                    if(!mergeItemStack(is1, 1, 2, false)) return ItemStack.EMPTY;
                } else if(index >= 3 && index < 30) {
                    if(!mergeItemStack(is1, 30, 39, false)) return ItemStack.EMPTY;
                } else if(index >= 30 && index < 39 && !mergeItemStack(is1, 3, 30, false)) return ItemStack.EMPTY;
            } else if(!mergeItemStack(is1, 3, 39, false)) return ItemStack.EMPTY;
            
            if(is1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();
            
            if(is1.getCount() == is.getCount()) return ItemStack.EMPTY;
            slot.onTake(ep, is1);
        } return is;
    }
    
    public static class SlotFuel extends Slot {
        public SlotFuel(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return TileEntityCompressor.isItemFuel(is);}
    }
    
    public static class SlotOutput extends Slot {
        public SlotOutput(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return false;}
    }
}