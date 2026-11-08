package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITESkeleton;
import net.xiaoyu233.mitemod.miteite.util.MobOverwritor;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAncientBoneLord.class)
public class EntityAncientBoneLordTrans extends EntityBoneLord {
   public EntityAncientBoneLordTrans(World world) {
      super(world);
   }

   @Inject(method = "addRandomEquipment", at = @At("HEAD"), cancellable = true)
   protected void addRandomEquipment(CallbackInfo ci) {
      this.addRandomWeapon();
      MobOverwritor.setArmor_ITE(this,Item.bootsAncientMetal,Item.legsAncientMetal,Item.plateAncientMetal,Item.helmetAncientMetal,112,0);
      ((ITESkeleton) this).initStockedWeapon();
      ci.cancel();
   }

   @Override
   protected void enchantEquipment(ItemStack item_stack) {
      if ((double) this.getRNG().nextFloat() <= 0.2 + (double) this.getWorld().getDayOfOverworld() / 64.0 / 10.0) {
         EnchantmentHelper.addRandomEnchantment(
                 this.getRNG(),
                 item_stack,
                 (int) (5.0F + (float) ((this.getRNG().nextInt(15 + this.getWorld().getDayOfOverworld() / 24) + 3) / 10) * (float)this.getRNG().nextInt(18))
         );
      }
   }

   public boolean getCanSpawnHere(boolean perform_light_check) {
      if (!this.getWorld().isOverworld()) {
         return super.getCanSpawnHere(perform_light_check);
      } else {
         Vec3 pos = this.getFootPos();
         return this.getWorld().isBloodMoon24HourPeriod()
                 && this.getWorld().getDayOfOverworld() >= Configs.GameMechanics.MobSpawning.ANCIENT_BONE_LORD_SPAWN_LIMIT_DAY.get()
                 && !(
                 this.getWorld().getClosestEntityLivingBase(this, new Class[]{EntityAncientBoneLord.class}, 64.0, false, false) instanceof EntityAncientBoneLord
         )
                 && this.getWorld().getBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()) != Block.waterMoving
                 && !this.getWorld().anySolidBlockIn(this.boundingBox);
      }
   }

   @Inject(method = "getExperienceValue", at = @At("HEAD"), cancellable = true)
   public void injectExpVal(CallbackInfoReturnable<Integer> cir) {
      cir.setReturnValue(Configs.Entities.BONE_LORD_TWEAK.get() ? super.getExperienceValue() * 4 : super.getExperienceValue() * 2);
   }
}
