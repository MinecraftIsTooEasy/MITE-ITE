package net.xiaoyu233.mitemod.miteite.trans.block.tileentity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public class TileEntityFurnaceTrans extends TileEntity {

   @Inject(method = "getHeatLevelRequired", at = @At("HEAD"), cancellable = true)
   private static void getHeatLevelRequired(int item_id, CallbackInfoReturnable<Integer> cir) {
      if (item_id == Block.coalBlock.blockID) {
         cir.setReturnValue(5);
      } else if (item_id == Block.oreAdamantium.blockID || item_id == Block.blockIron.blockID || item_id == Block.blockMithril.blockID || item_id == Blocks.netherAdamantiumOre.blockID) {
         cir.setReturnValue(4);
      }
   }

}
