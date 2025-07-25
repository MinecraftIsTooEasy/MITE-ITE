package net.xiaoyu233.mitemod.miteite.trans.container;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEnchantment.class)
public abstract class ContainerEnchantmentTrans extends Container {
   @Shadow public abstract boolean canInteractWith(EntityPlayer entityPlayer);

   public ContainerEnchantmentTrans(EntityPlayer player) {
      super(player);
   }

   @ModifyConstant(method = "calcEnchantmentLevelsForSlot", constant = @Constant(intValue = 24, ordinal = 0))
   private int vibraniumMaxLevel(int constant, @Local Item item) {
      if (item.getHardestMetalMaterial() == Materials.vibranium) {
         return Integer.MAX_VALUE;
      }
      return constant;
   }

   @ModifyConstant(method = "calcEnchantmentLevelsForSlot", constant = @Constant(intValue = 2, ordinal = 0))
   private int modifyCommon(int constant) {
      return constant * 2;
   }

   @ModifyConstant(method = "getNumAccessibleBookshelves", constant = @Constant(intValue = 1, ordinal = 0))
   private int modifyNumAccessibleBookshelvesn(int constant) {
      return 3;
   }

   @ModifyConstant(method = "getNumAccessibleBookshelves", constant = @Constant(intValue = 1, ordinal = 1))
   private int modifyNumAccessibleBookshelvesn_1(int constant) {
      return 3;
   }
}

