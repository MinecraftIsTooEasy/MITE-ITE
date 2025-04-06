package net.xiaoyu233.mitemod.miteite.trans.item.recipe;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import net.xiaoyu233.fml.reload.event.RecipeModifyEvent;
import net.xiaoyu233.fml.reload.event.recipe.RecipeModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.*;

@Mixin(CraftingManager.class)
public abstract class CraftingManagerHelper {

   @Shadow ShapedRecipes addRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
      return null;
   }

   //TODO Move to recipe modifier API
   @Redirect(method = "<init>",
           at = @At(value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapedRecipes;"),
           slice = @Slice(
                   from = @At(value = "FIELD", target = "Lnet/minecraft/Item;appleGold:Lnet/minecraft/Item;"),
                   to = @At(value = "FIELD", target = "Lnet/minecraft/Item;goldenCarrot:Lnet/minecraft/Item;")))
   private ShapedRecipes injectModifyGoldenAppleRecipe(CraftingManager caller, ItemStack par1ItemStack, Object... par2ArrayOfObj) {
      return this.addRecipe(new ItemStack(Item.appleGold, 1, 0),
              "###",
                              "IXI",
                              "###",
              '#', Item.goldNugget,
              'I', Item.ingotGold,
              'X', Item.appleRed);
   }
}
