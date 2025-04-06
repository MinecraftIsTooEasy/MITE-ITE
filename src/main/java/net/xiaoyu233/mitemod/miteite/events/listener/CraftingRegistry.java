package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

import java.util.function.Consumer;

public class CraftingRegistry implements Consumer<CraftingRecipeRegisterEvent> {
    @Override
    public void accept(CraftingRecipeRegisterEvent event) {
        MITEITEItemRegistryInit.registerRecipes(event);
        MITEITEBlockRegistryInit.registerRecipes(event);
    }
}
