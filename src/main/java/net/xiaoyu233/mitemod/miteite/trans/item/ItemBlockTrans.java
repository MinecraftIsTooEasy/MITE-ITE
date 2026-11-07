package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.Block;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.block.BlockForgingTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlock.class)
public abstract class ItemBlockTrans {
    @Shadow public abstract Block getBlock();

    @Inject(method = "getItemDisplayName", at = @At("HEAD"), cancellable = true)
    private void forgingTableDisplayName(ItemStack itemStack, CallbackInfoReturnable<String> cir) {
        if (this.getBlock() instanceof BlockForgingTable forgingTable) {
            cir.setReturnValue(forgingTable.getItemDisplayName(itemStack));
        }
    }
}
