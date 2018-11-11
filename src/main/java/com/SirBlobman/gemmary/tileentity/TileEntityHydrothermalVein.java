package com.SirBlobman.gemmary.tileentity;

import com.SirBlobman.gemmary.recipe.HydrothermalVeinRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public class TileEntityHydrothermalVein extends TileEntity implements IInventory, ITickable {
    /**
     * Slot -> Item Type<br/>
     * 0 -> Input<br/>
     * 1 -> Output<br/>
     * 
     * 2 -> Water<br/>
     * 3 -> Lava
     * 
     */
    private NonNullList<ItemStack> storedItems = NonNullList.withSize(4, ItemStack.EMPTY);
    private int lavaRemaining, waterRemaining, progress;
    
    public double getFractionWaterRemaining() {
        double water = this.waterRemaining;
        double fraction = (water / 2500.0D);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getFractionLavaRemaining() {
        double lava = this.lavaRemaining;
        double fraction = (lava / 2500.0D);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public double getFractionProgressCompleted() {
        double currentProgress = this.progress;
        double fraction = (currentProgress / 1000.0D);
        return MathHelper.clamp(fraction, 0.0D, 1.0D);
    }
    
    public boolean canHydrate() {
        if(this.storedItems.get(0).isEmpty()) return false;
        
        ItemStack resultItem = HydrothermalVeinRecipes.getInstance().getResult(this.storedItems.get(0));
        if(resultItem.isEmpty()) return false;
        
        ItemStack outputItem = this.storedItems.get(1);
        if(outputItem.isEmpty()) return true;
        else if(!outputItem.isItemEqual(resultItem)) return false;
        else if(outputItem.getCount() + resultItem.getCount() <= getInventoryStackLimit() && outputItem.getCount() + resultItem.getCount() <= outputItem.getMaxStackSize()) return true;
        else return (outputItem.getCount() + resultItem.getCount() <= resultItem.getMaxStackSize());
    }
    
    public void hydrateItem() {
        if(canHydrate()) {
            ItemStack inputItem = this.storedItems.get(0);
            ItemStack resultItem = HydrothermalVeinRecipes.getInstance().getResult(inputItem);
            ItemStack outputItem = this.storedItems.get(1);
            
            if(outputItem.isEmpty()) this.storedItems.set(1, resultItem.copy());
            else if(outputItem.isItemEqual(resultItem)) outputItem.grow(resultItem.getCount());
            inputItem.shrink(1);
        }
    }
    
    public boolean hasWater() {
        return (this.waterRemaining > 0);
    }
    
    public boolean hasLava() {
        return (this.lavaRemaining > 0);
    }

    /**
     * @param stack The {@link ItemStack} to check
     * @return The value of {@code stack} in millibuckets
     */
    public static int getWaterValue(ItemStack stack) {
        if(!stack.isEmpty()) {
            Item item = stack.getItem();
            if(item == Items.WATER_BUCKET) return 1000;
        }
        
        return 0;
    }
    /**
     * @param stack The {@link ItemStack} to check
     * @return The value of {@code stack} in millibuckets
     */
    public static int getLavaValue(ItemStack stack) {
        if(!stack.isEmpty()) {
            Item item = stack.getItem();
            if(item == Items.LAVA_BUCKET) return 1000;
        }
        
        return 0;
    }
    
    public static boolean isWaterSource(ItemStack stack) {
        int value = getWaterValue(stack);
        return (value > 0);
    }
    
    public static boolean isLavaSource(ItemStack stack) {
        int value = getLavaValue(stack);
        return (value > 0);
    }
    
    @Override
    public int getSizeInventory() {
        return this.storedItems.size();
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
    public boolean hasCustomName() {
        return false;
    }
    
    @Override
    public String getName() {
        return "container.gemmary.hydrothermal_vein";
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public void openInventory(EntityPlayer player) {
        
    }
    
    @Override
    public void closeInventory(EntityPlayer player) {
        
    }
    
    @Override
    public int getFieldCount() {
        return 3;
    }
    
    @Override
    public void clear() {
        this.storedItems.clear();
    }
    
    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.storedItems) {
            if(!stack.isEmpty()) return false;
        }
        
        return true;
    }
    
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemInSlot = this.storedItems.get(index);
        
        this.storedItems.set(index, stack);
        if(stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
        
        boolean flag = (!stack.isEmpty() && stack.isItemEqual(itemInSlot) && ItemStack.areItemStackTagsEqual(stack, itemInSlot));
        if(index == 0 && !flag) {
            this.progress = 0;
            markDirty();
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        this.storedItems = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.storedItems);
        
        this.progress = compound.getInteger("Progress");
        this.waterRemaining = compound.getInteger("Water");
        this.lavaRemaining = compound.getInteger("Lava");
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        compound.setInteger("Progress", this.progress);
        compound.setInteger("Water", this.waterRemaining);
        compound.setInteger("Lava", this.lavaRemaining);
        ItemStackHelper.saveAllItems(compound, this.storedItems);
        return compound;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        TileEntity tileEntity = this.world.getTileEntity(this.pos);
        if(tileEntity != this) return false;

        double x = this.pos.getX() + 0.5D;
        double y = this.pos.getY() + 0.5D;
        double z = this.pos.getZ() + 0.5D;
        double distanceSq = player.getDistance(x, y, z);
        return (distanceSq <= 64.0D);
    }
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 1) return false; //Output Slot
        else if(index == 2) return isWaterSource(stack); //Water Slot
        else if(index == 3) return isLavaSource(stack);  //Lava Slot
        else return true; //All other slots
    }
    
    @Override
    public int getField(int id) {
        switch(id) {
        case 0: return this.waterRemaining;
        case 1: return this.lavaRemaining;
        case 2: return this.progress;
        default: return 0;
        }
    }
    
    @Override
    public void setField(int id, int value) {
        switch(id) {
        case 0: this.waterRemaining = value; break;
        case 1: this.lavaRemaining = value; break;
        case 2: this.progress = value; break;
        }
    }
    
    @Override
    public void update() {
        if(this.waterRemaining < 0) this.waterRemaining = 0;
        if(this.lavaRemaining < 0) this.lavaRemaining = 0;
        
        boolean flag0 = hasWater();
        boolean flag1 = hasLava();
        boolean flag2 = false;
        
        if(!world.isRemote) {
            ItemStack waterItem = this.storedItems.get(2);
            if(this.waterRemaining < 2500 && !waterItem.isEmpty()) {
                int waterValue = getWaterValue(waterItem);
                if(waterValue > 0) {
                    this.waterRemaining = MathHelper.clamp(this.waterRemaining + waterValue, 0, 2500);
                    
                    Item item = waterItem.getItem();
                    waterItem.shrink(1);
                    if(waterItem.isEmpty()) {
                        ItemStack containerItem = item.getContainerItem(waterItem);
                        this.storedItems.set(2, containerItem);
                    }
                }
            }
            
            ItemStack lavaItem = this.storedItems.get(3);
            if(this.lavaRemaining < 2500 && !lavaItem.isEmpty()) {
                int lavaValue = getLavaValue(lavaItem);
                if(lavaValue > 0) {
                    this.lavaRemaining = MathHelper.clamp(this.lavaRemaining + lavaValue, 0, 2500);
                    
                    Item item = lavaItem.getItem();
                    lavaItem.shrink(1);
                    if(lavaItem.isEmpty()) {
                        ItemStack containerItem = item.getContainerItem(lavaItem);
                        this.storedItems.set(3, containerItem);
                    }
                }
            }
            
            if(hasWater() && hasLava()) {
                this.progress++;
                this.waterRemaining--;
                this.lavaRemaining--;
                
                if(this.progress >= 1000) {
                    this.progress = 0;
                    hydrateItem();
                    flag1 = true;
                }
            } else if((!hasWater() || !hasLava()) && this.progress > 0) this.progress = MathHelper.clamp(this.progress - 2, 0, 1000);
        
            if(flag0 != hasWater()) markDirty();
            if(flag1 != hasLava()) markDirty();
        }
        
        if(flag2) markDirty();
    }
}