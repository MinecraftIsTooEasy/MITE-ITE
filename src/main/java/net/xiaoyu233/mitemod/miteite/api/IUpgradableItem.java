package net.xiaoyu233.mitemod.miteite.api;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.registry.ITERegistryImpl;

import javax.annotation.Nonnull;

public interface IUpgradableItem {
    int getExpReqForLevel(int i, boolean weapon);
    boolean isMaxToolLevel(ItemStack itemStack);
    int getMaxToolLevel(ItemStack itemStack);
    default float getEquipmentExpBounce(@Nonnull ItemStack itemStack){
        return ITERegistryImpl.modifyForgingExpBonus(itemStack, 0.0F);
    }

    void addExpForTool(ItemStack stack, EntityPlayer player, int exp);
}
