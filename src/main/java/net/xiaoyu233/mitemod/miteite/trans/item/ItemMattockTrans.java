package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.BlockBreakInfo;
import net.minecraft.ItemMattock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(ItemMattock.class)
public abstract class ItemMattockTrans extends ItemShovelTrans {
    @Override
    @SoftOverride
    protected int getExpForBlockBreak(BlockBreakInfo blockBreakInfo) {
        return 2;
    }
}
