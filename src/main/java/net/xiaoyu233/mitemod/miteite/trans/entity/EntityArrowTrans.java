package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityArrow.class)
public abstract class EntityArrowTrans extends Entity {
   @Shadow public Entity shootingEntity;
   @Shadow public abstract ItemStack getLauncher();

   public EntityArrowTrans(World par1World) {
      super(par1World);
   }

   @ModifyVariable(method = "setThrowableHeading", at = @At("HEAD"), argsOnly = true, name = "velocity", ordinal = 3)
   private float applyVibraniumBowVelocity(float velocity){
      ItemStack launcher = this.getLauncher();
      if (launcher != null && launcher.getItem() == MITEITEItemRegistryInit.VIBRANIUM_BOW && this.shootingEntity instanceof EntityPlayer) {
         return velocity * 1.75F;
      }
      return velocity;
   }

}
