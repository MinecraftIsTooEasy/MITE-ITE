package net.xiaoyu233.mitemod.miteite.trans.block.tileentity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEBeacon;
import net.xiaoyu233.mitemod.miteite.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(TileEntityBeacon.class)
public class TileEntityBeaconTrans extends TileEntity implements ITEBeacon {
   @Shadow private int levels;

   @Unique private BlockPos currentPos;

   @Inject(method = "updateState", at = @At("HEAD"))
   private void addBlockPos(CallbackInfo ci) {
      if (this.currentPos == null) {
         this.currentPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord);
      }
   }

   @Inject(method = "addEffectsToPlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;addPotionEffect(Lnet/minecraft/PotionEffect;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
   private void addCraftingBoostToPlayer(CallbackInfo ci, double var1, byte var3, AxisAlignedBB var4, List var5, Iterator var6, EntityPlayer var7) {
      var7.setCraftingBoostFactor(this.getCraftingBoostFactor(this.levels), this.currentPos);
      var7.setCraftingBoostTimer(160);
   }

   public void clearAllPlayersBoost() {
      double var1 = this.levels * 10 + 10;
      AxisAlignedBB var4 = AxisAlignedBB.getAABBPool().getAABB(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1).expand(var1, var1, var1);
      for (Object o : this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, var4)) {
         EntityPlayer EntityPlayer = (EntityPlayer) o;
         EntityPlayer.setCraftingBoostFactor(0.0F, this.currentPos);
      }
   }

   @Unique
   private float getCraftingBoostFactor(int level) {
      switch(level) {
      case 1:
         return 0.25F;
      case 2:
         return 0.4F;
      case 3:
         return 0.6F;
      case 4:
         return 0.85F;
      default:
         return 0F;
      }
   }

   @Inject(
           method = "readFromNBT(Lnet/minecraft/NBTTagCompound;)V",
           at = @At("RETURN")
   )
   public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      this.currentPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord);
   }

   @Inject(
           method = "updateState()V",
           at = @At("HEAD")
   )
   private void injectUpdatePos(CallbackInfo ci) {
      if (this.currentPos == null) {
         this.currentPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord);
      }
   }
}
