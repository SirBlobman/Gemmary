package xyz.sirblobman.mod.gemmary.block.entity;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.block.GemmaryBlocks;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class GemmaryBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GemmaryMod.MOD_ID);

    public static void registerTiles(IEventBus eventBus) {
        TILES.register(eventBus);
    }

    @SuppressWarnings("DataFlowIssue")
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerEntity(
            BlockEntitySupplier<T> supplier, String id, Supplier<Block> block) {
        Supplier<BlockEntityType<T>> builderSupplier = () -> {
            Builder<T> builder = Builder.of(supplier, block.get());
            return builder.build(null);
        };

        return TILES.register(id, builderSupplier);
    }

    public static RegistryObject<BlockEntityType<CompressorMachine>> COMPRESSOR =
            registerEntity(CompressorMachine::new, "compressor", GemmaryBlocks.COMPRESSOR_MACHINE);

    public static RegistryObject<BlockEntityType<HydrothermalVeinMachine>> HYDROTHERMAL_VEIN =
            registerEntity(HydrothermalVeinMachine::new, "hydrothermal_vein",
                    GemmaryBlocks.HYDROTHERMAL_VEIN_MACHINE);
}
