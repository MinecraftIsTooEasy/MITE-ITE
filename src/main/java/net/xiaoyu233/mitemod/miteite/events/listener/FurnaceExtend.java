package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.events.SmeltingRecipeRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.SmeltingHandler;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.util.Configs;

import java.util.function.Consumer;

public class FurnaceExtend implements Consumer<SmeltingRecipeRegisterEvent> {
    @Override
    public void accept(SmeltingRecipeRegisterEvent event) {
        event.register(Block.coalBlock.blockID, new ItemStack(MITEITEItemRegistryInit.DIAMOND_CHUNK));
        event.register(MITEITEBlockRegistryInit.netherAdamantiumOre.blockID, new ItemStack(Item.ingotAdamantium));
        if (!FishModLoader.hasMod("extreme") && !FishModLoader.hasMod("extreme-lh")) {
            event.register(Block.blockIron.blockID, new ItemStack(Item.ingotMithril));
            event.register(Block.blockMithril.blockID, new ItemStack(Item.ingotAdamantium));
            event.registerSpecial((itemStack, i) -> itemStack.itemID == Block.blockIron.blockID && i >= 3 && itemStack.stackSize >= Configs.Item.Recipes.IRON_TO_MITHRIL_COOK_TIME.get() ? SmeltingHandler.result(4, new ItemStack(Item.ingotMithril)) : null);
            event.registerSpecial((itemStack, i) -> itemStack.itemID == Block.blockMithril.blockID && i >= 4 && itemStack.stackSize >= Configs.Item.Recipes.MITHRIL_TO_ADAMANTIUM_COOK_TIME.get() ? SmeltingHandler.result((Configs.Item.Recipes.MITHRIL_TO_ADAMANTIUM_COOK_TIME.get()), new ItemStack(Item.ingotAdamantium)) : null);
        }
    }
}

