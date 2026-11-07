package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface IForgingEffect {

    @Nullable
    ItemStack apply(@Nonnull ForgingContext context, @Nonnull ItemStack result);
}
