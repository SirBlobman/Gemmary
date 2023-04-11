package xyz.sirblobman.mod.gemmary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import xyz.sirblobman.mod.gemmary.block.entity.CompressorMachine;

import org.jetbrains.annotations.NotNull;

public final class CompressorMachineBlock extends BaseEntityBlock {
    public CompressorMachineBlock() {
        super(Properties.of(Material.HEAVY_METAL, MaterialColor.METAL));
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState state) {
        return new CompressorMachine(blockPos, state);
    }
}
