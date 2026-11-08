package net.xiaoyu233.mitemod.miteite.api.forging;

import javax.annotation.Nonnull;

public interface IForgingListener {

    default void onForgingStart(@Nonnull ForgingContext context) {
    }

    default void onRecipeResolved(@Nonnull ForgingContext context) {
    }

    default void beforeSuccess(@Nonnull ForgingContext context) {
    }

    default void beforeFailure(@Nonnull ForgingContext context) {
    }

    default void afterForging(@Nonnull ForgingContext context, boolean success) {
    }
}
