package xyz.sirblobman.mod.gemmary.item;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public final class ClothItem extends Item {
    public ClothItem() {
        super(new Properties().durability(10));
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        if(isDamaged(stack)) {
            int damageValue = stack.getDamageValue();
            int maxDamage = stack.getMaxDamage();
            return (damageValue <= maxDamage);
        }

        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        int damageValue = copy.getDamageValue();
        copy.setDamageValue(damageValue + 1);
        return copy;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockPos position = context.getClickedPos();
            BlockState state = level.getBlockState(position);

            Block block = state.getBlock();
            if (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK) {
                RandomSource random = level.getRandom();
                int randomInt = random.nextInt(10);
                if (randomInt == 0) {
                    ItemEntity itemEntity = EntityType.ITEM.create(level);
                    if (itemEntity == null) {
                        return InteractionResult.FAIL;
                    }

                    itemEntity.setItem(new ItemStack(GemmaryItems.RUBY, 1));
                    itemEntity.moveTo(position, 0.0F, 0.0F);
                    level.addFreshEntity(itemEntity);
                    level.setBlockAndUpdate(position, Blocks.AIR.defaultBlockState());
                }

                Player player = context.getPlayer();
                if (player != null) {
                    InteractionHand hand = context.getHand();
                    ItemStack stack = context.getItemInHand();
                    stack.hurtAndBreak(1, player, event -> {
                        event.broadcastBreakEvent(hand);
                    });
                }

                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }

        return InteractionResult.FAIL;
    }
}
