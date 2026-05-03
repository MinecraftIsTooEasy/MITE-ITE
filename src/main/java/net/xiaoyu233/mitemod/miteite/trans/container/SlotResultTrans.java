package net.xiaoyu233.mitemod.miteite.trans.container;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.achievement.MITEITEAchievementRegistryInit;
import net.xiaoyu233.mitemod.miteite.api.ITERecipe;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotCrafting.class)
public class SlotResultTrans {
   @Shadow private EntityPlayer thePlayer;
   @Shadow public CraftingResult crafting_result;

   @Redirect(method = "modifyStackForRightClicks", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;setQuality(Lnet/minecraft/EnumQuality;)Lnet/minecraft/ItemStack;"))
   private ItemStack redirectRemoveSetQuality(ItemStack caller, EnumQuality quality){
      //Do nothing to remove it!
      return caller;
   }

   @Redirect(method = "canPlayerCraftItem",at = @At(value = "INVOKE",target = "Lnet/minecraft/InventoryCrafting;hasDamagedItem()Z"))
   private boolean removeDamageLimitation(InventoryCrafting caller) {
      IRecipe recipe = this.crafting_result.recipe;
      if (recipe instanceof ShapedRecipes) {
         return !((ITERecipe) recipe).isExtendsNBT() && caller.hasDamagedItem();
      } else if (!(recipe instanceof ShapelessRecipes)) {
         return caller.hasDamagedItem();
      } else {
         return !((ITERecipe)recipe).isExtendsNBT() && caller.hasDamagedItem();
      }
   }
}
