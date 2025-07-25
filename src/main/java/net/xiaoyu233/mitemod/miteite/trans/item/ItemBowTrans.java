package net.xiaoyu233.mitemod.miteite.trans.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemBow.class)
public class ItemBowTrans extends Item {
   @Shadow private Material reinforcement_material;

   @ModifyArg(method = "<init>",at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBow;setMaxDamage(I)Lnet/minecraft/Item;"))
   private int injectInit(int origin) {
      return reinforcement_material == Materials.vibranium ? 512 : origin;
   }

   public ItemBowTrans(int id, Material reinforcement_material) {
      super(id, Material.wood, "bows/" + reinforcement_material.toString() + "/");
   }

   @WrapOperation(method = "addInformation", at = @At(value = "INVOKE", target = "Lnet/minecraft/Translator;getFormatted(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"))
   private String injectAddInfo(String key, Object[] par1ArrayOfObj, Operation<String> original) {
      if (this.reinforcement_material == Materials.vibranium) {
         return Translator.getFormatted(key, 75);
      }
      original.call(key, par1ArrayOfObj);
      return Translator.getFormatted(key, par1ArrayOfObj);
   }
}
