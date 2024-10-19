package net.xiaoyu233.mitemod.miteite.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import net.xiaoyu233.mitemod.miteite.block.Blocks;

public class MITEITEEmiPlugin implements EmiPlugin {
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(Blocks.vibraniumWorkBench));
        emiRegistry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(Blocks.furnaceVibraniumIdle));
    }
}
