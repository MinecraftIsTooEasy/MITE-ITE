package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.IAchievementListener;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.achievement.MITEITEAchievementRegistryInit;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class AchievementListener implements IAchievementListener {
    @Override
    public void onItemCrafted(EntityPlayer player, ItemStack itemStack) {
        if (itemStack == null) return;
        if (itemStack.getItem().isBlock() && itemStack.getItem().getAsItemBlock().getBlock() == MITEITEBlockRegistryInit.furnaceVibraniumIdle) {
            player.triggerAchievement(MITEITEAchievementRegistryInit.vibraniumFurnace);
        }
    }

    @Override
    public void onItemSmelt(EntityPlayer player, ItemStack itemStack) {
        Item item = itemStack.getItem();
        Block block = item instanceof ItemBlock ? ((ItemBlock) item).getBlock() : null;
        if (item == MITEITEItemRegistryInit.VIBRANIUM_INGOT) {
            player.triggerAchievement(MITEITEAchievementRegistryInit.vibraniumIngot);
        } else if (block == MITEITEBlockRegistryInit.anvilVibranium) {
            player.triggerAchievement(MITEITEAchievementRegistryInit.vibraniumAnvil);
        }
    }
}
