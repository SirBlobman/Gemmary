package xyz.sirblobman.mod.gemmary.data.provider;

import java.util.Collection;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;

import xyz.sirblobman.mod.gemmary.GemmaryMod;
import xyz.sirblobman.mod.gemmary.block.GemmaryBlocks;

import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ExistingFileHelper.ResourceType;
import net.minecraftforge.registries.RegistryObject;

public class GemmaryBlockStateProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public GemmaryBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, GemmaryMod.MOD_ID, helper);
        this.existingFileHelper = helper;
    }

    @Override
    protected void registerStatesAndModels() {
        Collection<RegistryObject<Block>> entries = GemmaryBlocks.BLOCKS.getEntries();
        ResourceType texture = new ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
        for (RegistryObject<Block> entry : entries) {
            ResourceLocation resourceLocation = entry.getId();
            ResourceLocation textureLocation = new ResourceLocation(resourceLocation.getNamespace(),
                    ModelProvider.BLOCK_FOLDER + "/" + resourceLocation.getPath());
            if (!existingFileHelper.exists(textureLocation, texture)) {
                continue;
            }

            Block block = entry.get();
            ModelFile modelFile = cubeAll(block);
            simpleBlockWithItem(block, modelFile);
        }
    }
}
