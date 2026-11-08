package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.EnumQuality;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public interface IForgingRecipe {

    boolean matches(@Nonnull ForgingContext context);

    @Nullable
    default Item getTool() {
        return null;
    }

    @Nullable
    ForgingTableLevel getRequiredTableLevel();

    @Nonnull
    List<ItemStack> getMaterials();

    int getTimeRequirement();

    int getFailureChance();

    int getAxeDurabilityCost();

    int getHammerDurabilityCost();

    @Nullable
    EnumQuality getQualityReward();

    @Nonnull
    List<? extends IForgingEffect> getFailureEffects();

    @Nonnull
    default List<? extends IForgingEffect> getSuccessEffects() {
        return Collections.emptyList();
    }

    default boolean requiresAxe() {
        return this.getAxeDurabilityCost() > 0;
    }

    default boolean requiresHammer() {
        return this.getHammerDurabilityCost() > 0;
    }
}
