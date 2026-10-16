package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEWorld;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.MonsterUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLongdead.class)
public abstract class EntityLongdeadTrans extends EntitySkeletonTrans {
   @Shadow public abstract void addRandomWeapon();

   public EntityLongdeadTrans(World par1World) {
      super(par1World);
   }

   @Inject(method = "addRandomEquipment", at = @At("HEAD"), cancellable = true)
   protected void addRandomEquipment(CallbackInfo ci) {
      this.addRandomWeapon();
      int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
      if (day < 96) {
         this.setBoots((new ItemStack(Item.bootsIron)).randomizeForMob(this, true));
         this.setLeggings((new ItemStack(Item.legsIron)).randomizeForMob(this, true));
         this.setCuirass((new ItemStack(Item.plateIron)).randomizeForMob(this, true));
         this.setHelmet((new ItemStack(Item.helmetIron)).randomizeForMob(this, true));
      } else {
         MonsterUtil.addDefaultArmor(day, this, true);
      }
      this.initStockedWeapon();
      ci.cancel();
   }

   @Override
   protected boolean willChangeWeapon() {
      return Configs.Entities.BONE_LORD_AND_LONGDEAD_CHANGE_WEAPON_CHANCE.get() > this.rand.nextFloat();
   }

   protected void enchantEquipment(ItemStack item_stack) {
      int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
      if ((double)this.getRNG().nextFloat() <= 0.2D + (double) day / 64.0D / 10.0D) {
         EnchantmentHelper.addRandomEnchantment(this.getRNG(), item_stack, (int)(5.0F + (float)(this.getRNG().nextInt(15 + day / 48) / 10) * (float)this.getRNG().nextInt(18)));
      }

   }
}
