package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(EnchantmentLootBonus.class)
public abstract class EnchantmentLootBonusTrans extends Enchantment {
   @Shadow public abstract boolean canEnchantItem(Item item);
   @Shadow public abstract String getNameSuffix();
   @Shadow public abstract boolean isOnCreativeTab(CreativeTabs creativeModeTab);

   protected EnchantmentLootBonusTrans(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   @SoftOverride
   public int getNumLevelsForVibranium() {
      return 5;
   }

}
