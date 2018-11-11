package com.SirBlobman.gemmary.tileentity.container;

import com.SirBlobman.gemmary.tileentity.TileEntityHydrothermalVein;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHydrothermalVein extends Container {
    private static final int
    X_SPACING = 18, Y_SPACING = 18,
    
    HOTBAR_X = 8, HOTBAR_Y = 142,
    
    PLAYER_X = 8, PLAYER_Y = 84,
    
    WATER_X = 8, WATER_Y = 57,
    
    LAVA_X = 152, LAVA_Y = 57,
    
    INPUT_X = 49, INPUT_Y = 34,
    
    OUTPUT_X = 109, OUTPUT_Y = 35
    ;
    
    private final TileEntityHydrothermalVein tileEntity;
    private int[] cachedFields;
    public ContainerHydrothermalVein(InventoryPlayer inv, TileEntityHydrothermalVein tileEntity) {
        this.tileEntity = tileEntity;
        
        addSlotToContainer(new Slot(this.tileEntity, 0, INPUT_X, INPUT_Y));
        addSlotToContainer(new SlotOutput(this.tileEntity, 1, OUTPUT_X, OUTPUT_Y));
        addSlotToContainer(new SlotWater(this.tileEntity, 2, WATER_X, WATER_Y));
        addSlotToContainer(new SlotLava(this.tileEntity, 3, LAVA_X, LAVA_Y));
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                int slotID = 9 + y * 9 + x;
                int slotX = PLAYER_X + x * X_SPACING;
                int slotY = PLAYER_Y + y * Y_SPACING;
                Slot slot = new Slot(inv, slotID, slotX, slotY);
                addSlotToContainer(slot);
            }
        }
        
        for(int x = 0; x < 9; x++) {
            int slotID = x;
            int slotX = HOTBAR_X + X_SPACING * x;
            int slotY = HOTBAR_Y;
            Slot slot = new Slot(inv, slotID, slotX, slotY);
            addSlotToContainer(slot);
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileEntity.isUsableByPlayer(player);
    }
    
    @Override
    public void updateProgressBar(int id, int data) {
        this.tileEntity.setField(id, data);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        return ItemStack.EMPTY;
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
            int fieldCount = this.tileEntity.getFieldCount();
            for(int f = 0; f < fieldCount; ++f) {
                if(fieldChanged[f]) {
                    int field = this.cachedFields[f];
                    listener.sendWindowProperty(this, f, field);
                }
            }
        }
    }
    
    public static class SlotWater extends Slot {
        public SlotWater(IInventory inv, int index, int x, int y) {
            super(inv, index, x, y);
        }
        
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntityHydrothermalVein.isWaterSource(stack);
        }
    }
    
    public static class SlotLava extends Slot {
        public SlotLava(IInventory inv, int index, int x, int y) {
            super(inv, index, x, y);
        }
        
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntityHydrothermalVein.isLavaSource(stack);
        }
    }
    
    public static class SlotOutput extends Slot {
        public SlotOutput(IInventory inv, int index, int x, int y) {
            super(inv, index, x, y);
        }
        
        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }
    }
}