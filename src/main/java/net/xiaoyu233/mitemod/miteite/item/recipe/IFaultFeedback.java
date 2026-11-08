package net.xiaoyu233.mitemod.miteite.item.recipe;

import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.api.forging.ForgingContext;
import net.xiaoyu233.mitemod.miteite.api.forging.IForgingEffect;

public interface IFaultFeedback extends IForgingEffect {
    ItemStack accept(ItemStack itemStack);
    String getName();
    int getData();

    @Override
    default ItemStack apply(ForgingContext context, ItemStack result) {
        return this.accept(result);
    }
}
