package net.xiaoyu233.mitemod.miteite.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.config.EmiConfig;
import moddedmite.emi.api.EMIGuiContainerCreative;
import net.minecraft.CreativeTabs;
import net.minecraft.GuiContainerCreative;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.gui.GuiForgingTable;

import java.util.Objects;

public class MITEITEEmiPlugin implements EmiPlugin {
    public void register(EmiRegistry registry) {
        if (EmiConfig.moreWorkstation) {
            registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(MITEITEBlockRegistryInit.vibraniumWorkBench));
            registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(MITEITEBlockRegistryInit.furnaceVibraniumIdle));
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
