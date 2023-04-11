package xyz.sirblobman.mod.gemmary.item.creative;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab.Builder;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.item.GemmaryItems;

import net.minecraftforge.event.CreativeModeTabEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = GemmaryMod.MOD_ID, bus = Bus.MOD)
public final class GemmaryCreativeTabs {
    @SubscribeEvent
    public static void onRegisterCreativeTabs(Register e) {
        registerMainCreativeTab(e);
        registerPeriodicCreativeTab(e);
    }

    private static void registerCreativeTab(Register e, String id, Supplier<ItemStack> icon,
                                            DisplayItemsGenerator generator, @Nullable Consumer<Builder> extra) {
        ResourceLocation registryName = new ResourceLocation(GemmaryMod.MOD_ID, id);
        Component title = Component.translatable("item_group.gemmary." + id);
        Consumer<Builder> consumer = builder -> {
            builder.title(title);
            builder.icon(icon);
            builder.displayItems(generator);

            if (extra != null) {
                extra.accept(builder);
            }
        };

        e.registerCreativeModeTab(registryName, consumer);
    }

    private static void registerMainCreativeTab(Register e) {
        Supplier<ItemStack> icon = () -> new ItemStack(GemmaryItems.AMETHYST);
        registerCreativeTab(e, "main", icon, GemmaryCreativeTabs::getMainDisplayItems, null);
    }

    private static void registerPeriodicCreativeTab(Register e) {
        Supplier<ItemStack> icon = () -> new ItemStack(GemmaryItems.OXYGEN);
        registerCreativeTab(e, "periodic", icon, GemmaryCreativeTabs::getPeriodicDisplayItems, builder -> {
            builder.hideTitle();
            builder.withSearchBar();
            builder.withSlotColor(0x80_FFA500);
        });
    }

    private static void getMainDisplayItems(ItemDisplayParameters flags, Output output) {
        Collection<RegistryObject<Item>> entries = GemmaryItems.ITEMS.getEntries();
        for (RegistryObject<Item> entry : entries) {
            Item item = entry.get();
            output.accept(item);
        }
    }

    private static void getPeriodicDisplayItems(ItemDisplayParameters flags, Output output) {
        Collection<RegistryObject<Item>> entries = GemmaryItems.ELEMENTS.getEntries();
        for (RegistryObject<Item> entry : entries) {
            Item item = entry.get();
            output.accept(item);
        }
    }
}
