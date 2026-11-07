package net.xiaoyu233.mitemod.miteite.api;

import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlot;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingListener;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipe;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipeProvider;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingRecipe;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ITERegistry {
    void registerExpForLevel(Material material, int base, int increase);

    void registerWeaponCriteria(Predicate<Item> predicate);

    void registerMeatCriteria(Predicate<Item> criteria);

    void registerEnhanceStone(@Nonnull Material material, int failChance);

    void registerForgingRecipe(IForgingRecipe recipe);

    void registerForgingRecipe(@Nonnull Item tool, int levelToUpgrade, @Nullable ForgingTableLevel requiredLevel, @Nonnull List<ItemStack> materials, @Nullable Consumer<ForgingRecipe.Builder> settings);

    void registerForgingRecipeProvider(IForgingRecipeProvider provider);

    void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y);

    void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y, @Nonnull Predicate<ItemStack> validator);

    void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y, @Nullable ResourceLocation texture, @Nonnull Predicate<ItemStack> validator);

    void registerForgingArrow(@Nonnull ResourceLocation id, int x, int y, @Nullable ResourceLocation texture, @Nullable ResourceLocation fullTexture);

    void registerForgingListener(IForgingListener listener);

    void registerArmorModifier(ArmorModifierTypes modifier);

    void registerToolModifier(ToolModifierTypes modifier);

    void registerItemLevelUpListener(IItemLevelUpListener listener);
}
