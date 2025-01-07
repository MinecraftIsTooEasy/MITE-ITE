package net.xiaoyu233.mitemod.miteite.api;

import net.minecraft.Item;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

import java.util.function.Predicate;

public interface ITERegistry {
    void registerExpForLevel(Material material, int base, int increase);

    void registerWeaponCriteria(Predicate<Item> predicate);

    void registerMeatCriteria(Predicate<Item> criteria);

    void registerForgingRecipe(Material material, ForgingTableLevel forgingTableLevel, int maxLevel);
}
