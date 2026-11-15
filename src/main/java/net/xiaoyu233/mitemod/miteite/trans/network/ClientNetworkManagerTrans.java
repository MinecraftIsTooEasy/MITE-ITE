package net.xiaoyu233.mitemod.miteite.trans.network;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public abstract class ClientNetworkManagerTrans extends NetHandler {
   @Shadow private Minecraft mc;

   @Inject(method = "handleMobSpawn", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLiving;rotationYawHead:F", shift = At.Shift.BEFORE, ordinal = 0))
   public void injectDenyBadSpawn(Packet24MobSpawn par1Packet24MobSpawn, CallbackInfo ci, @Local EntityLiving living) {
       if (living == null) {
          Minecraft.MITE_log.logWarning("Bad mob spawning packet with entity_id:" + par1Packet24MobSpawn.type);
       }
   }

   @Inject(method = "handleWindowItems", at = @At("RETURN"))
   public void handleWindowItems(Packet104WindowItems par1Packet104WindowItems, CallbackInfo ci) {
      this.mc.thePlayer.itemsSynced();
   }
}
