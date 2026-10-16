package net.xiaoyu233.mitemod.miteite.api;

import net.xiaoyu233.mitemod.miteite.item.ItemModifierTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface IItemLevelUpListener {

    default void modifyCandidates(@Nonnull ItemLevelUpContext context, @Nonnull List<ItemModifierTypes> candidates) {
    }

    @Nullable
    default ItemModifierTypes selectModifier(@Nonnull ItemLevelUpContext context, @Nonnull List<ItemModifierTypes> candidates) {
        return null;
    }

    default void onModifierApplied(@Nonnull ItemLevelUpContext context, @Nonnull ItemModifierTypes modifier, int newLevel, boolean isNewModifier) {
    }

    default void afterItemLevelUp(@Nonnull ItemLevelUpContext context) {
    }
}
