package xyz.sirblobman.mod.gemmary.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractMachine extends BaseContainerBlockEntity implements IMachine {
    public AbstractMachine(BlockEntityType<?> type, BlockPos position, BlockState state) {
        super(type, position, state);
    }
}
