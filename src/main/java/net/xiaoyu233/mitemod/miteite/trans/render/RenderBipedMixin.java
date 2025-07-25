package net.xiaoyu233.mitemod.miteite.trans.render;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ItemArmor;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(RenderBiped.class)
public class RenderBipedMixin {
    @Shadow @Final private static Map field_110859_k;

    @ModifyReturnValue(method = "func_110858_a", at = @At("TAIL"))
    private static ResourceLocation modifyResourcePath(ResourceLocation original, @Local(argsOnly = true) ItemArmor par0ItemArmor, @Local(argsOnly = true) int par1, @Local(argsOnly = true) String par2Str) {
        if (par0ItemArmor.getArmorMaterial() == Materials.vibranium) {
            String var3 = String.format("miteite:textures/models/armor/%s_layer_%d%s.png", par0ItemArmor.getTextureFilenamePrefix(), par1 == 2 ? 2 : 1, par2Str == null ? "" : String.format("_%s", par2Str));
            ResourceLocation var4 = (ResourceLocation) field_110859_k.get(var3);
            if (var4 == null) {
                var4 = new ResourceLocation(var3);
                field_110859_k.put(var3, var4);
            }
            return var4;
        }
        return original;
    }
}
