package net.xiaoyu233.mitemod.miteite.api.forging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface IForgingRecipeProvider {

    @Nullable
    IForgingRecipe findRecipe(@Nonnull ForgingContext context);
}
