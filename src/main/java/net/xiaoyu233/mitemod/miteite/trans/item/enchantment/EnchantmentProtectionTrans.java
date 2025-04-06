package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.EnchantmentProtection;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(EnchantmentProtection.class)
public abstract class EnchantmentProtectionTrans extends EnchantmentTrans {
   @Shadow public abstract boolean canEnchantItem(Item item);

   @SoftOverride
   public int getNumLevelsForVibranium() {
      return 5;
   }

}
