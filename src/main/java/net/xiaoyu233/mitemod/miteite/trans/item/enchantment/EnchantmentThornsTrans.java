package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.EnchantmentThorns;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(EnchantmentThorns.class)
public abstract class EnchantmentThornsTrans extends EnchantmentTrans{
   @Shadow public abstract boolean canEnchantItem(Item item);

   @SoftOverride
   public int getNumLevelsForVibranium() {
      return 5;
   }
}
