package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentDisarming.class)
public class EnchantmentDisarmingMixin {
    @Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
    public void fixModTool(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item instanceof ItemSword) {
            cir.setReturnValue(true);
        }
    }
}
