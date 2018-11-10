package com.SirBlobman.gemmary.tileentity;

import com.SirBlobman.gemmary.proxy.network.container.ContainerHydrothermalVein;
import com.SirBlobman.gemmary.recipe.HydrothermalVeinRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public class TileEntityHydrothermalVein extends TileEntity implements ITickable, IInventory {
    private static final int
        WATER_SLOTS  = 1,
        LAVA_SLOTS   = 1,
        INPUT_SLOTS  = 1,
        OUTPUT_SLOTS = 1,
        TOTAL_SLOTS  = (WATER_SLOTS + LAVA_SLOTS + INPUT_SLOTS + OUTPUT_SLOTS),
        
        WATER_SLOT_INDEX  = 0,
        LAVA_SLOT_INDEX   = (WATER_SLOT_INDEX + WATER_SLOTS),
        INPUT_SLOT_INDEX  = (LAVA_SLOT_INDEX + LAVA_SLOTS),
        OUTPUT_SLOT_INDEX = (INPUT_SLOT_INDEX + INPUT_SLOTS);
    
    private int waterRemaining;
    private int lavaRemaining;
    private int progress;
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);
    
    public int getSizeInventory() {return itemStacks.size();}
    public ItemStack getStackInSlot(int slot) {return itemStacks.get(slot);}
    public ItemStack decrStackSize(int slot, int count) {return ItemStackHelper.getAndSplit(itemStacks, slot, count);}
    public ItemStack removeStackFromSlot(int slot) {return ItemStackHelper.getAndRemove(itemStacks, slot);}
    public boolean hasCustomName() {return false;}
    public String getName() {return "container.gemmary.hydrothermal_vein";}
    public void setCustomInventoryName(String name) {}
    public int getInventoryStackLimit() {return 64;}
    public int getCompressTime(ItemStack is) {return 200;}
    public void openInventory(EntityPlayer ep) {}
    public void closeInventory(EntityPlayer ep) {}
    public String getGuiID() {return "gemmary:hydrothermal_vein";}
    public Container createContainer(InventoryPlayer ip, EntityPlayer ep) {return new ContainerHydrothermalVein(ip, this);}
    public int getFieldCount() {return 3;}
    public void clear() {itemStacks.clear();}
    public boolean hasWater() {return waterRemaining > 0;}
    public boolean hasLava() {return lavaRemaining > 0;}
    
    public double getWaterRemainingFraction() {
        double water = waterRemaining;
        double total = 2500.0D;
        double fraction = (water / total);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getLavaRemainingFraction() {
        double lava = lavaRemaining;
        double total = 2500.0D;
        double fraction = (lava / total);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getProgressCompletedFraction() {
        double curr = progress;
        double total = 1000.0D;
        double fraction = (curr / total);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    @Override
    public boolean isEmpty() {
        for(ItemStack is : itemStacks) {
            if(!is.isEmpty()) return false;
        } return true;
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        ItemStack is = itemStacks.get(slot);
        boolean flag = ((!stack.isEmpty()) && (stack.isItemEqual(is)) && (ItemStack.areItemStackTagsEqual(stack, is)));
        itemStacks.set(slot, stack);
        if(stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
        if(slot == INPUT_SLOT_INDEX && !flag) {
            progress = 0;
            markDirty();
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        itemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, itemStacks);
        progress = nbt.getInteger("Progress");
        waterRemaining = nbt.getInteger("WaterRemaining");
        lavaRemaining = nbt.getInteger("LavaRemaining");
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Progress", progress);
        nbt.setInteger("WaterRemaining", waterRemaining);
        nbt.setInteger("LavaRemaining", lavaRemaining);
        ItemStackHelper.saveAllItems(nbt, itemStacks);
        return nbt;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer ep) {
        if(world.getTileEntity(pos) != this) return false;
        else {
            double distance = ep.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            return (distance <= 64.0D);
        }
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack is) {
        if(slot == OUTPUT_SLOT_INDEX) return false;
        else if(slot == WATER_SLOT_INDEX) return isItemWaterSource(is);
        else if(slot == LAVA_SLOT_INDEX) return isItemLavaSource(is);
        else return true;
    }
    
    @Override
    public int getField(int id) {
        switch(id) {
            case 0: return waterRemaining;
            case 1: return lavaRemaining;
            case 2: return progress;
            default: return 0;
        }
    }
    
    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0: waterRemaining = value; break;
            case 1: lavaRemaining = value; break;
            case 2: progress = value; break;
        }
    }
    
    @Override
    public void update() {
        if(waterRemaining < 0) waterRemaining = 0;
        if(lavaRemaining < 0) lavaRemaining = 0;
        
        boolean flag0 = hasWater();
        boolean flag1 = hasLava();
        boolean flag2 = false;
        if(!world.isRemote) {
            ItemStack waterItem = itemStacks.get(WATER_SLOT_INDEX);
            if(waterRemaining < 2500 && !waterItem.isEmpty()) {
                int value = getItemWaterValue(waterItem);
                if(value > 0) {
                    waterRemaining = MathHelper.clamp(waterRemaining + value, 0, 2500);
                    Item item = waterItem.getItem();
                    waterItem.shrink(1);
                    if(waterItem.isEmpty()) {
                        ItemStack is1 = item.getContainerItem(waterItem);
                        itemStacks.set(WATER_SLOT_INDEX, is1);
                    }
                }
            }
            
            ItemStack lavaItem = itemStacks.get(LAVA_SLOT_INDEX);
            if(lavaRemaining < 2500 && !lavaItem.isEmpty()) {
                int value = getItemLavaValue(lavaItem);
                if(value > 0) {
                    lavaRemaining = MathHelper.clamp(lavaRemaining + value, 0, 2500);
                    Item item = lavaItem.getItem();
                    lavaItem.shrink(1);
                    if(lavaItem.isEmpty()) {
                        ItemStack is1 = item.getContainerItem(lavaItem);
                        itemStacks.set(LAVA_SLOT_INDEX, is1);
                    }
                }
            }
            
            if(hasWater() && hasLava()) {
                if(canHydrate()) {
                    ++progress;
                    --waterRemaining;
                    --lavaRemaining;
                    if(progress == 1000) {
                        progress = 0;
                        hydrateItem();
                        flag1 = true;
                    }
                } else progress = 0;
            } else if((!hasWater() || !hasLava()) && progress > 0) {
                progress = MathHelper.clamp(progress - 2, 0, 1000);
            } 
            
            if(flag0 != hasWater()) markDirty();
            if(flag1 != hasLava()) markDirty();
        } if(flag2) markDirty();
    }
    
    public boolean canHydrate() {
        if(itemStacks.get(INPUT_SLOT_INDEX).isEmpty()) return false;
        else {
            ItemStack is = HydrothermalVeinRecipes.instance().getResult(itemStacks.get(INPUT_SLOT_INDEX));
            if(is.isEmpty()) return false;
            else {
                ItemStack is1 = itemStacks.get(OUTPUT_SLOT_INDEX);
                if(is1.isEmpty()) return true;
                else if(!is1.isItemEqual(is)) return false;
                else if(is1.getCount() + is.getCount() <= getInventoryStackLimit() && is1.getCount() + is.getCount() <= is1.getMaxStackSize()) return true;
                else return is1.getCount() + is.getCount() <= is.getMaxStackSize();
            }
        }
    }
    
    public void hydrateItem() {
        if(canHydrate()) {
            ItemStack is0 = itemStacks.get(INPUT_SLOT_INDEX);
            ItemStack is1 = HydrothermalVeinRecipes.instance().getResult(is0);
            ItemStack is2 = itemStacks.get(OUTPUT_SLOT_INDEX);
            
            if(is2.isEmpty()) itemStacks.set(OUTPUT_SLOT_INDEX, is1.copy());
            else if(is2.getItem() == is1.getItem()) is2.grow(is1.getCount());
            is0.shrink(1);
        }
    }
    
    public static int getItemWaterValue(ItemStack is) {
        if(!is.isEmpty()) {
            Item item = is.getItem();
            if(item == Items.WATER_BUCKET) return 1000;
        }
        return 0;
    }
    
    public static int getItemLavaValue(ItemStack is) {
        if(!is.isEmpty()) {
            Item item = is.getItem();
            if(item == Items.LAVA_BUCKET) return 1000;
        }
        return 0;
    }
    
    public static boolean isItemWaterSource(ItemStack is) {
        int value = getItemWaterValue(is);
        return (value > 0);
    }
    
    public static boolean isItemLavaSource(ItemStack is) {
        int value = getItemLavaValue(is);
        return (value > 0);
    }
}