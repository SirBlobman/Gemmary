package com.SirBlobman.gemmary.forge;

import com.SirBlobman.gemmary.block.GBlocks;
import com.SirBlobman.gemmary.item.GItems;
import com.SirBlobman.gemmary.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class GOreDict {
    public static void registerBlocks() {
        reg(GBlocks.AMETHYST_BLOCK,     "blockAmethyst");
        reg(GBlocks.RUBY_BLOCK,         "blockRuby");
        reg(GBlocks.SAPPHIRE_BLOCK,     "blockSapphire");
        reg(GBlocks.TALC_BLOCK,         "blockTalc");
        reg(GBlocks.TANZANITE_BLOCK,    "blockTanzanite");
        reg(GBlocks.TOPAZ_BLOCK,        "blockTopaz");
        reg(GBlocks.TURQUOISE_BLOCK,    "blockTurquoise");
    }

    public static void registerItems() {
        reg(GItems.AMETHYST,            "gemAmethyst");
        reg(GItems.RUBY,                "gemRuby");
        reg(GItems.SAPPHIRE,            "gemSapphire");
        reg(GItems.TALC,                "gemTalc");
        reg(GItems.TANZANITE,           "gemTanzanite");
        reg(GItems.TOPAZ,               "gemTopaz");
        reg(GItems.TURQUOISE,           "gemTurquoise");
        reg(GItems.DIAMOND_PART,        "nuggetDiamond");
        reg(GItems.EMERALD_PART,        "nuggetEmerald");
    }

    private static void reg(Object o, String oreName) {
        if(o instanceof Block) {
            Block block = (Block) o;
            OreDictionary.registerOre(oreName, block);
        } else if(o instanceof Item) {
            Item item = (Item) o;
            OreDictionary.registerOre(oreName, item);
        } else if(o instanceof ItemStack) {
            ItemStack stack = (ItemStack) o;
            OreDictionary.registerOre(oreName, stack);
        } else {
            String error = "Invalid OreDictionary type '" + o.getClass().getSimpleName() + "'. The object you are registering must be a block or an item!";
            Util.print(error);
        }
    }
}