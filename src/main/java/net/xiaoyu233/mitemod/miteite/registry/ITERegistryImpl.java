package net.xiaoyu233.mitemod.miteite.registry;

import net.minecraft.Item;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableRecipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ITERegistryImpl implements ITERegistry {
    public static final Map<Material, BiFunction<Integer, Boolean, Integer>> expForLevelMap = new HashMap<>();
    public static final List<Predicate<Item>> weaponCriteria = new ArrayList<>();
    public static final List<Predicate<Item>> meatCriteria = new ArrayList<>();

    @Override
    public void registerExpForLevel(Material material, int base, int increase) {
        expForLevelMap.put(material, createExpForLevel(base, increase));
    }

    private BiFunction<Integer, Boolean, Integer> createExpForLevel(int base, int increase) {
        return (level, isWeapon) -> base + (level * increase * (isWeapon ? 2 : 1));
    }

    @Override
    public void registerWeaponCriteria(Predicate<Item> criteria) {
        weaponCriteria.add(criteria);
    }

    @Override
    public void registerMeatCriteria(Predicate<Item> criteria) {
        meatCriteria.add(criteria);
    }

    @Override
    public void registerForgingRecipe(Material material, ForgingTableLevel tableLevel, int maxLevel) {
        ForgingTableRecipes.registerForgingRecipe(material, tableLevel, maxLevel);
    }
}
