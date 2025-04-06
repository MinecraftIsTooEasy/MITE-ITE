package net.xiaoyu233.mitemod.miteite.trans.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.EnumQuality;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnumQuality.class)
public class EnumQualityTrans {
    @ModifyReturnValue(method = "getDurabilityModifier", at = @At("TAIL"))
    public float getDurabilityModifier(float original) {
        //To remove quality modifier
        return 1.0F;
    }
}
