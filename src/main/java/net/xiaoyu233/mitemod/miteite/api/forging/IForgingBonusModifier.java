package net.xiaoyu233.mitemod.miteite.api.forging;

import net.minecraft.ItemStack;

import javax.annotation.Nonnull;

public interface IForgingBonusModifier {

    default float modifyAttackBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus;
    }

    default float modifyHarvestBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus;
    }

    default float modifyProtectionBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus;
    }

    default float modifyExpBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus;
    }
}
