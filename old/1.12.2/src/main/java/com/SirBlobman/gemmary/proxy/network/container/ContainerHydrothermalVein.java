package com.SirBlobman.gemmary.proxy.network.container;

import com.SirBlobman.gemmary.recipe.HydrothermalVeinRecipes;
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
        X_SPACING = 18,  Y_SPACING = 18,
        HOTBAR_X  = 8,   HOTBAR_Y  = 142,
        PLAYER_X  = 8,   PLAYER_Y  = 84,
        WATER_X   = 8,   WATER_Y   = 57,
        LAVA_X    = 152, LAVA_Y    = 57, 
        INPUT_X   = 49,  INPUT_Y   = 34,
        OUTPUT_X  = 109, OUTPUT_Y  = 35;
    
    private static final int
        WATER_SLOT = 0,
        LAVA_SLOT = 1,
        INPUT_SLOT = 2,
        OUTPUT_SLOT = 3;
    
    private final TileEntityHydrothermalVein tileEntity;
    private int[] cachedFields;
    public ContainerHydrothermalVein(InventoryPlayer ip, TileEntityHydrothermalVein tileEntity) {
        this.tileEntity = tileEntity;
        addSlotToContainer(new SlotWater(tileEntity, 0, WATER_X, WATER_Y));
        addSlotToContainer(new SlotLava(tileEntity, 1, LAVA_X, LAVA_Y));
        addSlotToContainer(new Slot(tileEntity, 2, INPUT_X, INPUT_Y));
        addSlotToContainer(new SlotOutput(tileEntity, 3, OUTPUT_X, OUTPUT_Y));
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                int id = 9 + y * 9 + x;
                int xp = PLAYER_X + x * X_SPACING;
                int yp = PLAYER_Y + y * Y_SPACING;
                Slot slot = new Slot(ip, id, xp, yp);
                addSlotToContainer(slot);
            }
        }
        
        for(int x = 0; x < 9; x++) {
            Slot slot = new Slot(ip, x, HOTBAR_X + X_SPACING * x, HOTBAR_Y);
            addSlotToContainer(slot);
        }
    }
    
    public boolean canInteractWith(EntityPlayer ep) {return tileEntity.isUsableByPlayer(ep);}
    public void updateProgressBar(int id, int data) {tileEntity.setField(id, data);}
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        boolean allChanged = false;
        boolean[] fieldChanged = new boolean[tileEntity.getFieldCount()];
        if(cachedFields == null) {
            cachedFields = new int[tileEntity.getFieldCount()];
            allChanged = true;
        }
        
        for(int i = 0; i < cachedFields.length; ++i) {
            if(allChanged || cachedFields[i] != tileEntity.getField(i)) {
                cachedFields[i] = tileEntity.getField(i);
                fieldChanged[i] = true;
            }
        }
        
        for(int i = 0; i < listeners.size(); ++i) {
            IContainerListener icl = listeners.get(i);
            for(int field = 0; field < tileEntity.getFieldCount(); ++field) {
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
            if(index == OUTPUT_SLOT) {
                if(!mergeItemStack(is1, OUTPUT_SLOT + 1, OUTPUT_SLOT + 37, true)) return ItemStack.EMPTY;
                slot.onSlotChange(is1, is);
            } else if(index > OUTPUT_SLOT) {
                if(!HydrothermalVeinRecipes.instance().getResult(is1).isEmpty()) {
                    if(!mergeItemStack(is1, INPUT_SLOT, INPUT_SLOT + 1, false)) return ItemStack.EMPTY;
                } else if(TileEntityHydrothermalVein.isItemLavaSource(is1)) {
                    if(!mergeItemStack(is1, LAVA_SLOT, LAVA_SLOT + 1, false)) return ItemStack.EMPTY;
                } else if(TileEntityHydrothermalVein.isItemWaterSource(is1)) {
                    if(!mergeItemStack(is1, WATER_SLOT, WATER_SLOT + 1, false)) return ItemStack.EMPTY;
                } else if(index >= OUTPUT_SLOT + 1 && index < OUTPUT_SLOT + 28) {
                    if(!mergeItemStack(is1, OUTPUT_SLOT + 28, OUTPUT_SLOT + 37, false)) return ItemStack.EMPTY;
                } else if(index >= OUTPUT_SLOT + 28 && index < OUTPUT_SLOT + 37 && !mergeItemStack(is1, OUTPUT_SLOT + 1, OUTPUT_SLOT + 28, false)) return ItemStack.EMPTY;
            } else if(!mergeItemStack(is1, OUTPUT_SLOT + 1, OUTPUT_SLOT + 37, false)) return ItemStack.EMPTY;
            
            if(is1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();
            
            if(is1.getCount() == is.getCount()) return ItemStack.EMPTY;
            slot.onTake(ep, is1);
        } return is;
    }
    
    public static class SlotWater extends Slot {
        public SlotWater(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return TileEntityHydrothermalVein.isItemWaterSource(is);}
    }
    
    public static class SlotLava extends Slot {
        public SlotLava(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return TileEntityHydrothermalVein.isItemLavaSource(is);}
    }
    
    public static class SlotOutput extends Slot {
        public SlotOutput(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return false;}
    }
}