package com.SirBlobman.gemmary.tileentity;

import com.SirBlobman.gemmary.proxy.network.container.ContainerCompressor;
import com.SirBlobman.gemmary.recipe.CompressorRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public class TileEntityCompressor extends TileEntity implements IInventory, ITickable {
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    private int burnTime;
    private int currentItemBurnTime;
    private int totalCompressTime;
    private int compressTime;
    
    public double getCompressTimeCompleteFraction() {
        double time = compressTime;
        double total = totalCompressTime;
        double fraction = (time / total);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getFuelRemainingFraction() {
        if(currentItemBurnTime == 0) return 0.0D;
        else {
            double burn = burnTime;
            double curr = currentItemBurnTime;
            double fraction = ((curr - burn) / curr);
            return MathHelper.clamp(fraction, 0.0D, 1.0D);
        }
    }
    
    public double getFuelRemainingSeconds() {
        double seconds = (burnTime / 20.0D);
        return seconds;
    }
    
    public int getSizeInventory() {return itemStacks.size();}
    public ItemStack getStackInSlot(int slot) {return itemStacks.get(slot);}
    public ItemStack decrStackSize(int slot, int count) {return ItemStackHelper.getAndSplit(itemStacks, slot, count);}
    public ItemStack removeStackFromSlot(int slot) {return ItemStackHelper.getAndRemove(itemStacks, slot);}
    public boolean hasCustomName() {return false;}
    public String getName() {return "container.gemmary.compressor";}
    public void setCustomInventoryName(String name) {}
    public int getInventoryStackLimit() {return 64;}
    public boolean isBurning() {return burnTime > 0;}
    public static boolean isBurning(IInventory ii) {return ii.getField(0) > 0;}
    public int getCompressTime(ItemStack is) {return 200;}
    public static int getItemBurnTime(ItemStack is) {return TileEntityFurnace.getItemBurnTime(is);}
    public static boolean isItemFuel(ItemStack is) {return getItemBurnTime(is) > 0;}
    public void openInventory(EntityPlayer ep) {}
    public void closeInventory(EntityPlayer ep) {}
    public String getGuiID() {return "gemmary:compressor";}
    public Container createContainer(InventoryPlayer ip, EntityPlayer ep) {return new ContainerCompressor(ip, this);}
    public int getFieldCount() {return 4;}
    public void clear() {itemStacks.clear();}
    
    @Override
    public boolean isEmpty() {
        for(ItemStack is : itemStacks) {
            if(!is.isEmpty()) return false;
        } return true;
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        ItemStack is = itemStacks.get(slot);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(is) && ItemStack.areItemStackTagsEqual(stack, is);
        itemStacks.set(slot, stack);
        
        if(stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
        if(slot == 0 && !flag) {
            totalCompressTime = getCompressTime(stack);
            compressTime = 0;
            markDirty();
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        itemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, itemStacks);
        burnTime = nbt.getInteger("BurnTime");
        compressTime = nbt.getInteger("CompressTime");
        totalCompressTime = nbt.getInteger("TotalCompressTime");
        currentItemBurnTime = getItemBurnTime(itemStacks.get(1));
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("BurnTime", (short) burnTime);
        nbt.setInteger("CompressTime", (short) compressTime);
        nbt.setInteger("TotalCompressTime", (short) totalCompressTime);
        ItemStackHelper.saveAllItems(nbt, itemStacks);
        return nbt;
    }
    
    @Override
    public void update() {
        boolean flag = isBurning();
        boolean flag1 = false;
        if(isBurning()) --burnTime;
        if(!world.isRemote) {
            ItemStack is = itemStacks.get(1);
            if(isBurning() || !is.isEmpty() && !itemStacks.get(0).isEmpty()) {
                if(!isBurning() && canCompress()) {
                    burnTime = getItemBurnTime(is);
                    currentItemBurnTime = burnTime;
                    if(isBurning()) {
                        flag1 = true;
                        if(!is.isEmpty()) {
                            Item item = is.getItem();
                            is.shrink(1);
                            if(is.isEmpty()) {
                                ItemStack is1 = item.getContainerItem(is);
                                itemStacks.set(1, is1);
                            }
                        }
                    }
                }
                
                if(isBurning() && canCompress()) {
                    ++compressTime;
                    if(compressTime == totalCompressTime) {
                        compressTime = 0;
                        totalCompressTime = getCompressTime(itemStacks.get(0));
                        compressItem();
                        flag1 = true;
                    }
                } else compressTime = 0;
            } else if(!isBurning() && compressTime > 0) {
                compressTime = MathHelper.clamp(compressTime - 2, 0, totalCompressTime);
            } if(flag != isBurning()) flag1 = true;
        } if(flag1) markDirty();
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
        if(slot == 2) return false;
        else if(slot != 1) return true;
        else return isItemFuel(is);
    }
    
    @Override
    public int getField(int id) {
        switch(id) {
            case 0: return burnTime;
            case 1: return currentItemBurnTime;
            case 2: return compressTime;
            case 3: return totalCompressTime;
            default: return 0;
        }
    }
    
    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0: burnTime = value; break;
            case 1: currentItemBurnTime = value; break;
            case 2: compressTime = value; break;
            case 3: totalCompressTime = value; break;
        }
    }
    
    public boolean canCompress() { 
        if(itemStacks.get(0).isEmpty()) return false;
        else {
            ItemStack is = CompressorRecipes.instance().getResult(itemStacks.get(0));
            if(is.isEmpty()) return false;
            else {
                ItemStack is1 = itemStacks.get(2);
                if(is1.isEmpty()) return true;
                else if(!is1.isItemEqual(is)) return false;
                else if(is1.getCount() + is.getCount() <= getInventoryStackLimit() && is1.getCount() + is.getCount() <= is1.getMaxStackSize()) return true;
                else return is1.getCount() + is.getCount() <= is.getMaxStackSize();
            }
        }
    }
    
    public void compressItem() {
        if(canCompress()) {
            ItemStack is0 = itemStacks.get(0);
            ItemStack is1 = CompressorRecipes.instance().getResult(is0);
            ItemStack is2 = itemStacks.get(2);
            
            if(is2.isEmpty()) itemStacks.set(2, is1.copy());
            else if(is2.getItem() == is1.getItem()) is2.grow(is1.getCount());
            is0.shrink(1);
        }
    }
}