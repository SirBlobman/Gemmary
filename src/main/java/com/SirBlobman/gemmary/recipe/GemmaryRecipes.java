package com.SirBlobman.gemmary.recipe;

import com.SirBlobman.gemmary.constant.ModInfo;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class GemmaryRecipes {
    private static final ResourceLocation CATEGORY_GEMS_TO_BLOCKS = new ResourceLocation(ModInfo.MODID, "gems_to_blocks");
    private static final ResourceLocation CATEGORY_BLOCKS_TO_GEMS = new ResourceLocation(ModInfo.MODID, "blocks_to_gems");
    
    public static void registerAllRecipes(IForgeRegistry<IRecipe> ifr) {
        registerGemRecipe(ifr, "blockAmethyst", "gemAmethyst");
        registerGemRecipe(ifr, "blockRuby", "gemRuby");
        registerGemRecipe(ifr, "blockSapphire", "gemSapphire");
        registerGemRecipe(ifr, "blockTalc", "gemTalc");
        registerGemRecipe(ifr, "blockTanzanite", "gemTanzanite");
        registerGemRecipe(ifr, "blockTopaz", "gemTopaz");
        registerGemRecipe(ifr, "blockTurquoise", "gemTurquouse");
    }
    
    public static void registerGemRecipe(IForgeRegistry<IRecipe> ifr, String blockName, String gemName) {
        if(OreDictionary.doesOreNameExist(blockName) && OreDictionary.doesOreNameExist(gemName)) {
            OreDictionary.getOres(blockName).forEach(output1 -> {
                Object[] recipeShape = {"GGG", "GGG", "GGG", 'G', gemName};
                ShapedOreRecipe recipe = new ShapedOreRecipe(CATEGORY_GEMS_TO_BLOCKS, output1, recipeShape);
                ResourceLocation rl = getNewRecipeLocation();
                recipe.setRegistryName(rl);
                ifr.register(recipe);
            });
            
            OreDictionary.getOres(gemName).forEach(output2 -> {
                ItemStack copy2 = output2.copy();
                copy2.setCount(9);
                ShapelessOreRecipe recipe = new ShapelessOreRecipe(CATEGORY_BLOCKS_TO_GEMS, copy2, blockName);
                ResourceLocation rl = getNewRecipeLocation();
                recipe.setRegistryName(rl);
                ifr.register(recipe);
            });
        }
    }
    
    private static int recipeCount = 0;
    private static ResourceLocation getNewRecipeLocation() {
        recipeCount++;
        String path = "recipe" + recipeCount;
        ResourceLocation rl = new ResourceLocation(ModInfo.MODID, path);
        return rl;
    }
}
