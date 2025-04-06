package net.xiaoyu233.mitemod.miteite.trans.util;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestItemRenderHelper.class)
public class ChestItemRenderHelperTrans {

    @Unique private TileEntityChest vibraniumChest;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void ChestItemRenderHelper(CallbackInfo ci) {
        this.vibraniumChest = new TileEntityChest(EnumChestType.strongbox, MITEITEBlockRegistryInit.chestVibranium);
    }

    @Inject(method = "renderChest", at = @At("RETURN"))
    public void renderChest(Block par1Block, int par2, float par3, CallbackInfo ci) {
        if (par1Block.blockID == MITEITEBlockRegistryInit.chestVibranium.blockID) {
            TileEntityRenderer.instance.renderTileEntityAt(this.vibraniumChest, 0.0, 0.0, 0.0, 0.0F);
        }
    }

}
