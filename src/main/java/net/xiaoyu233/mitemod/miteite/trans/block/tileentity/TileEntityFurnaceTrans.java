package net.xiaoyu233.mitemod.miteite.trans.block.tileentity;

import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public class TileEntityFurnaceTrans extends TileEntity {

   @Inject(method = "getHeatLevelRequired", at = @At("HEAD"), cancellable = true)
   private static void getHeatLevelRequired(int item_id, CallbackInfoReturnable<Integer> cir) {
      if (item_id == Block.coalBlock.blockID) {
         cir.setReturnValue(5);
      }
      if (item_id == MITEITEBlockRegistryInit.netherAdamantiumOre.blockID) {
         cir.setReturnValue(4);
      }
      if (!FishModLoader.hasMod("extreme")) {
         if (item_id == Block.blockIron.blockID || item_id == Block.blockMithril.blockID) {
            cir.setReturnValue(4);
         }
      }
   }
}
