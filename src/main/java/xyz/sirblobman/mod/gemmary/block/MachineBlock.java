package xyz.sirblobman.mod.gemmary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

import xyz.sirblobman.mod.gemmary.block.entity.AbstractMachine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MachineBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public MachineBlock() {
        super(Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).noOcclusion());
        registerDefaultState();
    }

    private void registerDefaultState() {
        StateDefinition<Block, BlockState> stateDefinition = getStateDefinition();
        BlockState defaultState = stateDefinition.any();
        defaultState.setValue(FACING, Direction.NORTH);
        registerDefaultState(defaultState);
    }

    protected abstract void openContainer(Level world, BlockPos position, Player player);

    @Override
    @SuppressWarnings("deprecation") // Deprecated by Mojang, but still used by some blocks.
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos position,
                                          @NotNull Player player, @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult result) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        openContainer(world, position, player);
        return InteractionResult.CONSUME;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        Direction horizontalDirection = context.getHorizontalDirection();
        Direction opposite = horizontalDirection.getOpposite();
        BlockState defaultState = defaultBlockState();
        return defaultState.setValue(FACING, opposite);
    }

    @Override
    public void setPlacedBy(@NotNull Level world, @NotNull BlockPos position, @NotNull BlockState state,
                            @Nullable LivingEntity entity, @NotNull ItemStack stack) {
        if (!stack.hasCustomHoverName()) {
            return;
        }

        BlockEntity blockEntity = world.getBlockEntity(position);
        if (blockEntity instanceof AbstractMachine machine) {
            Component hoverName = stack.getHoverName();
            machine.setCustomName(hoverName);
        }
    }

    @Override
    @SuppressWarnings("deprecation") // Deprecated by Mojang, but still used by some blocks.
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation") // Deprecated by Mojang, but still used by some blocks.
    public int getAnalogOutputSignal(@NotNull BlockState state, Level world, @NotNull BlockPos position) {
        BlockEntity entity = world.getBlockEntity(position);
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(entity);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation") // Deprecated by Mojang, but still used by some blocks.
    public @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation) {
        Direction currentFacing = state.getValue(FACING);
        Direction newFacing = rotation.rotate(currentFacing);
        return state.setValue(FACING, newFacing);
    }

    @Override
    @SuppressWarnings("deprecation") // Deprecated by Mojang, but still used by some blocks.
    public @NotNull BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror) {
        Direction currentFacing = state.getValue(FACING);
        Rotation rotation = mirror.getRotation(currentFacing);
        return state.rotate(rotation);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
