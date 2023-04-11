package xyz.sirblobman.mod.gemmary;

import xyz.sirblobman.mod.gemmary.block.GemmaryBlocks;
import xyz.sirblobman.mod.gemmary.block.entity.GemmaryBlockEntities;
import xyz.sirblobman.mod.gemmary.item.GemmaryItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GemmaryMod.MOD_ID)
public final class GemmaryMod {
    public static final String MOD_ID = "gemmary";

    public GemmaryMod() {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        IEventBus modEventBus = context.getModEventBus();

        GemmaryBlocks.registerBlocks(modEventBus);
        GemmaryBlockEntities.registerTiles(modEventBus);
        GemmaryItems.registerItems(modEventBus);
    }
}
