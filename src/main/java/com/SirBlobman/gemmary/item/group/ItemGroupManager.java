package com.SirBlobman.gemmary.item.group;

import net.minecraft.item.ItemGroup;

public final class ItemGroupManager {
    public static final ItemGroup
        GEMMARY_ITEMS = new ItemGroupGemmary("gemmaryItems", "amethyst"),
        GEMMARY_ATOMS = new ItemGroupGemmary("gemmaryAtoms", "hydrogen_atom")
    ;
}