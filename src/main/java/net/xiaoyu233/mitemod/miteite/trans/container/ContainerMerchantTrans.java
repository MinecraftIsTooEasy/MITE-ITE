package net.xiaoyu233.mitemod.miteite.trans.container;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(ContainerMerchant.class)
public abstract class ContainerMerchantTrans extends Container {

   public ContainerMerchantTrans(EntityPlayer player) {
      super(player);
   }

   @Shadow
   public abstract boolean canInteractWith(EntityPlayer var1);

   @Inject(
           method = "onContainerClosed(Lnet/minecraft/EntityPlayer;)V",
           at = @At(
                   ordinal = 0,
                   shift = At.Shift.AFTER,
                   value = "INVOKE_ASSIGN",
                   target = "Lnet/minecraft/InventoryMerchant;getStackInSlotOnClosing(I)Lnet/minecraft/ItemStack;"
           )
   )
   private void injectSyncWhenClose(EntityPlayer par1EntityPlayer, CallbackInfo callback) {
      this.updatePlayerInventory(par1EntityPlayer);
   }

   @Inject(
           method = "transferStackInSlot(Lnet/minecraft/EntityPlayer;I)Lnet/minecraft/ItemStack;",
           at = @At("TAIL")
   )
   private void injectSyncWhenTransferItems(EntityPlayer par1EntityPlayer, int par2, CallbackInfoReturnable<ItemStack> c) {
      if (!this.player.worldObj.isRemote) {
         this.updatePlayerInventory(this.player);
         ((ServerPlayer)this.player).sendContainerAndContentsToPlayer(this, this.getInventory());
      }
   }

   @Unique
   private void updatePlayerInventory(EntityPlayer player) {
      ArrayList<ItemStack> itemList = new ArrayList<>();

      for(int index = 0; index < player.openContainer.inventorySlots.size(); ++index) {
         itemList.add(((Slot)player.openContainer.inventorySlots.get(index)).getStack());
      }

      ((ServerPlayer)player).sendContainerAndContentsToPlayer(player.openContainer, itemList);
   }
}
