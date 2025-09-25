package net.xiaoyu233.mitemod.miteite.trans.item.recipe;

import net.minecraft.Item;
import net.minecraft.RecipesIngots;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipesIngots.class)
public class RecipeIngotsTrans {
   @Final
   @Shadow
   @Mutable
   private static Object[][] block_or_crystal_dismantling_based_items;
   @Final
   @Shadow
   @Mutable
   private static Object[][] shard_3x3_based_items;

   @Redirect(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/Item;emerald:Lnet/minecraft/Item;"))
   private static Item smallerEmeraldRecipe_0() {
      return null;
   }

   @Redirect(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/Item;shardEmerald:Lnet/minecraft/Item;"))
   private static Item smallerEmeraldRecipe_1() {
      return null;
   }
}
