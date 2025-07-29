package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentLootBonus.class)
public class EnchantmentLootBonusMixin {
    @Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
    public void fixModTool(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (ReflectHelper.dyCast(this) == Enchantment.fortune) {
            cir.setReturnValue(item instanceof ItemPickaxe || item instanceof ItemShovel);
        }
    }
}
