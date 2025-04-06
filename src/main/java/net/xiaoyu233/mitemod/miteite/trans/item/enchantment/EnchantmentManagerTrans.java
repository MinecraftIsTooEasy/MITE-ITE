package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnchantmentHelper.class)
public class EnchantmentManagerTrans {
   @ModifyConstant(method = "buildEnchantmentList", constant = @Constant(floatValue = 0.5f, ordinal = 0))
   private static float modify(float constant) {
      return 0.75f;
   }

   @WrapOperation(method = "mapEnchantmentData", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;getNumLevels()I"))
   private static int modify(Enchantment instance, Operation<Integer> original, @Local Item item) {
      if (item.getHardestMetalMaterial() == Materials.vibranium) {
         return instance.getNumLevelsForVibranium();
      }
      return original.call(instance);
   }
}
