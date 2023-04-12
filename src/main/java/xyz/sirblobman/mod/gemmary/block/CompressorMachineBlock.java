package xyz.sirblobman.mod.gemmary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import xyz.sirblobman.mod.gemmary.block.entity.CompressorMachine;

import org.jetbrains.annotations.NotNull;

public final class CompressorMachineBlock extends MachineBlock {
    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState state) {
        return new CompressorMachine(blockPos, state);
    }

    @Override
    protected void openContainer(Level world, BlockPos position, Player player) {
        // TODO
    }
}
