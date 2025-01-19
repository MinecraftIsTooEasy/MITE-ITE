package net.xiaoyu233.mitemod.miteite.trans.render;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderEarthElemental.class)
public class RenderEarthElementalTrans extends RenderBiped {
   public RenderEarthElementalTrans() {
      super(new ModelInvisibleStalker(), 0.5F);
   }


   @Inject(method = "getTextures", at = @At("HEAD"), cancellable = true)
   private void injectGetPlankTexture(EntityEarthElemental earth_elemental, CallbackInfoReturnable<ResourceLocation> cir){
      //Plank type
      if (earth_elemental.getType() == 5) {
         cir.setReturnValue(super.textures[10]);
      }
   }

   @Inject(method = "setTextures()V", at = @At("RETURN"))
   private void injectTexture(CallbackInfo c){
      this.setTexture(10, "plank", false);
   }

   @Inject(method = "setTexture", at = @At("HEAD"))
   private void setTexture(int index, String name, boolean magma, CallbackInfo ci) {
      if (index == 10) {
         this.setTexture(index, "miteite:textures/entity/earth_elemental/" + name + "/earth_elemental_" + name + (magma ? "_magma" : ""), "miteite:textures/entity/earth_elemental/" + (magma ? "earth_elemental_magma" : "earth_elemental"));
      }
   }

   @Shadow
   protected void setTextures() {
   }
   @Shadow
   private void setTexture(int index, String name, boolean magma) {
   }
}
