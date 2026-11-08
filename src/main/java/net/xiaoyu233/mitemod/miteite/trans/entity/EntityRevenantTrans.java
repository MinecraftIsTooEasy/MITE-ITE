package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.MobOverwritor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRevenant.class)
public class EntityRevenantTrans extends EntityZombie {
   public EntityRevenantTrans(World world) {
      super(world);
   }

   /**
    * @author XiaoYu233
    * @reason add better equipment
    */
   @Overwrite
   protected void addRandomEquipment() {
      this.addRandomWeapon();
      MobOverwritor.setArmor_ITE(this,Item.bootsRustedIron,Item.legsRustedIron,Item.plateRustedIron,Item.helmetRustedIron,128,0);
   }

   protected void enchantEquipment(ItemStack item_stack) {
      int day = this.getWorld().getDayOfOverworld();
      if ((double)this.getRNG().nextFloat() <= 0.2D + (double) day / 64.0D / 10.0D) {
         EnchantmentHelper.addRandomEnchantment(this.getRNG(), item_stack, (int)(5.0F + (float)((this.getRNG().nextInt(15 + day / 24) + 3) / 10) * (float)this.getRNG().nextInt(18)));
      }

   }
}
