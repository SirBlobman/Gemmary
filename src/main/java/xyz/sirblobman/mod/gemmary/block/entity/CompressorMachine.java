package xyz.sirblobman.mod.gemmary.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CompressorMachine extends AbstractMachine {
    public CompressorMachine(BlockPos blockPos, BlockState state) {
        super(GemmaryBlockEntities.COMPRESSOR.get(), blockPos, state);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, @NotNull ItemStack stack, @Nullable Direction face) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, @NotNull ItemStack stack, @NotNull Direction face) {
        return false;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return null;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int slot, @NotNull Inventory stack) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return null;
    }

    @Override
    public @NotNull ItemStack removeItem(int p_18942_, int p_18943_) {
        return null;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        return null;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {

    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return false;
    }

    @Override
    public void clearContent() {

    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {

    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void fillStackedContents(@NotNull StackedContents contents) {

    }
}
