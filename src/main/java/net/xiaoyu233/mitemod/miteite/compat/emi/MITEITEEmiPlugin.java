package net.xiaoyu233.mitemod.miteite.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.config.EmiConfig;
import moddedmite.emi.api.EMIGuiContainerCreative;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingRecipe;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.gui.GuiForgingTable;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingRecipe;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableRecipes;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MITEITEEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        if (EmiConfig.moreWorkstation) {
            registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(MITEITEBlockRegistryInit.vibraniumWorkBench));
            registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(MITEITEBlockRegistryInit.furnaceVibraniumIdle));
        }

        EmiRecipeCategory forging = new EmiRecipeCategory(new ResourceLocation("miteite", "forging"), EmiStack.of(MITEITEBlockRegistryInit.blockForgingTable));
        registry.addCategory(forging);
        for (ItemStack stack : (List<ItemStack>) MITEITEBlockRegistryInit.blockForgingTable.getItemStacks()) {
            registry.addWorkstation(forging, EmiStack.of(stack));
        }
        List<ForgingRecipe> forgingRecipes = new ArrayList<>();
        for (IForgingRecipe recipe : ITERegistryImpl.forgingRecipes) {
            if (recipe instanceof ForgingRecipe && !forgingRecipes.contains(recipe)) {
                forgingRecipes.add((ForgingRecipe) recipe);
            }
        }
        for (ForgingRecipe recipe : ForgingTableRecipes.getAllRecipes()) {
            if (!forgingRecipes.contains(recipe)) {
                forgingRecipes.add(recipe);
            }
        }
        for (ForgingRecipe recipe : forgingRecipes) {
            registry.addRecipe(new ForgingEmiRecipe(forging, recipe));
        }

        registry.addExclusionArea(GuiForgingTable.class, (screen, consumer) -> {
            int left = ((EMIGuiContainerCreative) screen).getGuiLeft();
            int top = ((EMIGuiContainerCreative) screen).getGuiTop();
            int width = ((EMIGuiContainerCreative) screen).getxSize();
            if (!Objects.equals(screen.forgingTable.slots.getToolItem(), null)) {
                consumer.accept(new Bounds(left, top, width + 85, 55));
            }
        });
    }
}
