package xyz.sirblobman.mod.gemmary.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GemItem extends Item {
    private final double hardness;

    public GemItem(double hardness) {
        super(new Properties());
        this.hardness = hardness;
    }

    public double getMohsHardness() {
        return this.hardness;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> lore,
                                @NotNull TooltipFlag flags) {
        super.appendHoverText(stack, world, lore, flags);
        double hardness = getMohsHardness();

        MutableComponent line = Component.empty();
        line.append(Component.translatable("lore.gemmary.mohs_hardness")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
        line.append(Component.literal(" "));
        line.append(Component.literal(String.format("%.2f", hardness)).withStyle(ChatFormatting.GRAY));
        lore.add(line);
    }
}
