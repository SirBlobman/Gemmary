package xyz.sirblobman.mod.gemmary.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider.Factory;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.data.provider.GemmaryBlockStateProvider;
import xyz.sirblobman.mod.gemmary.data.provider.GemmaryItemModelProvider;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = GemmaryMod.MOD_ID, bus = Bus.MOD)
public final class GemmaryDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();

        Factory<?> factory1 = output -> new GemmaryItemModelProvider(output, helper);
        Factory<?> factory2 = output -> new GemmaryBlockStateProvider(output, helper);

        generator.addProvider(e.includeClient(), factory1);
        generator.addProvider(e.includeClient(), factory2);
    }
}
