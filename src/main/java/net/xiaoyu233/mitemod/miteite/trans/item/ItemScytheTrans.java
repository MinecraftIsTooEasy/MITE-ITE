package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.BlockBreakInfo;
import net.minecraft.ItemScythe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(ItemScythe.class)
public abstract class ItemScytheTrans extends ItemToolTrans {
    @Override
    @SoftOverride
    protected int getExpForBlockBreak(BlockBreakInfo blockBreakInfo) {
        return 1;
    }
}
