package net.xiaoyu233.mitemod.miteite.util;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingContext;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingEffect;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingListener;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipe;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipeProvider;
import net.xiaoyu233.mitemod.miteite.inventory.container.ForgingTableSlots;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public final class ForgingHelper {
    private ForgingHelper() {
    }

    @Nonnull
    public static ForgingContext createContext(@Nonnull ForgingTableSlots slots, @Nullable EntityPlayer player, int tableLevel) {
        return new ForgingContext(slots.getTileEntity(), slots, player, slots.getToolItem(), tableLevel);
    }

    @Nonnull
    public static ForgingContext preview(@Nonnull ForgingTableSlots slots, @Nullable EntityPlayer player, int tableLevel) {
        ForgingContext context = createContext(slots, player, tableLevel);
        resolve(context);
        return context;
    }

    @Nonnull
    public static ForgingContext preview(@Nonnull ForgingTableSlots slots, int tableLevel) {
        return preview(slots, null, tableLevel);
    }

    public static boolean prepare(@Nonnull ForgingContext context) {
        for (IForgingListener listener : ITERegistryImpl.forgingListeners) {
            listener.onForgingStart(context);
        }
        if (context.isCancelled()) {
            return false;
        }
        if (!resolve(context)) {
            return false;
        }

        IForgingRecipe recipe = context.requireRecipe();
        ForgingTableLevel requiredLevel = recipe.getRequiredTableLevel();
        if (requiredLevel != null && context.getTableLevel() < requiredLevel.getLevel()) {
            return false;
        }
        if (recipe.requiresHammer() && context.getHammerStack() == null) {
            return false;
        }
        if (recipe.requiresAxe() && context.getAxeStack() == null) {
            return false;
        }
        if (!context.getSlots().getNeedItems(context.getMaterials()).isEmpty()) {
            return false;
        }
        return !context.isCancelled();
    }

    public static boolean resolve(@Nonnull ForgingContext context) {
        IForgingRecipe recipe = findRecipe(context);
        if (recipe == null) {
            context.setRecipe(null);
            return false;
        }
        context.setRecipe(recipe);
        seed(context, recipe);
        for (IForgingListener listener : ITERegistryImpl.forgingListeners) {
            listener.onRecipeResolved(context);
        }
        return !context.isCancelled();
    }

    @Nullable
    private static IForgingRecipe findRecipe(@Nonnull ForgingContext context) {
        List<IForgingRecipe> recipes = ITERegistryImpl.forgingRecipes;
        for (int i = recipes.size() - 1; i >= 0; i--) {
            IForgingRecipe recipe = recipes.get(i);
            if (recipe.matches(context)) {
                return recipe;
            }
        }
        List<IForgingRecipeProvider> providers = ITERegistryImpl.forgingRecipeProviders;
        for (int i = providers.size() - 1; i >= 0; i--) {
            IForgingRecipe recipe = providers.get(i).findRecipe(context);
            if (recipe != null) {
                return recipe;
            }
        }
        return null;
    }

    private static void seed(@Nonnull ForgingContext context, @Nonnull IForgingRecipe recipe) {
        context.setFailureChance(recipe.getFailureChance());
        context.setTime(recipe.getTimeRequirement());
        context.setAxeDurabilityCost(recipe.getAxeDurabilityCost());
        context.setHammerDurabilityCost(recipe.getHammerDurabilityCost());
        context.setFailureAxeDurabilityCost(recipe.getAxeDurabilityCost());
        context.setFailureHammerDurabilityCost(recipe.getHammerDurabilityCost());
        context.setQualityReward(recipe.getQualityReward());
        context.setUpgradeLevel(true);
        context.setMaterials(recipe.getMaterials());
        context.getSuccessEffects().clear();
        context.getSuccessEffects().addAll(recipe.getSuccessEffects());
        context.getFailureEffects().clear();
        context.getFailureEffects().addAll(recipe.getFailureEffects());
    }

    @Nullable
    public static ItemStack complete(@Nonnull ForgingContext context) {
        for (IForgingListener listener : ITERegistryImpl.forgingListeners) {
            listener.beforeSuccess(context);
        }
        return applyEffects(context, context.getToolStack(), context.getSuccessEffects());
    }

    @Nullable
    public static ItemStack fail(@Nonnull ForgingContext context) {
        for (IForgingListener listener : ITERegistryImpl.forgingListeners) {
            listener.beforeFailure(context);
        }
        return applyEffects(context, context.getToolStack(), context.getFailureEffects());
    }

    public static void afterForging(@Nonnull ForgingContext context, boolean success) {
        for (IForgingListener listener : ITERegistryImpl.forgingListeners) {
            listener.afterForging(context, success);
        }
    }

    @Nullable
    private static ItemStack applyEffects(@Nonnull ForgingContext context, @Nullable ItemStack result, @Nonnull List<IForgingEffect> effects) {
        if (result == null) {
            return null;
        }
        for (IForgingEffect effect : effects) {
            result = effect.apply(context, result);
            if (result == null) {
                return null;
            }
        }
        return result;
    }
}
