package net.xiaoyu233.mitemod.miteite.registry;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEPlugin;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import net.xiaoyu233.mitemod.miteite.item.Materials;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

public class ITEVanillaPlugin implements ITEPlugin {
    @Override
    public void register(ITERegistry registry) {
        registry.registerWeaponCriteria(item -> item instanceof ItemSword || item instanceof ItemBattleAxe || item instanceof ItemWarHammer);
        registry.registerMeatCriteria(item -> item instanceof ItemMeat);

        registry.registerExpForLevel(Material.copper, 100, 50);
        registry.registerExpForLevel(Material.silver, 100, 50);
        registry.registerExpForLevel(Material.gold, 125, 60);
        registry.registerExpForLevel(Material.iron, 150, 75);
        registry.registerExpForLevel(Material.ancient_metal, 150, 75);
        registry.registerExpForLevel(Material.mithril, 200, 100);
        registry.registerExpForLevel(Material.adamantium, 200, 120);
        registry.registerExpForLevel(Materials.vibranium, 200, 150);

        registry.registerForgingRecipe(Material.iron, ForgingTableLevel.IRON, 2);
        registry.registerForgingRecipe(Material.ancient_metal, ForgingTableLevel.MITHRIL, 2);
        registry.registerForgingRecipe(Material.mithril, ForgingTableLevel.MITHRIL, 4);
        registry.registerForgingRecipe(Material.adamantium, ForgingTableLevel.ADAMANTIUM, 6);
        registry.registerForgingRecipe(Materials.vibranium, ForgingTableLevel.VIBRANIUM, 9);
    }
}
