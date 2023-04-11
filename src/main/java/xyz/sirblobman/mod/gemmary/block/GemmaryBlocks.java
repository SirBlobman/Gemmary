package xyz.sirblobman.mod.gemmary.block;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.item.GemmaryItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class GemmaryBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GemmaryMod.MOD_ID);

    public static void registerBlocks(IEventBus modEventBus) {
        registerGemBlocks();
        BLOCKS.register(modEventBus);
    }

    private static RegistryObject<Block> registerBlock(Supplier<Block> supplier, String id) {
        RegistryObject<Block> register = BLOCKS.register(id, supplier);
        Supplier<Item> itemSupplier = () -> {
            Block block = register.get();
            Properties properties = new Properties();
            return new BlockItem(block, properties);
        };

        GemmaryItems.ITEMS.register(id, itemSupplier);
        return register;
    }

    public static final RegistryObject<Block> COMPRESSOR_MACHINE =
            registerBlock(CompressorMachineBlock::new, "compressor_machine");

    public static final RegistryObject<Block> HYDROTHERMAL_VEIN_MACHINE =
            registerBlock(HydrothermalVeinMachineBlock::new, "hydrothermal_vein_machine");

    public static void registerGemBlocks() {
        registerBlock(() -> new GemBlock(7.0D), "amethyst_block");
        registerBlock(() -> new GemBlock(9.0D), "ruby_block");
        registerBlock(() -> new GemBlock(9.0D), "sapphire_block");
        registerBlock(() -> new GemBlock(1.0D), "talc_block");
        registerBlock(() -> new GemBlock(6.75D), "tanzanite_block");
        registerBlock(() -> new GemBlock(8.0D), "topaz_block");
        registerBlock(() -> new GemBlock(5.5D), "turquoise_block");
    }
}
