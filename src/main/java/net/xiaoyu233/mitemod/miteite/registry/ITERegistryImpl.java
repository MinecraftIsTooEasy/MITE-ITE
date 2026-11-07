package net.xiaoyu233.mitemod.miteite.registry;

import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.IItemLevelUpListener;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingArrowDefinition;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlot;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingSlotDefinition;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingListener;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipe;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipeProvider;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingRecipe;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ITERegistryImpl implements ITERegistry {
    public static final Map<Material, BiFunction<Integer, Boolean, Integer>> tExpForLevelMap = new HashMap<>();
    public static final Map<Material, BiFunction<Integer, Boolean, Integer>> aExpForLevelMap = new HashMap<>();
    public static final List<Predicate<Item>> weaponCriteria = new ArrayList<>();
    public static final List<Predicate<Item>> meatCriteria = new ArrayList<>();
    public static final Map<Material, Integer> enhanceStoneFailChances = new HashMap<>();
    public static final List<IItemLevelUpListener> itemLevelUpListeners = new ArrayList<>();
    public static final List<IForgingRecipe> forgingRecipes = new ArrayList<>();
    public static final List<IForgingRecipeProvider> forgingRecipeProviders = new ArrayList<>();
    public static final List<ForgingSlotDefinition> forgingSlots = new ArrayList<>();
    public static ForgingArrowDefinition forgingArrow;
    public static final List<IForgingListener> forgingListeners = new ArrayList<>();

    @Override
    public void registerExpForLevel(Material material, int base, int increase) {
        tExpForLevelMap.put(material, createExpForLevel(base, increase));
    }

    @Override
    public void registerToolExpForLevel(Material material, int base, int increase) {
        tExpForLevelMap.put(material, createExpForLevel(base, increase));
    }

    @Override
    public void registerArmorExpForLevel(Material material, int base, int increase) {
        aExpForLevelMap.put(material, createExpForLevel(base, increase));
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
    public void registerEnhanceStone(@Nonnull Material material, int failChance) {
        if (material != null) {
            enhanceStoneFailChances.put(material, failChance);
        }
    }

    public static int getEnhanceStoneFailChance(@Nullable Material material) {
        if (material == null) {
            return 55;
        }
        return enhanceStoneFailChances.get(material);
    }

    @Override
    public void registerForgingRecipe(IForgingRecipe recipe) {
        if (recipe != null && !forgingRecipes.contains(recipe)) {
            forgingRecipes.add(recipe);
        }
    }

    @Override
    public void registerForgingRecipe(@Nonnull Item tool, int levelToUpgrade, @Nullable ForgingTableLevel requiredLevel, @Nonnull List<ItemStack> materials, @Nullable Consumer<ForgingRecipe.Builder> settings) {
        if (tool == null || materials == null) {
            return;
        }
        ForgingRecipe.Builder builder = ForgingRecipe.Builder.of(tool, levelToUpgrade, requiredLevel);
        for (ItemStack material : materials) {
            if (material != null) {
                builder.addMaterials(material);
            }
        }
        if (settings != null) {
            settings.accept(builder);
        }
        builder.build(this::registerForgingRecipe);
    }

    @Override
    public void registerForgingRecipeProvider(IForgingRecipeProvider provider) {
        if (provider != null && !forgingRecipeProviders.contains(provider)) {
            forgingRecipeProviders.add(provider);
        }
    }

    @Override
    public void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y) {
        this.registerForgingSlot(id, slot, x, y, null, stack -> true);
    }

    @Override
    public void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y, @Nonnull Predicate<ItemStack> validator) {
        this.registerForgingSlot(id, slot, x, y, null, validator);
    }

    @Override
    public void registerForgingSlot(@Nonnull ResourceLocation id, @Nonnull ForgingSlot slot, int x, int y, @Nullable ResourceLocation texture, @Nonnull Predicate<ItemStack> validator) {
        if (id == null || slot == null || validator == null) {
            return;
        }
        ForgingSlotDefinition definition = new ForgingSlotDefinition(id, slot, x, y, texture, validator);
        for (int i = 0; i < forgingSlots.size(); i++) {
            if (forgingSlots.get(i).getId().equals(id)) {
                forgingSlots.set(i, definition);
                return;
            }
        }
        forgingSlots.add(definition);
    }

    @Override
    public void registerForgingArrow(@Nonnull ResourceLocation id, int x, int y, @Nullable ResourceLocation texture,
                                     @Nullable ResourceLocation fullTexture) {
        if (id == null) {
            return;
        }
        forgingArrow = new ForgingArrowDefinition(id, x, y, texture, fullTexture);
    }

    @Override
    public void registerForgingListener(IForgingListener listener) {
        if (listener != null && !forgingListeners.contains(listener)) {
            forgingListeners.add(listener);
        }
    }

    @Override
    public void registerArmorModifier(ArmorModifierTypes modifier) {
        ArmorModifierTypes.register(modifier);
    }

    @Override
    public void registerToolModifier(ToolModifierTypes modifier) {
        ToolModifierTypes.register(modifier);
    }

    @Override
    public void registerItemLevelUpListener(IItemLevelUpListener listener) {
        if (listener != null) {
            itemLevelUpListeners.add(listener);
        }
    }
}
