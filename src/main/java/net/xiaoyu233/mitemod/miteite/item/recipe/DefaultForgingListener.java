package net.xiaoyu233.mitemod.miteite.item.recipe;

import net.minecraft.EnumQuality;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingContext;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingListener;

import javax.annotation.Nonnull;

public class DefaultForgingListener implements IForgingListener {

    @Override
    public void onRecipeResolved(@Nonnull ForgingContext context) {
        this.applyToolFailureChance(context);
        this.applyToolEffectivity(context);
        this.applyFailureDurability(context);
    }

    private void applyToolFailureChance(@Nonnull ForgingContext context) {
        int bounceChanceFromTool = 0;
        int ironLevel = Material.iron.getMinHarvestLevel();

        ItemStack hammer = context.getHammerStack();
        if (hammer != null) {
            bounceChanceFromTool += Math.max((hammer.getMaterialForRepairs().getMinHarvestLevel() - ironLevel) * 3, 0);
        }
        ItemStack axe = context.getAxeStack();
        if (axe != null) {
            bounceChanceFromTool += Math.max((axe.getMaterialForRepairs().getMinHarvestLevel() - ironLevel) * 2, 0);
        }

        context.setFailureChance(context.getFailureChance() - bounceChanceFromTool);
    }

    private void applyToolEffectivity(@Nonnull ForgingContext context) {
        ItemStack axe = context.getAxeStack();
        ItemStack hammer = context.getHammerStack();
        float effectivity = axe != null && hammer != null
                ? 0.25F + (float) axe.getMaterialForRepairs().getMinHarvestLevel() / 5.0F
                        + (float) hammer.getMaterialForRepairs().getMinHarvestLevel() / 4.0F
                : 1.0F;
        context.setTime(Math.round(context.getTime() / effectivity));
    }

    private void applyFailureDurability(@Nonnull ForgingContext context) {
        context.setFailureAxeDurabilityCost(context.getAxeDurabilityCost() / 2);
        context.setFailureHammerDurabilityCost(context.getHammerDurabilityCost() / 2);
    }

    @Override
    public void beforeSuccess(@Nonnull ForgingContext context) {
        context.addSuccessEffect((ctx, result) -> {
            if (ctx.isUpgradeLevel()) {
                result.setForgingGrade(result.getForgingGrade() + 1);
            }
            return result;
        });
        context.addSuccessEffect((ctx, result) -> {
            EnumQuality quality = ctx.getQualityReward();
            if (quality != null) {
                result.setQuality(quality);
            }
            return result;
        });
    }
}
