package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.ItemArmor;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingBonusModifier;

import javax.annotation.Nonnull;

public class DefaultForgingBonusModifier implements IForgingBonusModifier {

    @Override
    public float modifyAttackBonus(@Nonnull ItemStack stack, float bonus) {
        if (stack.getItem() instanceof ItemTool) {
            bonus += (float) (stack.getEnhanceFactor() * (double) ((ItemTool) stack.getItem()).getCombinedDamageVsEntity());
        }
        return bonus;
    }

    @Override
    public float modifyHarvestBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus + (float) stack.getEnhanceFactor();
    }

    @Override
    public float modifyProtectionBonus(@Nonnull ItemStack stack, float bonus) {
        if (stack.getItem() instanceof ItemArmor armor) {
	        float rawProtection = (float) (armor.getNumComponentsForDurability() * armor.getMaterialProtection()) / 24.0F;
            bonus += (float) (stack.getEnhanceFactor() * (double) rawProtection * 0.68F + (double) ((float) stack.getForgingGrade() / 3.0F)) * 0.75F;
        }
        return bonus;
    }

    @Override
    public float modifyExpBonus(@Nonnull ItemStack stack, float bonus) {
        return bonus + (0.05F + stack.getForgingGrade() * 0.05F) * stack.getForgingGrade() / 2;
    }
}
