package com.SirBlobman.gemmary.tileentity;

import com.SirBlobman.gemmary.recipe.CompressorRecipes;

import net.minecraft.entity.player.EntityPlayer;
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
    /**
     * Slot -> Item Type<br/>
     * 0 -> Input<br/>
     * 1 -> Fuel<br/>
     * 2 -> Output
     */
    private NonNullList<ItemStack> storedItems = NonNullList.withSize(3, ItemStack.EMPTY);
    private int burnTime, currentItemBurnTime, totalCompressTime, compressTime;
    
    public double getFractionCompressTimeComplete() {
        double compressTimeDouble = this.compressTime;
        double totalCompressTimeDouble = this.totalCompressTime;
        double fraction = (compressTimeDouble / totalCompressTimeDouble);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getFractionRemainingFuel() {
        if(this.currentItemBurnTime == 0) this.currentItemBurnTime = 200;
        
        double burnTimeDouble = this.burnTime;
        double currentItemBurnTimeDouble = this.currentItemBurnTime;
        double fraction = (burnTimeDouble / currentItemBurnTimeDouble);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getSecondsRemainingFuel() {
        double burnTimeDouble = this.burnTime;
        return (burnTimeDouble / 20.0D);
    }
    
    private boolean canCompress() {
        ItemStack input = this.storedItems.get(0);
        if(input.isEmpty()) return false;
        
        ItemStack recipeOutput = CompressorRecipes.getInstance().getResult(input.copy());
        if(recipeOutput.isEmpty()) return false;
        
        ItemStack currentOutput = this.storedItems.get(2);
        if(currentOutput.isEmpty()) return true;
        else if(!recipeOutput.isItemEqual(recipeOutput)) return false;
        else {
            int currentOutputCount = currentOutput.getCount();
            int recipeOutputCount = recipeOutput.getCount();
            int combinedCount = (currentOutputCount + recipeOutputCount);
            int inventoryStackLimit = getInventoryStackLimit();
            int currentOutputMaxSize = currentOutput.getMaxStackSize();
            int recipeOutputMaxSize = recipeOutput.getMaxStackSize();
            
            if(combinedCount <= inventoryStackLimit && combinedCount <= currentOutputMaxSize) return true;
            else return (combinedCount <= recipeOutputMaxSize);
        }
    }
    
    private void compressItem() {
        if(canCompress()) {
            ItemStack inputItem = this.storedItems.get(0);
            ItemStack resultItem = CompressorRecipes.getInstance().getResult(inputItem.copy());
            ItemStack outputItem = this.storedItems.get(2);
            
            if(outputItem.isEmpty()) this.storedItems.set(2, resultItem.copy());
            else if(outputItem.isItemEqual(resultItem)) outputItem.grow(resultItem.getCount());
            
            inputItem.shrink(1);
        }
    }
    
    private static int getBurnTime(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack);
    }
    
    public static boolean isFuel(ItemStack stack) {
        int fuelValue = getBurnTime(stack);
        return (fuelValue > 0);
    }
    
    public boolean isBurning() {
        return (this.burnTime > 0);
    }
    
    @Override
    public void update() {
        boolean flag0 = isBurning();
        boolean flag1 = false;
        if(isBurning()) --this.burnTime;
        if(!world.isRemote) {
            ItemStack inputItem = this.storedItems.get(0);
            ItemStack fuelItem = this.storedItems.get(1);
            if(isBurning() || !fuelItem.isEmpty() && !inputItem.isEmpty()) {
                if(!isBurning() && canCompress()) {
                    this.burnTime = getBurnTime(fuelItem);
                    this.currentItemBurnTime = this.burnTime;
                    if(isBurning()) {
                        flag1 = true;
                        if(!fuelItem.isEmpty()) {
                            Item item = fuelItem.getItem();
                            fuelItem.shrink(1);
                            if(fuelItem.isEmpty()) {
                                ItemStack containerItem = item.getContainerItem(fuelItem);
                                this.storedItems.set(1, containerItem);
                            }
                        }
                    }
                }
                
                if(isBurning() && canCompress()) {
                    ++this.compressTime;
                    if(this.compressTime == this.totalCompressTime) {
                        this.compressTime = 0;
                        this.totalCompressTime = 200;
                        compressItem();
                        flag1 = true;
                    }
                } else this.compressTime = 0;
            } else if(!isBurning() && this.compressTime > 0) this.compressTime = MathHelper.clamp((compressTime - 2), 0, this.totalCompressTime);
            
            if(flag0 != isBurning()) flag1 = true;
        }
        
        if(flag1) markDirty();
    }
    
    @Override
    public String getName() {
        return "container.gemmary.compressor";
    }
    
    @Override
    public boolean hasCustomName() {
        return false;
    }
    
    @Override
    public int getSizeInventory() {
        return this.storedItems.size();
    }
    
    @Override
    public boolean isEmpty() {
        for(ItemStack item : this.storedItems) {
            if(!item.isEmpty()) return false;
        }
        
        return true;
    }
    
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.storedItems.get(index);
    }
    
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.storedItems, index, count);
    }
    
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.storedItems, index);
    }
    
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack indexItem = getStackInSlot(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(indexItem) && ItemStack.areItemStackTagsEqual(stack, indexItem);
        this.storedItems.set(index, stack);
        
        if(stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
        if(index == 0 && !flag) {
            this.totalCompressTime = 200;
            this.compressTime = 0;
            markDirty();
        }
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if(this.world.getTileEntity(this.pos) != this) return false;
        
        double x = (this.pos.getX() + 0.5D);
        double y = (this.pos.getY() + 0.5D);
        double z = (this.pos.getZ() + 0.5D);
        
        double distanceSq = player.getDistance(x, y, z);
        return (distanceSq <= 64.0D);
    }
    
    @Override
    public void openInventory(EntityPlayer player) {}
    
    @Override
    public void closeInventory(EntityPlayer player) {}
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 2) return false;
        if(index != 1) return true;
        
        return isFuel(stack);
    }
    
    @Override
    public int getField(int id) {
        switch(id) {
        case 0: return this.burnTime;
        case 1: return this.currentItemBurnTime;
        case 2: return this.compressTime;
        case 3: return this.totalCompressTime;
        default: return 0;
        }
    }
    
    @Override
    public void setField(int id, int value) {
        switch(id) {
        case 0: this.burnTime = value; break;
        case 1: this.currentItemBurnTime = value; break;
        case 2: this.compressTime = value; break;
        case 3: this.totalCompressTime = value; break;
        }
    }
    
    @Override
    public int getFieldCount() {
        return 4;
    }
    
    @Override
    public void clear() {
        this.storedItems.clear();
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        this.storedItems = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.storedItems);
        
        this.burnTime = compound.getInteger("Burn Time");
        this.compressTime = compound.getInteger("Compress Time");
        this.totalCompressTime = compound.getInteger("Total Compress Time");
        this.currentItemBurnTime = getBurnTime(this.storedItems.get(1));
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        compound.setInteger("Burn Time", this.burnTime);
        compound.setInteger("Compress Time", this.compressTime);
        compound.setInteger("Total Compress Time", this.totalCompressTime);
        ItemStackHelper.saveAllItems(compound, this.storedItems);
        return compound;
    }
}