package xyz.sirblobman.mod.gemmary.data.provider;

import java.util.Collection;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.item.GemmaryItems;

import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public final class GemmaryItemModelProvider extends ItemModelProvider {
    public GemmaryItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, GemmaryMod.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        Collection<RegistryObject<Item>> items = GemmaryItems.ITEMS.getEntries();
        for (RegistryObject<Item> object : items) {
            ResourceLocation resourceLocation = object.getId();
            ResourceLocation textureLocation = new ResourceLocation(resourceLocation.getNamespace(),
                    "item/" + resourceLocation.getPath());
            if (!existingFileHelper.exists(textureLocation, ModelProvider.TEXTURE)) {
                continue;
            }

            basicItem(resourceLocation);
        }

        Collection<RegistryObject<Item>> elements = GemmaryItems.ELEMENTS.getEntries();
        for (RegistryObject<Item> object : elements) {
            ResourceLocation resourceLocation = object.getId();
            ResourceLocation textureLocation = new ResourceLocation(resourceLocation.getNamespace(),
                    "item/" + resourceLocation.getPath());
            if (!existingFileHelper.exists(textureLocation, ModelProvider.TEXTURE)) {
                continue;
            }

            basicItem(resourceLocation);
        }
    }
}
