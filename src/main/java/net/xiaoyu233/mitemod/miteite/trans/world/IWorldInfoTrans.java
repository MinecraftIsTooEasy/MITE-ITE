package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.WorldInfo;
import net.minecraft.WorldInfoShared;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WorldInfo.class)
public interface IWorldInfoTrans {
    @Accessor
    WorldInfoShared getShared();
}
