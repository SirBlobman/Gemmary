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

public final class ElementItem extends Item {
    private final String elementSymbol;
    private final int atomicNumber;
    private final double atomicWeight;

    public ElementItem(@NotNull String symbol, int number, double weight) {
        super(new Properties());
        this.elementSymbol = symbol;
        this.atomicNumber = number;
        this.atomicWeight = weight;
    }

    public @NotNull String getElementSymbol() {
        return this.elementSymbol;
    }

    public int getAtomicNumber() {
        return this.atomicNumber;
    }

    public double getAtomicWeight() {
        return this.atomicWeight;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> lore,
                                @NotNull TooltipFlag flags) {
        String elementSymbol = getElementSymbol();
        int atomicNumber = getAtomicNumber();
        double atomicWeight = getAtomicWeight();

        MutableComponent line1 = Component.empty();
        line1.append(Component.translatable("lore.gemmary.element_symbol")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
        line1.append(Component.literal(" "));
        line1.append(Component.literal(elementSymbol).withStyle(ChatFormatting.GRAY));
        lore.add(line1);

        MutableComponent line2 = Component.empty();
        line2.append(Component.translatable("lore.gemmary.atomic_number")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
        line2.append(Component.literal(" "));
        line2.append(Component.literal(Integer.toString(atomicNumber)).withStyle(ChatFormatting.GRAY));
        lore.add(line2);

        MutableComponent line3 = Component.empty();
        line3.append(Component.translatable("lore.gemmary.atomic_weight")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
        line3.append(Component.literal(" "));
        line3.append(Component.literal(String.format("%.4f", atomicWeight)).withStyle(ChatFormatting.GRAY));
        lore.add(line3);
    }
}
