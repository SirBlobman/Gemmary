package xyz.sirblobman.mod.gemmary.block;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GemBlock extends Block {
    private final double hardness;

    public GemBlock(double hardness) {
        super(Properties.copy(Blocks.DIAMOND_BLOCK).strength((float) hardness));
        this.hardness = hardness;
    }

    public double getMohsHardness() {
        return this.hardness;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter world, @NotNull List<Component> lore,
                                @NotNull TooltipFlag flags) {
        double hardness = getMohsHardness();

        MutableComponent line = Component.empty();
        line.append(Component.translatable("lore.gemmary.mohs_hardness")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
        line.append(Component.literal(" "));
        line.append(Component.literal(String.format("%.2f", hardness)).withStyle(ChatFormatting.GRAY));
        lore.add(line);
    }
}
